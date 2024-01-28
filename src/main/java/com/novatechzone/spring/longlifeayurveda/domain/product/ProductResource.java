package com.novatechzone.spring.longlifeayurveda.domain.product;

import com.novatechzone.spring.longlifeayurveda.LongLifeAyurvedaApplication;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductResource {

    private final ProductService productService;

    @GetMapping("/all_products/{app_id}")
    public ResponseEntity<?> getProducts(@PathVariable("app_id") String appId){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return productService.getProducts();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid app id!");
        }
    }

}
