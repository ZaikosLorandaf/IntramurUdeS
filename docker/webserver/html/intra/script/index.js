initialisation();

//~~~~~~~~~~~~ Functions ~~~~~~~~~//
function initialisation() {
    const div = document.getElementById("btn-login");
    axios.get(`${backendAddress}/api/sport`, { })
        .then(function (response) {
            console.log(response.data);
        })
        .catch(function (error) {
            console.log(error);
        });
    getSportsData();
}

function getSportsData() {
    axios.get(`${backendAddress}/api/sport/get_sport_ligue`, { })
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
