package Taxi_Service.model.DAO;

import com.epam.taxi_service.models.entities.User;

import static Taxi_Service.Constants.*;

public final class DAOTestUtils {

    public static User getTestUser() {
        return User.builder()
                .id(ID_VALUE)
                .email(EMAIL_VALUE)
                .name(NAME_VALUE)
                .surname(SURNAME_VALUE)
                .password(PASSWORD_VALUE)
                .roleId(ROLE_ID_VALUE)
                .build();
    }


}