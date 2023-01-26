package com.epam.taxi_service.dto;


import lombok.*;

@Data
@EqualsAndHashCode(of = {"email", "name", "surname"})
@Builder
public class UserDTO {
    private long id;
    private String email;
    private String name;
    private String surname;
    private String role;
}
