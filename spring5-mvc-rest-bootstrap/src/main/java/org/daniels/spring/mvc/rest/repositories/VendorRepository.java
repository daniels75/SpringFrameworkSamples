package org.daniels.spring.mvc.rest.repositories;


import org.daniels.spring.mvc.rest.domain.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
}
