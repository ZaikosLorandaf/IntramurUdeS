function getSportsData() {
    axios.get("http://localhost:8888/api/get_sport_ligue", { })
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
        const sportBtn = document.createElement('button');
        sportBtn.className = 'sport-btn';
        sportBtn.setAttribute('data-bs-toggle', 'collapse');
        sportBtn.setAttribute('data-bs-target', `#${sport.id}-seasons`);
        sportBtn.setAttribute('aria-expanded', 'false');
        sportBtn.textContent = sport.name;
        container.appendChild(sportBtn);

        const collapseDiv = document.createElement('div');
        collapseDiv.className = 'collapse';
        collapseDiv.id = `${sport.id}-seasons`;

        sport.seasons.forEach(season => {
            const seasonBtn = document.createElement('button');
            seasonBtn.className = 'btn season-btn';
            seasonBtn.textContent = season;

            // 🔗 Ajout du comportement de redirection
            seasonBtn.addEventListener('click', () => {
                const url = `dashboard.html?ligue=${encodeURIComponent(season)}&sport=${encodeURIComponent(sport.name)}`;
                window.location.href = url;
            });

            collapseDiv.appendChild(seasonBtn);
        });

        container.appendChild(collapseDiv);
    });
}

// Appeler cette fonction une fois que Keycloak est initialisé
document.addEventListener('DOMContentLoaded', () => {
    getSportsData();
});
