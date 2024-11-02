package com.store.furniture.repository;

import com.store.furniture.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
