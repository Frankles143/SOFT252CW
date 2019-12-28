package PatientManagementSystem.Model.Users.UserTests;

import PatientManagementSystem.Model.Gender;
import PatientManagementSystem.Model.Serialization;
import PatientManagementSystem.Model.UserIDRegex;
import PatientManagementSystem.Model.Users.*;
import org.junit.jupiter.api.Test;

import javax.print.Doc;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class UserTests {
    public static ArrayList<Admin> testAdminUsers = new ArrayList<>();
    public static ArrayList<Doctor> testDoctorUsers = new ArrayList<>();
    public static ArrayList<Patient> testPatientUsers = new ArrayList<>();
    public static ArrayList<Secretary> testSecretaryUsers = new ArrayList<>();

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

        //Serialize and Deserialize all the user data
        Serialization.SaveUserData();

        UserData.AdminUsers.remove(alex);
        UserData.DoctorUsers.remove(JD);
        UserData.PatientUsers.remove(josh);
        UserData.SecretaryUsers.remove(pam);

        Serialization.LoadUserData();

        //Check that the lists still match
        assertEquals(controlAdmin.get(0).getId(), UserData.AdminUsers.get(0).getId());
        assertEquals(controlDoctor.get(0).getId(), UserData.DoctorUsers.get(0).getId());
        assertEquals(controlPatient.get(0).getId(), UserData.PatientUsers.get(0).getId());
        assertEquals(controlSecretary.get(0).getId(), UserData.SecretaryUsers.get(0).getId());

        UserData.AdminUsers.remove(alex);
        UserData.DoctorUsers.remove(JD);
        UserData.PatientUsers.remove(josh);
        UserData.SecretaryUsers.remove(pam);

        //Serialize the list again after removing the users
        Serialization.SaveUserData();
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

        assertFalse(UserData.PatientUsers.contains(josh));
        assertFalse(UserData.DoctorUsers.contains(JD));
        assertFalse(UserData.PatientUsers.contains(josh));
        assertFalse(UserData.SecretaryUsers.contains(pam));
    }

    @Test
    void OutputUsers(){
        Serialization.LoadUserData();

        for (Admin person: UserData.AdminUsers){
            System.out.println("Name: " + person.getName());
        }
    }
}