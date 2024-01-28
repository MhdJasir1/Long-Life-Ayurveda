package com.novatechzone.spring.longlifeayurveda.domain.cart;

import com.novatechzone.spring.longlifeayurveda.LongLifeAyurvedaApplication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartResource {

    private final CartService cartService;

    @PostMapping("/add_product/{product_id}/{app_id}")
    public ResponseEntity<?> addToCart(@PathVariable("app_id") String appId, @PathVariable("product_id") Long productId,@Valid @RequestBody AddToCartRequestDTO addToCartRequestDTO){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return cartService.addToCart(productId, addToCartRequestDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid appId");
        }
    }


    @DeleteMapping("/remove_product/{product_id}/{app_id}")
    public ResponseEntity<?> removeFromCart (@PathVariable("app_id") String appId, @PathVariable("product_id") Long productId){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return cartService.removeFromCart (productId);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid app id!");
        }
    }


    @GetMapping("/all_products/{app_id}")
    public ResponseEntity<?> getProducts(@PathVariable("app_id") String appId){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return cartService.getProducts();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid app id!");
        }
    }
}
