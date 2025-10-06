package org.example;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class StudentResourceTest {
    @Inject
    DatabaseService ds;

    @BeforeEach
    void reset() {
        ds.reset(); // seeds 2 datasets
    }

    @Test
    void getAll_returnsTwoSeeded() {
        given()
                .when()
                .get("/students")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("size()", is(2))
                .body("[0].id", notNullValue());
    }

    @Test
    void getById_returnsFirstSeeded() {
        given()
                .when()
                .get("/students/1")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("id", is(1))
                .body("fullName", is("Gerald Unterrainer"))
                .body("email", is("gerald@unterrainer.info"));
    }

    @Test
    void post_createsId3_and_isFetchable() {
        String payload = """  
                {
                      "fullName": "Max Smith",
                      "email": "max@example.com",
                      "birthDate": "2001-09-21T00:00:00"
                }
                """;

        String location =
                given()
                        .contentType("application/json")
                        .body(payload)
                        .when()
                        .post("/students")
                        .then()
                        .statusCode(201)
                        .header("Location", notNullValue())
                        .extract()
                        .header("Location");

        // Get new ID from location of the test we just made...
        long id = Long.parseLong(location.substring(location.lastIndexOf('/') + 1));
        assertThat(id, is(3L));

        given()
                .when()
                .get("/students/" + id)
                .then()
                .statusCode(200)
                .body("id", is((int) id))
                .body("email", is("max@example.com"));
    }

    @Test
    void delete_removesId1_and_followupGetIs404() {
        given()
                .when()
                .delete("/students/1")
                .then()
                .statusCode(204);

        given()
                .when()
                .get("/students/1")
                .then()
                .statusCode(404);
    }

    @Test
    void put_replacesId1_and_returnsUpdated() {
        String payload = """
                {
                    "id": 1,
                    "fullName": "Gerald U.",
                    "email": "gerald.u@example.com",
                    "birthDate": "1975-05-02T00:00:00"
                }
                """;

        given()
                .contentType("application/json")
                .body(payload)
                .when()
                .put("/students/1")
                .then()
                .statusCode(200)
                .contentType("application/json")
                .body("id", is(1))
                .body("fullName", is("Gerald U."))
                .body("email", is("gerald.u@example.com"));
    }
}
