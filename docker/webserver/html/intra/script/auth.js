let roleNumber;

const keycloak = new Keycloak({
    realm: "usager",
    "auth-server-url": keycloakAddress,
    "ssl-required": "external",
    clientId: "frontend",
    "public-client": true,
    "confidential-port": 0
});

async function manageAuth() {
    // const auth = await keycloak.init({ onLoad: 'check-sso' });
    const auth = keycloak.authenticated;
    if (!auth) {
        keycloak.login();
    } else {
        logout();
    }
    return auth;
}

function logout() {
    const redirectUri = "/intra";
    const logoutUrl = keycloak.createLogoutUrl({ redirectUri });
    keycloak.clearToken();
    keycloak.logout(logoutUrl);
    window.location.href = logoutUrl;
}

async function authAndButton() {
    const authenticated = await manageAuth();
    updateButton();
    console.log(authenticated)
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

function getToken() {
    return keycloak.token;
}


window.addEventListener('DOMContentLoaded', async () => {
    const auth = await silentButton();
    roleNumber = (auth ? 2 : 0);
    try {
        initialisation()
    } catch (error) {};
});

