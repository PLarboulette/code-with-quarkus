package plarboulette;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

    @Test
    public void testGetMessage() {
        given()
                .when().get(("/hello/async"))
                .then()
                .statusCode(200)
                .body(is("Hello from async service"));
    }

    @Test
    public void testEnvironment () {
        given()
                .when().get("/hello/environment")
                .then()
                .statusCode(200)
                .body(is("production"));
    }

}