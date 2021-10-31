package plarboulette.repository;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import plarboulette.repository.impl.ConsumerRepository;

import javax.inject.Inject;
import java.util.List;

@QuarkusTest
public class ConsumerRepositoryTest {

    @Inject
    ConsumerRepository repository;

    @Test
    public void testGetValue () {
        Assertions.assertEquals("15", repository.getValue());
    }

    @Test
    public void testNames () {
        Assertions.assertEquals(
            List.of("Andy", "Adalbert", "Joachim"), repository.names()
        );
    }
}
