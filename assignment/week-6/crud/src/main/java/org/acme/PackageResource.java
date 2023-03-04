package org.acme;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
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
    public Uni<Response> getPackage() {
        LOG.info("Request GET Method for Package");
        // akan mengembalikan semua item yang ada di Package entity
        // itemnya akan tercetak dengan status ok
        // bila ada kesalahan maka mengembalikan server error (500)
        return Package.listAll()
                .onItem().transform(rows -> Response.ok(rows).build())
                .onFailure().recoverWithItem(Response.serverError().build());
    }

    @POST
    public Uni<Response> postPackage(JsonObject requestBody) {
        LOG.info("Request POST " + requestBody + " to database");

        String name, arch, desc, url, maintainer, license;

        // try catch agar bila terjadi error pada JsonObject
        // maka bisa ditangani dengan mengirim bad request ke client
        try {

            name = requestBody.getString("Name");
            arch = requestBody.getString("Architecture");
            desc = requestBody.getString("Description");
            url = requestBody.getString("URL");
            maintainer = requestBody.getString("Maintainer");
            license = requestBody.getString("License");

        } catch (Exception e) {
            LOG.error(e.getMessage());
            LOG.error("Maybe some object in Request Body is null ?");
            return Uni.createFrom().item(Response.status(Response.Status.BAD_REQUEST).build());
        }

        Package pkgsin = new Package.PackageBuilder()
                                    .setName(name)
                                    .setArch(arch)
                                    .setDesc(desc)
                                    .setUrl(url)
                                    .setMaintainer(maintainer)
                                    .setLicense(license)
                                    .build();
    
        // menggunakan Package Find akan mengetahui bila nama yang akan dimasuki
        // ke database sudah ada atau belum, bila sudah ada maka membuat exception yang
        // nanti akan ditangkap oleh onFailure bila tidak maka akan lanjut menambahkan
        // request ke database
        return Package.find("Name", name).firstResult()
                .onItem().ifNotNull().failWith(() -> new NotFoundException("Package not found in database"))
                .onItem().transformToUni(x -> pkgsin.persistAndFlush())
                .onItem().transform(rows -> Response.ok("ok").build())
                .onFailure().recoverWithItem(Response.serverError().build());
    }
    

    @PUT
    @Path("{name}")
    public Uni<Response> putPackage(String name, JsonObject requestBody) {
        LOG.info("Request PUT " + requestBody + " to database");

        String arch, desc, url, maintainer, license;
        
        try {

            arch = requestBody.getString("Architecture");
            desc = requestBody.getString("Description");
            url = requestBody.getString("URL");
            maintainer = requestBody.getString("Maintainer");
            license = requestBody.getString("License");

        } catch (Exception e) {
            LOG.error(e.getMessage());
            LOG.error("Maybe some object in Request Body is null ?");
            return Uni.createFrom().item(Response.status(Response.Status.BAD_REQUEST).build());
        }

        // hampir sama dengan method POST tetapi bedanya method ini mengecek
        // apakah nama tidak ada dalam database, bila tidak ada kita tidak dapat
        // mengubahnya dan akan mengirimkan bad request atau 400 ke client
        return Package.find("Name", requestBody.getString("Name")).firstResult()
            .onItem().ifNull().failWith(() -> new NotFoundException("Package not found in database"))
            .onItem().transformToUni( x -> {
                return Package.update(
                    "Name = ?1, Architecture = ?2, Description = ?3, URL = ?4, Maintainer = ?5, License = ?6 where Name = ?7"
                    , name, arch, desc, url, maintainer, license, name);
            })
            .onItem().transform(rows -> Response.ok("ok").build())
            .onFailure().recoverWithItem(Response.status(Status.BAD_REQUEST).build());
    }

    @DELETE
    @Path("{name}")
    public Uni<Response> deletePackage(String name) {
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
