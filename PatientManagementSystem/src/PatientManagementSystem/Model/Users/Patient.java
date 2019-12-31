package PatientManagementSystem.Model.Users;

import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.*;
import PatientManagementSystem.Model.Gender;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;

public class Patient extends AbstractPerson {

    private Gender gender;
    private int age;
    private ArrayList<ConsultationNote> consultationNotes;
    private ArrayList<Appointment> appointments;
    private ArrayList<Prescription> prescriptions;


    public Patient(String id, String name, String address, String password, Gender gender, int age) {
        super(id, name, address, password);
        this.gender = gender;
        this.age = age;
        this.consultationNotes = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    public Patient(String id, String name, String address, Gender gender, int age) {
        super(id, name, address);
        this.gender = gender;
        this.age = age;
        this.consultationNotes = new ArrayList<>();
        this.appointments = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


    public void setPassword(String password){
        this.setEncryptedPassword(Password.HashPassword(password, this.getSalt()).get());
    }

    @Override
    public void update(AbstractPerson person) {
        if (Logon.getCurrentPatient() == person) {
            //Create a dialog box
        }
    }

    /**
     * Sorts the arraylist into numerical order by ID and then counts through assigning the new ID to the first available
     * @return returns an unused ID
     * @author Josh Franklin
     */
    public static String CreateId(){
        UserData.PatientUsers.sort(Comparator.comparing(AbstractPerson::getId));

        DecimalFormat formatter = new DecimalFormat("0000");
        int idNumber = 1;
        String id = "";

        for (Patient patient : UserData.PatientUsers) {

            id = "P" + formatter.format(idNumber);

            if (!patient.getId().equals(id)){
                return id;
            }
            idNumber++;
        }

        id = "P" + formatter.format(idNumber);
        return id;
    }

    public static boolean CreateAccountRequest(String name, String address, Gender gender, int age){
        try {
            AccountRequest newAccount = new AccountRequest(name, address, gender, age);
            SystemData.accountRequests.add(newAccount);
            System.out.println("Account request successful");

            Message.CreateMessage(newAccount.getName(), "Secretary", "Someone has requested a new account");
            return true;
        } catch (Exception e) {
            System.out.println("Could not create account request: " + e);
            return false;
        }
    }

    /**
     * Can be used by the controller to output patient history
     * @return ArrayList of this patients consultation notes
     * @author Josh Franklin
     */
    public ArrayList<ConsultationNote> getConsultationNotes() {
        return consultationNotes;
    }

    public void addConsultationNotes(ConsultationNote newNote){
        consultationNotes.add(newNote);
    }

    public void removeConsultationNote(ConsultationNote note){
        consultationNotes.remove(note);
    }

    /**
     * Can be used by the controller to output all of the patients prescriptions
     * @return ArrayList of this patients prescriptions
     * @author Josh Franklin
     */
    public ArrayList<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void addPrescription(Prescription newPrescription){
        prescriptions.add(newPrescription);
    }

    public void removePrescription(Prescription prescriptionToBeRemoved){
        prescriptions.remove(prescriptionToBeRemoved);
    }

    /**
     * Can be used by patient to see all appointments
     * @author Josh Franklin
     * @return list of appointments
     */
    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void addAppointment(Appointment newAppointment){
        appointments.add(newAppointment);
    }

    public void removeAppointment(Appointment appointmentToBeRemoved){
        appointments.remove(appointmentToBeRemoved);
    }

    /**
     * Creates feedback object and stores it where the admins can check through it
     * @param doctor doctor you are creating feedback for
     * @param rating an int between 1-10
     * @param feedbackNotes the actual feedback string
     * @author Josh Franklin
     */
    public void CreateFeedback(Doctor doctor, int rating, String feedbackNotes){
        try {
            DoctorFeedback newFeedback = new DoctorFeedback(doctor, rating, feedbackNotes);
            SystemData.uncheckedFeedback.add(newFeedback);
            Message.CreateMessage(Patient.this.getName(), "Admin", "New doctor feedback to check");
        } catch (Exception e) {
            System.out.println("Cannot add new feedback" + e);
        }
    }

    /**
     * Returns the checked feedback attached to that Doctor
     * @param doctor doctor that you want all feedback for
     * @return ArrayList of feedback objects
     * @author Josh Franklin
     */
    public ArrayList<DoctorFeedback> ViewDoctorFeedback(Doctor doctor){
        ArrayList<DoctorFeedback> feedback = null;
        try {
            feedback = doctor.getFeedback();
        }
        catch (Exception e) {
            System.out.println("Error getting feedback!" + e);
        }
        return feedback;
    }

    /**
     * Creates a new appointment using an ArrayList of dates supplied by the controller and view
     * @author Josh Franklin
     * @param doctor doctor who patient is requesting to see
     * @param possibleDates a list of date objects for secretary to pick between
     */
    public void AppointmentRequest(Doctor doctor, ArrayList<LocalDateTime> possibleDates){
        try {
            Appointment newAppointment = new Appointment(doctor, Patient.this, possibleDates);
            SystemData.appointmentRequests.add(newAppointment);
            Message.CreateMessage(Patient.this.getName(), "Secretary", "A patient has requested a new appointment");
        } catch (Exception e) {
            System.out.println("Appointment request failed" + e);
        }
    }

    /**
     * Current patient adds themselves to the termination request list
     * @author Josh Franklin
     */
    public void RequestAccountTermination(){
        SystemData.accountTerminationRequests.add(Patient.this);

        Message.CreateMessage(Patient.this.getName(), "Secretary", "A patient has requested an account termination");
    }
}
