package PatientManagementSystem.Model.State;

import PatientManagementSystem.Model.System.Password;
import PatientManagementSystem.Model.System.SystemData;
import PatientManagementSystem.Model.Users.*;

import java.security.SecureRandom;

/**
 * A state pattern class for logging in with a specific user and managing states
 * @author Josh Franklin
 */
public abstract class Logon {
    private final static int LOGGED_OUT = 0;
    private final static int ADMIN_LOGIN = 1;
    private final static int DOCTOR_LOGIN = 2;
    private final static int PATIENT_LOGIN = 3;
    private final static int SECRETARY_LOGIN = 4;

    private static int state = LOGGED_OUT;

    private static Admin currentAdmin;
    private static Doctor currentDoctor;
    private static Patient currentPatient;
    private static Secretary currentSecretary;

    public static int getState() {
        return state;
    }

    public static Admin getCurrentAdmin() {
        if (currentAdmin != null) {
            return currentAdmin;
        } else {
            System.out.println("No admin currently logged in");
            return null;
        }
    }

    private static void setCurrentAdmin(Admin currentAdmin) {
        Logon.currentAdmin = currentAdmin;
    }

    public static Doctor getCurrentDoctor() {
        if (currentDoctor != null) {
            return currentDoctor;
        } else {
            System.out.println("No doctor currently logged in");
            return null;
        }
    }

    private static void setCurrentDoctor(Doctor currentDoctor) {
        Logon.currentDoctor = currentDoctor;
    }

    public static Patient getCurrentPatient() {
        if (currentPatient != null) {
            return currentPatient;
        } else {
            System.out.println("No patient currently logged in");
            return null;
        }
    }

    private static void setCurrentPatient(Patient currentPatient) {
        Logon.currentPatient = currentPatient;
    }

    public static Secretary getCurrentSecretary() {
        if (currentSecretary != null) {
            return currentSecretary;
        } else {
            System.out.println("No patient currently logged in");
            return null;
        }
    }

    private static void setCurrentSecretary(Secretary currentSecretary) {
        Logon.currentSecretary = currentSecretary;
    }

    public static void Logout(){
        state = LOGGED_OUT;
        setCurrentAdmin(null);
        setCurrentDoctor(null);
        setCurrentPatient(null);
        setCurrentSecretary(null);
        System.out.println("Logged out");
    }

    public static void AdminLogin(String password, Admin admin){
        if (state == LOGGED_OUT) {
            if (Password.VerifyPassword(password, admin)) {
                state = ADMIN_LOGIN;
                setCurrentAdmin(admin);
                setCurrentDoctor(null);
                setCurrentPatient(null);
                setCurrentSecretary(null);
                System.out.println("Admin logged in");
            } else{
             System.out.println("Incorrect password!");
            }
        } else {
            System.out.println("Already logged in");
        }
    }

    public static void DoctorLogin(String password, Doctor doctor){
        if (state == LOGGED_OUT){
            if (Password.VerifyPassword(password, doctor)){
                state = DOCTOR_LOGIN;
                setCurrentAdmin(null);
                setCurrentDoctor(doctor);
                setCurrentPatient(null);
                setCurrentSecretary(null);
                System.out.println("Doctor logged in");
            } else {
                System.out.println("Incorrect password!");
            }
        } else {
        System.out.println("Already logged in");
        }
    }

    public static void PatientLogin(String password, Patient patient){
        if (state == LOGGED_OUT){
            if (Password.VerifyPassword(password, patient)) {
                state = PATIENT_LOGIN;
                setCurrentAdmin(null);
                setCurrentDoctor(null);
                setCurrentPatient(patient);
                setCurrentSecretary(null);
                System.out.println("Patient logged in");
            } else {
                System.out.println("Incorrect password!");
            }
        } else {
            System.out.println("Already logged in");
        }
    }

    public static void SecretaryLogin(String password, Secretary secretary){
        if (state == LOGGED_OUT){
            if (Password.VerifyPassword(password, secretary)){
                state = SECRETARY_LOGIN;
                setCurrentAdmin(null);
                setCurrentDoctor(null);
                setCurrentPatient(null);
                setCurrentSecretary(secretary);
                System.out.println("Secretary logged in");
            } else {
                System.out.println("Incorrect password!");
            }
        } else {
            System.out.println("Already logged in");
        }
    }
}
