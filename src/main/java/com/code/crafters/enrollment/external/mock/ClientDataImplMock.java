package com.code.crafters.enrollment.external.mock;

import com.code.crafters.enrollment.external.ClientDataService;
import org.springframework.stereotype.Service;

@Service
public class ClientDataImplMock implements ClientDataService {
    @Override
    public boolean exists(String personalId) {
        if (personalId.length() > 10) {
            return false;
        }
        return true;
    }

    @Override
    public int riskScore(String personalId) {
        return Integer.valueOf(personalId.substring(0, 1)) * 20;
    }
}
