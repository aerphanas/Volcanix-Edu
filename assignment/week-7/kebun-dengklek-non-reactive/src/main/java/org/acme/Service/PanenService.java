package org.acme.Service;

import java.sql.Date;
import java.time.LocalDate;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonObject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.acme.Controler.PanenResource;
import org.acme.Model.Kebun;
import org.jboss.logging.Logger;

@ApplicationScoped
public class PanenService {
  
  private static final Logger LOG = Logger.getLogger(PanenResource.class);

  // get method
  public Response getKebunAll() {

    LOG.info("get all hasil kebun");
    return Response.ok(Kebun.listAll()).build();
  }

  // post method
  @Transactional
  public Response postHasilKebun(JsonObject requestBody) {

    String komoditas;
    Integer total;
    // mendapatkan tanggal sekarang dan 
    // parsing ke format date untuk sql
    LocalDate localDate = LocalDate.now();
    Date sqlDate = Date.valueOf(localDate);

    try {
      komoditas = requestBody.getString("komoditas");
      total = requestBody.getInt("total");
    } catch (Exception e) {
      return Response.status(Response.Status.BAD_REQUEST).build();
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
    
    if ( Kebun.find("created", sqlDate).firstResult() == null ) {
      LOG.info("POST Ok");
      inputKebun.persist();
      return Response.ok("ok").build();
    } else {
      return Response.status(Status.BAD_REQUEST).build();
    }
  }

  // put method
  @Transactional
  public Response putHasilKebun(String date, JsonObject requestBody) {
    String komoditas;
    Integer total;
    // mendapatkan tanggal sekarang dan 
    // parsing ke format date untuk sql
    LocalDate localDate = LocalDate.now();
    Date sqlDate = Date.valueOf(localDate);
    Date created = Date.valueOf(date);

    
    try {
      komoditas = requestBody.getString("komoditas");
      total = requestBody.getInt("total");
    } catch (Exception e) {
      return Response.status(Response.Status.BAD_REQUEST).build();
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

    if ( Kebun.find("created", created).firstResult() != null ) {
      Kebun.update( "komoditas = ?1, total = ?2, updated = ?3  where created = ?4"
                  , komoditas, total, sqlDate, created);
      return Response.ok("ok").build();
    } else {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
  }

  // delete method
  @Transactional
  public Response deleteHasilKebun (String date) {

    // parsing ke Date format
    Date created = Date.valueOf(date);
    LOG.info("DELETE " + created);

    if ( Kebun.delete("created", created) > 0 ) {
      LOG.info("DELETE Ok");
      return Response.noContent().build();
    } else {
      return Response.status(Status.NOT_FOUND).build();
    }
  }

}
