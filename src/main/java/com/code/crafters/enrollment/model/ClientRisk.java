package com.code.crafters.enrollment.model;

public enum ClientRisk {
    NO_RISK(0, 20), MEDIUM_RISK(21, 99), HIGH_RISK(100);
    private int min;
    private int max;

    ClientRisk(int min, int max) {
        this.min = min;
        this.max = max;
    }

    ClientRisk(int min) {
        this.min = min;
    }

    public int getMin() {
        return this.min;
    }

    public int getMax() {
        return this.max;
    }
}
