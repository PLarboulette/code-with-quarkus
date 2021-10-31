package plarboulette;

import plarboulette.models.Book;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@ApplicationScoped
@Path("/books")
public class BookResource {

    private List<Book> books;

    public BookResource () {
        this.books = new ArrayList<>();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Book> getAll () {
        return this.books.stream().sorted(
                Comparator.comparing(Book::getName)
        ).collect(Collectors.toList());
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response getById (@PathParam("name") String name) {
        Optional<Book> oBook = books.stream().filter(
                book -> book.getName().equals(name)

        ).findFirst();
        if (oBook.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Unknown book: " + name).build();
        } else {
            return Response.ok(oBook).build();
        }
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Book addBook (Book book) {
        this.books.add(book);
        return book;
    }

    @DELETE
    @Path("/{name}")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public boolean delete (@PathParam("name") String name) {
       books = books.stream().filter(book -> !book.getName().equals(name)).collect(Collectors.toList());
       return true;
    }

    @DELETE
    @Path("/")
    @Produces(MediaType.TEXT_PLAIN)
    public boolean clean (){
        books = new ArrayList<Book>();
        return true;
    }

}
