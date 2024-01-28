package com.novatechzone.spring.longlifeayurveda.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ResponseEntity<?> getProducts() {
        List<Product> allProducts = productRepository.findAll();
        ArrayList<Product> productArrayList = new ArrayList<>();
        allProducts.forEach(product -> {
            productArrayList.add(product);
        });
        return ResponseEntity.status(HttpStatus.OK).body(productArrayList);

    }
}
