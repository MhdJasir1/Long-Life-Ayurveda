package com.novatechzone.spring.longlifeayurveda.domain.customer.address;

import com.novatechzone.spring.longlifeayurveda.LongLifeAyurvedaApplication;
import com.novatechzone.spring.longlifeayurveda.domain.customer.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class CustomerAddressResource {

    private final CustomerAddressService customerAddressService;

    @PutMapping("/update_address/{app_id}")
    public ResponseEntity<?> updateAddress(@PathVariable("app_id") String appId, @Valid @RequestBody UpdateAddressDTO updateAddressDTO){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return customerAddressService.updateAddress(updateAddressDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid app id!");
        }
    }
}
