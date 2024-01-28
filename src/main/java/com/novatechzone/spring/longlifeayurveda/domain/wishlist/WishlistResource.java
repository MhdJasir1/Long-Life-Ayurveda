package com.novatechzone.spring.longlifeayurveda.domain.wishlist;

import com.novatechzone.spring.longlifeayurveda.LongLifeAyurvedaApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistResource {

    private final WishlistService wishlistService;

    @PostMapping("/add_product/{product_id}/{app_id}")
    public ResponseEntity<?> addToWishlist(@PathVariable("app_id") String appId, @PathVariable("product_id") Long productId){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return wishlistService.addToWishlist(productId);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid appId");
        }
    }

    @DeleteMapping("/remove_product/{product_id}/{app_id}")
    public ResponseEntity<?> removeFromWishlist (@PathVariable("app_id") String appId,  @PathVariable("product_id") Long productId){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return wishlistService.removeFromWishlist (productId);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid app id!");
        }
    }


    @GetMapping("/all_products/{app_id}")
    public ResponseEntity<?> getProducts(@PathVariable("app_id") String appId){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return wishlistService.getProducts();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid app id!");
        }
    }
}
