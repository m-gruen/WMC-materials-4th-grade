package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Path("/students")
public class StudentResource {

    Long nextId = 1L;
    final Map<Long, Student> students = new HashMap<>();

    void datasourceReset() {
        students.clear();
        nextId = 1L;

        DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Student s = Student.builder().id(nextId++).fullName("Gerald Unterrainer").email("gerald@unterrainer.info")
                .birthDate(LocalDateTime.parse("1975-05-02 00:00", f)).build();
        students.put(s.getId(), s);
        s = Student.builder().id(nextId++).fullName("Kasperl Unterrainer").email("kasperl@unterrainer.info")
                .birthDate(LocalDateTime.parse("1995-10-23 00:00", f)).build();
        students.put(s.getId(), s);
    }

    public StudentResource() {
        datasourceReset();
    }

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStudents() {
        return Response.ok(students.values()).build(); // 200 OK + JSON-Body
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudent(@PathParam("id") long id) {
        Student s = students.get(id);
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
        incoming.setId(nextId++);
        students.put(incoming.getId(), incoming);
        return Response.created(
                uriInfo.getAbsolutePathBuilder().path(incoming.getId() + "").build()
        ).entity(incoming).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteStudent(@PathParam("id") long id) {
        students.remove(id);
        return Response.noContent().build(); // 204 - no content
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("id") long id, Student incoming) {
        if (!students.containsKey(id)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of("error", "Student not found")).build();
        }

        incoming.setId(id);
        students.put(id, incoming);
        return Response.ok(incoming).build(); // 200 + updated object
    }
}
