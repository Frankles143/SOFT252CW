package PatientManagementSystem.Model.Users;

import PatientManagementSystem.Model.System.*;

import javax.print.Doc;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class Doctor extends AbstractPerson {
    private ArrayList<DoctorFeedback> feedback;
    private static int count = 0;

    public Doctor(String id, String name, String address) {
        super(id, name, address);
    }

    @Override
    public void update(AbstractPerson person) {
        for (Doctor allDoctors: UserData.DoctorUsers) {
            //Notify DRs on the GUI
        }
    }

    public static String CreateId(){
        DecimalFormat formatter = new DecimalFormat("000");

        String newID = "D" + formatter.format(++count);

        return newID;
    }

    public ArrayList<DoctorFeedback> getFeedback() {
        return feedback;
    }

    /**
     * Create a consultation note and attach it to relevant patient
     * @author Josh Franklin
     */
    public void CreateConsultationNotes(Patient patient, Date date, String notes){
        try {
            ConsultationNote newNote = new ConsultationNote(Doctor.this, patient, date, notes);
            patient.addConsultationNotes(newNote);
        }
        catch (Exception e) {
            System.out.println("Could not create new note!" + e);
        }
    }

    /**
     * Returns all of patient consultation notes in an array for the controller to output
     * @return ArrayList of ConsultationNote
     */
    public ArrayList<ConsultationNote> ViewPatientHistory(Patient patient){
        return patient.getConsultationNotes();
    }

    public void CreateNewMedicine(String medicineName){
        try {
            Medicine newMedicine = new Medicine(medicineName);
            SystemData.medicines.add(newMedicine);
        } catch (Exception e) {
            System.out.println("Unable to create new medicine");
        }
    }

    /**
     * Creates a new prescription and attaches it to a patient
     * @author Josh Franklin
     */
    public void PrescribeMedicine(Patient patient, String notes, Medicine medicine, int qty, String dosage){
        try {
            Prescription newPrescription = new Prescription(Doctor.this, patient, notes, medicine, qty, dosage);
            patient.addPrescription(newPrescription);
        } catch (Exception e) {
            System.out.println("Unable to prescribe medicine" + e);
        }
    }

    /**
     * Creates a new appointment for a patient
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










