package Model.System;

import PatientManagementSystem.Model.UserIDRegex;
import PatientManagementSystem.Model.Users.Doctor;

/**
 * Doctor feedback object that can attached to relevant Doctor
 * @author Josh Franklin
 */
public class DoctorFeedback {
    private Doctor doctor;
    private int rating;
    private String feedbackNotes;

    public DoctorFeedback(Doctor doctor, int rating, String feedbackNotes) {
        this.doctor = doctor;
        setRating(rating);
        this.feedbackNotes = feedbackNotes;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating >= 1 && rating <= 10){
            this.rating = rating;
        } else {
            System.out.println("Please enter a value between 1 and 10");
        }
    }

    public String getFeedbackNotes() {
        return feedbackNotes;
    }

    public void setFeedbackNotes(String feedbackNotes) {
        this.feedbackNotes = feedbackNotes;
    }
}
