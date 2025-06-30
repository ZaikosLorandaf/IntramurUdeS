package ca.usherbrooke.fgen.api.service;

import ca.usherbrooke.fgen.api.backend.Singleton.OGClass;
import ca.usherbrooke.fgen.api.backend.Singleton.TeamSingleton;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;

@QuarkusTest
class GetDashboardTest {

    @InjectMock
    OGClass ogClass;

    @Test
    void testGetEquipesData_withMock() {
        // Crée un mock de TeamSingleton
        TeamSingleton mockTeamSingleton = Mockito.mock(TeamSingleton.class);

        // Stub la méthode getEquipesData
        when(mockTeamSingleton.getEquipesData("soccer", "ligue1")).thenReturn("{\"mock\":\"ok\"}");

        // Stub ogClass pour qu’il retourne le mock
        when(ogClass.getTeamSingleton()).thenReturn(mockTeamSingleton);

        // Effectue un appel HTTP simulé et vérifie le résultat
        given()
                .queryParam("sport", "soccer")
                .queryParam("ligue", "ligue1")
                .when()
                .get("/api/dashboard/equipes")
                .then()
                .statusCode(200)
                .body(is("{\"mock\":\"ok\"}"));
    }
}
