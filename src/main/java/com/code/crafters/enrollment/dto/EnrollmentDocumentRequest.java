package com.code.crafters.enrollment.dto;

import com.code.crafters.enrollment.model.IdentityCard;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.NotNull;

@Data
public class EnrollmentDocumentRequest {
    @Schema(name = "accountType", example = "Personal account", required = true)
    @NotNull(message = "AccountType is required")
    private String accountType;
    @Schema(name = "currency", example = "RON", required = true)
    @NotNull(message = "Currency is required")
    private String currency;
    @Schema(name = "identityCard", required = true)
    @NotNull(message = "IdentityCard is required")
    private IdentityCard identityCard;
}
