package com.ecommerce.msauthservice.repository;

import com.ecommerce.msauthservice.domain.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {
    Optional<UserAuth> findByEmail(String email);
    boolean existsByEmail(String email);
}
