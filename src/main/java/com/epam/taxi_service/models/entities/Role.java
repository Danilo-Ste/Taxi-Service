package com.epam.taxi_service.models.entities;

import lombok.Getter;

public enum Role {ADMIN(1), CLIENT(2);
    @Getter private final int value;

    Role(int value) {
        this.value = value;
    }

    public static Role getRole(int value) {
        for (Role role: Role.values()) {
            if (role.value == value) {
                return role;
            }
        }
        return CLIENT;
    }
}
