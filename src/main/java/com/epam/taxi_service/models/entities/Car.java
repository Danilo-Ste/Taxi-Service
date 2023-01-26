package com.epam.taxi_service.models.entities;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
public class Car {
    private long id;
    private String address;
    @EqualsAndHashCode.Exclude private int categoryId;
    private int capacity;
    @EqualsAndHashCode.Exclude private int IdState;

}
