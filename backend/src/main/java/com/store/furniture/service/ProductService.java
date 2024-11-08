package com.store.furniture.service;

import com.store.furniture.dto.request.ProductCreationRequest;
import com.store.furniture.dto.request.ProductUpdateRequest;
import com.store.furniture.dto.response.ProductResponse;
import com.store.furniture.entity.Product;
import com.store.furniture.exception.AppException;
import com.store.furniture.exception.ErrorCode;
import com.store.furniture.mapper.ProductMapper;
import com.store.furniture.repository.CategoryRepository;
import com.store.furniture.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;
    ProductMapper productMapper;
    CategoryRepository categoryRepository;
    CloudinaryService cloudinaryService;


    public ProductResponse createProduct(ProductCreationRequest productCreationRequest) {
        categoryRepository.findById(productCreationRequest.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
        Product product = productMapper.toProduct(productCreationRequest);
        var urlImg = cloudinaryService.uploadImage(productCreationRequest.getImage());
        product.setImage(urlImg);
        return productMapper.toProductResponse(productRepository.save(product));
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toProductResponse).toList();
    }
    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id)
                 .orElseThrow(
                 () -> new AppException(ErrorCode.USERNAME_EXISTS)
         );
         return productMapper.toProductResponse(product);
    }

    public ProductResponse updateProduct(String id, ProductUpdateRequest productUpdateRequest) {
        var product = productRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTS)
        );

        categoryRepository.findById(productUpdateRequest.getCategoryId())
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        productMapper.updateProduct(product, productUpdateRequest);

        return productMapper.toProductResponse(productRepository.save(product));
    }
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
    }
}
