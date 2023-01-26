package com.epam.taxi_service.utils;

import com.epam.taxi_service.dto.CarDTO;
import com.epam.taxi_service.dto.UserDTO;
import com.epam.taxi_service.models.entities.Car;
import com.epam.taxi_service.models.entities.Role;
import com.epam.taxi_service.models.entities.User;

public class Convertor {

    public static User convertDTOToUser(UserDTO userDTO) {
        return User.builder()
                .id(userDTO.getId())
                .email(userDTO.getEmail())
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .build();
    }
    public static Car convertDTOToCar(CarDTO carDTO) {
        return Car.builder()
                .id(carDTO.getId())
                .address(carDTO.getAddress())
                .capacity(carDTO.getCapacity())
                .category(carDTO.getCategory())
                .IdState(carDTO.getIdState())
                .build();
    }


    public static UserDTO convertUserToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .surname(user.getSurname())
                .role(String.valueOf(Role.getRole(user.getRoleId())))
                .build();
    }

    public static CarDTO convertCarToDTO(Car car) {
        return CarDTO.builder()
                .id(car.getId())
                .address(car.getAddress())
                .capacity(car.getCapacity())
                .category(car.getCategory())
                .IdState(car.getIdState())
                .build();
    }
}
