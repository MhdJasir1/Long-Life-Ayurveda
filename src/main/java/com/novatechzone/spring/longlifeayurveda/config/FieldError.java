package com.novatechzone.spring.longlifeayurveda.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FieldError {
    private String errorCode;
    private String field;
}
