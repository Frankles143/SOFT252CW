package src.PatientManagementSystem.Model.Users.UserTests;

import PatientManagementSystem.Model.Gender;
import PatientManagementSystem.Model.Serialization;
import PatientManagementSystem.Model.Users.*;
import PatientManagementSystem.Model.UserIDRegex;
import org.junit.jupiter.api.Test;

import javax.print.Doc;
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
        //Create one of each user
        Admin alex = new Admin("A9999", "Alex Barret", "Plymouth");
        Doctor JD = new Doctor("D9999", "John Dorian", "America");
        Patient josh = new Patient("P9999", "Josh Franklin", "Plymouth", Gender.MALE, 24);
        Secretary pam = new Secretary("S9999", "Pam Something", "Someplace");

        //Add one of each user to the ArrayList
        UserData.AdminUsers.add(alex);
        UserData.DoctorUsers.add(JD);
        UserData.PatientUsers.add(josh);
        UserData.SecretaryUsers.add(pam);

        //Make sure the control lists are the same before the serialization
        ArrayList<Admin> controlAdmin = UserData.AdminUsers;
        ArrayList<Patient> controlPatient = UserData.PatientUsers;
        ArrayList<Doctor> controlDoctor = UserData.DoctorUsers;
        ArrayList<Secretary> controlSecretary = UserData.SecretaryUsers;


        //Serialize and Deserialize all the user data
        Serialization.SaveUserData();
        Serialization.LoadUserData();

        //Check that the lists still match
        assertEquals(controlAdmin.get(0).getId(), UserData.AdminUsers.get(0).getId());
        assertEquals(controlDoctor.get(0).getId(), UserData.DoctorUsers.get(0).getId());
        assertEquals(controlPatient.get(0).getId(), UserData.PatientUsers.get(0).getId());
        assertEquals(controlSecretary.get(0).getId(), UserData.SecretaryUsers.get(0).getId());
    }
}