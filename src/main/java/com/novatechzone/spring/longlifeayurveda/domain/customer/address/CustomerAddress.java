package com.novatechzone.spring.longlifeayurveda.domain.customer.address;

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
@Table(name = "customer_address")
public class CustomerAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "customer_id",insertable = false,updatable = false)
    private Customer customer;

    @Column(name = "address_number",length = 20)
    private String addressNumber;

    @Column(name = "street_number",columnDefinition = "TEXT")
    private String streetNumber;

    @Column(name = "city",length = 50)
    private String city;

    @Column(name = "zip_code",length = 6)
    private String zipCode;

}
