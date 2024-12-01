package com.store.furniture.repository;

import com.store.furniture.entity.PasswordResetOtp;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<PasswordResetOtp, Long> {
    Optional<PasswordResetOtp> findByEmailAndOtp(String email, String otp);
}
