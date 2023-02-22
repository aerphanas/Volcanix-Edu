package org.acme;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.oracle.svm.core.annotate.Delete;


@Path("/hello")
public class GreetingResource {

    public static enum Order{
        desc, asc;
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(
        @Context UriInfo uriInfo,
        @QueryParam("order") Order order,
        @HeaderParam("authorization") String authorization
    ) {
        return String.format("URI: %s - Order %s - Authorization: %s \n",
                                uriInfo.getAbsolutePath(), order, authorization);
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void create(String message) {
        System.out.println("create");
    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String update(String message) {
        System.out.println("update");
        return message;
    }

    @Delete
    public void delete() {
        System.out.println("delete");
    }
}