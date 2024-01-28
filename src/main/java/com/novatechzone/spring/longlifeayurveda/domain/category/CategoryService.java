package com.novatechzone.spring.longlifeayurveda.domain.category;

import com.novatechzone.spring.longlifeayurveda.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public ResponseEntity<?> getCategories() {
        List<Category> allProducts = categoryRepository.findAll();
        ArrayList<Category> categoryArrayList = new ArrayList<>();
        allProducts.forEach(category -> {
            categoryArrayList.add(category);
        });
        return ResponseEntity.status(HttpStatus.OK).body(categoryArrayList);

    }
}
