package com.epam.taxi_service.models.entities;


import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
public class User {
    private long id;
    private String name;
    private String surname;
    private String email;
    private transient String password;
    @EqualsAndHashCode.Exclude private int roleId;

}
