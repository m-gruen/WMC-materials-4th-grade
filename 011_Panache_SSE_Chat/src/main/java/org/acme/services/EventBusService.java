package org.acme.services;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.subscription.MultiEmitter;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.concurrent.CopyOnWriteArrayList;

@ApplicationScoped
public class EventBusService {
    private final CopyOnWriteArrayList<MultiEmitter<? super Object>> clients = new CopyOnWriteArrayList<>();

    public Multi<Object> eventStream() {
        return Multi.createFrom().emitter(emitter -> {
            clients.add(emitter);
            emitter.onTermination(() -> clients.remove(emitter));
        });
    }

    public void publish(Object evt) {
        clients.forEach(em -> em.emit(evt));
    }
}
