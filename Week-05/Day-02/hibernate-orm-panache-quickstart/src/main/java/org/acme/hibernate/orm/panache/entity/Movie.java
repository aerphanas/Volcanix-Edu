package org.acme.hibernate.orm.panache.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "movie", schema="movies")
public class Movie extends PanacheEntityBase {

    @Id
    @Column(name = "movie_id")
    public Integer id;

    @Column(name = "title")
    public String title;

    @Column(name = "movie_status")
    public String status;

    public Movie() {
    }

    public Movie(Integer name) {
        this.id = name;
    }
}
