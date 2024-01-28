package com.novatechzone.spring.longlifeayurveda.domain.security;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthRequestDTO {
    @NotBlank(message = "Mobile is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Invalid mobile number. It should be a 10-digit number.")
    private String mobile;
    @NotBlank(message = "Password is required")
    private String password;
}
