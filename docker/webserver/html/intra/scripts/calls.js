function requestStudent() {
    const div = document.getElementById('title');
    const span = div.firstElementChild;
    axios.get("http://localhost:8888/api/student", {
        headers: {
            'Authorization': 'Bearer ' + keycloak.token
        }
    })
        .then(function (response) {
            console.log("Response: ", response.status);
            span.innerHTML = '<br> <strong>' + response.data.cip + '</strong> </br>' +
                '<br> <strong>' + response.data.last_name + '</strong> </br>' +
                '<br> <strong>' + response.data.first_name + '</strong> </br>' +
                '<br> <strong>' + response.data.email + '</strong> </br>' +
                '<br> <strong>' + response.data.roles + '</strong> </br>'
        })
        .catch(function (error) {
            console.log('refreshing');
            keycloak.updateToken(5).then(function () {
                console.log('Token refreshed');
            }).catch(function () {
                console.log('Failed to refresh token');
            })
        });
    span.innerHTML = '<br> <strong>' + "Vous n'avez pas le role d'étudiant" + '</strong> </br>'
}

function requestTeacher() {
    const div = document.getElementById('title');
    const span = div.firstElementChild;
    axios.get("http://localhost:8888/api/teacher", {
        headers: {
            'Authorization': 'Bearer ' + keycloak.token
        }
    })
        .then(function (response) {
            console.log("Response: ", response.status);
            span.innerHTML = '<br> <strong>' + response.data.cip + '</strong> </br>' +
                '<br> <strong>' + response.data.last_name + '</strong> </br>' +
                '<br> <strong>' + response.data.first_name + '</strong> </br>' +
                '<br> <strong>' + response.data.email + '</strong> </br>' +
                '<br> <strong>' + response.data.roles + '</strong> </br>'
        })
        .catch(function (error) {
            console.log('refreshing');
            keycloak.updateToken(5).then(function () {
                console.log('Token refreshed');
            }).catch(function () {
                console.log('Failed to refresh token');
            })
        });
    span.innerHTML = '<br> <strong>' + "Vous n'avez pas le role d'enseignant" + '</strong> </br>'
}

// ~~~~~ Sports ~~~~~~ //
function listSport() {
    const div = document.getElementById('title');
    const span = div.firstElementChild;
    axios.get("http://localhost:8888/api/listSport/", {})
        .then(function (response) {
            span.innerHTML +=
                response.data;
        })
        .catch(function (error) {
            span.innerHTML += '<div>Erreur : ' + error + '</div>';
        });
}

function addSport() {
    const div = document.getElementById('title');
    const span = div.firstElementChild;
    const nomSport = document.getElementById("nomSport").value;
    axios.post("http://localhost:8888/api/addSport/", {
        nom: nomSport
    })
        .then(function (response) {
            span.innerHTML +=
                '<br> nom : ' +  response.data;
        })
        .catch(function (error) {
            span.innerHTML += '<div>Erreur : ' + error + '</div>';
        });
}

function getSport() {
    const div = document.getElementById('title');
    const span = div.firstElementChild;
    const nomLigue = document.getElementById("nomSport").value;
    axios.get("http://localhost:8888/api/getSport/"+nomSport, {})
        .then(function (response) {
            span.innerHTML +=
                '<br> nom : ' +  response.data;
        })
        .catch(function (error) {
            span.innerHTML += '<div>Erreur : ' + error + '</div>';
        });
}

function removeSport() {
    const div = document.getElementById('title');
    const span = div.firstElementChild;
    const nomSport =document.getElementById("nomSport").value;
    axios.get("http://localhost:8888/api/removeSport/"+nomSport, {})
        .then(function (response) {
            span.innerHTML +=
                '<br> nom : ' +  response.data;
        })
        .catch(function (error) {
            span.innerHTML += '<div>Erreur : ' + error + '</div>';
        });
}

