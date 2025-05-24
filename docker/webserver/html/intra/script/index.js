export const roleNumber = 2; // 0 user, 1, chef, 2 admin

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
            addLeagueBtn.textContent = '➕ Modifier les ligues';
            addLeagueBtn.addEventListener('click', () => {
                alert(`Ajouter une ligue pour ${sport.name}`);
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
        addSportBtn.textContent = '➕ Modifier les sports';
        addSportBtn.addEventListener('click', () => {
            alert('Fonction pour ajouter un sport');
        });
        container.appendChild(addSportBtn);
    }
}


// Appeler cette fonction une fois que Keycloak est initialisé
document.addEventListener('DOMContentLoaded', () => {
    getSportsData();
});
