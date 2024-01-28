package com.novatechzone.spring.longlifeayurveda.domain.security;

import com.novatechzone.spring.longlifeayurveda.LongLifeAyurvedaApplication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/customer")
public class AuthResource {

    public final AuthService authService;

    @PostMapping("/auth/{app_id}")
    public ResponseEntity<?> registerAndLoginCustomer(@PathVariable("app_id") String appId, @Valid @RequestBody AuthRequestDTO authRequestDTO){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return authService.registerAndLoginCustomer(authRequestDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid app id!");
        }
    }

    @PostMapping("/auth/otp/{app_id}")
    public ResponseEntity<?> getCustomerPinNumber(@PathVariable("app_id") String appId, @Valid @RequestBody MobileRequestDTO mobileRequestDTO){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return authService.getCustomerPinNumber(mobileRequestDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid app id!");
        }
    }

    @PostMapping("/auth/verify/{app_id}")
    public ResponseEntity<?> verifyCustomerMobile(@PathVariable("app_id") String appId, @Valid @RequestBody VerifyRequestDTO verifyRequestDTO){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return authService.verifyCustomerMobile(verifyRequestDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid app id!");
        }
    }

}
