package com.code.crafters.enrollment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class DenialDocumentRequest {
    @Schema(name = "denialCause", example = "Client with high risc score", required = true)
    @NotNull(message = "Denial Cause is required")
    private String denialCause;
}
