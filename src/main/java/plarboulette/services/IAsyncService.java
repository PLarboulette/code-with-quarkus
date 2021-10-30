package plarboulette.services;

import io.smallrye.mutiny.Uni;

public interface IAsyncService {
    Uni<String> getMessage();
}
