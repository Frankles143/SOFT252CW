package PatientManagementSystem.Controller;

import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.*;
import PatientManagementSystem.Model.Users.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public abstract class AdminController {

    public static ArrayList<Message> OutputAdminMessages(){
        ArrayList<Message> userMessages = new ArrayList<Message>();
        for (Message message : SystemData.messages) {
            if (Logon.getCurrentAdmin().getId().equals(message.getReceiver().getId())){
                userMessages.add(message);
            }
        }
        return userMessages;
    }

    public static DefaultTableModel OutputAdminMessagesTable(){
        ArrayList<Message> userMessages = OutputAdminMessages();
        return ControllerUtils.OutputMessagesTable(userMessages);
    }

    public static void DeleteMessage(int messageToDelete){
        ArrayList<Message> userMessages = OutputAdminMessages();

        SystemData.messages.remove(userMessages.get(messageToDelete));
        Serialization.SaveSystemData();
    }

    public static DefaultTableModel OutputUsers(){
        String columns[] = {"User Type", "Name", "User ID"};
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

    public static DefaultTableModel OutputUncheckedFeedback(){
        String columns[] = {"Doctor", "Rating", "Feedback notes"};
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

    public static void SaveFeedbackRow(int ratingsIndex, String newNotes){
        try {
            Logon.getCurrentAdmin().EditDoctorRatings(SystemData.uncheckedFeedback.get(ratingsIndex), newNotes);
            Serialization.SaveAll();
            JOptionPane.showMessageDialog(null, "Feedback updated");
        } catch (Exception e) {
            System.out.println("Unable to save feedback row: " + e);
        }

    }

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
