package org.acme.Controler;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.acme.Service.PanenService;

import io.smallrye.mutiny.Uni;
import io.vertx.core.json.JsonObject;

@Path("/panen")
@Produces(MediaType.APPLICATION_JSON)
public class PanenResource {
  
  @Inject
  PanenService panen;

  @GET
  public Uni<Response> getAll() {
    return panen.getKebunAll();
  }

  @POST
  public Uni<Response> posPanen(JsonObject requestBody) {
    return panen.postHasilKebun(requestBody);
  }

  @PUT
  @Path("{date}")
  public Uni<Response> putPanen(String date, JsonObject requestBody) {
    return panen.putHasilKebun(date, requestBody);
  }

  @DELETE
  @Path("{date}")
  public Uni<Response> delPanen(String date) {
    return panen.deleteHasilKebun(date);
  }

}
