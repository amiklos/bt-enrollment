package com.code.crafters.enrollment.service;

import com.code.crafters.enrollment.external.ClientDataService;
import com.code.crafters.enrollment.model.ClientRisk;
import com.code.crafters.enrollment.model.DenialCause;
import com.code.crafters.enrollment.model.EnrollmentResult;
import com.code.crafters.enrollment.model.IdentityCard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnrollmentServiceTest {
    @Mock
    private ClientDataService clientDataService;
    @InjectMocks
    private EnrollmentService enrollmentService;

    @Test
    public void fullClientCheckValid() {
        when(clientDataService.exists(any(String.class))).thenReturn(false);
        when(clientDataService.riskScore(any(String.class))).thenReturn(ClientRisk.MEDIUM_RISK.getMax());

        IdentityCard identityCard = new IdentityCard();
        identityCard.setPersonalId("29011244323");
        EnrollmentResult enrollmentResult = enrollmentService.fullClientCheck(identityCard);

        assertEquals(true, enrollmentResult.isValid());
        assertNull(enrollmentResult.getDenialCause());
    }

    @Test
    public void clientAlreadyExists() {
        when(clientDataService.exists(any(String.class))).thenReturn(true);

        IdentityCard identityCard = new IdentityCard();
        identityCard.setPersonalId("29011244323");
        EnrollmentResult enrollmentResult = enrollmentService.fullClientCheck(identityCard);

        assertEquals(false, enrollmentResult.isValid());
        assertEquals(DenialCause.ACCOUNT_ALREADY_EXISTS.getCause(), enrollmentResult.getDenialCause());
    }

    @Test
    public void clientHighRisc() {
        when(clientDataService.exists(any(String.class))).thenReturn(false);
        when(clientDataService.riskScore(any(String.class))).thenReturn(ClientRisk.HIGH_RISK.getMin());

        IdentityCard identityCard = new IdentityCard();
        identityCard.setPersonalId("29011244323");
        EnrollmentResult enrollmentResult = enrollmentService.fullClientCheck(identityCard);

        assertEquals(false, enrollmentResult.isValid());
        assertEquals(DenialCause.HIGH_RISK_SCORE.getCause(), enrollmentResult.getDenialCause());
    }

}
