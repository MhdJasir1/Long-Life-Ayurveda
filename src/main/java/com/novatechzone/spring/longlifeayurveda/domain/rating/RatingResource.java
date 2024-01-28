package com.novatechzone.spring.longlifeayurveda.domain.rating;

import com.novatechzone.spring.longlifeayurveda.LongLifeAyurvedaApplication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rate_product")
@RequiredArgsConstructor
public class RatingResource {

    private final RatingService ratingService;

    @PostMapping("/{product_id}/{app_id}")
    public ResponseEntity<?> rateProduct(@PathVariable("app_id") String appId, @PathVariable("product_id") Long productId, @Valid @RequestBody RatingRequestDTO ratingRequestDTO){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return ratingService.rateProduct(productId,ratingRequestDTO);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid app id");
        }
    }


    @GetMapping("/all_ratings/{app_id}")
    public ResponseEntity<?> getRatings(@PathVariable("app_id") String appId){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return ratingService.getRatings();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid app id!");
        }
    }
}
