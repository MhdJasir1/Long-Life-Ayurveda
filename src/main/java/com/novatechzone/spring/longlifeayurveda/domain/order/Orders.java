package com.novatechzone.spring.longlifeayurveda.domain.order;

import com.novatechzone.spring.longlifeayurveda.domain.customer.Customer;
import com.novatechzone.spring.longlifeayurveda.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "product_id",referencedColumnName = "product_id",insertable = false,updatable = false)
    private Product product;

    @Column(name = "customer_id")
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "customer_id",insertable = false,updatable = false)
    private Customer customer;

    @Column(name = "order_status")
    @Enumerated(value = EnumType.STRING)
    private OrderStatus orderStatus = OrderStatus.PENDING;

    @Column(name = "qty")
    private Integer qty;

    @Column(name = "total")
    private Double total;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

}
