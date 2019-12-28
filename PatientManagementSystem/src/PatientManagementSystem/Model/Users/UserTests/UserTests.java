package PatientManagementSystem.Model.Users.UserTests;

import PatientManagementSystem.Model.Gender;
import PatientManagementSystem.Model.Serialization;
import PatientManagementSystem.Model.System.Password;
import PatientManagementSystem.Model.System.SystemData;
import PatientManagementSystem.Model.UserIDRegex;
import PatientManagementSystem.Model.Users.*;
import org.junit.jupiter.api.Test;

import javax.print.Doc;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTests {
    //Create one of each user
    Admin alex = new Admin("A9999", "Alex Barret", "Plymouth", "password");
    Doctor JD = new Doctor("D9999", "John Dorian", "America", "password");
    Patient josh = new Patient("P9999", "Josh Franklin", "Plymouth", "password", Gender.MALE, 24);
    Secretary pam = new Secretary("S9999", "Pam Something", "Someplace", "password");

    @Test
    void setId() {
        String goodString = "A0123";
        String badString = "00A123AA";

        assertTrue(goodString.matches(UserIDRegex.getRegex()));
        assertFalse(badString.matches(UserIDRegex.getRegex()));
    }

    @Test
    void CreateNewId(){
        String newID = Patient.CreateId();
        assertTrue(newID.matches(UserIDRegex.getRegex()));

        System.out.println(newID);
    }

    @Test
    void VerifyPasswords(){
        assertTrue(Password.VerifyPassword("password", josh));
        assertFalse(Password.VerifyPassword("notPassword", josh));
    }

    @Test
    void PasswordChange(){
        String oldSalt = josh.getSalt();
        alex.ChangeUserPassword("thisIsANewPassword", josh);
        String newSalt = josh.getSalt();

        assertTrue(Password.VerifyPassword("thisIsANewPassword", josh));
        assertFalse(Password.VerifyPassword("password", josh));
        assertFalse(oldSalt.equals(newSalt));
    }

    @Test
    void SaveLoadUser(){
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

        //Serialize all the user data
        Serialization.SaveUserData();

        //Remove the users manually
        UserData.AdminUsers.remove(alex);
        UserData.DoctorUsers.remove(JD);
        UserData.PatientUsers.remove(josh);
        UserData.SecretaryUsers.remove(pam);

        //Reload the data
        Serialization.LoadUserData();

        //Check that the lists still match
        assertEquals(controlAdmin.get(0).getId(), UserData.AdminUsers.get(0).getId());
        assertEquals(controlDoctor.get(0).getId(), UserData.DoctorUsers.get(0).getId());
        assertEquals(controlPatient.get(0).getId(), UserData.PatientUsers.get(0).getId());
        assertEquals(controlSecretary.get(0).getId(), UserData.SecretaryUsers.get(0).getId());

        //Remove the users for real
        UserData.AdminUsers.remove(alex);
        UserData.DoctorUsers.remove(JD);
        UserData.PatientUsers.remove(josh);
        UserData.SecretaryUsers.remove(pam);

        //Serialize the list again to make sure the test data doesn't end up in with real users
        Serialization.SaveUserData();
    }

    @Test
    void CreateUsers(){
        alex.CreateAdmin("Moss", "The IT Dept.", "fire");
        UserData.AdminUsers.get(0).CreateSecretary("John", "Royal William Yard", "punch");
        UserData.AdminUsers.get(0).CreateDoctor("Cox", "Sacred Heart", "Anger");

        Patient.CreateAccountRequest("bob", "builder land", Gender.MALE, 42);
        UserData.SecretaryUsers.get(0).ApprovePatientAccount(SystemData.accountRequests.get(0));

        assertTrue(UserData.AdminUsers.contains(UserData.AdminUsers.get(0)));
        assertTrue(UserData.DoctorUsers.contains(UserData.DoctorUsers.get(0)));
        assertTrue(UserData.PatientUsers.contains(UserData.PatientUsers.get(0)));
        assertTrue(UserData.SecretaryUsers.contains(UserData.SecretaryUsers.get(0)));
    }

    @Test
    void RemoveUsers(){
        UserData.AdminUsers.add(alex);
        UserData.DoctorUsers.add(JD);
        UserData.PatientUsers.add(josh);
        UserData.SecretaryUsers.add(pam);

        assertTrue(UserData.AdminUsers.contains(alex));
        assertTrue(UserData.DoctorUsers.contains(JD));
        assertTrue(UserData.PatientUsers.contains(josh));
        assertTrue(UserData.SecretaryUsers.contains(pam));

        josh.RequestAccountTermination();
        pam.ApproveAccountTermination(josh);

        alex.RemoveDoctor(JD);
        alex.RemoveSecretary(pam);

        UserData.AdminUsers.remove(alex);

        assertFalse(UserData.AdminUsers.contains(alex));
        assertFalse(UserData.DoctorUsers.contains(JD));
        assertFalse(UserData.PatientUsers.contains(josh));
        assertFalse(UserData.SecretaryUsers.contains(pam));
    }



}