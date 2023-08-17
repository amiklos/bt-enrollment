package com.code.crafters.enrollment.external;

import org.springframework.stereotype.Service;

@Service
public interface ClientDataService {

    boolean exists(String personalId);

    int riskScore(String personalId);

}
