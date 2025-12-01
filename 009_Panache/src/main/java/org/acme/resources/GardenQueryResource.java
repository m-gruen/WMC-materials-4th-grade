package org.acme.resources;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.acme.entities.Garden;

import java.util.List;

@Path("/gardens")
@Produces(MediaType.APPLICATION_JSON)
public class GardenQueryResource {

    @GET
    @Path("/top5")
    public Uni<List<Garden>> getTop5Gardens() {
        return Garden.find("#Garden.sortedByAddress")   // NamedQuery
                .range(0, 4)                           // LIMIT 5 (0–4)
                .list();
    }
}