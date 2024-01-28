package com.novatechzone.spring.longlifeayurveda.domain.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByMobile(String mobile);
    Optional<Object> findByMobileAndPassword(String mobile, String password);
}
