package org.acme;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;

@Path("/db")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleResource {

        @GET
        public Uni<Response> get() {
            Log.info("Request GET Method");
            return People.listAll()
                    .onItem().transform(row -> Response.ok(row).build())
                    .onFailure().recoverWithItem(Response.serverError().build());
        }

        @POST
        public Uni<Response> addPeople(JsonObject requestBody) {
    
            Log.info("Request POST Method with " + requestBody);
            People person = new People();
            person.setName(requestBody.getString("name"));
            person.setUmur(requestBody.getInt("age"));
            person.setId(requestBody.getInt("id"));
    
            return person.persistAndFlush()
                    .onItem().transform(rows -> Response.ok("ok").build())
                    .onFailure().recoverWithItem(Response.serverError().build());
        }

        @PUT
        @Path("{id}")
        public Uni<Response> putPeople(@PathParam("id") Integer id, JsonObject requestBody) {
            Log.info("Request PUT Method with id " + id + " and " + requestBody);
            return People.<People>findById(id)
                    .onItem().ifNull().failWith(new NotFoundException())
                    .onItem().transform(person -> {
                        person.setName(requestBody.getString("name"));
                        person.setUmur(requestBody.getInt("age"));
                        return person;
                    })
                    .onItem().transformToUni(person -> person.persistAndFlush())
                    .onItem().transform(x -> Response.ok("ok").build())
                    .onFailure().recoverWithItem(Response.serverError().build());
        }
        

        // TODO fix this shit
        // @PUT
        // @Path("{id}")
        // public Uni<Response> updatePeople(@PathParam("id") Integer id, JsonObject requestBody) {
        //     return People.<People>findById(id)
        //             .onItem().ifNotNull().transform(person -> {
        //                 person.setName(requestBody.getString("name"));
        //                 person.setUmur(requestBody.getInt("age"));
        //                 return person;
        //             })
        //             .onItem().ifNull().failWith(new WebApplicationException("Person with id " + id + " not found", Response.Status.NOT_FOUND))
        //             .flatMap(person -> person.persistAndFlush())
        //             .onItem().transform(rows -> {
        //                 return rows != null ? Response.ok(rows).build() : Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        //             });
        // }

        @DELETE
        @Path("{id}")
        public Uni<Response> deletePeople(Integer id) {
            Log.info("Request DELETE Method to id " + id);
            return People.delete("id", id)
                    .onItem().transform(rows -> {
                        return rows > 0 ? Response.noContent().build()
                                        : Response.status(Status.NOT_FOUND).build();
                    })
                    .onFailure().recoverWithItem(Response.serverError().build());
        }
    
}