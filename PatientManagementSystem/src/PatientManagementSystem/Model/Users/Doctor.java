package PatientManagementSystem.Model.Users;

import PatientManagementSystem.Model.System.*;

import javax.print.Doc;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class Doctor extends AbstractPerson {
    private ArrayList<DoctorFeedback> feedback;

    public Doctor(String id, String name, String address, String password) {
        super(id, name, address, password);
        feedback = new ArrayList<>();
    }

    @Override
    public void update(AbstractPerson person) {
        for (Doctor allDoctors: UserData.DoctorUsers) {
            //Notify DRs on the GUI
        }
    }

    /**
     * Sorts the arraylist into numerical order by ID and then counts through assigning the new ID to the first available
     * @return returns an unused ID
     * @author Josh Franklin
     */
    public static String CreateId(){
        UserData.DoctorUsers.sort(Comparator.comparing(AbstractPerson::getId));

        DecimalFormat formatter = new DecimalFormat("0000");
        int idNumber = 1;
        String id = "";

        for (Doctor doctor : UserData.DoctorUsers) {

            id = "D" + formatter.format(idNumber);

            if (!doctor.getId().equals(id)){
                return id;
            }
            idNumber++;
        }

        id = "D" + formatter.format(idNumber);
        return id;
    }

    public ArrayList<DoctorFeedback> getFeedback() {
        return feedback;
    }

    public void addFeedback(DoctorFeedback newFeedback){
        this.feedback.add(newFeedback);
    }

    /**
     * Create a consultation note and attach it to relevant patient
     * @author Josh Franklin
     * @param patient owner of the note
     * @param date date of the consultation
     * @param notes string contents of the consultation note
     */
    public void CreateConsultationNotes(Patient patient, Date date, String notes){
        try {
            ConsultationNote newNote = new ConsultationNote(Doctor.this, patient, date, notes);
            patient.addConsultationNotes(newNote);
            Message.CreateMessage(Doctor.this.getName(), patient, "You have new consultation notes to view in your History tab");
        }
        catch (Exception e) {
            System.out.println("Could not create new note!" + e);
        }
    }

    /**
     * Returns all of patient consultation notes in an array for the controller to output
     * @param patient patient who's history we are viewing
     * @return ArrayList of ConsultationNote
     * @author Josh Franklin
     */
    public ArrayList<ConsultationNote> ViewPatientHistory(Patient patient){
        return patient.getConsultationNotes();
    }

    public void CreateNewMedicine(String medicineName){
        try {
            Medicine newMedicine = new Medicine(medicineName);
            SystemData.medicines.add(newMedicine);
            Message.CreateMessage(Doctor.this.getName(), "Secretary", "New medicine, please make sure we have some in stock");
        } catch (Exception e) {
            System.out.println("Unable to create new medicine");
        }
    }

    /**
     * Creates a new prescription and attaches it to a patient
     * @author Josh Franklin
     * @param patient owner of the prescription
     * @param notes any additional notes required
     * @param medicine medicine object
     * @param qty amount of medicine being given
     * @param dosage notes of how much to take and how often
     */
    public void PrescribeMedicine(Patient patient, String notes, Medicine medicine, int qty, String dosage){
        try {
            Prescription newPrescription = new Prescription(Doctor.this, patient, notes, medicine, qty, dosage);
            patient.addPrescription(newPrescription);
            Message.CreateMessage(Doctor.this.getName(), patient, "You have a new prescription waiting for you");
        } catch (Exception e) {
            System.out.println("Unable to prescribe medicine" + e);
        }
    }

    /**
     * Creates a new appointment for a patient
     * @param patient patient for appointment to be made for
     * @param date date of the appointment
     * @author Josh Franklin
     */
    public void CreateAppointment(Patient patient, Date date){
        try {
            Appointment newAppointment = new Appointment(Doctor.this, patient, date);

            patient.addAppointment(newAppointment);
        } catch (Exception e) {
            System.out.println("Could not create new appointment" + e);
        }
    }

    /**
     * This function returns only appointments relevant to the doctor calling the method, after checking all patients and all appointments.
     * @return an ArrayList of Appointment relevant only to the doctor who invoked the method
     * @author Josh Franklin
     */
    public ArrayList<Appointment> ViewAppointments(){
        ArrayList<Appointment> relevantAppointments = new ArrayList<>();

        for (Patient patient: UserData.PatientUsers) {
            for (Appointment appointment : patient.getAppointments()) {
                if (appointment.getDoctor() == Doctor.this) {
                    relevantAppointments.add(appointment);
                }
            }
        }
        return relevantAppointments;
    }
}










