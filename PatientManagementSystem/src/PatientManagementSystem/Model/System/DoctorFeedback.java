package PatientManagementSystem.Model.System;

import PatientManagementSystem.Model.UserIDRegex;

public class DoctorFeedback {
    private String doctorID;
    private int rating;
    private String feedbackNotes;

    public DoctorFeedback(String doctorID, int rating, String feedbackNotes) {
        setDoctorID(doctorID);
        setRating(rating);
        this.feedbackNotes = feedbackNotes;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        if (doctorID.matches(UserIDRegex.getRegex())) {
            this.doctorID = doctorID;
        } else {
            System.out.println("ID does not match format");
        }
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
