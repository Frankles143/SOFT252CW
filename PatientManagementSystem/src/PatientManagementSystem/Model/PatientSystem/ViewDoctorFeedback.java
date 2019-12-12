package PatientManagementSystem.Model.PatientSystem;

import PatientManagementSystem.Model.System.DoctorFeedback;
import PatientManagementSystem.Model.Users.Doctor;

import java.util.ArrayList;

/**
 * A class to return all the feedback for one doctor
 * @author Josh Franklin
 */
public abstract class ViewDoctorFeedback {
    /**
     * Returns the checked feedback attached to that Doctor
     * @param doctor
     * @return
     * @author Josh Franklin
     */
    public ArrayList<DoctorFeedback> ViewDoctorFeedback(Doctor doctor){
        ArrayList<DoctorFeedback> feedback = null;
        try {
            feedback = doctor.getFeedback();
        }
        catch (Exception e) {
            System.out.println("Error getting feedback!");
        }
        return feedback;
    }
}
