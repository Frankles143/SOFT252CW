package PatientManagementSystem.Model.Tests;

import PatientManagementSystem.Model.Gender;
import PatientManagementSystem.Model.System.Serialization;
import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.*;
import PatientManagementSystem.Model.UserIDRegex;
import PatientManagementSystem.Model.Users.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

/**
 * A class to hold all of my JUnit tests
 * @author Josh Franklin
 */
class JUnitTests {
    //Create one of each user
    private Admin alex = new Admin("A9999", "Alex Barret", "Plymouth", "password");
    private Doctor JD = new Doctor("D9999", "John Dorian", "America", "password");
    private Patient josh = new Patient("P9999", "Josh Franklin", "Plymouth", "password", Gender.MALE, 24);
    private Secretary pam = new Secretary("S9999", "Pam Something", "Someplace", "password");

    /**
     * Checks that the regex system for the IDs is in place and working
     * @author Josh Franklin
     */
    @Test
    void setId() {
        String goodString = "A0123";
        String badString = "00A123AA";

        assertTrue(goodString.matches(UserIDRegex.getRegex()));
        assertFalse(badString.matches(UserIDRegex.getRegex()));
    }

    /**
     * Checks that the CreateId function is working correctly when creating a new patient
     * @author Josh Franklin
     */
    @Test
    void CreateNewId(){
        String newID = Patient.CreateId();
        assertTrue(newID.matches(UserIDRegex.getRegex()));

        System.out.println(newID);
    }

    /**
     * Checks the verify password function, to make sure they match with the initial, un-hashed password
     * @author Josh Franklin
     */
    @Test
    void VerifyPasswords(){
        assertTrue(Password.VerifyPassword("password", josh));
        assertFalse(Password.VerifyPassword("notPassword", josh));
    }

    /**
     * Makes sure the password change function works correctly to change the encrypted password and the salt for that person
     * @author Josh Franklin
     */
    @Test
    void PasswordChange(){
        String oldSalt = josh.getSalt();
        alex.ChangeUserPassword("thisIsANewPassword", josh);
        String newSalt = josh.getSalt();

        assertTrue(Password.VerifyPassword("thisIsANewPassword", josh));
        assertFalse(Password.VerifyPassword("password", josh));
        assertNotEquals(oldSalt, newSalt);
    }

    /**
     * This adds a bunch of people to the UserData list, then tests that they are still there after serializing, removing them and de-serializing.
     * @author Josh Franklin
     */
    @Test
    void SaveLoadUser(){
        //Load all data so we don't overwrite any real data
        Serialization.LoadUserData();

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
        assertEquals(controlAdmin.contains(alex), UserData.AdminUsers.contains(alex));
        assertEquals(controlDoctor.contains(JD), UserData.DoctorUsers.contains(JD));
        assertEquals(controlPatient.contains(josh), UserData.PatientUsers.contains(josh));
        assertEquals(controlSecretary.contains(pam), UserData.SecretaryUsers.contains(pam));

        //Remove the users for real
        UserData.AdminUsers.remove(alex);
        UserData.DoctorUsers.remove(JD);
        UserData.PatientUsers.remove(josh);
        UserData.SecretaryUsers.remove(pam);

        //Serialize the list again to make sure the test data doesn't end up in with real users
        Serialization.SaveUserData();
    }

