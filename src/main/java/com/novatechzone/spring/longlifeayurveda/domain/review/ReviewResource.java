package com.novatechzone.spring.longlifeayurveda.domain.review;

import com.novatechzone.spring.longlifeayurveda.LongLifeAyurvedaApplication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewResource {
    private final ReviewService reviewService;

    @PostMapping("/add_review/{product_id}/{app_id}")
    public ResponseEntity<?> addReview(@PathVariable("app_id") String appId, @PathVariable("product_id") Long productId, @Valid @RequestBody ReviewRequestDTO reviewRequestDTO){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return reviewService.addReview(productId,reviewRequestDTO);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid app id");
        }
    }

    @GetMapping("/all_reviews/{app_id}")
    public ResponseEntity<?> getReviews(@PathVariable("app_id") String appId){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return reviewService.getReviews();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid app id!");
        }
    }
}
