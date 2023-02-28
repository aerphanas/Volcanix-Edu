package org.acme;

import java.io.StringReader;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GreetingResource {

    private ArrayList<People> list = new ArrayList<>();

    @GET
    public Response getList() {
        return Response.ok(list).status(200).build();
    }

    @POST
    public Response addList(String requestBody) {

        StringReader input = new StringReader(requestBody);
        JsonObject json = Json.createReader(input).readObject();

        String name = json.getString("name");
        Integer id = json.getInt("id");

        People peeps = new People(id, name);
        list.add(peeps);
        
        return Response.ok("{\"status\":\"ok\"}").status(201).build();
    }

    @PUT
    public Response editList(String requestBody) {

        StringReader input = new StringReader(requestBody);
        JsonObject json = Json.createReader(input).readObject();

        String name = json.getString("name");
        Integer id = json.getInt("id");


        for (People p : list) {
            if (p.getId() == id) {
                p.setName(name);
                return Response.ok("{\"status\":\"ok\"}").status(200).build();
            }
        }

        return Response.noContent().status(404).build();
    }

    @DELETE
    public Response deleteList(String requestBody) {

        StringReader input = new StringReader(requestBody);
        JsonObject json = Json.createReader(input).readObject();

        Integer id = json.getInt("id");

        for ( People p : list) {
            if (p.getId() == id) {
                list.remove(p);
                return Response.ok("{\"status\":\"ok\"}").status(200).build();
            }
        }

        return Response.noContent().status(404).build();
    }

}