package org.acme.resources;

import io.quarkus.hibernate.orm.rest.data.panache.PanacheEntityResource;
import org.acme.entities.Message;

public interface MessagesResource extends PanacheEntityResource<Message, Long> {
}
