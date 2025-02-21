package PatientManagementSystem.Model.Users;

import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.DoctorFeedback;
import PatientManagementSystem.Model.System.Message;
import PatientManagementSystem.Model.System.Password;
import PatientManagementSystem.Model.System.SystemData;

import javax.swing.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;

public class Admin extends AbstractPerson{

    public Admin(String id, String name, String address, String password) {
        super(id, name, address, password);
    }

    @Override
    public void update(AbstractPerson person) {
        if (Logon.getCurrentAdmin() == person) {
            JOptionPane.showMessageDialog(null, "You have a new message!");
        }
    }

    /**
     * Sorts the arraylist into numerical order by ID and then counts through assigning the new ID to the first available
     * @return returns an unused ID
     * @author Josh Franklin
     */
    public static String CreateId(){
        UserData.AdminUsers.sort(Comparator.comparing(AbstractPerson::getId));

        DecimalFormat formatter = new DecimalFormat("0000");
        int idNumber = 1;
        String id = "";

        for (Admin admin : UserData.AdminUsers) {

            id = "A" + formatter.format(idNumber);

            if (!admin.getId().equals(id)){
                return id;
            }
            idNumber++;
        }

        id = "A" + formatter.format(idNumber);
        return id;
    }

    /**
     * A class only for creating new Admin accounts
     * @author Josh Franklin
     * @param name name of new admin
     * @param address address of new admin
     * @param password password of new admin
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
     * Deletes an admin user
     * @param admin admin to be deleted
     */
    public void RemoveAdmin(Admin admin){
        try {
            if (UserData.AdminUsers.contains(admin)){
                UserData.AdminUsers.remove(admin);
                System.out.println("Admin removed successfully");
            } else {
                System.out.println("This Admin does not exist");
            }
        } catch (Exception e) {
            System.out.println("Unable to remove Admin: " + e);
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
     * @param feedback The feedback that needs to be edited
     * @param newNotes The new notes in string form to be set
     * @author Josh Franklin
     */
    public void EditDoctorRatings(DoctorFeedback feedback, String newNotes) {
        try {
            feedback.setFeedbackNotes(newNotes);
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
            checkedFeedback.getDoctor().addFeedback(checkedFeedback);
            SystemData.uncheckedFeedback.remove(checkedFeedback);
            Message.CreateMessage(Admin.this.getName(), checkedFeedback.getDoctor(), "You have new feedback");
        } catch (Exception e) {
            System.out.println("Unable to add feedback to Doctor and remove from unchecked list: " + e);
        }
    }

    /**
     * Method to create new doctor user
     * @param name name of new doctor
     * @param address address of new doctor
     * @param password password of new doctor
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
     * @param password password of new secretary
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
     * A method to remove a patient user
     * @param patientToBeDeleted user to be removed
     */
    public void RemovePatient(Patient patientToBeDeleted){
        try {
            if (UserData.PatientUsers.contains(patientToBeDeleted)){
                UserData.PatientUsers.remove(patientToBeDeleted);
                System.out.println("Patient removed successfully");
            } else {
                System.out.println("This Patient does not exist");
            }
        }
        catch (Exception e) {
            System.out.println("Could not remove this Patient" + e);
        }
    }

    /**
     * Allows admins to change anyone's password (in case of forgotten passwords)
     * @param newPassword the new password
     * @param person the person who's password will be changed
     */
    public void ChangeUserPassword(String newPassword, AbstractPerson person){
        Password.ChangePassword(newPassword, person);
        Message.CreateMessage(Admin.this.getName(), person, "An admin has changed your password");
    }
}
