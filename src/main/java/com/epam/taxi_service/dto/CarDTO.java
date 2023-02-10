package com.epam.taxi_service.dto;

import com.epam.taxi_service.Exception.DAOException;
import com.epam.taxi_service.models.entities.Car;
import com.epam.taxi_service.models.entities.User;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
@Data
@Builder
public class CarDTO {
    private long id;
    private String address;
    private int categoryId;
    private int capacity;
    private int IdState;
}
