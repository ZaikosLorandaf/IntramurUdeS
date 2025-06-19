let roleNumber;

const keycloak = new Keycloak({
    realm: "usager",
    "auth-server-url": "http://localhost:8180/",
    "ssl-required": "external",
    clientId: "frontend",
    "public-client": true,
    "confidential-port": 0
});

async function manageAuth() {
    const authenticated = await keycloak.init({ onLoad: 'check-sso' });
    if (!authenticated) {
        keycloak.login();
    } else {
        logout();
    }
    return authenticated;
}

function logout() {
    const redirectUri = "https://localhost/intra/";
    const logoutUrl = keycloak.createLogoutUrl({ redirectUri });
    keycloak.clearToken();
    window.location.href = logoutUrl;
}

async function authAndButton() {
    const authenticated = await manageAuth();
    updateButton();
    console.log(authenticated)
    roleNumber = (authenticated ? 2 : 0);
}

function updateButton() {
    const btn = document.getElementById('btn-login');
    if (btn) {
        btn.textContent = keycloak.authenticated ? 'Déconnexion' : 'Connexion';
    }
}

async function silentButton() {
    const authenticated = await keycloak.init({ onLoad: 'check-sso' });
    if (authenticated) {
        updateButton();
    }
    return authenticated;
}

// Pour récupérer le token (utile si besoin)
function getToken() {
    return keycloak.token;
}


window.addEventListener('DOMContentLoaded', async () => {
    const auth = await silentButton();
    roleNumber = (auth ? 2 : 0);
    initialisation();
});

