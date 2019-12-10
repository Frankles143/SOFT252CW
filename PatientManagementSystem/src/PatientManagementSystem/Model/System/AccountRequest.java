package PatientManagementSystem.Model.System;

public class AccountRequest {
    private String newPatientName;
    private String newPatientAddress;

    public AccountRequest(String newPatientName, String newPatientAddress) {
        this.newPatientName = newPatientName;
        this.newPatientAddress = newPatientAddress;
    }

}
