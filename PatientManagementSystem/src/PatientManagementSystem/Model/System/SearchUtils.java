package PatientManagementSystem.Model.System;

import PatientManagementSystem.Model.Users.*;

public abstract class SearchUtils {

    public static AbstractPerson FindUser(String userId){
        String userType = userId.substring(0,1);
        switch (userType) {
            case "A":
                for (Admin admin : UserData.AdminUsers) {
                    if (admin.getId().contains(userId)){
                        return admin;
                    }
                }
                break;
            case "D":
                for (Doctor doctor : UserData.DoctorUsers) {
                    if (doctor.getId().contains(userId)){
                        return doctor;
                    }
                }
                break;
            case "P":
                for (Patient patient : UserData.PatientUsers) {
                    if (patient.getId().contains(userId)){
                        return patient;
                    }
                }
                break;
            case "S":
                for (Secretary secretary : UserData.SecretaryUsers) {
                    if (secretary.getId().contains(userId)){
                        return secretary;
                    }
                }
                break;
            default:
                System.out.println("Incorrect User ID");
        }
        return null;
    }
}
