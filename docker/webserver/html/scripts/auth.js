// Initialize Keycloak
const keycloak = new Keycloak({
    url: 'https://localhost/8180',
    realm: 'usager',
    clientId: 'frontend'
});

keycloak.init({ onLoad: 'check-sso' }).then(function(authenticated) {
    updateButton(authenticated);
}).catch(function() {
    console.error('Failed to initialize Keycloak');
});

function updateButton(isAuthenticated) {
    const btn = document.getElementById('btn-login');
  btn.textContent = isAuthenticated ? 'Logout' : 'Connection';
}

function checkAuth() {
  if (keycloak.authenticated) {
    keycloak.logout();
  } else {
    keycloak.login();
  }
}






// const keycloak = new Keycloak({
//     realm: "usager",
//     "auth-server-url": "http://localhost:8180/",
//     "ssl-required": "external",
//     clientId: "frontend",
//     "public-client": true,
//     "confidential-port": 0
// });
//
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
//
// // Init avec check-sso, ça ne force pas la connexion mais vérifie la session
// function initKeycloak() {
//     return keycloak.init({
//         onLoad: 'check-sso',
//         silentCheckSsoRedirectUri: window.location.origin + "/silent-check-sso.html"
//     });
// }
//
//
// initKeycloak()
//     .then(() => {
//         const div = document.getElementById("btn-login");
//         const logoutOptions = { redirectUri : "http://localhost/8888/" };
//         if (isAuthenticated()) {
//             console.log("Utilisateur connecté !");
//             roleNumber = getRoleNumber();
//             console.log("Rôle détecté:", roleNumber);
//             div.textContent = "Déconnexion";
//             div.onclick = () => keycloak.logout(logoutOptions);
//         } else {
//             console.log("Utilisateur non connecté.");
//             div.textContent = "Connexion";
//             div.onclick = () => keycloak.login();
//         }
//
//         getSportsData();
//     })
//     .catch(() => {
//         console.error("Erreur lors de l'initialisation de Keycloak");
//     });
//
//
// // Pour savoir si connecté
// function isAuthenticated() {
//     return keycloak.authenticated;
// }
//
// // Pour récupérer le token (utile si besoin)
// function getToken() {
//     return keycloak.token;
// }
