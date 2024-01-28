package com.novatechzone.spring.longlifeayurveda.domain.wishlist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Long> {

    Wishlist findByCustomerIdAndProductId(Long customerId, Long productId);
}
