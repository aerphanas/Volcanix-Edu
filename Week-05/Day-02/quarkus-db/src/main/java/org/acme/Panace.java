package org.acme;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.ws.rs.Path;

import org.jboss.resteasy.reactive.RestResponse.Status;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

@Entity
class Person extends PanacheEntity {
    public String name;
    public LocalDate birth;
    public Status status;

    public Person() {
    }

    // return name as uppercase in the model
    public String getName(){
        return name.toUpperCase();
    }

    // store all names in lowercase in the DB
    public void setName(String name){
        this.name = name.toLowerCase();
    }

    public LocalDate getBirth() {
        return birth;
    }

    public void setBirth(LocalDate birth) {
        this.birth = birth;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}

@Path("/panace")
public class Panace {
    Person human = new Person();
}
