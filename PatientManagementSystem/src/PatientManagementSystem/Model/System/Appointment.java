package PatientManagementSystem.Model.System;

import java.util.ArrayList;
import java.util.Date;

/**
 * An appointment object to hold relevant information
 * @author Josh Franklin
 */
public class Appointment {
    private String doctorID;
    private String patientID;
    private ArrayList<Date> possibleDates;

    public Appointment(String doctorID, String patientID, ArrayList<Date> possibleDates) {
        this.doctorID = doctorID;
        this.patientID = patientID;
        this.possibleDates = possibleDates;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public ArrayList<Date> getPossibleDates() {
        return possibleDates;
    }

    public void setPossibleDates(ArrayList<Date> possibleDates) {
        this.possibleDates = possibleDates;
    }
}

