package com.novatechzone.spring.longlifeayurveda.domain.customer;

import com.novatechzone.spring.longlifeayurveda.domain.customer.address.CustomerAddressRepository;
import com.novatechzone.spring.longlifeayurveda.domain.customer.address.UpdateAddressDTO;
import com.novatechzone.spring.longlifeayurveda.dto.RequestMetaDTO;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RequestMetaDTO requestMetaDTO;
    public static final String UPLOAD_DIR = "upload";
    public ResponseEntity<?> updateProfile(UpdateProfileDTO updateProfileDTO) {

        if (customerRepository.findById(requestMetaDTO.getCustomerId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
        else {
            Customer customer = customerRepository.findById(requestMetaDTO.getCustomerId()).get();
            if (!updateProfileDTO.getEmail().isEmpty()) {
                customer.setEmail(updateProfileDTO.getEmail());
            }
            if (!updateProfileDTO.getFullName().isEmpty()) {
                customer.setFullName(updateProfileDTO.getFullName());
            }
            if (!updateProfileDTO.getPassword().isEmpty()) {
                customer.setPassword(updateProfileDTO.getPassword());
            }

            customer.setUpdatedAt(String.valueOf(LocalDateTime.now()));
            customerRepository.save(customer);

            return ResponseEntity.status(HttpStatus.OK).body("Profile updated successfully");
        }
    }

    public ResponseEntity<?> updateProfileImage(MultipartFile file) throws IOException {
        if (customerRepository.findById(requestMetaDTO.getCustomerId()).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        } else if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Please select an image");
        } else{
            Path path = Paths.get(UPLOAD_DIR);
            if (!Files.exists(path)){
                Files.createDirectory(path);
            }
            String extension = FilenameUtils.getExtension(file.getOriginalFilename());
            String fileName = System.currentTimeMillis() +"."+extension;

            Path filePath = path.resolve(fileName);
            Files.copy(file.getInputStream(),filePath, StandardCopyOption.REPLACE_EXISTING);

            String appUrl = String.format("http://%s:%s", InetAddress.getLocalHost(),8080);
            String url = UPLOAD_DIR +"/"+fileName;

            Customer customer = customerRepository.findById(requestMetaDTO.getCustomerId()).get();
            customer.setImagePath(url);
            customer.setUpdatedAt(String.valueOf(LocalDateTime.now()));
            customerRepository.save(customer);

            return ResponseEntity.status(HttpStatus.OK).body("Profile pic updated successfully");

        }
    }
}
