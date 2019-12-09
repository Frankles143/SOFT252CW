package PatientManagementSystem.Model.Users.UserTests;

import PatientManagementSystem.Gender;
import PatientManagementSystem.Model.Serialization;
import PatientManagementSystem.Model.Users.AbstractPerson;
import PatientManagementSystem.Model.Users.Patient;
import PatientManagementSystem.Model.Users.UserData;
import PatientManagementSystem.UserIDRegex;
import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
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

        UserData.PatientUsers.add(josh);
        Serialization.SaveUserData(UserData.PatientUsers);

        testLoad = Serialization.LoadUserData();

        assertSame(josh, testLoad);


    }
}