    /**
     * This adds a bunch of data objects to the system data list, then tests that they are still there after serializing, removing them and de-serializing.
     * @author Josh Franklin
     */
    @Test
    void SaveLoadSystemData() {
        LocalDateTime date = LocalDateTime.now();
        ArrayList<LocalDateTime> possibleDates = new ArrayList<>();
        possibleDates.add(date);

        //Load data first so we don't overwrite any real data
        Serialization.LoadSystemData();

        //Create one of each object
        DoctorFeedback docFeedback = new DoctorFeedback(JD, 9, "Warm hands");
        Medicine aspirin = new Medicine("Aspirin");
        AccountRequest newAccount = new AccountRequest("Jos", "Earth", Gender.MALE, 66);
        Appointment newAppointment = new Appointment(JD, josh, possibleDates);
        Message newMessage = new Message("Michael", pam, "You're fired!");

        //Add them to the relevant ArrayList
        SystemData.uncheckedFeedback.add(docFeedback);
        SystemData.medicines.add(aspirin);
        SystemData.accountRequests.add(newAccount);
        josh.RequestAccountTermination();
        SystemData.appointmentRequests.add(newAppointment);
        SystemData.messages.add(newMessage);

        //Set up control ArrayLists
        ArrayList<DoctorFeedback> controlUncheckedFeedback = SystemData.uncheckedFeedback;
        ArrayList<Medicine> controlMedicines = SystemData.medicines;
        ArrayList<AccountRequest> controlAccountRequests = SystemData.accountRequests;
        ArrayList<Patient> controlAccountTerminationRequests = SystemData.accountTerminationRequests;
        ArrayList<Appointment> controlAppointmentRequests = SystemData.appointmentRequests;
        ArrayList<Message> controlMessages = SystemData.messages;

        //Save the data
        Serialization.SaveSystemData();

        //Manually empty the lists
        SystemData.uncheckedFeedback.remove(docFeedback);
        SystemData.medicines.remove(aspirin);
        SystemData.accountRequests.remove(newAccount);
        SystemData.accountTerminationRequests.remove(josh);
        SystemData.appointmentRequests.remove(newAppointment);
        SystemData.messages.remove(newMessage);

        //Load the data
        Serialization.LoadSystemData();

        //Check the lists still match
        assertEquals(controlUncheckedFeedback.contains(docFeedback), SystemData.uncheckedFeedback.contains(docFeedback));
        assertEquals(controlMedicines.contains(aspirin), SystemData.medicines.contains(aspirin));
        assertEquals(controlAccountRequests.contains(newAccount), SystemData.accountRequests.contains(newAccount));
        assertEquals(controlAccountTerminationRequests.contains(josh), SystemData.accountTerminationRequests.contains(josh));
        assertEquals(controlAppointmentRequests.contains(newAppointment), SystemData.appointmentRequests.contains(newAppointment));
        assertEquals(controlMessages.contains(newMessage), SystemData.messages.contains(newMessage));

        //Manually empty the lists again
        SystemData.uncheckedFeedback.remove(docFeedback);
        SystemData.medicines.remove(aspirin);
        SystemData.accountRequests.remove(newAccount);
        SystemData.accountTerminationRequests.remove(josh);
        SystemData.appointmentRequests.remove(newAppointment);
        SystemData.messages.remove(newMessage);

        //Save again to make sure test data has been removed
        Serialization.SaveSystemData();
    }

    /**
     * A test to check the various user creation methods
     * @author Josh Franklin
     */
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

    /**
     * A test to check the various user removal methods
     * @author Josh Franklin
     */
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
        assertFalse(UserData.PatientUsers.contains(josh));

        UserData.PatientUsers.add(josh);
        pam.RemovePatient(josh);

        alex.RemoveDoctor(JD);
        alex.RemoveSecretary(pam);

        UserData.AdminUsers.remove(alex);

