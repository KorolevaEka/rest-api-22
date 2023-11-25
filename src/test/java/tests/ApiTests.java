package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;


public class ApiTests extends BaseTest {
    @Test
    public void singleUserTest200() {
        given()
                .log().uri()
                .log().method()
                .when()
                .get("/users/2")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("data.first_name", is("Janet"));
    }


    @Test
    public void registerSuccessfulTest200() {
        String authBody = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";

        given()
                .log().uri()
                .log().method()
                .body(authBody)

                .when()
                .contentType(JSON)
                .post("/register")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void registerUnsuccessfulTest400() {
        String authBody = "{\"email\": \"sydney@fife\"}";

        given()
                .log().uri()
                .log().method()
                .body(authBody)
                .when()
                .contentType(JSON)
                .post("/register")
                .then()
                .log().status()
                .log().body()
                .statusCode(400) //
                .body("error", is("Missing password"));
    }


    @Test
    public void loginSuccessfulTest200() {
        String authBody = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}";

        given()
                .log().uri()
                .log().method()
                .body(authBody)
                .when()
                .contentType(JSON)
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200) //
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void loginUnsuccessfulTest400() {
        String authBody = "{\"email\": \"peter@klaven\"}";

        given()
                .log().uri()
                .log().method()
                .body(authBody)
                .when()
                .contentType(JSON)
                .post("/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(400) //
                .body("error", is("Missing password"));
    }

    @Test
    public void createUserTest201() {
        String authBody = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .log().uri()
                .log().method()
                .body(authBody)
                .when()
                .contentType(JSON)
                .post("/users")
                .then()
                .log().status()
                .log().body()
                .statusCode(201) //
                .body("name", is("morpheus"))
                .body("job", is("leader"));
    }
}


