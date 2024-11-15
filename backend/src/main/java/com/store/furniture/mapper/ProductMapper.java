package com.store.furniture.mapper;

import com.store.furniture.dto.request.ProductCreationRequest;
import com.store.furniture.dto.request.ProductUpdateRequest;
import com.store.furniture.dto.response.ProductResponse;
import com.store.furniture.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(target = "image", ignore = true)
    Product toProduct(ProductCreationRequest productCreationRequest);

    @Mapping(source = "categoryId", target = "category.id")
//    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(target = "image", ignore = true)
    void updateProduct(@MappingTarget Product product , ProductUpdateRequest productUpdateRequest);

    @Mapping(source = "category.id", target = "categoryId")
    ProductResponse toProductResponse(Product product);
}
