package org.acme;

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
import javax.json.JsonObject;

import org.jboss.logging.Logger;

import io.smallrye.mutiny.Uni;

@Path("/packages")
@Produces(MediaType.APPLICATION_JSON)
public class PackageResource {

    // membuat log agar debugging lebih mudah
    private static final Logger LOG = Logger.getLogger(PackageResource.class);

    @GET
    public Uni<Response> get() {
        LOG.info("Request GET Method for Package");
        // agan mengembalikan semua item yang ada di Package entity
        // itemnya akan tercetak dengan status ok
        // bila ada kesalahan maka mengembalikan server error (500)
        return Package.listAll()
                .onItem().transform(row -> Response.ok(row).build())
                .onFailure().recoverWithItem(Response.serverError().build());
    }

    @POST
    public Uni<Response> addGithub(JsonObject requestBody) {
        LOG.info("Request POST " + requestBody + " to database");

        Package pkg = new Package();

        // menggunakan Package Find akar mengetahui bila nama yang akan dimasuki
        // ke database sudah ada atau belum, bila sudah ada maka membuat exception yang
        // nanti akan ditangkap oleh onFailure bila tidak maka akan lanjut menambahkan
        // request ke database
        return Package.find("name", requestBody.getString("Name")).firstResult()
            .onItem().ifNotNull().failWith(() -> new NotFoundException("Package not found in database"))
            .onItem().transformToUni( x -> {
                pkg.setName(requestBody.getString("Name"));
                pkg.setArch(requestBody.getString("Architecture"));
                pkg.setDesc(requestBody.getString("Description"));
                pkg.setUrl(requestBody.getString("URL"));
                pkg.setMaintainer(requestBody.getString("Maintainer"));
                pkg.setLicense(requestBody.getString("License"));
                return pkg.persistAndFlush();
            })
            .onItem().transform(rows -> Response.ok("ok").build())
            .onFailure().recoverWithItem(Response.serverError().build());
    }


    @PUT
    @Path("{name}")
    public Uni<Response> putGithub(@PathParam("name") String name, JsonObject requestBody) {
        LOG.info("Request PUT " + requestBody + " to database");

        Package pkg = new Package();

        // hampir sama dengan method POST tetapi bedanya method ini mengecek
        // apakah nama tidak ada dalam database, bila tidak ada kita tidak dapat
        // mengubahnya dan akan mengirimkan bad request atau 400 ke client
        return Package.find("name", requestBody.getString("Name")).firstResult()
            .onItem().ifNull().failWith(() -> new NotFoundException("Package not found in database"))
            .onItem().transformToUni( x -> {
                pkg.setName(requestBody.getString("Name"));
                pkg.setArch(requestBody.getString("Architecture"));
                pkg.setDesc(requestBody.getString("Description"));
                pkg.setUrl(requestBody.getString("URL"));
                pkg.setMaintainer(requestBody.getString("Maintainer"));
                pkg.setLicense(requestBody.getString("License"));
                return pkg.persistAndFlush();
            })
            .onItem().transform(rows -> Response.ok("ok").build())
            .onFailure().recoverWithItem(Response.status(Status.BAD_REQUEST).build());
    }

    @DELETE
    @Path("{name}")
    public Uni<Response> deleteGithub(String name) {
        LOG.info("Request DELETE to Package " + name);
    
        // menghapus dengan nama bila terjadi error maka akan
        // tertangkap oleh onFailure dan memberikan kode 400 bad request
        return Package.delete("name", name)
                .onItem().transform(rows -> {
                    return rows > 0 ? Response.noContent().build()
                                    : Response.status(Status.NOT_FOUND).build();
                })
                .onFailure().recoverWithItem(Response.status(Status.BAD_REQUEST).build());
    }

}
