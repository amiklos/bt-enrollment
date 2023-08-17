package com.code.crafters.enrollment.service;

import com.code.crafters.enrollment.external.ClientDataService;
import com.code.crafters.enrollment.model.ClientRisk;
import com.code.crafters.enrollment.model.DenialCause;
import com.code.crafters.enrollment.model.EnrollmentResult;
import com.code.crafters.enrollment.model.IdentityCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
    @Autowired
    private ClientDataService clientDataService;

    public EnrollmentResult fullClientCheck(IdentityCard identityCard) {
        if (clientDataService.exists(identityCard.getPersonalId())) {
            return new EnrollmentResult(false, DenialCause.ACCOUNT_ALREADY_EXISTS.getCause());
        }
        if (clientDataService.riskScore(identityCard.getPersonalId()) >= ClientRisk.HIGH_RISK.getMin()) {
            return new EnrollmentResult(false, DenialCause.HIGH_RISK_SCORE.getCause());
        }
        return new EnrollmentResult(true, null);
    }

}
