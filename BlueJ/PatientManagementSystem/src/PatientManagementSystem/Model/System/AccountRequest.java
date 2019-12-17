package src.PatientManagementSystem.Model.System;

/**
 * An account request object to hold relevant information for secretaries to check
 * @author Josh Franklin
 */
public class AccountRequest {
    private String newPatientName;
    private String newPatientAddress;

    public AccountRequest(String newPatientName, String newPatientAddress) {
        this.newPatientName = newPatientName;
        this.newPatientAddress = newPatientAddress;
    }

}
