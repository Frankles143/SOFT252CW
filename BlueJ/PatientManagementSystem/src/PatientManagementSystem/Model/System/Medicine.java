package src.PatientManagementSystem.Model.System;

/**
 * Medicine object to store relevant information
 * @author Josh Franklin
 */
public class Medicine {
    private String medicineName;

    public Medicine(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }
}
