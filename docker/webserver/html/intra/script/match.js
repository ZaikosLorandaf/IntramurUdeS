const matchDatas = {
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
}

// FAIRE L APPEL DANS DASHBOARD.JS
document.addEventListener('DOMContentLoaded', populateMatchDays);
