package PatientManagementSystem.Controller;

import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.ConsultationNote;

import java.util.ArrayList;

public abstract class PatientController {

    public static ArrayList<ConsultationNote> GetPatientHistory(){
        return Logon.getCurrentPatient().getConsultationNotes();
    }
}
