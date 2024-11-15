package com.store.furniture.mapper;

import com.store.furniture.dto.request.CategoryCreationRequest;
import com.store.furniture.dto.request.CategoryUpdateRequest;
import com.store.furniture.dto.response.CategoryResponse;
import com.store.furniture.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryCreationRequest categoryCreationRequest);

    void updateCategory(@MappingTarget Category category, CategoryUpdateRequest categoryUpdateRequest);

    CategoryResponse toCategoryResponse(Category category);
}
