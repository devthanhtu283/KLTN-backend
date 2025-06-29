package com.demo.helpers;

public enum CacheName {
    JOBS("jobs");
    private final String value;

    CacheName(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
