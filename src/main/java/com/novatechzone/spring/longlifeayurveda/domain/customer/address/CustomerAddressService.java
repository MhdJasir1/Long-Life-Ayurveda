package com.novatechzone.spring.longlifeayurveda.domain.customer.address;

import com.novatechzone.spring.longlifeayurveda.domain.customer.Customer;
import com.novatechzone.spring.longlifeayurveda.domain.customer.CustomerRepository;
import com.novatechzone.spring.longlifeayurveda.dto.RequestMetaDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerAddressService {

    private final CustomerAddressRepository customerAddressRepository;
    private final CustomerRepository customerRepository;
    private final RequestMetaDTO requestMetaDTO;

    public ResponseEntity<?> updateAddress(UpdateAddressDTO updateAddressDTO) {
        if (customerRepository.findById(requestMetaDTO.getCustomerId()).isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        } else{
            CustomerAddress customerAddress = new CustomerAddress();
            Customer customer = customerRepository.findById(requestMetaDTO.getCustomerId()).get();
            customerAddress.setAddressNumber(updateAddressDTO.getAddressNumber());
            customerAddress.setStreetNumber(updateAddressDTO.getStreet());
            customerAddress.setZipCode(updateAddressDTO.getZipCode());
            customerAddress.setCity(updateAddressDTO.getCity());
            customerAddress.setCustomerId(customer.getCustomerId());

            customerAddressRepository.save(customerAddress);
            return ResponseEntity.status(HttpStatus.OK).body("Address updated successfully");
        }
    }
}
