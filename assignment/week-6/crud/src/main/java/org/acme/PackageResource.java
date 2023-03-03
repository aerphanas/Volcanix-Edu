package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.jboss.logging.Logger;

import io.smallrye.mutiny.Uni;

@Path("/packages")
@Produces(MediaType.APPLICATION_JSON)
public class PackageResource {

    private static final Logger LOG = Logger.getLogger(PackageResource.class);

    @GET
    public Uni<Response> get() {
        LOG.info("Request GET Method");
        return Package.listAll()
                .onItem().transform(row -> Response.ok(row).build())
                .onFailure().recoverWithItem(Response.serverError().build());
    }

}
