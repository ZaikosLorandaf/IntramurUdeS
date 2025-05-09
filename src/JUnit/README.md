## Infos des tests

- Les tests **ne sont pas exécutés dans un ordre déterminé**. Chaque méthode annotée avec `@Test` peut être exécutée dans un ordre différent à chaque exécution.
- Il ne faut **pas compter sur l’ordre d’exécution** entre les tests (ex. : ne jamais supposer que `testAjouter()` sera exécuté avant `testRetirer()`).
- Chaque test doit être **indépendant** : il doit pouvoir être exécuté seul, sans dépendre de l'état laissé par un autre test.
- Utiliser `@BeforeEach` pour initialiser un état propre avant chaque test, et `@AfterEach` pour nettoyer après, est une bonne pratique.
- Il est recommandé de **ne pas modifier des ressources partagées** (fichiers, bases de données, objets statiques) sans les réinitialiser.
- Si un test échoue, les autres continuent à s’exécuter : cela permet de détecter plusieurs problèmes en une seule exécution.
- Éviter les effets de bord entre tests améliore la fiabilité et la maintenabilité du code testé.

## Fonctions pratiques de JUnit

- `assertEquals(expected, actual)`           → Vérifie que les deux valeurs sont égales
- `assertNotEquals(unexpected, actual)`      → Vérifie qu’elles sont différentes
- `assertTrue(condition)`                    → Vérifie que la condition est vraie
- `assertFalse(condition)`                   → Vérifie que la condition est fausse
- `assertNull(object)`                       → Vérifie que l’objet est null
- `assertNotNull(object)`                    → Vérifie que l’objet n’est pas null
- `assertThrows(Exception.class, () -> ...)` → Vérifie qu’une exception est bien levée
- `assertDoesNotThrow(() -> ...)`            → Vérifie qu’aucune exception n’est levée
- `assertArrayEquals(expectedArray, actualArray)` → Compare deux tableaux
- `fail()`                                   → Échoue explicitement un test

## Lien

Documentation officielle JUnit dans IntelliJ :  
https://www.jetbrains.com/help/idea/junit.html#intellij
