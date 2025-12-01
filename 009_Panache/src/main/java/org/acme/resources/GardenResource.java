package org.acme.resources;

import io.quarkus.hibernate.reactive.rest.data.panache.PanacheEntityResource;
import io.quarkus.rest.data.panache.ResourceProperties;
import org.acme.entities.Garden;

@ResourceProperties(path = "gardens")
public interface GardenResource extends PanacheEntityResource<Garden, Long> {
}
