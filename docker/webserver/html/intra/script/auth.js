// Initialize Keycloak
const keycloak = new Keycloak({
    realm: "usager",
    "auth-server-url": "http://localhost:8180/",
    "ssl-required": "external",
    clientId: "frontend",
    "public-client": true,
    "confidential-port": 0
});

async function checkAuth() {
  const authenticated = await keycloak.init({ onLoad: 'check-sso' });
    if (!authenticated) {
      keycloak.login();
    } else {
      console.log('Already authenticated');
      console.log('Token:', keycloak.token);
      // Continue app logic here if already authenticated
    }
}

function updateButton() {
    const btn = document.getElementById('btn-login');
    btn.textContent = keycloak.authenticated  ? 'Déconnexion' : 'Connexion';
}

async function authAndButton() {
    await checkAuth();
    updateButton();
}

// Pour récupérer le token (utile si besoin)
function getToken() {
    return keycloak.token;
}

//
// // Init avec check-sso, ça ne force pas la connexion mais vérifie la session
// function initKeycloak() {
//     return keycloak.init({
//         onLoad: 'check-sso',
//         silentCheckSsoRedirectUri: window.location.origin + "/silent-check-sso.html"
//     });
// }
