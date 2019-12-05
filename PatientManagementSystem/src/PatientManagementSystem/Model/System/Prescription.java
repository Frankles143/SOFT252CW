package PatientManagementSystem.Model.System;

public class Prescription {
    private String doctorID;
    private String patientID;
    private String doctorNotes;
    private Medicine medicine;
    private int quantity;
    private String dosage;
    private final String regex = "^[ADPS]\\d{4}$";  //This will only allow pattern of A0123

    public Prescription(String doctorID, String patientID, String doctorNotes, Medicine medicine, int quantity, String dosage) {
        setDoctorID(doctorID);
        setPatientID(patientID);
        this.doctorNotes = doctorNotes;
        this.medicine = medicine;
        this.quantity = quantity;
        this.dosage = dosage;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        if (doctorID.matches(regex)) {
            this.doctorID = doctorID;
        } else {
            System.out.println("ID does not match format");
        }
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        if (patientID.matches(regex)) {
            this.patientID = patientID;
        } else {
            System.out.println("ID does not match format");
        }
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
}
