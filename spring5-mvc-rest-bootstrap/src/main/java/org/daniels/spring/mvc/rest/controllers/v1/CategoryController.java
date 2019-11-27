package org.daniels.spring.mvc.rest.controllers.v1;

import org.daniels.spring.mvc.rest.api.v1.model.CategoryDTO;
import org.daniels.spring.mvc.rest.api.v1.model.CatorgoryListDTO;
import org.daniels.spring.mvc.rest.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/v1/categories/")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CatorgoryListDTO> getAllCategories() {
        return new ResponseEntity<>(
                new CatorgoryListDTO(categoryService.getAllCategories()), HttpStatus.OK);
    }

    @GetMapping(path = "{name}")
    public ResponseEntity<CategoryDTO> getCategoryByName(@PathVariable  final String name) {
        return  new ResponseEntity<>(
                categoryService.getCategoryByName(name), HttpStatus.OK
        );
    }

}
