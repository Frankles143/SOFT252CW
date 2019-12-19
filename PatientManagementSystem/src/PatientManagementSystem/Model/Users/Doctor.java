package PatientManagementSystem.Model.Users;

import PatientManagementSystem.Model.System.*;

import javax.print.Doc;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class Doctor extends AbstractPerson {
    private ArrayList<DoctorFeedback> feedback;
    private static int count = 0;

    public Doctor(String id, String name, String address) {
        super(id, name, address);
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
        ConsultationNote newNote;
        try {
            newNote = new ConsultationNote(Doctor.this, patient, date, notes);
            patient.addConsultationNotes(newNote);
        }
        catch (Exception e) {
            System.out.println("Could not create new note!");
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
        Medicine newMedicine = new Medicine(medicineName);
        SystemData.medicines.add(newMedicine);
    }

    /**
     * Creates a new prescription and attaches it to a patient
     * @author Josh Franklin
     */
    public void PrescribeMedicine(Patient patient, String notes, Medicine medicine, int qty, String dosage){
        Prescription newPrescription = new Prescription(Doctor.this, patient, notes, medicine, qty, dosage);
        patient.addPrescription(newPrescription);
    }

    /**
     * Creates a new appointment for a patient
     * @author Josh Franklin
     */
    public void CreateAppointment(Patient patient, Date date){
        Appointment newAppointment = new Appointment(Doctor.this, patient, date);

        patient.addAppointment(newAppointment);
    }
}
