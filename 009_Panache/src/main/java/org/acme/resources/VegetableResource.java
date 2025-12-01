package org.acme.resources;

import io.quarkus.hibernate.reactive.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import org.acme.entities.Vegetable;

@ResourceProperties(path = "vegetables")
public interface VegetableResource extends PanacheEntityResource<Vegetable, Long> {
}
