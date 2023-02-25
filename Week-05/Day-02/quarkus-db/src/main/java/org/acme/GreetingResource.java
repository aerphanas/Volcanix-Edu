package org.acme;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.reactive.RestCookie;
import org.jboss.resteasy.reactive.RestForm;
import org.jboss.resteasy.reactive.RestHeader;
import org.jboss.resteasy.reactive.RestMatrix;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import io.agroal.api.AgroalDataSource;

@Path("/hello")
public class GreetingResource {

    @Inject
    AgroalDataSource dataSource;

    @GET
    @Path("/db")
    @Produces(MediaType.TEXT_PLAIN)
    public String getData() {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM movies.movie LIMIT 1");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String data = rs.getString(2);
                return data;
            }
        } catch (SQLException e) {
            return "Error 1";
        }
        return "Error 2";
    }

    @GET
    @Path("/db/{num}")
    public String name(Integer num) {
        try (Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM movies.movie LIMIT 1");
        ResultSet rs = ps.executeQuery()) {
        while (rs.next()) {
            String data = rs.getString(num);
            return data;
        }
        } catch (SQLException e) {
            return "Error 1";
        }
        return "";
    }

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("{name}")
    public String hello(String name){
        return "hello " + name;
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void create(String message) {
        System.out.println("create");
    }

    @PUT
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String update(String message) {
        System.out.println("update");
        return message;
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    public String delete() {
        return "deleted";
    }
}

@Path("/test/{type}")
class Test{
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello(){
        return "Test";
    }

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String allParams(@RestPath String type,
                            @RestMatrix String variant,
                            @RestQuery String age,
                            @RestCookie String level,
                            @RestHeader("X-Cheese-Secret-Handshake")
                            String secretHandshake,
                            @RestForm String smell) {
        return type + "/" + variant + "/" + age + "/" + level + "/"
            + secretHandshake + "/" + smell;
    }
}

@Path("/calc")
class Calcullate {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String wellcome() {
        return "wellcome to calculator";
    }
    
}