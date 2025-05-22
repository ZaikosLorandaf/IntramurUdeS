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

function showInfo(team) {
    const data = {
        A: { joueurs: "Alice, Axel, Ana", matchs: "3 gagnés, 1 perdu" },
        B: { joueurs: "Bruno, Béatrice, Basile", matchs: "2 gagnés, 2 perdus" },
        C: { joueurs: "Carla, Charles, Chloé", matchs: "1 gagné, 3 perdus" },
        D: { joueurs: "David, Daphnée, Damien", matchs: "4 gagnés, 0 perdu" },
    };
    const info = data[team];
    document.getElementById("equipe-info").innerHTML = `
          <h4>Équipe ${team}</h4>
          <p><strong>Joueurs :</strong> ${info.joueurs}</p>
          <p><strong>Matchs :</strong> ${info.matchs}</p>
        `;
    getDataEquipe();
}

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
