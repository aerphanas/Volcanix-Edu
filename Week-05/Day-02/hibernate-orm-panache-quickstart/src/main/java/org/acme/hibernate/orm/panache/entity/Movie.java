package org.acme.hibernate.orm.panache.entity;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Cacheable
@Table(name = "movie", schema="movies")
public class Movie extends PanacheEntityBase {

    @Id
    @Column(name = "title")
    public String name;

    public Movie() {
    }

    public Movie(String name) {
        this.name = name;
    }
}
