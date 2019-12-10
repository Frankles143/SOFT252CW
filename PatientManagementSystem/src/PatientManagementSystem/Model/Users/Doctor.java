package PatientManagementSystem.Model.Users;

import PatientManagementSystem.Model.System.DoctorFeedback;

import java.util.ArrayList;

public class Doctor extends AbstractPerson {
    ArrayList<DoctorFeedback> feedback;

    public Doctor(String id, String name, String address) {
        super(id, name, address);
    }

    public ArrayList<DoctorFeedback> getFeedback() {
        return feedback;
    }
}
