package com.novatechzone.spring.longlifeayurveda.domain.category;

import com.novatechzone.spring.longlifeayurveda.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
