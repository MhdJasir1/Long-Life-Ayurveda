package com.novatechzone.spring.longlifeayurveda.domain.story;

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
@Table(name = "story")
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "customer_id")
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "customer_id",updatable = false,insertable = false)
    private Customer customer;

    @Column(name = "shares")
    private Integer shares;

    @Column(name = "attach_path",columnDefinition = "TEXT")
    private String attachPath;

    @Column(name = "created_at")
    private String createdAt;

    @Column(name = "updated_at")
    private String updatedAt;

}
