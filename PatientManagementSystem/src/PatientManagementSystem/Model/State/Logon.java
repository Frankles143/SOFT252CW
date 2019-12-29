package PatientManagementSystem.Model.State;

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

    private int state = LOGGED_OUT;

    private static Admin currentAdmin;
    private static Doctor currentDoctor;
    private static Patient currentPatient;
    private static Secretary currentSecretary;

    public int getState() {
        return state;
    }

    public static Admin getCurrentAdmin() {
        return currentAdmin;
    }

    private static void setCurrentAdmin(Admin currentAdmin) {
        Logon.currentAdmin = currentAdmin;
    }

    public static Doctor getCurrentDoctor() {
        return currentDoctor;
    }

    private static void setCurrentDoctor(Doctor currentDoctor) {
        Logon.currentDoctor = currentDoctor;
    }

    public static Patient getCurrentPatient() {
        return currentPatient;
    }

    private static void setCurrentPatient(Patient currentPatient) {
        Logon.currentPatient = currentPatient;
    }

    public static Secretary getCurrentSecretary() {
        return currentSecretary;
    }

    private static void setCurrentSecretary(Secretary currentSecretary) {
        Logon.currentSecretary = currentSecretary;
    }

    public void Logout(){
        state = LOGGED_OUT;
        setCurrentAdmin(null);
        setCurrentDoctor(null);
        setCurrentPatient(null);
        setCurrentSecretary(null);
        System.out.println("Logged out");
    }

    public void AdminLogin(Admin admin){
        if (state == LOGGED_OUT) {
            state = ADMIN_LOGIN;
            setCurrentAdmin(admin);
            setCurrentDoctor(null);
            setCurrentPatient(null);
            setCurrentSecretary(null);
            System.out.println("Admin logged in");
        } else {
            System.out.println("Already logged in");
        }
    }

    public void DoctorLogin(Doctor doctor){
        if (state == LOGGED_OUT){
            state = DOCTOR_LOGIN;
            setCurrentAdmin(null);
            setCurrentDoctor(doctor);
            setCurrentPatient(null);
            setCurrentSecretary(null);
            System.out.println("Doctor logged in");
        } else {
        System.out.println("Already logged in");
        }
    }

    public void PatientLogin(Patient patient){
        if (state == LOGGED_OUT){
            state = PATIENT_LOGIN;
            setCurrentAdmin(null);
            setCurrentDoctor(null);
            setCurrentPatient(patient);
            setCurrentSecretary(null);
            System.out.println("Patient logged in");
        } else {
            System.out.println("Already logged in");
        }
    }

    public void SecretaryLogin(Secretary secretary){
        if (state == LOGGED_OUT){
            state = SECRETARY_LOGIN;
            setCurrentAdmin(null);
            setCurrentDoctor(null);
            setCurrentPatient(null);
            setCurrentSecretary(secretary);
            System.out.println("Secretary logged in");
        } else {
            System.out.println("Already logged in");
        }
    }
}
