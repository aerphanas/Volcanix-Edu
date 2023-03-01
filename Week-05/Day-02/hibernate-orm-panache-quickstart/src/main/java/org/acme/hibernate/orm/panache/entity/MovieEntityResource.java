package org.acme.hibernate.orm.panache.entity;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.oracle.svm.core.annotate.Inject;

import io.quarkus.panache.common.Sort;

@Path("/movie")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MovieEntityResource {

    @Inject
    Movie entity;

    @GET
    public Response get() {
        return Response.ok(
            Movie.listAll(Sort.by("movie_id"))
        ).build();
    }

    @GET
    @Path("{id}")
    public Response getSingle(Integer id) {
        return Response.ok(
            Movie.findById(id)
        ).build();
    }

    @GET
    @Path("/count")
    public Response count() {
        return Response.ok(Movie.count()).build();
    }

    @POST
    @Transactional
    public Response create(Movie fruit) {
        if (fruit.id != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        fruit.persist();
        return Response.ok(fruit).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Movie update(Long id, Movie fruit) {
        if (fruit.id == null) {
            throw new WebApplicationException("Fruit Name was not set on request.", 422);
        }

        Movie entity = Movie.findById(id);

        if (entity == null) {
            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
        }

        entity.id = fruit.id;

        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(Long id) {
        Movie entity = Movie.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
        }
        entity.delete();
        return Response.status(204).build();
    }

}
