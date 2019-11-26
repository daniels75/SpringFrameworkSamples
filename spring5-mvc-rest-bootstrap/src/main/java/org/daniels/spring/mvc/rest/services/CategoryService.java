package org.daniels.spring.mvc.rest.services;


import org.daniels.spring.mvc.rest.api.v1.model.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getAllCategories();

    CategoryDTO getCategoryByName(String name);
}
