package PatientManagementSystem.Model.Users;

import PatientManagementSystem.Model.Observer.Observer;
import PatientManagementSystem.Model.System.DoctorFeedback;
import PatientManagementSystem.Model.System.SystemData;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Admin extends AbstractPerson{
    private static int count = 0;

    public Admin(String id, String name, String address) {
        super(id, name, address);
    }

    @Override
    public void update(AbstractPerson person) {
        for (Admin allAdmins: UserData.AdminUsers) {
            //Notify admins on the GUI
        }
    }

    public static String CreateId(){
        DecimalFormat formatter = new DecimalFormat("000");

        String newID = "A" + formatter.format(++count);

        return newID;
    }

    /**
     * A class only for creating new Admin accounts
     * @author Josh Franklin
     */
    public void CreateAdmin(String name, String address){
        try {
            Admin newAdmin = new Admin(this.CreateId(), name, address);
            UserData.AdminUsers.add(newAdmin);
        }
        catch (Exception e) {
            System.out.println("There was an error: " + e);
        }
    }

    /**
     * Returns all unchecked feedback so it can be reviewed by Admin
     * @return return an ArrayList of DoctorFeedback
     * @author Josh Franklin
     */
    public ArrayList<DoctorFeedback> ViewDoctorRatings () {
        return SystemData.uncheckedFeedback;
    }

    public void EditDoctorRatings() {
        //Edit feedback
        //Changes the text (never the rating number) and saves over the currently open ArrayList index
    }

    public void AttachFeedback() {
        //Adds the Feedback object to the relevant doctors feedback ArrayList
    }

    /**
     * Method to create new doctor user
     * @param name
     * @param address
     * @author Josh Franklin
     */
    public void CreateDoctor( String name, String address){
        try {
            Doctor newDoctor = new Doctor(Doctor.CreateId(), name, address);
            UserData.DoctorUsers.add(newDoctor);
        }
        catch (Exception e) {
            System.out.println("There was an error: " + e);
        }
    }

    /**
     * Removes a doctor from the ArrayList
     * @param doctorToBeRemoved
     * @author Josh Franklin
     */
    public void RemoveDoctor(Doctor doctorToBeRemoved){
        try {
            UserData.DoctorUsers.remove(doctorToBeRemoved);
        }
        catch (Exception e) {
            System.out.println("Could not remove this Doctor" + e);
        }
    }

    /**
     * Method to create a new secretary user
     * @param name
     * @param address
     * @author Josh Franklin
     */
    public void CreateSecretary(String name, String address){
        try {
            Secretary newSecretary = new Secretary(Secretary.CreateId(), name, address);
            UserData.SecretaryUsers.add(newSecretary);
        }
        catch (Exception e) {
            System.out.println("There was an error: " + e);
        }
    }

    /**
     * Removes secretary from ArrayList
     * @param secretaryToBeRemoved
     * @author Josh Franklin
     */
    public void RemoveSecretary(Secretary secretaryToBeRemoved){
        try {
            UserData.SecretaryUsers.remove(secretaryToBeRemoved);
        }
        catch (Exception e) {
            System.out.println("Could not remove this Secretary" + e);
        }
    }
}
