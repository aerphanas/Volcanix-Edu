package org.acme;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/task")
public class TaskResource {

    @GET
    public List<Task> listAll() {
        return Task.listAll();
    }
}