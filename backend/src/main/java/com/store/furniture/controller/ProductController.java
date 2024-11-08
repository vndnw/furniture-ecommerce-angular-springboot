package com.store.furniture.controller;

import com.store.furniture.dto.ApiResponse;
import com.store.furniture.dto.request.ProductCreationRequest;
import com.store.furniture.dto.request.ProductUpdateRequest;
import com.store.furniture.dto.response.ProductResponse;
import com.store.furniture.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    ApiResponse<ProductResponse> createProduct(ProductCreationRequest productCreationRequest) {
        var product = productService.createProduct(productCreationRequest);
        return ApiResponse.<ProductResponse>builder().data(product).build();
    }

    @GetMapping
    ApiResponse<List<ProductResponse>> getAllProducts() {
        var products = productService.getAllProducts();
        return ApiResponse.<List<ProductResponse>>builder().data(products).build();
    }

    @GetMapping("/{id}")
    ApiResponse<ProductResponse> getProductById(@PathVariable String id) {
        var product = productService.getProductById(id);
        return ApiResponse.<ProductResponse>builder().data(product).build();
    }

    @PutMapping("{id}")
    ApiResponse<ProductResponse> updateProduct(@PathVariable String id, @RequestBody ProductUpdateRequest productUpdateRequest) {
        var product = productService.updateProduct(id, productUpdateRequest);
        return ApiResponse.<ProductResponse>builder().data(product).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<ProductResponse> deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
        return ApiResponse.<ProductResponse>builder().build();
    }
}
