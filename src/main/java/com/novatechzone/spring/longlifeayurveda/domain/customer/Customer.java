package com.novatechzone.spring.longlifeayurveda.domain.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "full_name", length = 150)
    private String fullName;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "password", length = 25)
    private String password;

    @Column(name = "mobile",unique = true, length = 12)
    private String mobile;

    @Column(name = "verification_code", length = 6)
    private String verificationCode;

    @Column(name = "status")
    private Integer status =0;

    @Column(name = "image_path",columnDefinition = "TEXT")
    private String imagePath;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

}
