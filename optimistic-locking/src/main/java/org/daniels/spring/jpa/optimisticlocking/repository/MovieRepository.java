package org.daniels.spring.jpa.optimisticlocking.repository;

import org.daniels.spring.jpa.optimisticlocking.domain.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {

}
