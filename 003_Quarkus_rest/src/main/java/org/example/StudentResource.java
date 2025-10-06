package org.example;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.*;

@Path("/students")
public class StudentResource {

    DatabaseService ds;

    public StudentResource(DatabaseService ds) {
        ds.reset();
        this.ds = ds;
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response students() {
        return Response.ok(ds.getStudents()).build(); // 200 + list
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudent(@PathParam("id") long id) {
        Student s = ds.getStudent(id);
        if (s == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Student not found")).build();
        }
        return Response.ok(s).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStudent(Student incoming, @Context UriInfo uriInfo) {
        incoming.setId(ds.inc());
        ds.addStudent(incoming);
        return Response.created(
                uriInfo.getAbsolutePathBuilder().path(incoming.getId() + "").build()
        ).entity(incoming).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") long id) {
        ds.delStudent(id);
        return Response.noContent().build(); // 204 - no content
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("id") long id, Student incoming) {
        if (!ds.containsStudent(id)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Student not found")).build();
        }

        incoming.setId(id);
        ds.addStudent(incoming);
        return Response.ok(incoming).build(); // 200 + updated object
    }
}
