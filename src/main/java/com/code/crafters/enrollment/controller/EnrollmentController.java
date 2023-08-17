package com.code.crafters.enrollment.controller;

import com.code.crafters.enrollment.dto.DenialDocumentRequest;
import com.code.crafters.enrollment.dto.EnrollmentDocumentRequest;
import com.code.crafters.enrollment.model.EnrollmentResult;
import com.code.crafters.enrollment.model.IdentityCard;
import com.code.crafters.enrollment.service.EnrollmentService;
import com.code.crafters.enrollment.util.EnrollmentDocumentGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.io.InputStream;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {
    @Autowired
    private EnrollmentService enrollmentService;
    @Autowired
    private EnrollmentDocumentGenerator enrollmentDocumentGenerator;

    @Operation(summary = "Check if a client can create a new enrollment")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The enrollment is denied if a client has a high risk score " +
                    "or the enrollment already exists",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EnrollmentResult.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid request or mandatory fields are missing",
                    content = @Content) })
    @PostMapping(value = "/check")
    public ResponseEntity<EnrollmentResult> clientCheck(@Valid @RequestBody IdentityCard identityCard) {
        EnrollmentResult enrollmentResult = enrollmentService.fullClientCheck(identityCard);
        return ResponseEntity.ok().body(enrollmentResult);
    }

    @Operation(summary = "Generate the enrollment document")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The enrollment document is generated",
                    content = { @Content(mediaType = "application/pdf",
                            schema = @Schema(type = "string", format = "binary")) }),
            @ApiResponse(responseCode = "400", description = "Invalid request or mandatory fields are missing",
                    content = @Content) })
    @PostMapping(value = "/generate", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateEnrollmentDoc(@Valid @RequestBody EnrollmentDocumentRequest enrollmentDocumentRequest) {
        InputStream responseDocument = enrollmentDocumentGenerator.generateEnrollmentDocument(enrollmentDocumentRequest.getIdentityCard());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=enrollment_response.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(responseDocument));
    }

    @Operation(summary = "Generate the denial document including the reason of denial")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The denial document is generated",
                    content = { @Content(mediaType = "application/pdf",
                            schema = @Schema(type = "string", format = "binary")) }),
            @ApiResponse(responseCode = "400", description = "Invalid request or mandatory fields are missing",
                    content = @Content) })
    @PostMapping(value = "/denial", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> generateDenialDoc(@Valid @RequestBody DenialDocumentRequest denialDocumentRequest) {
        InputStream responseDocument = enrollmentDocumentGenerator.generateDenialDocument(denialDocumentRequest.getDenialCause());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=denial_response.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(responseDocument));
    }

}
