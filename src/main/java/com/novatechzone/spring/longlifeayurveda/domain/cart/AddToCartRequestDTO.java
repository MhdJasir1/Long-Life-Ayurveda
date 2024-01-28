package com.novatechzone.spring.longlifeayurveda.domain.cart;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AddToCartRequestDTO {

    @NotNull(message = "Qty is required")
    @Min(value = 1,message = "Invalid qty")
    private Integer qty;
}
