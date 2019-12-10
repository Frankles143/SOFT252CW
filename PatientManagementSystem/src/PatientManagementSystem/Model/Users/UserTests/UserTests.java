package PatientManagementSystem.Model.Users.UserTests;

import PatientManagementSystem.Model.Gender;
import PatientManagementSystem.Model.Serialization;
import PatientManagementSystem.Model.Users.AbstractPerson;
import PatientManagementSystem.Model.Users.Patient;
import PatientManagementSystem.Model.Users.UserData;
import PatientManagementSystem.Model.UserIDRegex;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTests {

    @Test
    void setId() {
        String goodString = "A0123";
        String badString = "00A123AA";

        assertTrue(goodString.matches(UserIDRegex.getRegex()));
        assertFalse(badString.matches(UserIDRegex.getRegex()));
    }

    @Test
    void SaveLoadUser(){
        Patient josh = new Patient("P9999", "Josh Franklin", "Plymouth", Gender.MALE, 24);
        ArrayList<AbstractPerson> testLoad;
        Patient testPatient;

        UserData.PatientUsers.add(josh);
        Serialization.SaveUserData(UserData.PatientUsers);

        testLoad = Serialization.LoadUserData();

        testPatient = (Patient) testLoad.get(0);

        assertTrue(josh.getId().equals(testPatient.getId()));
    }
}