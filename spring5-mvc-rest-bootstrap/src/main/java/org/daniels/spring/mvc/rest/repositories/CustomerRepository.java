package org.daniels.spring.mvc.rest.repositories;


import org.daniels.spring.mvc.rest.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
