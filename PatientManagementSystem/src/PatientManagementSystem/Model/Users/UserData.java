package PatientManagementSystem.Model.Users;

import java.io.*;
import java.util.ArrayList;

/**
 * A class to hold the ArrayLists for all the user data
 */
public class UserData implements Serializable {
    public static ArrayList<Admin> AdminUsers = new ArrayList<>();
    public static ArrayList<Doctor> DoctorUsers = new ArrayList<>();
    public static ArrayList<Patient> PatientUsers = new ArrayList<>();
    public static ArrayList<Secretary> SecretaryUsers = new ArrayList<>();
}
