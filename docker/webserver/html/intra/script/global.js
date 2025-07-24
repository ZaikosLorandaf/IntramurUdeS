const backendAddress = "https://backend.intramurudes.com";
const keycloakAddress = "https://kc.intramurudes.com";

fetch('fragments/header.html')
    .then(response => response.text())
    .then(data => {
        document.getElementById('header-container').innerHTML = data;
    });

fetch('fragments/footer.html')
    .then(response => response.text())
    .then(data => {
        document.getElementById('footer-container').innerHTML = data;
    });
