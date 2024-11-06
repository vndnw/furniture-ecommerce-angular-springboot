package com.store.furniture.repository;

import com.store.furniture.entity.Admin;
import com.store.furniture.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
