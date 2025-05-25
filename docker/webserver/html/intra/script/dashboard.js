// Permission pour l'affichage
const roleNumber = 2; // 0 user, 1, chef, 2 admin

const equipeData = {
    A: { joueurs: "Remi, Axel, Ana", matchs: "3 gagnés, 1 perdu" },
    B: { joueurs: "Bruno, Béatrice, Basile", matchs: "2 gagnés, 2 perdus" },
    C: { joueurs: "Carla, Charles, Chloé", matchs: "1 gagné, 3 perdus" },
    D: { joueurs: "David, Daphnée, Damien", matchs: "4 gagnés, 0 perdu" },
};

const matchData = {
    "2025-05-21": [
        { heure: "10:00", equipes: "Équipe A vs Équipe B", lieu: "Gymnase 1" },
        { heure: "14:30", equipes: "Équipe C vs Équipe D", lieu: "Gymnase 2" }
    ],
    "2025-05-25": [
        { heure: "09:00", equipes: "Équipe A vs Équipe D", lieu: "Gymnase 1" },
        { heure: "11:00", equipes: "Équipe B vs Équipe C", lieu: "Gymnase 3" },
        { heure: "16:00", equipes: "Équipe A vs Équipe C", lieu: "Extérieur" }
    ],
    "2025-05-28": [
        { heure: "13:00", equipes: "Équipe D vs Équipe B", lieu: "Gymnase 2" }
    ]
};

window.onload = function() {
    const params = new URLSearchParams(window.location.search);
    const sport = params.get('sport');
    const season = params.get('ligue');

    console.log(sport);
    console.log(season);

    const monBoutonRetourLigue = document.getElementById('btn-retour-ligue');
    monBoutonRetourLigue.textContent = "← " + season + " ( " + sport + " )";

    renderEquipeList(); // <-- Ajout ici
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
    const div = document.createElement("div");
    div.className = "card-custom";
    div.textContent = `Modifier`;
    // div.onclick = () => ;
    container.appendChild(div);
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

    document.getElementById("equipe-info").innerHTML = `
        <h4>Équipe ${team}</h4>
        <div class="equipe-container" id="equipe-content">
            <div class="joueurs-col">${joueursList}</div>
            <div class="stats-col">
                <p><strong>Matchs :</strong><br>${info.matchs}</p>
                <button className="player-btn" onClick="()">Modifier Stats</button>
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
        document.getElementById("player-info").innerHTML = `
            <h4>${nom}</h4>
            <p><strong>Statistiques :</strong><br>${stat}</p>
            <button className="player-btn" onClick="()">Modifier Joueur</button>
            <button onclick="retourEquipe('${team}')">← Retour à l'équipe</button>
        `;
    };

    // Fonction de retour
    window.retourEquipe = function (team) {
        showInfo(team); // Recharge l'affichage original de l'équipe
    };
}

function showMatchDay(date) {
    const container = document.getElementById("match-details");
    const title = document.getElementById("selected-day");
    const matchs = matchData[date] || [];

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
