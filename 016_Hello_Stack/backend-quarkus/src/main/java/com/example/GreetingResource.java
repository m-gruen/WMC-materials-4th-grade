package com.example;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GreetingResource {

    @GET
    @Path("/hello")
    public Greeting hello() {
        var g = new Greeting();
        g.message = "Hello from Quarkus + Postgres";
        return g;
    }

    @GET
    @Path("/greetings")
    public List<Greeting> list() {
        return Greeting.listAll();
    }

    @POST
    @Path("/greetings")
    @Transactional
    public Greeting create(Greeting g) {
        g.id = null;
        g.persist();
        return g;
    }
}