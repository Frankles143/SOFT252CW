package PatientManagementSystem.Model.Users;

import PatientManagementSystem.Model.System.*;
import PatientManagementSystem.Model.Gender;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class Patient extends AbstractPerson implements Serializable {

    private Gender gender;
    private int age;
    private static int count = 0;
    private ArrayList<ConsultationNote> consultationNotes;
    private ArrayList<Appointment> appointments;
    private ArrayList<Prescription> prescriptions;


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

    public static int getCount() {
        return count;
    }

    public static void setCount(int count) {
        Patient.count = count;
    }

    public static String CreateId(){
        DecimalFormat formatter = new DecimalFormat("000");

        String newID = "P" + formatter.format(++count);

        return newID;
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
     */
    public void AppointmentRequest(Doctor doctor, ArrayList<Date> possibleDates){
        try {
            Appointment newAppointment = new Appointment(doctor, Patient.this, possibleDates);
            SystemData.appointmentRequests.add(newAppointment);
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

        //Notify Secretary
    }
}
