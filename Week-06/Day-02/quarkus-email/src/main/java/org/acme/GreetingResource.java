package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

@Path("/hello")
public class GreetingResource {

    @Inject
    Mailer mailer;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public void hello() {
        mailer.send(Mail.withText("unprofesional2000@gmail.com", "mailer test", "mailer test"));
    }
}