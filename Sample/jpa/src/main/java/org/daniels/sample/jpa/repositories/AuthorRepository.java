package org.daniels.sample.jpa.repositories;

import org.daniels.sample.jpa.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
