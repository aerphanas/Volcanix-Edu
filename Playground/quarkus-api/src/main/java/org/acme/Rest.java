package org.acme;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Rest {

    private List<String> list = Collections.synchronizedList(new ArrayList<>());

    @GET
    public List<People> allList() {
        List<People> response = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            People item = new People(i + 1, list.get(i));
            response.add(item);
        }
        return response;
    }
    
    

    @POST
    public Response addList(String requestBody) {
        JsonObject jsonObject = Json.createReader(new StringReader(requestBody)).readObject();
        String name = jsonObject.getString("name");
        list.add(name);
        return Response.status(201).build();
    }
    

    @PUT
    @Path("/{id}")
    public String updateList(@PathParam("id") int id, String name) {
        if (id >= list.size()) {
            return "Index out of range";
        }
        list.set(id, name);
        return "OK";
    }

    @DELETE
    @Path("/{id}")
    public String deleteList(@PathParam("id") int id) {
        Optional<String> removed = Optional.ofNullable(list.remove(id));
        return removed.isPresent() ? "OK" : "Index out of range";
    }
}
