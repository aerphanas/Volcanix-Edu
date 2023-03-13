package org.acme.Service;

import org.acme.Controler.PanenResource;
import org.acme.Model.Kebun;

import java.sql.Date;
import java.time.LocalDate;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.jboss.logging.Logger;

import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;

@ApplicationScoped
public class PanenService {

  private static final Logger LOG = Logger.getLogger(PanenResource.class);

  // for get method
  public Uni<Response> getKebunAll() {

    LOG.info("get all hasil kebun");
    return Kebun.listAll()
            .onItem().transform( x -> {
              return Response.ok(x).build();
            })
            .onFailure().recoverWithItem(Response.serverError().build());

  }

  // for post method
  public Uni<Response> postHasilKebun(JsonObject requestBody) {

    String komoditas;
    Integer total;
    // mendapatkan tanggal sekarang dan 
    // parsing ke format date untuk sql
    LocalDate localDate = LocalDate.now();
    Date sqlDate = Date.valueOf(localDate);

    
    try {
      komoditas = requestBody.getString("komoditas");
      total = requestBody.getInteger("total");
    } catch (Exception e) {
      return Uni.createFrom().item(
        Response.status(Response.Status.BAD_REQUEST).build());
      }

    LOG.info("POST : komoditas -> " +
              komoditas +
              " total -> " +
              total +
              " date -> " +
              sqlDate);

    Kebun inputKebun = new Kebun.KebunBuilder()
                                .addKomoditas(komoditas)
                                .addTotal(total)
                                .addCreated(sqlDate)
                                .addUpdated(sqlDate)
                                .build();

    return Kebun.find("created", sqlDate).firstResult()
                                .onItem().ifNotNull().failWith(() -> new NotFoundException("Package not found in database"))
                                .onItem().transformToUni(x -> {
                                    LOG.info("POST Ok");
                                    return inputKebun.persistAndFlush();
                                })
                                .onItem().transform(rows -> Response.ok("ok").build())
                                .onFailure().recoverWithItem(Response.status(Response.Status.BAD_REQUEST).build());

  }

  // for Put Method
  public Uni<Response> putHasilKebun(String date, JsonObject requestBody) {

    String komoditas;
    Integer total;
    // mendapatkan tanggal sekarang dan 
    // parsing ke format date untuk sql
    LocalDate localDate = LocalDate.now();
    Date sqlDate = Date.valueOf(localDate);
    Date created = Date.valueOf(date);

    
    try {
      komoditas = requestBody.getString("komoditas");
      total = requestBody.getInteger("total");
    } catch (Exception e) {
      return Uni.createFrom().item(
        Response.status(Response.Status.BAD_REQUEST).build());
      }

    LOG.info("PUT : komoditas -> " +
              komoditas +
              " total -> " +
              total +
              " date input -> " +
              date +
              " current date -> " +
              sqlDate);
    LOG.info(Kebun.find("created", created));

    return Kebun.find("created", created).firstResult()
                                .onItem().ifNull().failWith( () -> new NotFoundException("not found in database"))
                                .onItem().transformToUni( x -> {
                                    LOG.info("PUT Ok");
                                    return Kebun.update( "komoditas = ?1, total = ?2, updated = ?3  where created = ?4"
                                                          , komoditas, total, sqlDate, created);
                                })
                                .onItem().transform(rows -> Response.ok("ok").build())
                                .onFailure().recoverWithItem(Response.status(Status.BAD_REQUEST).build());

  }

  // for delete method
  public Uni<Response> deleteHasilKebun (String date) {

    // parsing ke Date format
    Date created = Date.valueOf(date);
    LOG.info("DELETE " + created);

    // menghapus dengan tanggal dibuat
    return Kebun.delete("created", created)
                    .onItem().transform(rows -> {
                        LOG.info("DELETE Ok");
                        return rows > 0 ? Response.noContent().build()
                                        : Response.status(Status.NOT_FOUND).build();
                    })
                    .onFailure().recoverWithItem(Response.status(Status.BAD_REQUEST).build());

    }

}
