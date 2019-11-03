package org.daniels.sample.jpa.repositories;

import org.daniels.sample.jpa.model.Publisher;
import org.springframework.data.repository.CrudRepository;


public interface PublisherRepository extends CrudRepository<Publisher, Long> {
}
