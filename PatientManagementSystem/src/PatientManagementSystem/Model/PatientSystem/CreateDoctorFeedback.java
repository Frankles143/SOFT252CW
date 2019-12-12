package PatientManagementSystem.Model.PatientSystem;

import PatientManagementSystem.Model.AdminSystem.FeedbackData;
import PatientManagementSystem.Model.System.DoctorFeedback;
import PatientManagementSystem.Model.Users.Doctor;

/**
 * A class to create feedback that is sent to the admins to be checked.
 * @author Josh Franklin
 */
public abstract class CreateDoctorFeedback {

    /**
     * Creates feedback object and stores it where the admins can check through it
     * @param doctor
     * @param rating
     * @param feedbackNotes
     * @author Josh Franklin
     */
    public void CreateFeedback(Doctor doctor, int rating, String feedbackNotes){
        DoctorFeedback newFeedback = null;
        try {
            newFeedback = new DoctorFeedback(doctor, rating, feedbackNotes);
            FeedbackData.uncheckedFeedback.add(newFeedback);
        } catch (Exception e) {
            System.out.println("Cannot add new feedback");
        }
    }
}
