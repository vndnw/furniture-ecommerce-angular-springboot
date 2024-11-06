package com.store.furniture.mapper;

import com.store.furniture.dto.request.AdminCreationRequest;
import com.store.furniture.dto.request.AdminUpdateRequest;
import com.store.furniture.dto.request.CustomerCreationRequest;
import com.store.furniture.dto.request.CustomerUpdateRequest;
import com.store.furniture.dto.response.AdminResponse;
import com.store.furniture.dto.response.CustomerResponse;
import com.store.furniture.entity.Admin;
import com.store.furniture.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toCustomer(CustomerCreationRequest customerCreationRequest);

    void updateCustomer(@MappingTarget Customer customer , CustomerUpdateRequest customerUpdateRequest);
    CustomerResponse toCustomerResponse(Customer customer);
}
