package com.novatechzone.spring.longlifeayurveda.domain.rating;

import com.novatechzone.spring.longlifeayurveda.domain.cart.Cart;
import com.novatechzone.spring.longlifeayurveda.domain.customer.Customer;
import com.novatechzone.spring.longlifeayurveda.domain.customer.CustomerRepository;
import com.novatechzone.spring.longlifeayurveda.domain.product.ProductRepository;
import com.novatechzone.spring.longlifeayurveda.dto.RequestMetaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final RatingRepository ratingRepository;
    private final RequestMetaDTO requestMetaDTO;

    public ResponseEntity<?> rateProduct(Long productId, RatingRequestDTO ratingRequestDTO) {
        if (customerRepository.findById(requestMetaDTO.getCustomerId()).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }else if (String.valueOf(productId).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }else{

            if (ratingRepository.findByProductIdAndCustomerId(productId,requestMetaDTO.getCustomerId()) != null){
                Rating alreadyRated = ratingRepository.findByProductIdAndCustomerId(productId, requestMetaDTO.getCustomerId());
                alreadyRated.setStar(ratingRequestDTO.getStar());
                ratingRepository.save(alreadyRated);

                return ResponseEntity.status(HttpStatus.OK).body("Product rating updated successfully");
            }else{
                if (productRepository.findById(productId).isEmpty()){
                    return ResponseEntity.status(HttpStatus.OK).body("Product not found");
                } else {
                    Rating rating = new Rating();
                    rating.setCustomerId(requestMetaDTO.getCustomerId());
                    rating.setProductId(Long.valueOf(productId.toString()));
                    rating.setStar(ratingRequestDTO.getStar());
                    ratingRepository.save(rating);

                    return ResponseEntity.status(HttpStatus.OK).body("Product rated successfully");
                }
            }
        }
    }

    public ResponseEntity<?> getRatings() {
        List<Rating> allRatings = ratingRepository.findAll();
        ArrayList<Rating> ratingArrayList = new ArrayList<>();
        allRatings.forEach(product -> {
            ratingArrayList.add(product);
        });
        return ResponseEntity.status(HttpStatus.OK).body(ratingArrayList);
    }
}
