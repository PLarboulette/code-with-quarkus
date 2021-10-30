package plarboulette.services.impl;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;
import plarboulette.services.IAsyncService;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AsyncService implements IAsyncService {

    public Uni<String> getMessage() {
        return Uni.createFrom().item(
                () -> "Hello from async service"
        ).emitOn(Infrastructure.getDefaultExecutor());
    }
}
