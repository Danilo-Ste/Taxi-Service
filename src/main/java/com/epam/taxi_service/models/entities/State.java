package com.epam.taxi_service.models.entities;

import lombok.Getter;

public enum State {AVAILABLE(1),ON_ROUTE(2),INACTIVE(3);
    @Getter
    private final int value;
    State(int value) {
        this.value = value;
    }

    public static State getState(int value) {
        for (State state: State.values()) {
            if (state.value == value) {
                return state;
            }
        }
        return INACTIVE;
    }
}
