package PatientManagementSystem.Model.Users;

import PatientManagementSystem.Model.System.DoctorFeedback;
import PatientManagementSystem.Model.System.Password;
import PatientManagementSystem.Model.System.SystemData;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Admin extends AbstractPerson{
    private static int count = 0;

    public Admin(String id, String name, String address, String password) {
        super(id, name, address, password);
    }

    @Override
    public void update(AbstractPerson person) {
        for (Admin allAdmins: UserData.AdminUsers) {
            //Notify admins on the GUI
        }
    }

    public static String CreateId(){
        DecimalFormat formatter = new DecimalFormat("000");

        return "A" + formatter.format(++count);
    }

    /**
     * A class only for creating new Admin accounts
     * @author Josh Franklin
     */
    public void CreateAdmin(String name, String address, String password){
        try {
            Admin newAdmin = new Admin(CreateId(), name, address, password);
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

    /**
     * Finds the index of the feedback object to edit, then overwrites with a new feedback object created in the controller
     * @param oldFeedback The feedback that needs to be edited
     * @param newFeedback The new feedback to be saved
     * @author Josh Franklin
     */
    public void EditDoctorRatings(DoctorFeedback oldFeedback, DoctorFeedback newFeedback) {
        try {
            for (int i = 0; i < SystemData.uncheckedFeedback.size(); i++) {
                if (oldFeedback == SystemData.uncheckedFeedback.get(i)) {
                    SystemData.uncheckedFeedback.set(i, newFeedback);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("Unable to edit feedback: " + e);
        }
    }

    /**
     * Adds feedback object to relevant Doctor and removes the same feedback object from the uncheckedFeedback ArrayList
     * @param checkedFeedback This is the feedback object that has been checked and sent by the Admin
     * @author Josh Franklin
     */
    public void AttachFeedback(DoctorFeedback checkedFeedback) {
        try {
            checkedFeedback.getDoctor().getFeedback().add(checkedFeedback);
            SystemData.uncheckedFeedback.remove(checkedFeedback);
        } catch (Exception e) {
            System.out.println("Unable to add feedback to Doctor and remove from unchecked list: " + e);
        }
    }

    /**
     * Method to create new doctor user
     * @param name name of new doctor
     * @param address address of new doctor
     * @author Josh Franklin
     */
    public void CreateDoctor( String name, String address, String password){
        try {
            Doctor newDoctor = new Doctor(Doctor.CreateId(), name, address, password);
            UserData.DoctorUsers.add(newDoctor);
        }
        catch (Exception e) {
            System.out.println("There was an error: " + e);
        }
    }

    /**
     * Removes a doctor from the ArrayList
     * @param doctorToBeRemoved Doctor object to be removed
     * @author Josh Franklin
     */
    public void RemoveDoctor(Doctor doctorToBeRemoved){
        try {
            if (UserData.DoctorUsers.contains(doctorToBeRemoved)){
                UserData.DoctorUsers.remove(doctorToBeRemoved);
                System.out.println("Doctor removed successfully");
            } else {
                System.out.println("This Doctor does not exist");
            }
        }
        catch (Exception e) {
            System.out.println("Could not remove this Doctor" + e);
        }
    }

    /**
     * Method to create a new secretary user
     * @param name name of new secretary
     * @param address address of new secretary
     * @author Josh Franklin
     */
    public void CreateSecretary(String name, String address, String password){
        try {
            Secretary newSecretary = new Secretary(Secretary.CreateId(), name, address, password);
            UserData.SecretaryUsers.add(newSecretary);
        }
        catch (Exception e) {
            System.out.println("There was an error: " + e);
        }
    }

    /**
     * Removes secretary from ArrayList
     * @param secretaryToBeRemoved Secretary object to be removed
     * @author Josh Franklin
     */
    public void RemoveSecretary(Secretary secretaryToBeRemoved){
        try {
            if (UserData.SecretaryUsers.contains(secretaryToBeRemoved)){
                UserData.SecretaryUsers.remove(secretaryToBeRemoved);
                System.out.println("Secretary removed successfully");
            } else {
                System.out.println("Secretary does not exist");
            }
        }
        catch (Exception e) {
            System.out.println("Could not remove this Secretary" + e);
        }
    }

    /**
     * Allows admins to change anyone's password (in case of forgotten passwords)
     * @param newPassword the new password
     * @param person the person who's password will be changed
     */
    public void ChangeUserPassword(String newPassword, AbstractPerson person){
        Password.ChangePassword(newPassword, person);
    }
}
