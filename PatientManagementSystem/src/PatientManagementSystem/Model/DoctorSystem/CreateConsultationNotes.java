package PatientManagementSystem.Model.DoctorSystem;

import PatientManagementSystem.Model.System.ConsultationNote;
import PatientManagementSystem.Model.Users.Doctor;
import PatientManagementSystem.Model.Users.Patient;

import java.util.Date;

/**
 * A class to handle the creation of consultation notes
 * @author Josh Franklin
 */
public class CreateConsultationNotes {

    /**
     * Create a consultation note and attach it to relevant patient
     * @param doctor
     * @param patient
     * @param date
     * @param notes
     * @author Josh Franklin
     */
    public void CreateConsultationNotes(Doctor doctor, Patient patient, Date date, String notes){
        ConsultationNote newNote = null;
        try {
            newNote = new ConsultationNote(doctor, patient, date, notes);
            patient.consultationNotes.add(newNote);
        }
        catch (Exception e) {
            System.out.println("Could not create new note!");
        }
    }
}
