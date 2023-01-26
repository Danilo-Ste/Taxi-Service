package com.epam.taxi_service.models.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Order {
    private long Id;
    private String addressOfDeparture;
    private String addressOfDestination;
    private long user_id;
    private long car_id;
}
