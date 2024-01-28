package com.novatechzone.spring.longlifeayurveda.domain.wishlist;

import com.novatechzone.spring.longlifeayurveda.domain.customer.Customer;
import com.novatechzone.spring.longlifeayurveda.domain.customer.CustomerRepository;
import com.novatechzone.spring.longlifeayurveda.domain.product.Product;
import com.novatechzone.spring.longlifeayurveda.domain.product.ProductRepository;
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
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;
    private final RequestMetaDTO requestMetaDTO;

    public ResponseEntity<?> addToWishlist(Long productId) {
        if (customerRepository.findById(requestMetaDTO.getCustomerId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        } else if (String.valueOf(productId).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        } else {

            if ((wishlistRepository.findByCustomerIdAndProductId(requestMetaDTO.getCustomerId(), productId) != null)) {
                return ResponseEntity.status(HttpStatus.OK).body("Product already added to wishlist");
            } else {

                if (productRepository.findById(productId).isEmpty()){
                    return ResponseEntity.status(HttpStatus.OK).body("Product not found");
                } else{
                    Customer customer = customerRepository.findById(requestMetaDTO.getCustomerId()).get();
                    Wishlist wishlist = new Wishlist();
                    wishlist.setProductId(productId);
                    wishlist.setCustomerId(customer.getCustomerId());
                    wishlist.setCreatedAt(String.valueOf(LocalDateTime.now()));
                    wishlistRepository.save(wishlist);
                    return ResponseEntity.status(HttpStatus.OK).body("Product added to wishlist");
                }

            }
        }
    }

    public ResponseEntity<?> removeFromWishlist(Long productId) {
        if (customerRepository.findById(requestMetaDTO.getCustomerId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        } else if (String.valueOf(productId).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        } else {
            if (wishlistRepository.findByCustomerIdAndProductId(requestMetaDTO.getCustomerId(),productId) == null){
                return ResponseEntity.status(HttpStatus.OK).body("No matching product in your wishlist");
            }else{

                Wishlist wishlist = wishlistRepository.findByCustomerIdAndProductId(requestMetaDTO.getCustomerId(),productId);
                wishlistRepository.delete(wishlist);

                return ResponseEntity.status(HttpStatus.OK).body("Product removed from wishlist successfully");
            }
        }
    }

    public ResponseEntity<?> getProducts() {
        List<Wishlist> allProducts = wishlistRepository.findAll();
        ArrayList<Wishlist> productArrayList = new ArrayList<>();
        allProducts.forEach(product -> {
            productArrayList.add(product);
        });
        return ResponseEntity.status(HttpStatus.OK).body(productArrayList);
    }
}
