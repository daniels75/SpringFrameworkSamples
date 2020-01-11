package org.daniels.spring.jpa.optimisticlocking.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "movie")
public class Movie implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Version
  private Integer version;

  private String title;

  private Integer rating;


  public Movie() {
  }

  public Movie(String title, Integer rating) {
    this.title = title;
    this.rating = rating;
  }

  public Long getId() {
    return id;
  }

  public Movie setId(Long id) {
    this.id = id;
    return this;
  }

  public Integer getVersion() {
    return version;
  }

  public Movie setVersion(Integer version) {
    this.version = version;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public Movie setTitle(String title) {
    this.title = title;
    return this;
  }

  public Integer getRating() {
    return rating;
  }

  public Movie setRating(Integer rating) {
    this.rating = rating;
    return this;
  }
}
