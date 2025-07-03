
# Projet IntramurUdeS

> Plateforme de gestion des ligues sportives intramurales développée dans le cadre du cours de projet intégrateur à l'Université de Sherbrooke.

## Sommaire

- [Installation rapide](#installation-rapide)
- [Architecture du projet](#architecture-du-projet)
- [Tests & CI](#tests--ci)
- [Base de données](#base-de-données)
- [Développement](#développement)
- [Liens utiles](#liens-utiles)

---

## Installation rapide

1. Cloner le dépôt :
   ```bash
   git clone https://github.com/...

2. Démarrer l’environnement :

   ```bash
   cd docker
   docker compose up -d
   ```

3. Exécuter les scripts SQL :
``` bash
 docker/postgres/views.sql
 user: postgres
 password: postgres
 SET search_path = intramurudes;
```
4. Lancer `Message.main` (dans l'IDE ou via Gradle)
- Ajouter une configuration de démarrage
- Ajouter Quarkus 
  - nom: Message.main
  - Sélectionner application module: message.main

5. Accéder à l’application :

   ```
   https://localhost/intra
   ```

---

## Architecture du projet

```
.
├── docker/
│   ├── docker-compose.yml       # Services: Keycloak, Nginx QUIC, Postgres
│   ├── keycloak/                # Fichiers de config et JSON d'import
│   └── postgres/                # Script init.sql de la base
│   └── webserver/               # Page HTML et certificat serveur
├── src/
│   ├── main/
│   │   └── java/                # Code source (API REST, service, backend)
│   │   └── ressources/          # Liaison entre java et base de donnée
│   └── test/
│       └── java/                # Tests JUnit
├── .github/
│   └── workflows/
│       └── quarkus-tests.yml    # CI: exécution automatique des tests
└── README.md
```

---

## Tests & CI

* Tests unitaires en Java (JUnit 5)
* Déclenchés automatiquement via **GitHub Actions** (branche `master`)
* Fichier CI :

  ```
  .github/workflows/quarkus-tests.yml
  ```
* Astuce : générer un test dans IntelliJ avec `Alt+Entrée` sur une classe.
