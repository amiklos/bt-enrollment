package com.code.crafters.enrollment.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import javax.validation.constraints.*;
import java.time.LocalDate;
@Data
public class IdentityCard {
    @Schema(name = "personalId", example = "120324503975", required = true)
    @NotNull(message = "Personal id is required")
    private String personalId;
    @Schema(name = "firstName", example = "Ana", required = true)
    @NotNull(message = "FirstName is required")
    private String firstName;
    @Schema(name = "lastName", example = "Pop", required = true)
    @NotNull(message = "LastName is required")
    private String lastName;
    @Schema(name = "nationality", example = "Romana", required = true)
    @NotNull(message = "Nationality is required")
    private String nationality;
    @Schema(name = "placeOfBirth", example = "Cluj-Napoca", required = true)
    @NotNull(message = "PlaceOfBirth is required")
    private String placeOfBirth;
    //TODO - complete with Address object
    @Schema(name = "address", example = "Traian Mosoiu 33", required = true)
    @NotNull(message = "Address is required")
    private String address;
    @Schema(name = "issuedBy", example = "SPCLEP Cluj", required = true)
    @NotNull(message = "IssuedBy is required")
    private String issuedBy;
    @Schema(name = "validityFrom", example = "2022-06-01", format = "dd-MM-yyyy", required = true)
    @NotNull(message = "ValidityFrom is required")
    @Past(message = "ValidityFrom should be a date in past")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validityFrom;
    @Schema(name = "validityTo", example = "2026-06-01", format = "dd-MM-yyyy", required = true)
    @NotNull(message = "ValidityTo is required")
    @Future(message = "ValidityTo should be a date in future")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate validityTo;
}
