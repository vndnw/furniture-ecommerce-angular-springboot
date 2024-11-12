package com.store.furniture.service;

import com.store.furniture.dto.request.CategoryCreationRequest;
import com.store.furniture.dto.request.CategoryUpdateRequest;
import com.store.furniture.dto.response.CategoryResponse;
import com.store.furniture.exception.AppException;
import com.store.furniture.exception.ErrorCode;
import com.store.furniture.mapper.CategoryMapper;
import com.store.furniture.repository.CategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;

    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse createCategory(CategoryCreationRequest categoryCreationRequest) {
        var category = categoryMapper.toCategory(categoryCreationRequest);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }

    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toCategoryResponse).toList();
    }
    public CategoryResponse getCategoryById(Long id) {
        var category = categoryRepository.findById(id)
                 .orElseThrow(
                 () -> new AppException(ErrorCode.USERNAME_EXISTS)
         );
         return categoryMapper.toCategoryResponse(category);
    }
    @PreAuthorize("hasRole('ADMIN')")
    public CategoryResponse updateCategory(Long id, CategoryUpdateRequest customerUpdateRequest) {
        var category = categoryRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTS)
        );

        categoryMapper.updateCategory(category, customerUpdateRequest);
        return categoryMapper.toCategoryResponse(categoryRepository.save(category));
    }
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
