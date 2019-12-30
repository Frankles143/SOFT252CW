package PatientManagementSystem.Controller;

import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.SearchUtils;
import PatientManagementSystem.Model.Users.Admin;
import PatientManagementSystem.Model.Users.Doctor;
import PatientManagementSystem.Model.Users.Patient;
import PatientManagementSystem.Model.Users.Secretary;
import PatientManagementSystem.View.LoginPage;

import javax.swing.*;

public abstract class LoginController {

    public static boolean UserLogin(String id, String password){
        if (id.length() > 0 && password.length() > 0){
            String idType = id.substring(0, 1);
            switch (idType){
                case "A":
                    Admin admin = (Admin) SearchUtils.FindUser(id);
                    if (admin != null)
                        Logon.AdminLogin(password, admin);
                    return true;
                case "D":
                    Doctor doctor = (Doctor) SearchUtils.FindUser(id);
                    Logon.DoctorLogin(password, doctor);
                    return true;
                case "P":
                    Patient patient = (Patient) SearchUtils.FindUser(id);
                    Logon.PatientLogin(password, patient);
                    return true;
                case "S":
                    Secretary secretary = (Secretary) SearchUtils.FindUser(id);
                    Logon.SecretaryLogin(password, secretary);
                    return true;
                default:
                    return false;
            }
        } else {
            System.out.println("Please enter a username and password");
            return false;
        }
    }
}
