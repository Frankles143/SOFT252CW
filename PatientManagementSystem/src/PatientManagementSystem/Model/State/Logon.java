package PatientManagementSystem.Model.State;

import PatientManagementSystem.Model.System.Password;
import PatientManagementSystem.Model.Users.*;

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

    /**
     * Allows me to see what kind of user if logged in
     * @return State of who's logged in, or if no-one is
     * @author Josh Franklin
     */
    public static int getState() {
        return state;
    }

    /**
     * Returns Admin object of patient currently logged in
     * @return User object
     * @author Josh Franklin
     */
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

    /**
     * Returns Doctor object of patient currently logged in
     * @return User object
     * @author Josh Franklin
     */
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

    /**
     * Returns Patient object of patient currently logged in
     * @return User object
     * @author Josh Franklin
     */
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

    /**
     * Returns Secretary object of patient currently logged in
     * @return User object
     * @author Josh Franklin
     */
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

    /**
     * Logs everyone out and changes the state
     * @author Josh Franklin
     */
    public static void Logout(){
        state = LOGGED_OUT;
        setCurrentAdmin(null);
        setCurrentDoctor(null);
        setCurrentPatient(null);
        setCurrentSecretary(null);
        System.out.println("Logged out");
    }

    /**
     * Logs in a user and stores them as currently logged in, also changes state
     * @param password password to verify
     * @param admin User to login
     * @return Boolean, true if successful, false if not
     */
    public static boolean AdminLogin(String password, Admin admin){
        if (state == LOGGED_OUT) {
            if (Password.VerifyPassword(password, admin)) {
                state = ADMIN_LOGIN;
                setCurrentAdmin(admin);
                setCurrentDoctor(null);
                setCurrentPatient(null);
                setCurrentSecretary(null);
                System.out.println("Admin logged in");
                return true;
            } else{
             System.out.println("Incorrect password!");
             return false;
            }
        } else {
            System.out.println("Already logged in");
            return false;
        }
    }

    /**
     * Logs in a user and stores them as currently logged in, also changes state
     * @param password password to verify
     * @param doctor User to login
     * @return Boolean, true if successful, false if not
     */
    public static boolean DoctorLogin(String password, Doctor doctor){
        if (state == LOGGED_OUT){
            if (Password.VerifyPassword(password, doctor)){
                state = DOCTOR_LOGIN;
                setCurrentAdmin(null);
                setCurrentDoctor(doctor);
                setCurrentPatient(null);
                setCurrentSecretary(null);
                System.out.println("Doctor logged in");
                return true;
            } else {
                System.out.println("Incorrect password!");
                return false;
            }
        } else {
        System.out.println("Already logged in");
            return false;
        }
    }

    /**
     * Logs in a user and stores them as currently logged in, also changes state
     * @param password password to verify
     * @param patient User to login
     * @return Boolean, true if successful, false if not
     */
    public static boolean PatientLogin(String password, Patient patient){
        if (state == LOGGED_OUT){
            if (Password.VerifyPassword(password, patient)) {
                state = PATIENT_LOGIN;
                setCurrentAdmin(null);
                setCurrentDoctor(null);
                setCurrentPatient(patient);
                setCurrentSecretary(null);
                System.out.println("Patient logged in");
                return true;
            } else {
                System.out.println("Incorrect password!");
                return false;
            }
        } else {
            System.out.println("Already logged in");
            return false;
        }
    }

    /**
     * Logs in a user and stores them as currently logged in, also changes state
     * @param password password to verify
     * @param secretary User to login
     * @return Boolean, true if successful, false if not
     */
    public static boolean SecretaryLogin(String password, Secretary secretary){
        if (state == LOGGED_OUT){
            if (Password.VerifyPassword(password, secretary)){
                state = SECRETARY_LOGIN;
                setCurrentAdmin(null);
                setCurrentDoctor(null);
                setCurrentPatient(null);
                setCurrentSecretary(secretary);
                System.out.println("Secretary logged in");
                return true;
            } else {
                System.out.println("Incorrect password!");
                return false;
            }
        } else {
            System.out.println("Already logged in");
            return false;
        }
    }
}
