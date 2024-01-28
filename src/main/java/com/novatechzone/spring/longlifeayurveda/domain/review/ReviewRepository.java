package com.novatechzone.spring.longlifeayurveda.domain.review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    Review findByProductIdAndCustomerId(Long productId, Long customerId);
}
