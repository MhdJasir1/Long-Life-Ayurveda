package com.novatechzone.spring.longlifeayurveda.domain.order;

import com.novatechzone.spring.longlifeayurveda.domain.customer.CustomerRepository;
import com.novatechzone.spring.longlifeayurveda.domain.product.ProductRepository;
import com.novatechzone.spring.longlifeayurveda.dto.RequestMetaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final RequestMetaDTO requestMetaDTO;

    public ResponseEntity<?> addOrder(Long productId, OrderRequestDTO orderRequestDTO) {
        if (customerRepository.findById(requestMetaDTO.getCustomerId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        } else if (String.valueOf(productId).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        } else {
            if (productRepository.findById(productId).isEmpty()){
                return ResponseEntity.status(HttpStatus.OK).body("Product not found");
            } else {
                Orders orders = new Orders();
                orders.setCustomerId(requestMetaDTO.getCustomerId());
                orders.setProductId(Long.valueOf(productId.toString()));
                orders.setQty(orderRequestDTO.getQty());
                orders.setOrderStatus(OrderStatus.PENDING);
                orders.setCreatedAt(String.valueOf(LocalDateTime.now()));
                orderRepository.save(orders);

                return ResponseEntity.status(HttpStatus.OK).body("Product ordered successfully");
            }
        }
    }

    public ResponseEntity<?> viewOrderHistory() {
        List<Orders> allOrders = orderRepository.findAll();
        ArrayList<Orders> ordersArrayList = new ArrayList<>();
        allOrders.forEach(product -> {
            ordersArrayList.add(product);
        });
        return ResponseEntity.status(HttpStatus.OK).body(ordersArrayList);
    }

    public ResponseEntity<?> cancelOrder(Long orderId) {
        if (customerRepository.findById(requestMetaDTO.getCustomerId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        } else if (String.valueOf(orderId).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        } else {

            if (orderRepository.findByIdAndCustomerIdAndOrderStatus(orderId, requestMetaDTO.getCustomerId(), OrderStatus.PENDING) != null) {
                Orders alreadyOrdered = orderRepository.findByIdAndCustomerIdAndOrderStatus(orderId, requestMetaDTO.getCustomerId(), OrderStatus.PENDING);
                alreadyOrdered.setOrderStatus(OrderStatus.CANCELLED);
                orderRepository.save(alreadyOrdered);

                return ResponseEntity.status(HttpStatus.OK).body("Order canceled successfully");
            } else {

                return ResponseEntity.status(HttpStatus.OK).body("No matching product in ordered list");
            }
        }
    }

    public ResponseEntity<?> updateOrder(Long orderId, UpdateOrderRequestDTO updateOrderRequestDTO) {

        if (customerRepository.findById(requestMetaDTO.getCustomerId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        } else if (String.valueOf(orderId).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order not found");
        } else if (String.valueOf(updateOrderRequestDTO.getOrderStatus()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Order status not found");
        } else {

            if (orderRepository.findByIdAndCustomerId(orderId, requestMetaDTO.getCustomerId()) != null) {

                Orders alreadyOrdered = orderRepository.findByIdAndCustomerId(orderId, requestMetaDTO.getCustomerId());
                if (!(alreadyOrdered.getOrderStatus().toString().equals("CANCELLED"))) {

                    alreadyOrdered.setOrderStatus(OrderStatus.valueOf(updateOrderRequestDTO.getOrderStatus()));
                    alreadyOrdered.setUpdatedAt(String.valueOf(LocalDateTime.now()));
                    orderRepository.save(alreadyOrdered);

                    return ResponseEntity.status(HttpStatus.OK).body("Order status updated successfully");
                } else {

                    return ResponseEntity.status(HttpStatus.OK).body("No matching product in ordered list");
                }


            } else {

                return ResponseEntity.status(HttpStatus.OK).body("No matching product in ordered list");
            }
        }
    }

    public ResponseEntity<?> viewOngoingOrders() {

        if (orderRepository.findAllByCustomerIdAndOrderStatus(requestMetaDTO.getCustomerId(), OrderStatus.ONGOING) != null) {

            List<Orders> allOrders = orderRepository.findAllByCustomerIdAndOrderStatus(requestMetaDTO.getCustomerId(), OrderStatus.ONGOING);
            ArrayList<Orders> ordersArrayList = new ArrayList<>();
            allOrders.forEach(product -> {
                ordersArrayList.add(product);
            });
            return ResponseEntity.status(HttpStatus.OK).body(ordersArrayList);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Noting in ongoing orders");
        }
    }


    public ResponseEntity<?> viewCancelledOrders() {

        if (orderRepository.findAllByCustomerIdAndOrderStatus(requestMetaDTO.getCustomerId(), OrderStatus.CANCELLED) != null) {

            List<Orders> allOrders = orderRepository.findAllByCustomerIdAndOrderStatus(requestMetaDTO.getCustomerId(), OrderStatus.CANCELLED);
            ArrayList<Orders> ordersArrayList = new ArrayList<>();
            allOrders.forEach(product -> {
                ordersArrayList.add(product);
            });
            return ResponseEntity.status(HttpStatus.OK).body(ordersArrayList);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Noting in cancelled orders");
        }

    }
}
