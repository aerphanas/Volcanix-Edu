package org.acme;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestHTTPEndpoint(PeopleResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PeopleResourceTest {

    JsonObject requestBody = Json.createObjectBuilder()
                                    .add("id", 100)
                                    .add("name", "John")
                                    .add("age", 30)
                                    .build();

    @Test
    @Order(1)
    public void testGetAllPeople() {
        given()
            .when().get()
            .then()
                .statusCode(200)
                .body(is(not(empty())));
    }

    @Test
    @Order(2)
    public void testPostPeople() {
        given()
            .body(requestBody.toString())
            .header("Content-Type", "application/json")
            .when().post()
            .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(is(equalTo("ok")));
    }

    @Test
    @Order(3)
    public void testPutPeople() {
        JsonObject requestBody = Json.createObjectBuilder()
                                        .add("name", "Simon")
                                        .add("age", 30)
                                        .build();

        given()
            .body(requestBody.toString())
            .header("Content-Type", "application/json")
            .when().put("100")
            .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .body(is(equalTo("ok")));
    }

    @Test
    @Order(4)
    public void testDeletePerson() {
        given()
            .when().delete("100")
            .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());
    }

}
