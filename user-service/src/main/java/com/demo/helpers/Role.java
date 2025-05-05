package com.demo.helpers;

public enum Role {
    ROLE_ADMIN,
    ROLE_SEEKER,
    ROLE_EMPLOYER;

    public static Role fromUserType(int type) {
        return switch (type) {
            case 0 -> ROLE_ADMIN;
            case 1 -> ROLE_SEEKER;
            case 2 -> ROLE_EMPLOYER;
            default -> throw new IllegalArgumentException("Invalid user type");
        };
    }
}
