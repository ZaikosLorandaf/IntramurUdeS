
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

    * `views.sql`

4. Lancer `Message.main` (dans l'IDE ou via Gradle)

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
├── src/
│   ├── main/
│   │   └── java/                # Code source (API REST, service, backend)
│   └── test/
│       └── java/                # Tests JUnit
├── logs/                        # Dossier vide, créé automatiquement
├── .github/
│   └── workflows/
│       └── quarkus-tests.yml    # CI: exécution automatique des tests
└── README.md
```

---

## Tests & CI

* Tests unitaires en Java (JUnit 5)
* Déclenchés automatiquement via **GitHub Actions** (branche `master-remi`)
* Fichier CI :

  ```
  .github/workflows/quarkus-tests.yml
  ```
* Astuce : générer un test dans IntelliJ avec `Alt+Entrée` sur une classe.

---

## Base de données

* Initialisée par `docker/postgres/init.sql`

* MyBatis utilisé pour la couche mapper :

    * `src/main/java/mapper/`
    * Config : `src/main/resources/application.properties`

* Scripts complémentaires :

    * `populate_mock_data.sql` : données fictives
    * `views.sql` : vues manquantes (à exécuter si erreur "view doesn't exist")

---

## Développement

* Supprimé : `init.sh` et `setdata.sh`

    * → les scripts sont désormais intégrés dans le `docker-compose.yml` (service `auth`)
* Logger local :

    * Créer un dossier `logs/` à la racine du projet pour que `LoggerUtil` fonctionne
    * Exemple :

      ```java
      LoggerUtil.info("Démarrage de l'application");
      ```
