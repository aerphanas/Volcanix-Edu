package org.acme;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;



@Path("/peps")
@Produces(MediaType.APPLICATION_JSON)
public class PeopleResources {

    People individu = new People();

    @GET
    public Response getAll() {
        return Response.ok(People.listAll()).build();
    }

    @GET
    @Path("/id")
    public Response getId(String requestBody) {
        JsonObject json = Json.createReader(new StringReader(requestBody)).readObject();

        Integer id = json.getInt("id"); 

        return Response.ok(People.findById(id)).build();
    }

    @POST
    @Transactional
    public Response addPeople(String requestBody) {
        JsonObject json = Json.createReader(new StringReader(requestBody)).readObject();

        String name = json.getString("name");
        Integer age = json.getInt("age");
        Integer id = json.getInt("id");

        individu.setName(name);
        individu.setUmur(age);
        individu.setId(id);
        individu.persist();

        return Response.ok("ok").build();
    }

    @PUT
    @Transactional
    public Response putPeople(String requertBody) {
        JsonObject json = Json.createReader(new StringReader(requertBody)).readObject();

        String name = json.getString("name");
        Integer age = json.getInt("age");
        Integer id = json.getInt("id");

        People.deleteById(id);
        individu.setName(name);
        individu.setUmur(age);
        individu.setId(id);
        individu.persist();

        return Response.ok().build();
    }

    @DELETE
    @Transactional
    public Response deletePeople(String requestBody) {
        JsonObject json = Json.createReader(new StringReader(requestBody)).readObject();

        Integer id = json.getInt("id");

        People.deleteById(id);

        return Response.ok().build();
    }
}
