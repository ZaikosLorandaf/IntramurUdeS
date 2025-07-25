let sport;
let season;

// Page Initialisation
document.addEventListener("DOMContentLoaded", function () {
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
            callback();
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
        const rawData = response.data;
        console.log(rawData);
        equipeData = {};
        matchDatas = {};

        rawData.forEach((team, index) => {
            equipeData[String.fromCharCode(65 + index)] = {
                name: team.name,
                joueurs: team.players.reduce((acc, player) => {
                    acc[player.number] = {
                        name: player.name,
                        stats: (player.stats || []).reduce((obj, stat) => {
                            obj[stat.statement] = stat.value;
                            return obj;
                        }, {})
                    };
                    return acc;
                }, {}),
                stats: (team.stats || []).reduce((obj, stat) => {
                    obj[stat.statement] = stat.value;
                    return obj;
                }, {}),
                matchs: `${(team.matches || []).length} matchs`
            };

            (team.matches || []).forEach(match => {
                const date = match.date;
                if (!matchDatas[date]) {
                    matchDatas[date] = [];
                }

                // Récupérer les noms des deux équipes du match
                const equipes = (match.teams || []).map(t => t.name).join(" vs ");
                let affrontement = equipes + " vs " + team.name;

                const heure = formatHeure(match.beginTime);
                const lieu = match.place || "Inconnu";

                // Vérifie s’il existe déjà un match identique
                const exists = matchDatas[date].some(entry =>
                    entry.heure === heure &&
                    entry.lieu === lieu // &&
                    // entry.equipes === affrontement
                );

                if (!exists) {
                    matchDatas[date].push({
                        heure,
                        equipes: affrontement,
                        lieu
                    });
                }
            });
        });

    console.log("EquipeData reformatée:", equipeData);
        console.log("matchDatas:", matchDatas);
        renderTeamList();
        populateMatchDays();
    }).catch(function (error) {
        const container = document.querySelector('.main-container');
        container.innerHTML = '<div class="alert alert-danger">Erreur : ' + error + '</div>';
    });
}

let equipeData = {};
let matchDatas;

function renderTeamList() {
    const container = document.getElementById("liste-equipes");
    container.innerHTML = "";

    Object.keys(equipeData).forEach(team => {
        const div = document.createElement("div");
        div.className = "card-custom";
        div.textContent = `${equipeData[team].name}`;
        div.onclick = () => showInfo(equipeData, equipeData[team], team);
        container.appendChild(div);
    });

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
        container.appendChild(div);
    }
}

