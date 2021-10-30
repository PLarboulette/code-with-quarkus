package plarboulette;

import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import plarboulette.services.impl.AsyncService;

import javax.inject.Inject;

@QuarkusTest
public class AsyncServiceTest {


    // See https://github.com/quarkusio/quarkus/blob/main/extensions/mutiny/deployment/src/test/java/io/quarkus/mutiny/deployment/test/MutinyTest.java

    // just inject the service tou want to test
    @Inject
    AsyncService service;

    @Test
    public void testAsyncService() {
        String result = service.getMessage().await().indefinitely(); // Waiting for future response
        Assertions.assertEquals(result, "Hello from async service");
    }
}
