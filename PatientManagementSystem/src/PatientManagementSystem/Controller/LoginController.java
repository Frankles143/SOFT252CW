package PatientManagementSystem.Controller;

import PatientManagementSystem.Model.Gender;
import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.SearchUtils;
import PatientManagementSystem.Model.System.Serialization;
import PatientManagementSystem.Model.Users.Admin;
import PatientManagementSystem.Model.Users.Doctor;
import PatientManagementSystem.Model.Users.Patient;
import PatientManagementSystem.Model.Users.Secretary;

import javax.swing.*;

public abstract class LoginController {

    /**
     * User login method that first checks what kind of user they are by their ID, finds that user object using their ID and then runs the Login function
     * @param id id of user logging in
     * @param password password of user to be verified
     * @return Boolean, true if successfully, false if not
     * @author Josh Franklin
     */
    public static boolean UserLogin(String id, String password){
        if (id.length() > 0 && password.length() > 0){
            String idType = id.substring(0, 1);
            switch (idType){
                case "A":
                    Admin admin = (Admin) SearchUtils.FindUser(id);
                    if (admin != null){
                        Logon.AdminLogin(password, admin);
                        return true;
                        } else {
                            return false;
                        }
                case "D":
                    Doctor doctor = (Doctor) SearchUtils.FindUser(id);
                    if (doctor != null) {
                        Logon.DoctorLogin(password, doctor);
                        return true;
                    } else {
                            return  false;
                        }
                case "P":
                    Patient patient = (Patient) SearchUtils.FindUser(id);
                    if (patient != null) {
                        Logon.PatientLogin(password, patient);
                        return true;
                    } else {
                        return false;
                    }
                case "S":
                    Secretary secretary = (Secretary) SearchUtils.FindUser(id);
                    if (secretary != null) {
                        Logon.SecretaryLogin(password, secretary);
                        return true;
                    } else {
                        return false;
                    }
                default:
                    return false;
            }
        } else {
            System.out.println("Please enter a username and password");
            return false;
        }
    }

    /**
     * Creates a new patient account request
     * @param name name of new patient
     * @param address address of new patient
     * @param gender gender of new patient
     * @param age age of new patient
     * @param password password of new patient
     * @return Boolean, true if successful, false if not
     */
    public static boolean CreateNewUser(JTextField name, JTextField address, JComboBox gender, JSpinner age, JPasswordField password){
        Gender userGender;
        if (gender.getSelectedIndex() == 0){
            userGender = Gender.MALE;
        } else {
            userGender = Gender.FEMALE;
        }

        try {
            int userAge = (int) age.getValue();
            if(Patient.CreateAccountRequest(name.getText(), address.getText(), userGender, userAge, String.valueOf(password.getPassword()))){
                Serialization.SaveAll();
                JOptionPane.showMessageDialog(null, "New account request complete!");
                return true;
            } else {
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Cannot request new account");
            return false;
        }
    }


}
