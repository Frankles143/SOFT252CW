package PatientManagementSystem.Model.System;

import PatientManagementSystem.Model.Users.Doctor;
import PatientManagementSystem.Model.Users.Patient;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * An appointment object to hold relevant information
 * @author Josh Franklin
 */
public class Appointment implements Serializable {
    private Doctor doctor;
    private Patient patient;
    private ArrayList<LocalDateTime> possibleDates;
    private LocalDateTime confirmedDate;

    //A patient would send a request in this form
    public Appointment(Doctor doctor, Patient patient, ArrayList<LocalDateTime> possibleDates) {
        this.doctor = doctor;
        this.patient = patient;
        this.possibleDates = possibleDates;
    }

    //A secretary or doctor would create an appointment with one date
    public Appointment(Doctor doctor, Patient patient, LocalDateTime confirmedDate){
        this.doctor = doctor;
        this.patient = patient;
        this.confirmedDate = confirmedDate;
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

    public ArrayList<LocalDateTime> getPossibleDates() {
        return possibleDates;
    }

    public void setPossibleDates(ArrayList<LocalDateTime> possibleDates) {
        this.possibleDates = possibleDates;
    }

    public LocalDateTime getConfirmedDate() {
        return confirmedDate;
    }

    public void setConfirmedDate(LocalDateTime confirmedDate) {
        this.confirmedDate = confirmedDate;
    }
}

