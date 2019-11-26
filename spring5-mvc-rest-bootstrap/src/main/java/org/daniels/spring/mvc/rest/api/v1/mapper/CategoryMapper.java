package org.daniels.spring.mvc.rest.api.v1.mapper;

import org.daniels.spring.mvc.rest.api.v1.model.CategoryDTO;
import org.daniels.spring.mvc.rest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);


    CategoryDTO categoryToCategoryDTO(Category category);
}
