package com.store.furniture.controller;

import com.store.furniture.dto.ApiResponse;
import com.store.furniture.dto.request.CustomerCreationRequest;
import com.store.furniture.dto.request.CustomerUpdateRequest;
import com.store.furniture.dto.response.CustomerResponse;
import com.store.furniture.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping
    ApiResponse<CustomerResponse> createCustomer(@RequestBody CustomerCreationRequest customerCreationRequest) {
        var customer = customerService.createCustomer(customerCreationRequest);
        return ApiResponse.<CustomerResponse>builder().data(customer).build();
    }

    @GetMapping
    ApiResponse<List<CustomerResponse>> getAllCustomers() {
        var customers = customerService.getAllCustomers();
        return ApiResponse.<List<CustomerResponse>>builder().data(customers).build();
    }

    @GetMapping("/{id}")
    ApiResponse<CustomerResponse> getCustomerById(@PathVariable String id) {
        var customer = customerService.getCustomerById(id);
        return ApiResponse.<CustomerResponse>builder().data(customer).build();
    }

    @PutMapping("/{id}")
    ApiResponse<CustomerResponse> updateCustomer(@PathVariable String id, @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        var customer = customerService.updateCustomer(id, customerUpdateRequest);
        return ApiResponse.<CustomerResponse>builder().data(customer).build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<CustomerResponse> deleteCustomer(@PathVariable String id) {
        customerService.deleteCustomer(id);
        return ApiResponse.<CustomerResponse>builder().build();
    }
}
