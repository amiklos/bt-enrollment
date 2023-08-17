package com.code.crafters.enrollment.model;

public enum DenialCause {
    ACCOUNT_ALREADY_EXISTS("You already have an active account"),
    HIGH_RISK_SCORE("Your client risk score is too high. Your are not allowed to register a new account");

    private String cause;
    DenialCause(String cause) {
        this.cause = cause;
    }

    public String getCause() {
        return this.cause;
    }
}
