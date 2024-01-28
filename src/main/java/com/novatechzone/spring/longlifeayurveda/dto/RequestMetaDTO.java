package com.novatechzone.spring.longlifeayurveda.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RequestMetaDTO {

    @NotBlank(message = "Customer id is required")
    private Long customerId;

    @NotBlank(message = "Mobile is required")
    private String mobile;
}
