package org.acme;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/devel")
public class DeveloperResource {
    
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createDevel(Developer devel){
        devel.persist();
        return Response.created(
            UriBuilder.fromResource(DeveloperResource.class)
                      .path(Long.toString(devel.getId()))
                      .build()
        )
        .entity(devel)
        .build();
    }
}
