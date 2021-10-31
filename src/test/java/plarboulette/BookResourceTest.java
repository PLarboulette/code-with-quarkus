package plarboulette;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import plarboulette.models.Book;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BookResourceTest {

    // Will be used to convert Java objects to JSON, just for the Assertions
    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testAddBook() {
        this.clean();
        this.createDraculaBook();
        this.getAllBooks(1);
    }

    @Test
    public void getAllBooksTest () {

        this.clean();

        given()
        .body("{ \"name\": \"The Lord Of The Rings\", \"pages\": 345}")
        .header("Content-Type", MediaType.APPLICATION_JSON)
        .when()
        .post("/books")
        .then()
        .statusCode(200);

        this.getAllBooks(1);

    }

    @Test
    public void getByIdSuccess () throws JsonProcessingException {
        this.clean();
        this.createDraculaBook();

        given()
                .when()
                .header("Content-Type", MediaType.TEXT_PLAIN)
                .get("/books/Dracula")
                .then()
                .statusCode(200)
                .body(is(mapper.writeValueAsString(new Book("Dracula", 150))));
    }

    @Test
    public void getByIdFailure() throws JsonProcessingException {

        this.clean();

        given()
                .when()
                .header("Content-Type", MediaType.TEXT_PLAIN)
                .get("/books/Frankenstein")
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    public void testClean () {
        given()
                .when()
                .delete("/books")
                .then()
                .statusCode(200);

        this.getAllBooks(0);
    }

    @Test
    public void testDelete () {
        this.clean();
        this.createDraculaBook();

        given()
                .when()
                .header("Content-Type", MediaType.TEXT_PLAIN)
                .delete("/books/Dracula")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());

        this.getAllBooks(0);
    }

    private void clean () {
        given()
                .when()
                .delete("/books")
                .then()
                .statusCode(200);
    }

    private void getAllBooks (int expectedSize) {
        given()
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .body("$.size()", is(expectedSize));
    }

    private void createDraculaBook () {
        given()
                .body("{ \"name\": \"Dracula\", \"pages\": 150}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when()
                .post("/books")
                .then()
                .statusCode(200);
    }

}
