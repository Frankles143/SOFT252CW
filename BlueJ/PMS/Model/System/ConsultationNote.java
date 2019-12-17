package Model.System;

import PatientManagementSystem.Model.Users.Doctor;
import PatientManagementSystem.Model.Users.Patient;

import java.io.Serializable;
import java.util.Date;

/**
 * Consultation note object that can be attached to a patient
 * @author Josh Franklin
 */
public class ConsultationNote implements Serializable {
    Doctor doctor;
    Patient patient;
    Date date;
    String notes;

    public ConsultationNote(Doctor doctor, Patient patient, Date date, String notes) {
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.notes = notes;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