        assertFalse(UserData.AdminUsers.contains(alex));
        assertFalse(UserData.DoctorUsers.contains(JD));
        assertFalse(UserData.PatientUsers.contains(josh));
        assertFalse(UserData.SecretaryUsers.contains(pam));
    }

    /**
     * Tests the creation and attaching of consultation notes
     * @author Josh Franklin
     */
    @Test
    void ConsultationNotes(){
        JD.CreateConsultationNotes(josh, "Patient is doing just fine.");

        assertEquals("Patient is doing just fine.", josh.getConsultationNotes().get(0).getNotes());

        System.out.println(JD.ViewPatientHistory(josh).get(0).getNotes());
    }

    /**
     * Checks the creation of medicine, prescriptions and ordering of medicine works, as well a patient getting their prescription
     * @author Josh Franklin
     */
    @Test
    void PrescribeMedicine(){
        Medicine paracetamol = new Medicine("Paracetamol");

        JD.PrescribeMedicine(josh, "Patient needs these to shut up", paracetamol, 8, "Take 2 every 4 hours");

        assertEquals("Patient needs these to shut up", josh.getPrescriptions().get(0).getDoctorNotes());

        pam.OrderMedicine(paracetamol, 100);
        pam.GiveMedicine(josh.getPrescriptions().get(0));


        assertTrue(josh.getPrescriptions().get(0).isReceived());
    }

    /**
     * Checks the various methods of creating appointments
     * @author Josh Franklin
     */
    @Test
    void Appointments(){
        ArrayList<LocalDateTime> possibleDates = new ArrayList<>();
        LocalDateTime date = LocalDateTime.now();
        UserData.PatientUsers.add(josh);
        possibleDates.add(date);

        josh.AppointmentRequest(JD, possibleDates);
        assertEquals(josh, SystemData.appointmentRequests.get(0).getPatient());

        pam.ApproveAppointment(SystemData.appointmentRequests.get(0), date);
        assertEquals(JD, josh.getAppointments().get(0).getDoctor());
        assertThrows(IndexOutOfBoundsException.class, () -> SystemData.appointmentRequests.get(0));
        assertEquals(JD.ViewAppointments().get(0).getPatient(), josh.getAppointments().get(0).getPatient());

        JD.CreateAppointment(josh, date);
        assertEquals(JD, josh.getAppointments().get(1).getDoctor());

        pam.CreateAppointment(JD, josh, date);
        assertEquals(JD, josh.getAppointments().get(2).getDoctor());
    }

    /**
     * Checks the creation, editing and attaching of doctor feedback
     * @author Josh Franklin
     */
    @Test
    void ViewDoctorFeedback(){
        josh.CreateFeedback(JD, 10, "Great hair");
        assertEquals(10, SystemData.uncheckedFeedback.get(0).getRating());

        DoctorFeedback newFeedback = new DoctorFeedback(SystemData.uncheckedFeedback.get(0).getDoctor(), SystemData.uncheckedFeedback.get(0).getRating(), "FANTASTIC hair");
        alex.EditDoctorRatings(SystemData.uncheckedFeedback.get(0), newFeedback);
        assertNotEquals("Great hair", SystemData.uncheckedFeedback.get(0).getFeedbackNotes());

        alex.AttachFeedback(newFeedback);
        assertThrows(IndexOutOfBoundsException.class, () -> SystemData.uncheckedFeedback.get(0));
        assertEquals(newFeedback, JD.getFeedback().get(0));
    }

    /**
     * This test shows that our login and logout methods work correctly, even if someone is logged in
     * @author Josh Franklin
     */
    @Test
    void Logon(){
        assertEquals(0, Logon.getState());

        Logon.AdminLogin("password", alex);
        assertEquals(1, Logon.getState());
        assertEquals("Alex Barret", Logon.getCurrentAdmin().getName());

        Logon.Logout();
        assertEquals(0, Logon.getState());

        Logon.DoctorLogin("password", JD);
        assertEquals(2, Logon.getState());
        Logon.Logout();

        Logon.PatientLogin("password", josh);
        assertEquals(3, Logon.getState());
        Logon.Logout();

        Logon.SecretaryLogin("password", pam);
        assertEquals(4, Logon.getState());

        Logon.AdminLogin("password", alex);
        Logon.Logout();
    }

    @Test
    void UserSearch(){
        UserData.PatientUsers.add(josh);
        AbstractPerson person = SearchUtils.FindUser("P9999");
        System.out.println(person.getName());

        Patient patient = (Patient) SearchUtils.FindUser("P9999");
        System.out.println(patient.getName());

        assertEquals(person, patient);
    }

    @Test
    void SortingTest(){
        Patient josh1 = new Patient("P0001", "Josh Franklin1", "Plymouth", "password", Gender.MALE, 24);
        Patient josh2 = new Patient("P0002", "Josh Franklin2", "Plymouth", "password", Gender.MALE, 24);
        Patient josh3 = new Patient("P0003", "Josh Franklin3", "Plymouth", "password", Gender.MALE, 24);
        Patient josh4 = new Patient("P0004", "Josh Franklin4", "Plymouth", "password", Gender.MALE, 24);
        Patient josh5 = new Patient("P0005", "Josh Franklin5", "Plymouth", "password", Gender.MALE, 24);

        UserData.PatientUsers.add(josh1);
        UserData.PatientUsers.add(josh3);
        UserData.PatientUsers.add(josh2);
        UserData.PatientUsers.add(josh5);
        UserData.PatientUsers.add(josh4);

        for (Patient patient : UserData.PatientUsers){
            System.out.println(patient.getId());
        }

        UserData.PatientUsers.sort(Comparator.comparing(AbstractPerson::getId));

        for (Patient patient : UserData.PatientUsers){
            System.out.println(patient.getId());
        }

        System.out.println(Patient.CreateId());
    }

    @Test
    void DataAddition(){
        Serialization.LoadUserData();
        Serialization.LoadSystemData();

//        UserData.AdminUsers.add(alex);
//        UserData.DoctorUsers.add(JD);
//        UserData.PatientUsers.add(josh);
//        UserData.SecretaryUsers.add(pam);
//
//        JD.CreateConsultationNotes(josh, "Josh is doing very well.");
//
//        LocalDateTime date = LocalDateTime.now();
//        JD.CreateAppointment(josh, date);
//        System.out.println(josh.getAppointments().get(0).getConfirmedDate());
//
//        for (Patient patient : UserData.PatientUsers) {
//            System.out.println(patient.getName());
//        }

//        DoctorFeedback df = new DoctorFeedback(JD, 10, "Great hair");
//        JD.addFeedback(df);
//        for (DoctorFeedback docf : JD.getFeedback())
//        System.out.println(docf.getFeedbackNotes());

//        Message.CreateMessage("System", josh, "Something something dark side");
//        Message.CreateMessage("System", alex, "snape kills dumbledore");
//        Message.CreateMessage("System", pam, "somethings going down!");
//        Message.CreateMessage("System", JD, "hairmets are back in");

//        Password.ChangePassword("password", josh);

        Serialization.SaveUserData();
        Serialization.SaveSystemData();
    }
}





