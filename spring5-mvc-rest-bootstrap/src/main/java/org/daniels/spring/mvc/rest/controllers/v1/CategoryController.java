package org.daniels.spring.mvc.rest.controllers.v1;

import org.daniels.spring.mvc.rest.api.v1.model.CategoryDTO;
import org.daniels.spring.mvc.rest.api.v1.model.CatorgoryListDTO;
import org.daniels.spring.mvc.rest.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(CategoryController.BASE_URL)
public class CategoryController {

    public static final String BASE_URL = "/api/v1/categories";

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

/* old approach with @Controller annotation
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
*/
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CatorgoryListDTO getallCatetories(){
        return new CatorgoryListDTO(categoryService.getAllCategories());
    }

    @GetMapping("{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategoryByName(@PathVariable String name){
        return categoryService.getCategoryByName(name);
    }

}
