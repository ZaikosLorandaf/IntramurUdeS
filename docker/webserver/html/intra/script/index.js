let roleNumber = 0; // 0 user, 1, chef, 2 admin

// ********************************
// ********** KEYCLOAK ************
// ********************************
// On crée et initialise Keycloak ici
const keycloak = new Keycloak({
    realm: "usager",
    "auth-server-url": "http://localhost:8180/",
    "ssl-required": "external",
    clientId: "frontend",
    "public-client": true,
    "confidential-port": 0
});

function getRoleNumber() {
    if (!keycloak.tokenParsed || !keycloak.tokenParsed.realm_access) return 0;

    const roles = keycloak.tokenParsed.realm_access.roles;
    if (roles.includes("student")) return 2;
    if (roles.includes("teacher")) return 1;
    return 0;
}


function initKeycloak() {
    // Init avec check-sso, ça ne force pas la connexion mais vérifie la session
    return keycloak.init({
        onLoad: 'check-sso',
        silentCheckSsoRedirectUri: window.location.origin + "/silent-check-sso.html"
    });
}

function logout(){
    keycloak.logout();
    // window.location.href = 'index-league.html';
}

initKeycloak()
    .then(() => {
        const div = document.getElementById("btn-login");
        if (isAuthenticated()) {
            console.log("Utilisateur connecté !");
            roleNumber = getRoleNumber();
            console.log("Rôle détecté:", roleNumber);
            div.textContent = "Déconnexion";
            div.onclick = () => logout();
        } else {
            console.log("Utilisateur non connecté.");
            div.textContent = "Connexion";
            div.onclick = () => keycloak.login();
        }

        getSportsData();
    })
    .catch(() => {
        console.error("Erreur lors de l'initialisation de Keycloak");
    });

// Pour savoir si connecté
function isAuthenticated() {
    return keycloak.authenticated;
}

// Pour récupérer le token (utile si besoin)
function getToken() {
    return keycloak.token;
}


// ********************************
// ******* FIN KEYCLOAK ***********
// ********************************


function getSportsData() {
    axios.get("http://localhost:8888/api/index/get_sport_ligue", { })
        .then(function (response) {
            const sports = response.data;
            console.log(sports);
            renderSports(sports);
        })
        .catch(function (error) {
            const container = document.querySelector('.main-container');
            container.innerHTML = '<div class="alert alert-danger">Erreur : ' + error + '</div>';
        });
}

function renderSports(sports) {
    const container = document.querySelector('.main-container');
    container.innerHTML = "";

    const title = document.createElement('h3');
    title.className = 'text-center mb-4';
    title.textContent = 'Choisissez un sport';
    container.appendChild(title);

    sports.forEach(sport => {
        // 🔘 Bouton du sport
        const sportBtn = document.createElement('button');
        sportBtn.className = 'sport-btn btn btn-outline-primary mb-2 me-2';
        sportBtn.setAttribute('data-bs-toggle', 'collapse');
        sportBtn.setAttribute('data-bs-target', `#${sport.id}-seasons`);
        sportBtn.setAttribute('aria-expanded', 'false');
        sportBtn.textContent = sport.name;
        container.appendChild(sportBtn);

        // 🔽 Contenu des saisons
        const collapseDiv = document.createElement('div');
        collapseDiv.className = 'collapse mt-2 mb-4';
        collapseDiv.id = `${sport.id}-seasons`;

        // ➕ Ajouter ligue
        if (roleNumber === 2){
            const addLeagueBtn = document.createElement('button');
            addLeagueBtn.className = 'btn btn-secondary mb-2';
            addLeagueBtn.textContent = 'Gérer les ligues';
            addLeagueBtn.addEventListener('click', () => {
                window.open(`./modals/index-league.html?sport=${sport.name}`, 'popupWindow', 'width=500,height=400');
            });

            collapseDiv.appendChild(addLeagueBtn);
        }
        // 🔁 Saisons
        sport.seasons.forEach(season => {
            const seasonBtn = document.createElement('button');
            seasonBtn.className = 'btn btn-outline-success season-btn me-2 mb-2';
            seasonBtn.textContent = season;

            seasonBtn.addEventListener('click', () => {
                const url = `dashboard.html?ligue=${encodeURIComponent(season)}&sport=${encodeURIComponent(sport.name)}`;
                window.location.href = url;
            });

            collapseDiv.appendChild(seasonBtn);
        });

        container.appendChild(collapseDiv);
    });

    // 🔘 Ajouter sport
    if (roleNumber === 2){
        const addSportBtn = document.createElement('button');
        addSportBtn.className = 'btn btn-secondary mb-2';
        addSportBtn.textContent = 'Gérer les sports';
        addSportBtn.addEventListener('click', () => {
            window.open(`./modals/index-sport.html`, 'popupWindow', 'width=500,height=400');
        });
        container.appendChild(addSportBtn);
    }
}
