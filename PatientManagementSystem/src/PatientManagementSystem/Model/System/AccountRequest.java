package PatientManagementSystem.Model.System;

public class AccountRequest {
    private String newPatientName;
    private String newPatientAddress;

    public AccountRequest(String newPatientName, String newPatientAddress) {
        this.newPatientName = newPatientName;
        this.newPatientAddress = newPatientAddress;
    }

    public String getNewPatientName() {
        return newPatientName;
    }

    public void setNewPatientName(String newPatientName) {
        this.newPatientName = newPatientName;
    }

    public String getNewPatientAddress() {
        return newPatientAddress;
    }

    public void setNewPatientAddress(String newPatientAddress) {
        this.newPatientAddress = newPatientAddress;
    }
}
