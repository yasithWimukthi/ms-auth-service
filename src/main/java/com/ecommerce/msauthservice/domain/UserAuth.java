package com.ecommerce.msauthservice.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="users_auth", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class UserAuth {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private String email;

    @Column(nullable=false)
    private String password;

    @Column(nullable=false)
    private String role; // e.g., USER, ADMIN
}