package Model.System;

import PatientManagementSystem.Model.System.DoctorFeedback;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class to store unchecked feedback data in an Array List
 * @author Josh Franklin
 */
public class FeedbackData implements Serializable {
    public static ArrayList<DoctorFeedback> uncheckedFeedback = new ArrayList<>();
}
