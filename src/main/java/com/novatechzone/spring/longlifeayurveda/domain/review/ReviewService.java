package com.novatechzone.spring.longlifeayurveda.domain.review;

import com.novatechzone.spring.longlifeayurveda.domain.customer.CustomerRepository;
import com.novatechzone.spring.longlifeayurveda.domain.product.ProductRepository;
import com.novatechzone.spring.longlifeayurveda.domain.rating.Rating;
import com.novatechzone.spring.longlifeayurveda.dto.RequestMetaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final RequestMetaDTO requestMetaDTO;
    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;


    public ResponseEntity<?> addReview(Long productId, ReviewRequestDTO reviewRequestDTO) {
        if (customerRepository.findById(requestMetaDTO.getCustomerId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        } else if (String.valueOf(productId).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        } else {

            if (reviewRepository.findByProductIdAndCustomerId(productId, requestMetaDTO.getCustomerId()) != null) {
                return ResponseEntity.status(HttpStatus.OK).body("Already reviewed");
            } else {
                if (productRepository.findById(productId).isEmpty()) {
                    return ResponseEntity.status(HttpStatus.OK).body("Product not found");
                } else {
                    Review review = new Review();
                    review.setCustomerId(requestMetaDTO.getCustomerId());
                    review.setProductId(Long.valueOf(productId.toString()));
                    review.setDescription(reviewRequestDTO.getDescription());
                    review.setCreatedAt(String.valueOf(LocalDateTime.now()));
                    reviewRepository.save(review);

                    return ResponseEntity.status(HttpStatus.OK).body("Product reviewed successfully");
                }
            }
        }
    }

    public ResponseEntity<?> getReviews() {
        List<Review> allReviews = reviewRepository.findAll();
        ArrayList<Review> reviewArrayList = new ArrayList<>();
        allReviews.forEach(product -> {
            reviewArrayList.add(product);
        });
        return ResponseEntity.status(HttpStatus.OK).body(reviewArrayList);

    }
}
