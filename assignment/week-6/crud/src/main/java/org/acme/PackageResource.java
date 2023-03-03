package org.acme;

import javax.json.JsonObject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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

    @POST
    public Uni<Response> addGithub(JsonObject requestBody) {

        LOG.info("Request POST Method with " + requestBody);

        Package pkg = new Package();
        pkg.setName(requestBody.getString("Name"));
        pkg.setArch(requestBody.getString("Architecture"));
        pkg.setDesc(requestBody.getString("Description"));
        pkg.setUrl(requestBody.getString("URL"));
        pkg.setMaintainer(requestBody.getString("Maintainer"));
        pkg.setLicense(requestBody.getString("License"));


        return pkg.persistAndFlush()
                .onItem().transform(rows -> Response.ok("ok").build())
                .onFailure().recoverWithItem(Response.serverError().build());
    }

}
