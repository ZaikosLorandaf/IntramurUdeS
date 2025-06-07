// Initialize Keycloak
const keycloak = new Keycloak({
    realm: "usager",
    "auth-server-url": "http://localhost:8180/",
    "ssl-required": "external",
    clientId: "frontend",
    "public-client": true,
    "confidential-port": 0
});

function updateButton() {
    const btn = document.getElementById('btn-login');
    btn.textContent = keycloak.authenticated  ? 'Déconnexion' : 'Connexion';
}

async function authAndButton() {
    await checkAuth();
    updateButton();
}

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


// Pour récupérer le token (utile si besoin)
function getToken() {
    return keycloak.token;
}

// function getRoleNumber() {
//     if (!keycloak.tokenParsed || !keycloak.tokenParsed.realm_access)
//         return 0;
//
//     const roles = keycloak.tokenParsed.realm_access.roles;
//     if (roles.includes("student"))
//         return 2;
//     if (roles.includes("teacher"))
//         return 1;
//     return 0;
// }

//
// // Init avec check-sso, ça ne force pas la connexion mais vérifie la session
// function initKeycloak() {
//     return keycloak.init({
//         onLoad: 'check-sso',
//         silentCheckSsoRedirectUri: window.location.origin + "/silent-check-sso.html"
//     });
// }
