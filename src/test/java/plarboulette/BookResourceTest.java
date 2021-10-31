package plarboulette;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BookResourceTest {

    @Test
    public void testAddBook() {
        given()
                .body("{ \"name\": \"Dracula\", \"pages\": 150}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .post("/books")
                .then()
                .statusCode(200);
    }

    @Test
    public void getAllBooksTest () {

        given()
                .body("{ \"name\": \"The Lord Of The Rings\", \"pages\": 345}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .post("/books")
                .then()
                .statusCode(200);

        given()
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .body("$.size()", is(2));
    }

}
