package com.store.furniture.controller;

import com.store.furniture.dto.ApiResponse;
import com.store.furniture.dto.request.CategoryCreationRequest;
import com.store.furniture.dto.request.CategoryUpdateRequest;
import com.store.furniture.dto.response.CategoryResponse;
import com.store.furniture.service.CategoryService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    ApiResponse<CategoryResponse> createCategory(@RequestBody @Valid CategoryCreationRequest categoryCreationRequest) {
        var category = categoryService.createCategory(categoryCreationRequest);
        return ApiResponse.<CategoryResponse>builder().data(category).build();
    }

    @GetMapping
    ApiResponse<List<CategoryResponse>> getAllCategories() {
        var categories = categoryService.getAllCategories();
        return ApiResponse.<List<CategoryResponse>>builder().data(categories).build();
    }

    @GetMapping("/{id}")
    ApiResponse<CategoryResponse> getCategoryById(@PathVariable Long id) {
        var category = categoryService.getCategoryById(id);
        return ApiResponse.<CategoryResponse>builder().data(category).build();
    }

    @PutMapping("{id}")
    ApiResponse<CategoryResponse> updateCategory(
            @PathVariable Long id, @RequestBody @Valid CategoryUpdateRequest categoryUpdateRequest) {
        var category = categoryService.updateCategory(id, categoryUpdateRequest);
        return ApiResponse.<CategoryResponse>builder().data(category).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<CategoryResponse> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ApiResponse.<CategoryResponse>builder().build();
    }
}
