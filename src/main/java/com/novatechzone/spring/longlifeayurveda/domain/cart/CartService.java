package com.novatechzone.spring.longlifeayurveda.domain.cart;

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
public class CartService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final RequestMetaDTO requestMetaDTO;
    public ResponseEntity<?> addToCart(Long productId, AddToCartRequestDTO addToCartRequestDTO) {
        if (customerRepository.findById(requestMetaDTO.getCustomerId()).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        } else if (String.valueOf(productId).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }else {

            if ((cartRepository.findByCustomerIdAndProductId(requestMetaDTO.getCustomerId(),productId) != null)){
                Cart alreadyInCart = cartRepository.findByCustomerIdAndProductId(requestMetaDTO.getCustomerId(), productId);
                alreadyInCart.setQty(addToCartRequestDTO.getQty());
                cartRepository.save(alreadyInCart);

                return ResponseEntity.status(HttpStatus.OK).body("Product qty updated to cart");
            }else{

                if (productRepository.findById(productId).isEmpty()){
                    return ResponseEntity.status(HttpStatus.OK).body("Product not found");
                } else {

                    Customer customer = customerRepository.findById(requestMetaDTO.getCustomerId()).get();
                    Cart cart = new Cart();
                    cart.setProductId(productId);
                    cart.setCustomerId(customer.getCustomerId());
                    cart.setQty(addToCartRequestDTO.getQty());
                    cartRepository.save(cart);
                    return ResponseEntity.status(HttpStatus.OK).body("Product added to cart successfully");
                }
            }
        }
    }

    public ResponseEntity<?> removeFromCart(Long productId) {
        if (customerRepository.findById(requestMetaDTO.getCustomerId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        } else if (String.valueOf(productId).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        } else {
            if (cartRepository.findByCustomerIdAndProductId(requestMetaDTO.getCustomerId(),productId) == null){
                return ResponseEntity.status(HttpStatus.OK).body("No matching product in your cart");
            }else{

                Cart cart = cartRepository.findByCustomerIdAndProductId(requestMetaDTO.getCustomerId(),productId);
                cartRepository.delete(cart);

                return ResponseEntity.status(HttpStatus.OK).body("Product removed from cart successfully");
            }
        }
    }

    public ResponseEntity<?> getProducts() {
        List<Cart> allProducts = cartRepository.findAll();
        ArrayList<Cart> productArrayList = new ArrayList<>();
        allProducts.forEach(product -> {
            productArrayList.add(product);
        });
        return ResponseEntity.status(HttpStatus.OK).body(productArrayList);
    }
}
