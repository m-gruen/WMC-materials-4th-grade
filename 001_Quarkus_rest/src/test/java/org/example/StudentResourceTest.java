package org.example;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@QuarkusTest
class StudentResourceTest {

    @Inject
    StudentResource resource; // to get a handle on the id and the map field

    @BeforeEach
    void reset() {
        // Guaranties a deterministic start for each single test, no matter the order.
        resource.datasourceReset(); // seeds 2 datasets
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
}
