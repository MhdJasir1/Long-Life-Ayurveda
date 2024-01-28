package com.novatechzone.spring.longlifeayurveda.domain.order;

import com.novatechzone.spring.longlifeayurveda.LongLifeAyurvedaApplication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderResource {
    private final OrderService orderService;

    @PostMapping("/add_order/{product_id}/{app_id}")
    public ResponseEntity<?> addOrder(@PathVariable("app_id") String appId, @PathVariable("product_id") Long productId,@Valid @RequestBody OrderRequestDTO orderRequestDTO){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return orderService.addOrder(productId,orderRequestDTO);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid app id");
        }
    }

    @PostMapping("/cancel_order/{order_id}/{app_id}")
    public ResponseEntity<?> cancelOrder(@PathVariable("app_id") String appId, @PathVariable("order_id") Long orderId){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return orderService.cancelOrder(orderId);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid app id");
        }
    }

    @PostMapping("/update_order/{product_id}/{app_id}")
    public ResponseEntity<?> updateOrder(@PathVariable("app_id") String appId, @PathVariable("product_id") Long productId, @Valid @RequestBody UpdateOrderRequestDTO updateOrderRequestDTO){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return orderService.updateOrder(productId,updateOrderRequestDTO);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid app id");
        }
    }


    @GetMapping("/all_orders/{app_id}")
    public ResponseEntity<?> viewOrderHistory (@PathVariable("app_id") String appId){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return orderService.viewOrderHistory ();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid app id!");
        }
    }


    @GetMapping("/ongoing_orders/{app_id}")
    public ResponseEntity<?> viewOngoingOrders (@PathVariable("app_id") String appId){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return orderService.viewOngoingOrders ();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid app id!");
        }
    }


    @GetMapping("/cancelled_orders/{app_id}")
    public ResponseEntity<?> viewCancelledOrders (@PathVariable("app_id") String appId){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return orderService.viewCancelledOrders ();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid app id!");
        }
    }
}