// ~~~~~ Leagues ~~~~~~ //
function listLeague() {
    const div = document.getElementById('title');
    const span = div.firstElementChild;
    const sport = document.getElementById("nomSport").value;
    axios.get("http://localhost:8888/api/listLigue/" + sport, {})
        .then(function (response) {
            span.innerHTML +=
                response.data;
        })
        .catch(function (error) {
            span.innerHTML += '<div>Erreur : ' + error + '</div>';
        });
}

function addLeague() {
    const div = document.getElementById('title');
    const span = div.firstElementChild;
    const nomLigue =document.getElementById("nomLigue").value;
    const nomSport = document.getElementById("nomSport").value;
    const dateDebut = document.getElementById("date_debut").value;
    const dateFin = document.getElementById("date_fin").value;
    axios.post("http://localhost:8888/api/addLigue/",
        {
            nom_sport: nomSport,
            nom: nomLigue,
            date_debut: dateDebut,
            date_fin: dateFin
        })
        .then(function (response) {
            span.innerHTML +=
                '<br> nom : ' +  response.data;
        })
        .catch(function (error) {
            span.innerHTML += '<div>Erreur : ' + error + '</div>';
        });
}

function getLeague() {
    const div = document.getElementById('title');
    const span = div.firstElementChild;
    const nomLigue =document.getElementById("nomLigue").value;
    axios.get("http://localhost:8888/api/getLigue/"+nomLigue, {})
        .then(function (response) {
            span.innerHTML +=
                '<br> nom : ' +  response.data;
        })
        .catch(function (error) {
            span.innerHTML += '<div>Erreur : ' + error + '</div>';
        });
}

function removeLeague() {
    const div = document.getElementById('title');
    const span = div.firstElementChild;
    const nomLigue =document.getElementById("nomLigue").value;
    const nomSport = document.getElementById("nomSport").value;
    axios.get("http://localhost:8888/api/removeLigue/"+nomSport+"/"+nomLigue, {})
        .then(function (response) {
            span.innerHTML +=
                '<br> nom : ' +  response.data;
        })
        .catch(function (error) {
            span.innerHTML += '<div>Erreur : ' + error + '</div>';
        });
}

// ~~~~~ Team ~~~~~~ //
function listTeam() {
    const div = document.getElementById('title');
    const span = div.firstElementChild;
    const nomLigue =document.getElementById("nomLigue").value;
    const nomSport = document.getElementById("nomSport").value;
    axios.get("http://localhost:8888/api/listTeam/" +nomSport+"/"+ nomLigue, {})
        .then(function (response) {
            span.innerHTML +=
                response.data;
        })
        .catch(function (error) {
            span.innerHTML += '<div>Erreur : ' + error + '</div>';
        });
}

function addTeam() {
    const div = document.getElementById('title');
    const span = div.firstElementChild;
    const nom_Ligue =document.getElementById("nomLigue").value;
    const nom_Team =document.getElementById("nomTeam").value;
    const nom_Sport =document.getElementById("nomSport").value;
    axios.post("http://localhost:8888/api/addTeam/", {
        nomSport: nom_Sport,
        nomLigue: nom_Ligue,
        nomTeam: nom_Team
    })
        .then(function (response) {
            span.innerHTML +=
                '<br> nom : ' +  response.data + '</br>';
        })
        .catch(function (error) {
            span.innerHTML += '<div>Erreur : ' + error + '</div>';
        });
}


function removeTeam() {
    const div = document.getElementById('title');
    const span = div.firstElementChild;
    const nomLigue = document.getElementById("nomLigue").value;
    const nomTeam =document.getElementById("nomTeam").value;
    const nomSport = document.getElementById("nomSport").value;
    axios.get("http://localhost:8888/api/removeTeam/"+nomSport+"/"+ nomLigue +"/"+nomTeam, {})
        .then(function (response) {
            span.innerHTML +=
                '<br> nom : ' +  response.data + '</br>';
        })
        .catch(function (error) {
            span.innerHTML += '<div>Erreur : ' + error + '</div>';
        });
}

