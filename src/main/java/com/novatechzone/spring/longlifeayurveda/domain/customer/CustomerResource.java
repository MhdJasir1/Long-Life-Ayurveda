package com.novatechzone.spring.longlifeayurveda.domain.customer;

import com.novatechzone.spring.longlifeayurveda.LongLifeAyurvedaApplication;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class CustomerResource {

    private final CustomerService customerService;
    @PostMapping("/update_profile/{app_id}")
    public ResponseEntity<?> updateProfile(@PathVariable("app_id") String appId, @Valid @RequestBody UpdateProfileDTO updateProfileDTO){
        if (appId.equals(LongLifeAyurvedaApplication.baseId)){
            return customerService.updateProfile(updateProfileDTO);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid appId");
        }
    }

    @PostMapping("/update_profile_pic/{app_id}")
    public ResponseEntity<?> updateProfileImage(@PathVariable("app_id") String appId, @RequestParam("image") MultipartFile file) throws IOException {
        if (appId.equals(LongLifeAyurvedaApplication.baseId)) {
            return customerService.updateProfileImage(file);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Invalid app id!");
        }
    }

}
