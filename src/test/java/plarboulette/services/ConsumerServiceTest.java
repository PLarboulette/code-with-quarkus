package plarboulette.services;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import plarboulette.services.impl.ConsumerService;

import javax.inject.Inject;

@QuarkusTest
public class ConsumerServiceTest {

    @Inject
    ConsumerService consumerService;

    @Test
    public void testConsume () {
        Assertions.assertEquals("15", consumerService.consume());
    }
}
