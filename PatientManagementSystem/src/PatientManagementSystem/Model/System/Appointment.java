package PatientManagementSystem.Model.System;

import PatientManagementSystem.Model.Users.Doctor;
import PatientManagementSystem.Model.Users.Patient;

import java.util.ArrayList;
import java.util.Date;

/**
 * An appointment object to hold relevant information
 * @author Josh Franklin
 */
public class Appointment {
    private Doctor doctor;
    private Patient patient;
    private ArrayList<Date> possibleDates;

    public Appointment(Doctor doctor, Patient patient, ArrayList<Date> possibleDates) {
        this.doctor = doctor;
        this.patient = patient;
        this.possibleDates = possibleDates;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public ArrayList<Date> getPossibleDates() {
        return possibleDates;
    }

    public void setPossibleDates(ArrayList<Date> possibleDates) {
        this.possibleDates = possibleDates;
    }
}

