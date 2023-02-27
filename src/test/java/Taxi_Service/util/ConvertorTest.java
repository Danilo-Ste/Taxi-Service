package Taxi_Service.util;

import com.epam.taxi_service.dto.UserDTO;
import com.epam.taxi_service.models.entities.User;
import org.junit.Test;

import static Taxi_Service.Constants.*;
import static com.epam.taxi_service.utils.Convertor.*;
import static org.junit.Assert.*;
class ConvertorTest {
    @Test
    void testConvertDTOToUser() {
        User testUser = getTestUser();
        User dtoToUser = convertDTOToUser(getTestUserDTO());
        assertEquals(testUser, dtoToUser);
        assertNull(dtoToUser.getPassword());
        assertNotEquals(testUser.getPassword(), dtoToUser.getPassword());
    }
    @Test
    void testConvertUserToDTO() {
        UserDTO testDTO = getTestUserDTO();
        UserDTO userToDTO = convertUserToDTO(getTestUser());
        assertEquals(testDTO, userToDTO);
        assertEquals(testDTO.getId(), userToDTO.getId());
        assertEquals(testDTO.getRole(), userToDTO.getRole());
    }
    private UserDTO getTestUserDTO() {
        return UserDTO.builder()
                .id(ID_VALUE)
                .email(EMAIL_VALUE)
                .name(NAME_VALUE)
                .surname(SURNAME_VALUE)
                .role(ROLE_VISITOR)
                .build();
    }

    private User getTestUser() {
        return User.builder()
                .id(ID_VALUE)
                .email(EMAIL_VALUE)
                .password(PASSWORD_VALUE)
                .name(NAME_VALUE)
                .surname(SURNAME_VALUE)
                .roleId(ROLE_ID_VALUE)
                .build();
    }
}
