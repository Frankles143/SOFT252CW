package PatientManagementSystem.Model.Users.UserTests;

import PatientManagementSystem.UserIDRegex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractPersonTest {

    @Test
    void setId() {
        String goodString = "A0123";
        String badString = "00A123AA";

        assertTrue(goodString.matches(UserIDRegex.getRegex()));
        assertFalse(badString.matches(UserIDRegex.getRegex()));
    }
}