package org.daniels.spring.mvc.rest.spring5mvcrest.api.v1.mapper;

import org.daniels.spring.mvc.rest.api.v1.mapper.CategoryMapper;
import org.daniels.spring.mvc.rest.api.v1.model.CategoryDTO;
import org.daniels.spring.mvc.rest.domain.Category;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryMapperTest {

    public static final String NAME = "Joe";
    public static final long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @Test
    public void categoryToCategoryDTO() throws Exception {

        //given
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        //when
        CategoryDTO categoryDTO = categoryMapper.categoryToCategoryDTO(category);

        //then
        assertEquals(Long.valueOf(ID), categoryDTO.getId());
        assertEquals(NAME, categoryDTO.getName());
    }

}
