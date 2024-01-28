package com.novatechzone.spring.longlifeayurveda.domain.security;

import com.novatechzone.spring.longlifeayurveda.domain.customer.Customer;
import com.novatechzone.spring.longlifeayurveda.domain.customer.CustomerRepository;
import com.novatechzone.spring.longlifeayurveda.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtUtil jwtUtil;

    private final CustomerRepository customerRepository;

    public ResponseEntity<?> registerAndLoginCustomer(AuthRequestDTO authRequestDTO) {

        Customer customerByMobile = customerRepository.findByMobile(authRequestDTO.getMobile());
        String otp = String.format("%06d", new Random().nextInt(999999));

        if (customerByMobile == null) {
            //register
            Customer customer = new Customer();
            customer.setMobile(authRequestDTO.getMobile());
            customer.setPassword(authRequestDTO.getPassword());
            customer.setVerificationCode(otp);
            customer.setCreatedAt(String.valueOf(LocalDateTime.now()));
            customerRepository.save(customer);

            return ResponseEntity.status(HttpStatus.OK).body("Account created successfully. Please verify your account.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Account already exists");
        }
    }

    public ResponseEntity<?> verifyCustomerMobile(VerifyRequestDTO verifyRequestDTO) {

        Customer customerByMobile = customerRepository.findByMobile(verifyRequestDTO.getMobile());
        if (customerByMobile == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found. Register again.");
        } else {
            if (!customerByMobile.getVerificationCode().equals(verifyRequestDTO.getOtp())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Entered OTP is wrong, try again");
            } else{
                customerByMobile.setStatus(1);
                customerRepository.save(customerByMobile);

                String accessToken = jwtUtil.generateAccessToken(customerByMobile);
                Map<String, String> data = new HashMap<>();
                data.put("message", "Account verified. Good to go");
                data.put("accessToken", accessToken);
                return ResponseEntity.status(HttpStatus.OK).body(data);
            }
        }
    }
    
    public ResponseEntity<?> getCustomerPinNumber(MobileRequestDTO mobileRequestDTO) {

        Customer customerByMobile = customerRepository.findByMobile(mobileRequestDTO.getMobile());
        if (customerByMobile == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found. Register again.");
        } else {

            String otp = String.format("%06d", new Random().nextInt(999999));

            customerByMobile.setVerificationCode(otp);
            customerRepository.save(customerByMobile);

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(otp);
        }
    }


}
