package PatientManagementSystem.Model.System;

import PatientManagementSystem.UserIDRegex;

public class DoctorFeedback {
    private String doctorID;
    private int rating;
    private String feedbackNotes;

    public DoctorFeedback(String doctorID, int rating, String feedbackNotes) {
        setDoctorID(doctorID);
        this.rating = rating;
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
        this.rating = rating;
    }

    public String getFeedbackNotes() {
        return feedbackNotes;
    }

    public void setFeedbackNotes(String feedbackNotes) {
        this.feedbackNotes = feedbackNotes;
    }
}
