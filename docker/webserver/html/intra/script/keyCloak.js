let keycloak;

function initKeycloak() {
    keycloak = new Keycloak({
        "realm": "usager",
        "auth-server-url": "http://localhost:8180/",
        "ssl-required": "external",
        "clientId": "frontend",
        "public-client": true,
        "confidential-port": 0
    });

    keycloak.init({onLoad: 'login-required'}).then(authenticated => {
        console.log(authenticated ? 'authenticated' : 'not authenticated');
    }).catch(() => alert('Échec d\'initialisation'));
}

function logout() {
    alert("Déconnexion simulée");
}
