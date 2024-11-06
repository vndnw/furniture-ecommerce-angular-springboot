package com.store.furniture.service;

import com.store.furniture.dto.request.AdminCreationRequest;
import com.store.furniture.dto.request.AdminUpdateRequest;
import com.store.furniture.dto.request.CustomerCreationRequest;
import com.store.furniture.dto.request.CustomerUpdateRequest;
import com.store.furniture.dto.response.AdminResponse;
import com.store.furniture.dto.response.CustomerResponse;
import com.store.furniture.entity.Admin;
import com.store.furniture.entity.Customer;
import com.store.furniture.enums.Role;
import com.store.furniture.exception.AppException;
import com.store.furniture.exception.ErrorCode;
import com.store.furniture.mapper.AdminMapper;
import com.store.furniture.mapper.CustomerMapper;
import com.store.furniture.repository.AdminRepository;
import com.store.furniture.repository.CustomerRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CustomerService {
    CustomerRepository customerRepository;
    CustomerMapper customerMapper;


    public CustomerResponse createCustomer(CustomerCreationRequest customerCreationRequest) {
        if (customerRepository.existsByUsername(customerCreationRequest.getUsername()))
            throw new AppException(ErrorCode.USERNAME_EXISTS);

        Customer customer = customerMapper.toCustomer(customerCreationRequest);
        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }

    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream().map(customerMapper::toCustomerResponse).toList();
    }
    public CustomerResponse getCustomerById(String id) {
         Customer customer = customerRepository.findById(id)
                 .orElseThrow(
                 () -> new AppException(ErrorCode.USERNAME_EXISTS)
         );
         return customerMapper.toCustomerResponse(customer);
    }

    public CustomerResponse updateCustomer(String id, CustomerUpdateRequest customerUpdateRequest) {
        Customer customer = customerRepository.findById(id).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_EXISTS)
        );

        customerMapper.updateCustomer(customer, customerUpdateRequest);
        return customerMapper.toCustomerResponse(customerRepository.save(customer));
    }
    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }
}
