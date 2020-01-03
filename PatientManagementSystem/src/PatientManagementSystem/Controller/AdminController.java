package PatientManagementSystem.Controller;

import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.*;
import PatientManagementSystem.Model.Users.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public abstract class AdminController {
    /**
     * Gets the current Admins messages from SystemData
     * @return returns an ArrayList of Message objects
     * @author Josh Franklin
     */
    public static ArrayList<Message> OutputAdminMessages(){
        ArrayList<Message> userMessages = new ArrayList<Message>();
        for (Message message : SystemData.messages) {
            if (Logon.getCurrentAdmin().getId().equals(message.getReceiver().getId())){
                userMessages.add(message);
            }
        }
        return userMessages;
    }

    /**
     * Creates a table model with the users messages data inside
     * @return DefaultTableModel of users messages
     * @author Josh Franklin
     */
    public static DefaultTableModel OutputAdminMessagesTable(){
        ArrayList<Message> userMessages = OutputAdminMessages();
        return ControllerUtils.OutputMessagesTable(userMessages);
    }

    /**
     * Deletes users messages, using the same index that they were output with
     * @param messageToDelete the index at which the message needs to be removed
     * @author Josh Franklin
     */
    public static void DeleteMessage(int messageToDelete){
        ArrayList<Message> userMessages = OutputAdminMessages();

        SystemData.messages.remove(userMessages.get(messageToDelete));
        Serialization.SaveSystemData();
    }

    /**
     * Outputs all users, in groups of type of user
     * @return DefaultTableModel containing all users and some of their data
     * @author Josh Franklin
     */
    public static DefaultTableModel OutputUsers(){
        String[] columns = {"User Type", "Name", "User ID"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (int i = 0; i < UserData.AdminUsers.size(); i++){
            Object[] rowData = new Object[3];
            rowData[0] = "Admin";
            rowData[1] = UserData.AdminUsers.get(i).getName();
            rowData[2] = UserData.AdminUsers.get(i).getId();
            model.addRow(rowData);
        }
        for (int i = 0; i < UserData.DoctorUsers.size(); i++){
            Object[] rowData = new Object[3];
            rowData[0] = "Doctor";
            rowData[1] = UserData.DoctorUsers.get(i).getName();
            rowData[2] = UserData.DoctorUsers.get(i).getId();
            model.addRow(rowData);
        }
        for (int i = 0; i < UserData.PatientUsers.size(); i++){
            Object[] rowData = new Object[3];
            rowData[0] = "Patient";
            rowData[1] = UserData.PatientUsers.get(i).getName();
            rowData[2] = UserData.PatientUsers.get(i).getId();
            model.addRow(rowData);
        }
        for (int i = 0; i < UserData.SecretaryUsers.size(); i++){
            Object[] rowData = new Object[3];
            rowData[0] = "Secretary";
            rowData[1] = UserData.SecretaryUsers.get(i).getName();
            rowData[2] = UserData.SecretaryUsers.get(i).getId();
            model.addRow(rowData);
        }

        return model;
    }

    /**
     * Deletes a specific user, using their ID as reference
     * @param userId the ID of the user to be removed
     * @author Josh Franklin
     */
    public static void DeleteUser(String userId){
        String userType = userId.substring(0,1);
        switch (userType) {
            case "A":
                Admin admin = (Admin) SearchUtils.FindUser(userId);
                if (!Logon.getCurrentAdmin().equals(admin)) {
                    Logon.getCurrentAdmin().RemoveAdmin(admin);
                    Serialization.SaveAll();
                } else {
                    JOptionPane.showMessageDialog(null, "You cannot delete yourself!");
                    System.out.println("Cannot delete self");
                }
                break;
            case "D":
                Doctor doctor = (Doctor) SearchUtils.FindUser(userId);
                Logon.getCurrentAdmin().RemoveDoctor(doctor);
                Serialization.SaveAll();
                break;
            case "P":
                Patient patient = (Patient) SearchUtils.FindUser(userId);
                Logon.getCurrentAdmin().RemovePatient(patient);
                Serialization.SaveAll();
                break;
            case "S":
                Secretary secretary = (Secretary) SearchUtils.FindUser(userId);
                Logon.getCurrentAdmin().RemoveSecretary(secretary);
                Serialization.SaveAll();
                break;
            default:
                System.out.println("Incorrect User ID");
        }
    }

    /**
     * Creates a new user of either Admin, Doctor or Secretary type
     * @param userType index from a combo box to determine user type
     * @param name name of new user
     * @param address address of new user
     * @param password password of new user
     * @return return a boolean, true if successfully, false if not
     * @author Josh Franklin
     */
    public static boolean CreateNewUser(int userType, String name, String address, String password){
        Admin admin = Logon.getCurrentAdmin();
        switch (userType) {
            case 0:
                admin.CreateAdmin(name, address, password);
                Serialization.SaveAll();
                JOptionPane.showMessageDialog(null, "New admin created!");
                return true;
            case 1:
                admin.CreateDoctor(name, address, password);
                Serialization.SaveAll();
                JOptionPane.showMessageDialog(null, "New Doctor created!");
                return true;
            case 2:
                admin.CreateSecretary(name, address, password);
                Serialization.SaveAll();
                JOptionPane.showMessageDialog(null, "New Secretary created!");
                return true;
            default:
                return false;
        }
    }

    /**
     * Collects all unchecked doctor feedback from UserData
     * @return DefaultTableModel with all the feedback stored in it
     * @author Josh Franklin
     */
    public static DefaultTableModel OutputUncheckedFeedback(){
        String[] columns = {"Doctor", "Rating", "Feedback notes"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (int i = 0; i < SystemData.uncheckedFeedback.size(); i++){
            Object[] rowData = new Object[3];
            rowData[0] = SystemData.uncheckedFeedback.get(i).getDoctor().getName();
            rowData[1] = SystemData.uncheckedFeedback.get(i).getRating();
            rowData[2] = SystemData.uncheckedFeedback.get(i).getFeedbackNotes();
            model.addRow(rowData);
        }
        return model;
    }

    /**
     * This takes any changes to the feedback notes and saves them over the previous version
     * @param ratingsIndex Index of the DoctorFeedback to be saved over
     * @param newNotes The updated notes to be saved
     * @author Josh Franklin
     */
    public static void SaveFeedbackRow(int ratingsIndex, String newNotes){
        try {
            Logon.getCurrentAdmin().EditDoctorRatings(SystemData.uncheckedFeedback.get(ratingsIndex), newNotes);
            Serialization.SaveAll();
            JOptionPane.showMessageDialog(null, "Feedback updated");
        } catch (Exception e) {
            System.out.println("Unable to save feedback row: " + e);
        }

    }

    /**
     * Takes the feedback that has now been confirmed as checked and attaches it to relevant doctor
     * @param feedbackIndex Index of the feedback to be saved to doctor
     * @author Josh Franklin
     */
    public static void AttachFeedback(int feedbackIndex){
        try {
            Logon.getCurrentAdmin().AttachFeedback(SystemData.uncheckedFeedback.get(feedbackIndex));
            Serialization.SaveAll();
            JOptionPane.showMessageDialog(null, "Feedback saved");
        } catch (Exception e) {
            System.out.println("Unable to attach feedback: " + e);
        }

    }
}
