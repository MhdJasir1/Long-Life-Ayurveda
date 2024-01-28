package com.novatechzone.spring.longlifeayurveda.domain.rating;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RatingRequestDTO {

    @NotNull(message = "rating star is required")
    @Min(value = 1,message = "Invalid rating star")
    private Integer star;
}
