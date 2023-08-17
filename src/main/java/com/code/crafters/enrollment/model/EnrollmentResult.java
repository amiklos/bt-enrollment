package com.code.crafters.enrollment.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EnrollmentResult {
    private boolean valid;
    private String denialCause;
}
