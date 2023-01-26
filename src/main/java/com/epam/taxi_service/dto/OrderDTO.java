package com.epam.taxi_service.dto;

import lombok.*;

@Data
@EqualsAndHashCode(of = {"addressOfDeparture", "addressOfDestination"})
@Builder
public class OrderDTO {
    private long id;
    private String addressOfDeparture;
    private String addressOfDestination;
    private long user_id;
    private long car_id;
}
