package com.store.furniture.configuration;

import com.store.furniture.entity.User;
import com.store.furniture.enums.Role;
import com.store.furniture.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Slf4j
@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ApplicationInitConfig {
     PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository adminRepository) {
        return args -> {

            if (adminRepository.findByUsername("admin").isEmpty()) {

                User user = User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .role(Role.ADMIN.name())
                        .build();
                adminRepository.save(user);
                log.info("ADMIN has been created with default password 'admin'");
            }
        };
    }
}
