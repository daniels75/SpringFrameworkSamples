package org.daniels.sample.jpa.repositories;

import org.daniels.sample.jpa.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {
}
