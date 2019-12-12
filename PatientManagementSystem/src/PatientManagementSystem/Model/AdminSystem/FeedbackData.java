package PatientManagementSystem.Model.AdminSystem;

import PatientManagementSystem.Model.System.DoctorFeedback;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class to store feedback data in Array Lists
 * @author Josh Franklin
 */
public class FeedbackData implements Serializable {
    public static ArrayList<DoctorFeedback> uncheckedFeedback = new ArrayList<>();
    public static ArrayList<DoctorFeedback> finalFeedback = new ArrayList<>();
}
