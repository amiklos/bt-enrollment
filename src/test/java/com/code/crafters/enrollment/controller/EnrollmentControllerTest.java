package com.code.crafters.enrollment.controller;

import com.code.crafters.enrollment.controller.EnrollmentController;
import com.code.crafters.enrollment.model.DenialCause;
import com.code.crafters.enrollment.model.EnrollmentResult;
import com.code.crafters.enrollment.model.IdentityCard;
import com.code.crafters.enrollment.service.EnrollmentService;
import com.code.crafters.enrollment.util.EnrollmentDocumentGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnrollmentControllerTest {
    @Mock
    private EnrollmentService enrollmentService;
    @Mock
    private EnrollmentDocumentGenerator enrollmentDocumentGenerator;
    @InjectMocks
    private EnrollmentController enrollmentController;

    @Test
    public void clientCheckPassTest() {
        when(enrollmentService.fullClientCheck(any(IdentityCard.class))).thenReturn(new EnrollmentResult(true, null));

        ResponseEntity<EnrollmentResult> result = enrollmentController.clientCheck(new IdentityCard());

        assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
        EnrollmentResult enrollmentResult = result.getBody();
        assertEquals(true, enrollmentResult.isValid());
        assertNull(enrollmentResult.getDenialCause());
    }

    @Test
    public void clientCheckFail() {
        when(enrollmentService.fullClientCheck(any(IdentityCard.class)))
                .thenReturn(new EnrollmentResult(false, DenialCause.ACCOUNT_ALREADY_EXISTS.getCause()));

        ResponseEntity<EnrollmentResult> result = enrollmentController.clientCheck(new IdentityCard());

        assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
        EnrollmentResult enrollmentResult = result.getBody();
        assertEquals(false, enrollmentResult.isValid());
        assertEquals(DenialCause.ACCOUNT_ALREADY_EXISTS.getCause(), enrollmentResult.getDenialCause());
    }
}
