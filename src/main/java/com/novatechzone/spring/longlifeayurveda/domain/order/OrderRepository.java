package com.novatechzone.spring.longlifeayurveda.domain.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Long> {
//    Orders findByProductIdAndCustomerId(Long productId, Long customerId);

    Orders findByIdAndCustomerId(Long ordersId, Long customerId);

    Orders findByIdAndCustomerIdAndOrderStatus(Long orderId, Long customerId,OrderStatus orderStatus);

    Orders findByCustomerId(Long customerId);


    List<Orders> findAllByCustomerIdAndOrderStatus(Long customerId, OrderStatus ongoing);
}