function showInfo(myData,teame,abcd) {
    console.log(abcd);
    const joueursList = Object.entries(teame.joueurs)
        .map(([numero, joueur]) => `<button class="player-btn" onclick="showPlayer('${numero}','${joueur.name}','${abcd}')">${numero} ${joueur.name}</button>`)
        .join('');

    let buttonHTML = "";
    if (roleNumber === 2) {
        const myParams = new URLSearchParams(window.location.search);
        let sports = myParams.get('sport');
        let seasons = myParams.get('ligue');
        console.log(teame);
        buttonHTML = `<button class="btn btn-secondary mb-2" onclick="window.open('./modals/dashboard-equipe-stats.html?sport=${sports}&ligue=${seasons}&team=${teame.name}', 'popupWindow', 'width=500,height=400')">Gérer statistique</button>`;
    }

    const teamStats = teame.stats || {};
    const statsTable = `
        <table class="stats-table">
        <thead><tr><th>Statistiques</th><th>Valeurs</th></tr></thead>
        <tbody>
        ${Object.entries(teamStats).map(([cle, valeur]) => `
            <tr>
            <td style="padding: 0px 20px;">${cle}</td>
            <td style="padding: 0px 20px;">${valeur}</td>
            </tr>`).join('')}
        </tbody>
        </table>`;

    document.getElementById("equipe-info").innerHTML = `
        <h4>Équipe ${teame.name}</h4>
        <div class="equipe-container" id="equipe-content">
        <div class="joueurs-col">${joueursList}</div>
        <div class="stats-col">
        ${statsTable}
        ${buttonHTML}
        </div></div>
        <div id="player-info" style="display: none;"></div>`;

    window.showPlayer = function (number, nom, abcd) {
        const joueurData = equipeData[abcd].joueurs[number].stats || {};
        console.log(joueurData);
        const statsJoueurTable = `
            <table class="stats-table">
            <thead><tr><th>Statistiques</th><th>Valeurs</th></tr></thead>
            <tbody>
            ${Object.entries(joueurData).map(([cle, valeur]) => `
                <tr>
                <td style="padding: 0px 20px;">${cle}</td>
                <td style="padding: 0px 20px;">${valeur}</td>
                </tr>`).join('')}
            </tbody>
            </table>`;

        let modifierJoueurBtn = "";
        if (roleNumber === 2) {
            const params = new URLSearchParams(window.location.search);
            let sport = params.get('sport');
            let ligue = params.get('ligue');

            // modifierJoueurBtn = `<button class="btn btn-secondary mb-2" onclick="window.open('./modals/dashboard-joueur.html?sport=${sport}&ligue=${ligue}&team=${equipeData[abcd].name}&nom=${nom}', 'popupWindow', 'width=500,height=400')">Gérer stats joueur</button>`;
        }

        document.getElementById("equipe-content").style.display = "none";
        document.getElementById("player-info").style.display = "block";

        document.getElementById("player-info").innerHTML = `
            <h4>${number} ${nom}</h4>
            ${statsJoueurTable} 
            ${modifierJoueurBtn}
            <button class="btn btn-success mb-4" style="margin-top: 17px;" onclick="retourEquipe('${abcd}')">← Retour à l'équipe</button>`;
        console.log(teame);
    };

    window.retourEquipe = function (abcd) {
        showInfo(equipeData, equipeData[abcd], abcd);
    };
}

function parseDate(dateStr) {
    if (/^\d{4}-\d{2}-\d{2}Z$/.test(dateStr)) {
        return new Date(dateStr.replace('Z', 'T00:00:00Z'));
    }
    return new Date(dateStr);
}

function formatHeure(isoTime) {
    // Supprime le [UTC] si présent
    const cleaned = isoTime.replace(/\[.*\]/, "");
    const d = new Date(cleaned);
    return d.toLocaleTimeString("fr-FR", { hour: "2-digit", minute: "2-digit" });
}

function showMatchDay(date) {
    const container = document.getElementById("match-details");
    const title = document.getElementById("selected-day");
    console.log("cliked");
    const matchs = matchDatas[date] || [];
    title.textContent = `Matchs du ${parseDate(date).toLocaleDateString("fr-FR", { day: "numeric", month: "long", year: "numeric" })}`;

    container.innerHTML = matchs.map(match => `
        <div class="card border-0 shadow-sm">
        <div class="card-body">
        <h6 class="card-title">${match.equipes}</h6>
        <h7>${match.heure}</h7>
        <p class="card-text text-muted mb-0">Lieu : ${match.lieu}</p>      
        </div>
        </div>`).join("");
}
//

function formatDate(isoDate) {
    return parseDate(isoDate).toLocaleDateString('fr-FR', { day: 'numeric', month: 'long', year: 'numeric' });
}

function populateMatchDays() {
    const listContainer = document.getElementById('match-days-list');
    listContainer.innerHTML = '';

    // Trier les dates (clés) sans modifier matchDatas
    const sortedDates = Object.keys(matchDatas).sort((a, b) => new Date(a) - new Date(b));

    sortedDates.forEach(date => {
        const li = document.createElement('li');
        li.className = 'list-group-item list-group-item-action';
        li.textContent = formatDate(date); // formatDate("2025-06-01Z") => ex: "1 juin 2025"
        li.onclick = () => showMatchDay(date); // utilise toujours la bonne date pour accéder aux données
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