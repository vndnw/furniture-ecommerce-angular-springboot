package com.store.furniture.controller;

import com.store.furniture.dto.ApiResponse;
import com.store.furniture.dto.request.ProductCreationRequest;
import com.store.furniture.dto.request.ProductUpdateRequest;
import com.store.furniture.dto.response.PaginatedResponse;
import com.store.furniture.dto.response.ProductResponse;
import com.store.furniture.service.ProductService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    ApiResponse<ProductResponse> createProduct(@Valid ProductCreationRequest productCreationRequest) {
        var product = productService.createProduct(productCreationRequest);
        return ApiResponse.<ProductResponse>builder().data(product).build();
    }

    @GetMapping
    ApiResponse<PaginatedResponse<ProductResponse>> getAllProducts(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        var products = productService.getAllProducts(page, size);
        return ApiResponse.<PaginatedResponse<ProductResponse>>builder()
                .data(products)
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<List<ProductResponse>> searchProducts(@RequestParam String keyword) {
        var products = productService.searchProducts(keyword);
        return ApiResponse.<List<ProductResponse>>builder().data(products).build();
    }

    @GetMapping("/{id}")
    ApiResponse<ProductResponse> getProductById(@PathVariable String id) {
        var product = productService.getProductById(id);
        return ApiResponse.<ProductResponse>builder().data(product).build();
    }

    @PutMapping("{id}")
    ApiResponse<ProductResponse> updateProduct(
            @PathVariable String id, @Valid ProductUpdateRequest productUpdateRequest) {
        var product = productService.updateProduct(id, productUpdateRequest);
        return ApiResponse.<ProductResponse>builder().data(product).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<ProductResponse> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ApiResponse.<ProductResponse>builder().build();
    }
}
