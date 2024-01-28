package com.novatechzone.spring.longlifeayurveda.entity;

import com.novatechzone.spring.longlifeayurveda.domain.customer.Customer;
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
@Table(name = "notification")
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "customer_id",insertable = false,updatable = false)
    private Customer customer;

    @Column(name = "title",length = 100)
    private String title;

    @Column(name = "message",columnDefinition = "TEXT")
    private String message;

    @Column(name = "date", length = 15)
    private String date;

    @Column(name = "time", length = 15)
    private String time;

}
