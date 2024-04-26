package com.example.Project.User;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ADMIN"),
    USER("USER");

    UserRole(String value) {
        this.value = value;
    }

    private String value;
}
