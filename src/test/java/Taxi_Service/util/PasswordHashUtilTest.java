package Taxi_Service.util;

import com.epam.taxi_service.Exception.IncorrectPasswordException;
import org.junit.jupiter.api.Test;

import static com.epam.taxi_service.utils.PasswordHash.encode;
import static com.epam.taxi_service.utils.PasswordHash.verify;
import static org.junit.jupiter.api.Assertions.*;

class PasswordHashUtilTest {
    private static final String password = "PassWord1";
    private static final String wrongPassword = "Password1";

    @Test
    void testPasswordHashing() {
        String encoded = encode(password);
        assertNotEquals(password, encoded);
    }

    @Test
    void testVerifying() {
        String encoded = encode(password);
        assertDoesNotThrow(() -> verify(encoded, password));
    }

    @Test
    void testWrongPassword() {
        String encoded = encode(password);
        assertThrows(IncorrectPasswordException.class, () -> verify(encoded, wrongPassword));
    }
}