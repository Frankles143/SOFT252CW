package PatientManagementSystem.Model.Users.UserTests;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AbstractPersonTest {

    @Test
    void setId() {
        final String regex = "^[ADPS]\\d{4}$";
        String testString = "A0123";

        assertTrue(testString.matches(regex));
    }
}