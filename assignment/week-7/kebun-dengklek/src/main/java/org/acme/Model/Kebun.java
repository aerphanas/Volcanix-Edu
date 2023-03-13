package org.acme.Model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

@Entity
@Table(name = "hasil", schema = "kebun")
public class Kebun extends PanacheEntityBase {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
  @Column(name = "id")
  private String id;

  @Column(name = "komoditas")
  private String komoditas;

  @Column(name = "total")
  private Integer total;

  @Column(name = "created")
  private Date created;

  @Column(name = "updated")
  private Date updated;

  public Kebun() {
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getKomoditas() {
    return komoditas;
  }

  public void setKomoditas(String komoditas) {
    this.komoditas = komoditas;
  }

  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public Date getCreatedAt() {
    return created;
  }

  public void setCreatedAt(Date created) {
    this.created = created;
  }

  public Date getUpdatedAt() {
    return updated;
  }

  public void setUpdatedAt(Date updated) {
    this.updated = updated;
  }

  private Kebun(KebunBuilder i) {
    this.id = i.id;
    this.komoditas = i.komoditas;
    this.total = i.total;
    this.created = i.created;
    this.updated = i.updated;
  }

  public static class KebunBuilder {
    private String id;
    private String komoditas;
    private Integer total;
    private Date created;
    private Date updated;

    public KebunBuilder addId(String id) {
      this.id = id;
      return this;
    }
    public KebunBuilder addKomoditas(String komoditas) {
      this.komoditas = komoditas;
      return this;
    }
    public KebunBuilder addTotal(Integer total) {
      this.total = total;
      return this;
    }
    public KebunBuilder addCreatedAt(Date created) {
      this.created = created;
      return this;
    }
    public KebunBuilder addUpdatedAt(Date updated) {
      this.updated = updated;
      return this;
    }
    public Kebun build(){
      return new Kebun(this);
    }
  }
}
