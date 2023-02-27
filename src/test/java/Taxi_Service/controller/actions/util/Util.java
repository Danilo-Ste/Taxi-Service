package Taxi_Service.controller.actions.util;


import com.epam.taxi_service.dto.UserDTO;
import com.epam.taxi_service.models.entities.Role;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
    public static final String POST = "POST";
    public static final String GET = "GET";
    public static final String SERVLET_PATH = "somePath";
    public static final StringBuffer REQUEST_URL = new StringBuffer("someURL");
    public static final String CAPTCHA_VALUE = "someCaptcha";
    public static final String EN = "en";
    public static final String UA = "uk_UA";
    public static final String FONT = "fonts/arial.ttf";
    public static final long ID_ONE = 1L;
    public static final String ONE = "1";
    public static final String EMAIL_VALUE = "em@em.ua";
    public static final String NEW_EMAIL = "new@em.ua";
    public static final String PASS_VALUE = "Password1";
    public static final String NAME_VALUE = "someName";
    public static final String NEW_NAME = "newName";
    public static final String SURNAME_VALUE = "someSurname";
    public static final String NEW_SURNAME = "newSurname";
    public static final String TOPIC_VALUE = "someTopic";
     public static final String TITLE_VALUE = "someTitle";
     public static final String FUTURE_DATE = LocalDate.now().plusDays(ID_ONE).toString();
     public static final String LOCATION_VALUE = "someLocation";
     public static final String DESCRIPTION_VALUE = "someDescription";

    public static UserDTO getUserDTO() {
        return UserDTO.builder()
                .id(ID_ONE)
                .email(EMAIL_VALUE)
                .name(NAME_VALUE)
                .surname(SURNAME_VALUE)
                .role(Role.ADMIN.name())
                .build();
    }

    public static List<UserDTO> getUserDTOs(){
        List<UserDTO> users = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            users.add(UserDTO.builder()
                    .id(i).email(EMAIL_VALUE).name(NAME_VALUE).surname(SURNAME_VALUE).role(NAME_VALUE)
                    .build());
        }
        return users;
    }

    public static String clearString(String string) {
        return string
                .lines()
                .filter(s -> !(s.contains("CreationDate") || s.contains("ID") || s.contains("BaseFont")))
                .collect(Collectors.joining("\n"));
    }
}