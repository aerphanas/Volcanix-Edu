package org.acme.Controler;

import org.acme.Service.PackageService;
import org.acme.Util.RegexSafety;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import javax.inject.Inject;
import javax.json.JsonObject;

import io.smallrye.mutiny.Uni;

@Path("/packages")
@Produces(MediaType.APPLICATION_JSON)
public class PackageResource {

    @Inject
    PackageService pkgService;

    @GET
    public Uni<Response> getPackage() {
        return pkgService.packageList();
    }

    @POST
    public Uni<Response> postPackage(JsonObject requestBody) {
        return pkgService.packagePost(requestBody);
    }

    @PUT
    @Path("{name}")
    public Uni<Response> putPackage(String name, JsonObject requestBody) {
        return pkgService.packagePut(name, requestBody);
    }

    @DELETE
    @Path("{name}")
    public Uni<Response> deletePackage(String name) {
        return pkgService.packageDelete(name);
    }

}
