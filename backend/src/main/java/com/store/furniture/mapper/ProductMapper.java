package com.store.furniture.mapper;

import com.store.furniture.dto.request.CustomerCreationRequest;
import com.store.furniture.dto.request.CustomerUpdateRequest;
import com.store.furniture.dto.request.ProductCreationRequest;
import com.store.furniture.dto.request.ProductUpdateRequest;
import com.store.furniture.dto.response.CustomerResponse;
import com.store.furniture.dto.response.ProductResponse;
import com.store.furniture.entity.Customer;
import com.store.furniture.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper {


    Product toProduct(ProductCreationRequest productCreationRequest);

    void updateProduct(@MappingTarget Product product , ProductUpdateRequest productUpdateRequest);

    ProductResponse toProductResponse(Product product);
}
