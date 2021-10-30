package plarboulette;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.smallrye.mutiny.Uni;
import plarboulette.services.IAsyncService;
import plarboulette.services.IConsumerService;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Path("/hello")
public class GreetingResource {

    @Inject
    public IConsumerService consumer;

    @Inject
    public IAsyncService asyncService;

    private final MeterRegistry registry;

    public GreetingResource(MeterRegistry _registry) {
        this.registry = _registry;
    }

    // Endpoints
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

    // Example of functional programming
    @GET
    @Path("Functional")
    @Produces(MediaType.TEXT_PLAIN)
    public String functional() {

        List<String> test = Stream.of(1, 2 ,3)
                .map(i -> "Test " + i)
                .filter(p -> p.equals("Test 2"))
                .collect(Collectors.toList());

        Map<Integer, Integer> value = Map.of(1 ,1);

        return "Value : " + test;
    }

    // Example of dependency injection
    @GET
    @Path("/consumer")
    @Produces(MediaType.TEXT_PLAIN)
    public String consume () {
        return consumer.consume();
    }


    // Example of how to use properties
    @ConfigProperty(name = "environment")
    String environment;

    @GET
    @Path("environment")
    @Produces(MediaType.TEXT_PLAIN)
    public String environment () {
        return environment;
    }

    // Example of async call (reactive) (Use of Uni)
    @GET
    @Path("async")
    @Produces(MediaType.TEXT_PLAIN)
    public Uni<String> getMessage () {
        return asyncService.getMessage();
    }

    // Example of metrics
    // The metrics are available here : http://localhost:8080/q/metrics. It lets for example for Prometheus to scrap the metrics :)
    // Here, you can easily write a query with PromQL like that : calls {function = "counter"} tto return the number of calls on the first function.
    // Or display that in Grafana ^_^
    // See also https://quarkus.io/guides/micrometer. An alternative exists, Microprofile metrics, but i('s not recommended by Quarkus team
    @GET
    @Path("/counter")
    @Produces(MediaType.TEXT_PLAIN)
    public String counter() {
        registry.counter("calls", Tags.of("function", "counter")).increment();
        return "Test";
    }

    @GET
    @Path("/counter2")
    @Produces(MediaType.TEXT_PLAIN)
    public String counter2() {
        registry.counter("calls", Tags.of("function", "counter2")).increment();
        return "Test";
    }
}