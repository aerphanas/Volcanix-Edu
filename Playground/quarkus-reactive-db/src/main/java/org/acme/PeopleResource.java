package org.acme;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Uni;

@Path("/db")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleResource {

        @GET
        public Uni<Response> get() {
            return People.listAll()
                    .onItem().transform(row -> Response.ok(row).build())
                    .onFailure().recoverWithItem(Response.serverError().build());
        }

        @POST
        public Uni<Response> addPeople(String requestBody) {
            JsonObject json = Json.createReader(new StringReader(requestBody)).readObject();
    
            People person = new People();
            person.setName(json.getString("name"));
            person.setUmur(json.getInt("age"));
            person.setId(json.getInt("id"));
    
            return person.persistAndFlush()
                    .onItem().transform(rows -> Response.ok("ok").build())
                    .onFailure().recoverWithItem(Response.serverError().build());
        }

        // TODO fix this shit
        @PUT
        @Path("{id}")
        public Uni<Response> updatePeople(@PathParam("id") Integer id, String requestBody) {
            JsonObject json = Json.createReader(new StringReader(requestBody)).readObject();
            People person = new People();
            return People.findById(id)
                    .onItem().ifNull().failWith(() -> new WebApplicationException("Record not found", 404))
                    .onItem().ifNotNull().transform(entity -> {
                        person.setName(json.getString("name"));
                        person.setUmur(json.getInt("age"));
                        return entity;
                    })
                    .onItem().ifNotNull().transform(PanacheEntityBase::persistAndFlush)
                    .onItem().transform(rows -> rows == null ? 0 : rows)
                    .onItem().transform(rows -> rows != null ? Response.ok("ok").build() : Response.status(Status.NOT_MODIFIED).build())
                    .onFailure().recoverWithItem(Response.serverError().build());
        }

        @DELETE
        @Path("{id}")
        public Uni<Response> deletePeople(Integer id) {
            return People.delete("id", id)
                    .onItem().transform(rows -> {
                        return rows > 0 ? Response.noContent().build()
                                        : Response.status(Status.NOT_FOUND).build();
                    })
                    .onFailure().recoverWithItem(Response.serverError().build());
        }
    
}