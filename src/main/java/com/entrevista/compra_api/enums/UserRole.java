package com.entrevista.compra_api.enums;

public enum UserRole {
    ADMIN,
    USER;
    private String role;

    UserRole(String role) {
        this.role = role;
    }

    UserRole() {
    }

    public String getRole() {
        return role;
    }
}
