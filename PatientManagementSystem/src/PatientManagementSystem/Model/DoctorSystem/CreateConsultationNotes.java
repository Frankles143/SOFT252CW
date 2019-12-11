package PatientManagementSystem.Model.DoctorSystem;

import PatientManagementSystem.Model.System.ConsultationData;
import PatientManagementSystem.Model.System.ConsultationNote;
import PatientManagementSystem.Model.Users.Doctor;
import PatientManagementSystem.Model.Users.Patient;

import java.util.Date;

public class CreateConsultationNotes {

    public void CreateConsultationNotes(Doctor doctor, Patient patient, Date date, String notes){
        ConsultationNote newNote = null;
        try {
            newNote = new ConsultationNote(doctor, patient, date, notes);
            ConsultationData.ConsultationArray.add(newNote);
        }
        catch (Exception e) {
            System.out.println("Could not create new note!");
        }
    }
}
