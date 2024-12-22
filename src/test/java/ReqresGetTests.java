import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class ReqresGetTests {

    @Test
    void checkListUsers() {
        given()
        .when()
                .get("https://reqres.in/api/users?page=2")
        .then()
                .body("total", is(12))
                .body("data[0].id", is(7))
                .body("data[5].id", is(12));
    }

    @Test
    void getSingleUser() {
        given()
        .when()
                .get("https://reqres.in/api/users/2")
        .then()
                .body("data.id", is(2))
                .body("data.email", is("janet.weaver@reqres.in"));
    }

    @Test
    void getUserNotFound() {
        given()
        .when()
                .get("https://reqres.in/api/users/23")
        .then()
                .statusCode(404);
    }

    @Test
    void getSingleResource() {
        given()
        .when()
                .get("https://reqres.in/api/unknown/2")
        .then()
                .body("data.id", is(2))
                .body("data.color", is("#C74375"));
    }

}
