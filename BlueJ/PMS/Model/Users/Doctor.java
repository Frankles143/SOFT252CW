package Model.Users;

import PatientManagementSystem.Model.System.ConsultationNote;
import PatientManagementSystem.Model.System.DoctorFeedback;

import java.util.ArrayList;
import java.util.Date;

public class Doctor extends AbstractPerson {
    public ArrayList<DoctorFeedback> feedback;

    public Doctor(String id, String name, String address) {
        super(id, name, address);
    }

    public ArrayList<DoctorFeedback> getFeedback() {
        return feedback;
    }

    /**
     * Create a consultation note and attach it to relevant patient
     * @param patient
     * @param date
     * @param notes
     * @author Josh Franklin
     */
    public void CreateConsultationNotes(Patient patient, Date date, String notes){
        ConsultationNote newNote = null;
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
     * @param patient
     * @return ArrayList of ConsultationNote
     */
    public ArrayList<ConsultationNote> ViewPatientHistory(Patient patient){
        return patient.getConsultationNotes();
    }
}
