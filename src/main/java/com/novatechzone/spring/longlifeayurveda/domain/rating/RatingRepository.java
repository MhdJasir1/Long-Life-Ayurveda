package com.novatechzone.spring.longlifeayurveda.domain.rating;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
    Rating findByProductIdAndCustomerId(Long productId,Long customerId);
}
