let sport;
let season;
// let roleNumber = 2;
// Page Initialisation
document.addEventListener("DOMContentLoaded", function() {
    waitForRoleNumber(initPage);
});


function waitForRoleNumber(callback, retries = 20, interval = 100) {
    const check = () => {
        if (typeof roleNumber !== "undefined") {
            callback();
        } else if (retries > 0) {
            setTimeout(() => waitForRoleNumber(callback, retries - 1, interval), interval);
        } else {
            console.warn("roleNumber not defined after waiting");
            callback(); // on continue quand même pour éviter de bloquer toute la page
        }
    };
    check();
}

function initPage() {
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
        renderTeamList();
        callMatch();
    }).catch(function (error) {
        const container = document.querySelector('.main-container');
        container.innerHTML = '<div class="alert alert-danger">Erreur : ' + error + '</div>';
    });
}

// Logique Equipe
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

function renderTeamList() {
    const container = document.getElementById("liste-equipes");
    container.innerHTML = "";

    Object.keys(equipeData).forEach(team => {
        const div = document.createElement("div");
        div.className = "card-custom";
        div.textContent = `Équipe ${team}`;
        div.onclick = () => showInfo(team);
        container.appendChild(div);
    });
    console.log("affichage roleNumber", roleNumber);
    if (roleNumber === 2) {
        const div = document.createElement("div");
        div.className = "btn btn-secondary mb-2";
        div.textContent = `Gérer équipe/joueur`;

        const myParams = new URLSearchParams(window.location.search);
        let sports = myParams.get('sport');
        let seasons = myParams.get('ligue');

        div.addEventListener('click', () => {
            window.open(`./modals/dashboard-equipe.html?sport=${sports}&ligue=${seasons}`, 'popupWindow', 'width=500,height=500');
        });
        // div.onclick = () => ;
        container.appendChild(div);
    }
}

function showInfo(team) {
    const info = equipeData[team];

    const joueursList = Object.entries(info.joueurs)
        .map(([numero,joueur]) => `<button class="player-btn" onclick="showPlayer('${numero}','${joueur.name}', '${team}')">${numero} ${joueur.name}</button>`)
        .join('');

    let buttonHTML = "";
    if (roleNumber === 2) {
        const myParams = new URLSearchParams(window.location.search);
        let sports = myParams.get('sport');
        let seasons = myParams.get('ligue');

        buttonHTML = `<button class="btn btn-secondary mb-2" onclick="window.open('./modals/dashboard-equipe-stats.html?sport=${sports}&ligue=${seasons}&team=${team}', 'popupWindow', 'width=500,height=400')">Gérer statistique</button>`;
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

    // Fonction pour afficher les stats du joueur
    window.showPlayer = function (number ,nom, team) {
        const joueurData = equipeData[team].joueurs[number].stats;
        const stats = joueurData ? joueurData : {};

        // Générer un tableau des stats du joueur
        const statsJoueurTable = `
            <table class="stats-table">
            <thead>
            <tr><th>Statistiques</th><th>Valeurs</th></tr>
            </thead>
            <tbody>
            ${Object.entries(stats).map(([cle, valeur]) => `
                <tr>
                <td style="padding: 0px 20px;">${cle.replace(/([A-Z])/g, ' $1').toLowerCase().replace(/^./, c => c.toUpperCase())}</td>
                <td style="padding: 0px 20px;">${valeur}</td>
                </tr>
                `).join('')}
            </tbody>
            </table>
            `;

        // Affichage conditionnel pour rôle admin
        let modifierJoueurBtn = "";
        if (roleNumber === 2) {
            const params = new URLSearchParams(window.location.search);
            let sport = params.get('sport');
            let ligue = params.get('ligue');

            modifierJoueurBtn = `<button class="btn btn-secondary mb-2" onclick="window.open('./modals/dashboard-joueur.html?sport=${sport}&ligue=${ligue}&team=${team}&nom=${nom}', 'popupWindow', 'width=500,height=400')">Gérer stats joueur</button>`;
        }

        document.getElementById("equipe-content").style.display = "none";
        document.getElementById("player-info").style.display = "block";

        // Mise à jour du DOM
        document.getElementById("player-info").innerHTML = `
            <h4>${number} ${nom}</h4>
            ${statsJoueurTable}
        ${modifierJoueurBtn}
            <button class="btn btn-success mb-4" style="margin-top: 17px;" onclick="retourEquipe('${team}')">← Retour à l'équipe</button>
            `;
    };

    // Fonction de retour
    window.retourEquipe = function (team) {
        showInfo(team); // Recharge l'affichage original de l'équipe
    };
}


// Logique Matchs
let matchDatas;

function showMatchDay(date) {
    const container = document.getElementById("match-details");
    const title = document.getElementById("selected-day");
    console.log("cliked");
    const matchs = matchDatas[date] || [];

    title.textContent = `Matchs du ${new Date(date).toLocaleDateString("fr-FR", { day: "numeric", month: "long", year: "numeric" })}`;

    container.innerHTML = matchs.map(match => `
        <div class="card border-0 shadow-sm">
        <div class="card-body">
        <h6 class="card-title">${match.heure} - ${match.equipes}</h6>
        <p class="card-text text-muted mb-0">Lieu : ${match.lieu}</p>
        </div>
        </div>
        `).join("");
}

function formatDate(isoDate) {
    const date = new Date(isoDate);
    return date.toLocaleDateString('fr-FR', { day: 'numeric', month: 'long', year: 'numeric' });
}

function populateMatchDays() {
    const listContainer = document.getElementById('match-days-list');
    listContainer.innerHTML = '';

    Object.keys(matchDatas).forEach(date => {
        const li = document.createElement('li');
        li.className = 'list-group-item list-group-item-action';
        li.textContent = formatDate(date);
        li.onclick = () => showMatchDay(date);
        listContainer.appendChild(li);
    });

    if (roleNumber === 2) {
        const myParams = new URLSearchParams(window.location.search);
        let sport = myParams.get('sport');
        let season = myParams.get('ligue');

        const li = document.createElement('li');
        li.className = 'list-group-item list-group-item-action bg-secondary text-white text-center';
        li.textContent = 'Gérer Matchs';
        li.style.cursor = 'pointer';
        li.onclick = () => {
            window.open(`./modals/dashboard-date.html?sport=${sport}&league=${season}`, 'popupWindow', 'width=600,height=400');
        };
        listContainer.appendChild(li);
    }
}


function callMatch(){
    const myParams = new URLSearchParams(window.location.search);
    let sports = myParams.get('sport');
    let seasons = myParams.get('ligue');
    axios.get("http://localhost:8888/api/dashboard/matchs/"+sports+'/'+seasons, {
    }).then(function (response) {
        matchDatas = response.data;
        console.log(matchDatas);
        populateMatchDays();
    })
        .catch(function (error) {
            console.log(error);
        });
}

