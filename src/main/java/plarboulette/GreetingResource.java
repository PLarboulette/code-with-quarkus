package plarboulette;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Path("/hello")
public class GreetingResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "Test";
    }

    @GET
    @Path("Functional")
    @Produces(MediaType.TEXT_PLAIN)
    public String functional() {

        List<String> test = Stream.of(1, 2 ,3)
                .map(i -> "Test " + i)
                .filter(p -> p.equals("Test 2"))
                .collect(Collectors.toList());

        Map<Integer, Integer> value = Map.of(1 ,1);

        return "Value : " + test.toString();
    }

}