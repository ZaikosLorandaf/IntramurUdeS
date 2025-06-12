// Initialize Keycloak
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
      console.log('Already authenticated');
      console.log('Token:', keycloak.token);
      // Continue app logic here if already authenticated
    }
    return authenticated;
}

async function authAndButton() {
  const authenticated = await manageAuth();
  if (authenticated) {
    updateButton();
  }
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
}

// Pour récupérer le token (utile si besoin)
function getToken() {
    return keycloak.token;
}


window.addEventListener('DOMContentLoaded', () => {
  silentButton();
});

