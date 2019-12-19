package PatientManagementSystem.Model.System;

import PatientManagementSystem.Model.System.DoctorFeedback;
import PatientManagementSystem.Model.Users.Patient;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * A class to store ArrayLists of objects for the system that aren't applied to a specific person
 * @author Josh Franklin
 */
public class SystemData implements Serializable {
    public static ArrayList<DoctorFeedback> uncheckedFeedback = new ArrayList<>();
    public static ArrayList<Medicine> medicines = new ArrayList<>();
    public static ArrayList<AccountRequest> accountRequests = new ArrayList<>();
    public static ArrayList<Patient> accountTerminationRequests = new ArrayList<>();
    public static ArrayList<Appointment> appointmentRequests = new ArrayList<>();
    public static ArrayList<Message> messages = new ArrayList<>();
}
