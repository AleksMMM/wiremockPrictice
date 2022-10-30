import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

public class WireMockTests extends StartServer {

    @Test
    void getUsersTests() {
        RestAssured
           .given()
                .get("http://localhost:9999/user/getUser")
           .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    void getUserTwoTests() {
        RestAssured
                .given()
                .get("http://localhost:9999/user/getUserTwo")
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    void WireMockCodeConfigure() {

        String testApi = String.format("http://localhost:%d/emps/1",PORT);

        Response restAssured =
                RestAssured
                        .given()
                        .get(testApi)
                        .then()
                        .statusCode(201)
                        .extract()
                        .response();

        restAssured.prettyPeek();
    }

    @Test
    void WireMockResponseJson() {

        String testApi = String.format("http://localhost:%d/emps/2",PORT);

        Response restAssured =
                RestAssured
                        .given()
                        .get(testApi)
                        .then()
                        .statusCode(201)
                        .extract()
                        .response();

        restAssured.prettyPeek();
    }
}