// ~~~~~ Players ~~~~~~ //
function addPlayer() {
    const div = document.getElementById('title');
    const span = div.firstElementChild;
    const nomTeam =document.getElementById("nomTeam").value;
    const nomSport = document.getElementById("nomSport").value;
    const nomLigue =document.getElementById("nomLigue").value;
    const prenomPlayer =document.getElementById("prenomPlayer").value;
    const nomPlayer =document.getElementById("nomPlayer").value;
    axios.get("http://localhost:8888/api/addPlayer/"+nomSport+"/"+nomLigue+"/"+nomTeam + "/" + prenomPlayer + "/" + nomPlayer, {})
        .then(function (response) {
            span.innerHTML +=
                '<br> nom : ' +  response.data + '</br>';
        })
        .catch(function (error) {
            span.innerHTML += '<div>Erreur : ' + error + '</div>';
        });
}

function listPlayer() {
    const div = document.getElementById('title');
    const span = div.firstElementChild;
    const nomLigue =document.getElementById("nomLigue").value;
    const nomTeam =document.getElementById("nomTeam").value;
    const nomSport = document.getElementById("nomSport").value;
    axios.get("http://localhost:8888/api/listPlayer/" +nomSport+"/"+ nomLigue + "/" + nomTeam, {})
        .then(function (response) {
            span.innerHTML +=
                response.data;
        })
        .catch(function (error) {
            span.innerHTML += '<div>Erreur : ' + error + '</div>';
        });
}

function removePlayer() {
    const div = document.getElementById('title');
    const span = div.firstElementChild;
    const nomLigue = document.getElementById("nomLigue").value;
    const nomTeam =document.getElementById("nomTeam").value;
    const prenomPlayer = document.getElementById("prenomPlayer").value;
    const nomPlayer = document.getElementById("nomPlayer").value;
    const nomSport = document.getElementById("nomSport").value;
    axios.get("http://localhost:8888/api/removePlayer/"+nomSport+"/"+ nomLigue +"/"+nomTeam+"/"+prenomPlayer+"/"+nomPlayer, {})
        .then(function (response) {
            span.innerHTML +=
                '<br> nom : ' +  response.data + '</br>';
        })
        .catch(function (error) {
            span.innerHTML += '<div>Erreur : ' + error + '</div>';
        });
}

// ~~~~~ Misc ~~~~~~ //
function myAPI() {
    const div = document.getElementById('title');
    const span = div.firstElementChild;
    axios.get("http://localhost:8888/api/monendpoint", {
        headers: {
            'Authorization': 'Bearer ' + keycloak.token
        }
    })
        .then(function (response) {
            span.innerHTML = "";
            span.innerHTML += '<div>' + response.data + '</div>';
        })
        .catch(function (error) {
            span.innerHTML = '<div>Erreur : ' + error + '</div>';
        });
}

function requestMessage() {
    const div = document.getElementById('title');
    const span = div.firstElementChild;
    axios.get("http://localhost:8888/api/getmessages/e24/s3i/app2", {
        headers: {
            'Authorization': 'Bearer ' + keycloak.token
        }
    })
        .then(function (response) {
            console.log("Response: ", response.status);
            span.innerHTML = '';
            for (let i in response.data) {
                console.log(response.data[i]);
                span.innerHTML +=
                    '<br> <strong>' + response.data[i].cip + '</strong>' +
                    '<br> <strong>' + response.data[i].description + '</strong> </br>'
            }
        })
        .catch(function (error) {
            console.log('refreshing');
            keycloak.updateToken(5).then(function () {
                console.log('Token refreshed');
            }).catch(function () {
                console.log('Failed to refresh token');
            })
        });
    span.innerHTML = '<br> <strong>' + "Aucun message" + '</strong> </br>'
}
