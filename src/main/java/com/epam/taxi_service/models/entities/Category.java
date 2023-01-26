package com.epam.taxi_service.models.entities;

import lombok.Getter;

public enum Category {LUX(1), COMMON(2);
    @Getter
    private final int value;

    Category(int value) {
        this.value = value;
    }

    public static Category getRole(int value) {
        for (Category category: Category.values()) {
            if (category.value == value) {
                return category;
            }
        }
        return COMMON;
    }
}
