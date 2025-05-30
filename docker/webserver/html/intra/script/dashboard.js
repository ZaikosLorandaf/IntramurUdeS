let roleNumber = 0; // 0 user, 1, chef, 2 admin

let sport;
let season;

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

        const params = new URLSearchParams(window.location.search);
        let sport = params.get('sport');
        let season = params.get('ligue');

        console.log(sport);
        console.log(season);

        const monBoutonRetourLigue = document.getElementById('btn-retour-ligue');
        monBoutonRetourLigue.textContent = "← " + season + " ( " + sport + " )";

        axios.get("http://localhost:8888/api/dashboard/equipes", {
            params: {
                sport: sport,
                ligue: season
            }
        }).then(function (response) {
                equipeData = response.data;
                console.log(equipeData);
                renderEquipeList();
            })
            .catch(function (error) {
                const container = document.querySelector('.main-container');
                container.innerHTML = '<div class="alert alert-danger">Erreur : ' + error + '</div>';
            });
    })
    .catch(() => {
        console.error("Erreur lors de l'initialisation de Keycloak");
    });


function logout(){
    keycloak.logout();
    // window.location.href = 'index-league.html';
}

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

let equipeData = {
    A: { joueurs: "Remi, Axel, Ana", matchs: "3 gagnés, 1 perdu",stats: {
            matchsJoues: 4,
            victoires: 3,
            defaites: 1,
            pointsMarques: 89,
            pointsEncaisses: 65,
            differenceDePoints: 24
        } },
    B: { joueurs: "Bruno, Béatrice, Basile", matchs: "2 gagnés, 2 perdus",stats: {
            matchsJoues: 4,
            victoires: 3,
            defaites: 1,
            pointsMarques: 89,
            pointsEncaisses: 65,
            differenceDePoints: 24
        } },
    C: { joueurs: "Carla, Charles, Chloé", matchs: "1 gagné, 3 perdus",stats: {
            matchsJoues: 4,
            victoires: 3,
            defaites: 1,
            pointsMarques: 89,
            pointsEncaisses: 65,
            differenceDePoints: 24
        } },
    D: { joueurs: "David, Daphnée, Damien", matchs: "4 gagnés, 0 perdu",stats: {
            matchsJoues: 4,
            victoires: 3,
            defaites: 1,
            pointsMarques: 89,
            pointsEncaisses: 65,
            differenceDePoints: 24
        } },
};

function getDataEquipe() {
    const div = document.getElementById('equipe-info');
    const span = div.firstElementChild;

    axios.get("http://localhost:8888/api/data_equipe", {
    })
        .then(function (response) {
            console.log(response);
            span.innerHTML += '<div>' + response.data + '</div>';
        })
        .catch(function (error) {
            span.innerHTML = '<div>Erreur : ' + error + '</div>';
        });
}

function renderEquipeList() {
    const container = document.getElementById("liste-equipes");
    container.innerHTML = "";

    Object.keys(equipeData).forEach(team => {
        const div = document.createElement("div");
        div.className = "card-custom";
        div.textContent = `Équipe ${team}`;
        div.onclick = () => showInfo(team);
        container.appendChild(div);
    });

    if (roleNumber === 2) {
        const div = document.createElement("div");
        div.className = "card-custom";
        div.textContent = `Gérer équipe`;
        div.addEventListener('click', () => {
            window.open('./modals/dashboard-equipe.html', 'popupWindow', 'width=500,height=500');
        });
        // div.onclick = () => ;
        container.appendChild(div);
    }
}

function showInfo(team) {
    const joueursStats = {
        Remi: "1 but, 3 passes",
        Axel: "2 buts, 1 carton jaune",
        Ana: "Gardienne - 2 arrêts",
        Bruno: "1 but",
        Béatrice: "Capitaine, 2 passes décisives",
        Basile: "1 blessure, 1 but",
        Carla: "1 but",
        Charles: "1 carton rouge",
        Chloé: "2 passes",
        David: "Meilleur joueur - 3 buts",
        Daphnée: "1 but, 1 passe",
        Damien: "2 buts, 1 carton jaune",
    };

    const info = equipeData[team];

    const joueursList = info.joueurs
        .split(',')
        .map(nom => nom.trim())
        .map(nom => `<button class="player-btn" onclick="showPlayer('${nom}', '${team}')">${nom}</button>`)
        .join('');

    let buttonHTML = "";
    if (roleNumber === 2) {
        buttonHTML = `<button class="player-btn" onclick="window.open('./modals/dashboard-equipe-stats.html?equipe=${team}', 'popupWindow', 'width=500,height=400')">Modifier Stats</button>`;
    }

    const teamStats = info.stats || {};

    const statsTable = `
    <table class="stats-table">
        <thead>
            <tr><th>Statistiques</th><th>Valeurs</th></tr>
        </thead>
        <tbody>
            ${Object.entries(teamStats).map(([cle, valeur]) => `
                <tr>
                    <td style="padding: 0px 20px;">${cle.replace(/([A-Z])/g, ' $1').toLowerCase().replace(/^./, c => c.toUpperCase())}</td>
                    <td style="padding: 0px 20px;">${valeur}</td>
                </tr>
            `).join('')}
        </tbody>
    </table>
`;

    document.getElementById("equipe-info").innerHTML = `
    <h4>Équipe ${team}</h4>
    <div class="equipe-container" id="equipe-content">
        <div class="joueurs-col">${joueursList}</div>
        <div class="stats-col">
            ${statsTable}
            ${buttonHTML}
        </div>
    </div>
    <div id="player-info" style="display: none;"></div>
`;

    getDataEquipe();

    // Fonction pour afficher les stats du joueur
    window.showPlayer = function (nom, team) {
        const stat = joueursStats[nom] || "Aucune statistique disponible.";

        document.getElementById("equipe-content").style.display = "none";
        document.getElementById("player-info").style.display = "block";
        let modifierJoueurBtn = "";
        if (roleNumber === 2) {
            const params = new URLSearchParams(window.location.search);
            let sport = params.get('sport');
            let ligue = params.get('ligue');

            modifierJoueurBtn = `<button class="player-btn" onclick="window.open('./modals/dashboard-joueur.html?sport=${sport}&ligue=${ligue}&team=${team}&nom=${nom}', 'popupWindow', 'width=500,height=400')">Modifier Joueur</button>`;
        }



        document.getElementById("player-info").innerHTML = `
            <h4>${nom}</h4>
            <p><strong>Statistiques :</strong><br>${stat}</p>
            ${modifierJoueurBtn}
            <button onclick="retourEquipe('${team}')">← Retour à l'équipe</button>
        `;
    };

    // Fonction de retour
    window.retourEquipe = function (team) {
        showInfo(team); // Recharge l'affichage original de l'équipe
    };
}
