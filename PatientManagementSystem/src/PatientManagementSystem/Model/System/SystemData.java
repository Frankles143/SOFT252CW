package PatientManagementSystem.Model.System;

import PatientManagementSystem.Model.System.DoctorFeedback;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class to store ArrayLists of objects for the system that aren't applied to a specific person
 * @author Josh Franklin
 */
public class SystemData implements Serializable {
    public static ArrayList<DoctorFeedback> uncheckedFeedback = new ArrayList<>();
    public static ArrayList<Medicine> medicines = new ArrayList<>();
}
