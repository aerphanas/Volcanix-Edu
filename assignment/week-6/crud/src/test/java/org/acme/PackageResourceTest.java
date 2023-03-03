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
@TestHTTPEndpoint(PackageResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PackageResourceTest {

    JsonObject requestBody = Json.createObjectBuilder()
                                    .add("Name", "gcc")
                                    .add("Architecture", "x86_64")
                                    .add("Description", "The GNU Compiler Collection - C and C++ frontends")
                                    .add("URL", "https://gcc.gnu.org/")
                                    .add("Maintainer", "Giancarlo Razzolini")
                                    .add("License", "FDL, GPL3, LGPL, custom")
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

}
