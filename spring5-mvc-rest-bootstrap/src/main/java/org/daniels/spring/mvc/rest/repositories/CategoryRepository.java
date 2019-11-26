package org.daniels.spring.mvc.rest.repositories;

import org.daniels.spring.mvc.rest.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jt on 9/24/17.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
