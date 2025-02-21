package PatientManagementSystem.Model.System;

import PatientManagementSystem.Model.Users.Doctor;
import PatientManagementSystem.Model.Users.Patient;

import java.io.Serializable;

/**
 * A prescription object to hold relevant information
 * @author Josh Franklin
 */
public class Prescription implements Serializable {
    private Doctor doctor;
    private Patient patient;
    private String doctorNotes;
    private Medicine medicine;
    private int quantity;
    private String dosage;
    private boolean received;

    public Prescription(Doctor doctor, Patient patient, String doctorNotes, Medicine medicine, int quantity, String dosage) {
        this.doctor = doctor;
        this.patient = patient;
        this.doctorNotes = doctorNotes;
        this.medicine = medicine;
        this.quantity = quantity;
        this.dosage = dosage;
        this.received = false;
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

    public String getDoctorNotes() {
        return doctorNotes;
    }

    public void setDoctorNotes(String doctorNotes) {
        this.doctorNotes = doctorNotes;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public boolean isReceived() {
        return received;
    }

    public boolean PrescriptionReceived(){
        if (!this.received){
            this.received = true;
            return true;
        } else {
            System.out.println("Prescription already filled out!");
            return false;
        }
    }
}
