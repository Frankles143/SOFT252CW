package PatientManagementSystem.Controller;

import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.*;
import PatientManagementSystem.Model.Users.Doctor;
import PatientManagementSystem.Model.Users.UserData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class ControllerUtils {

    /**
     * Converts an ArrayList of messages into a table model
     * @param userMessages ArrayList of messages in which to be inserted to the table model
     * @return DefaultTableModel of user messages data
     * @author Josh Franklin
     */
    public static DefaultTableModel OutputMessagesTable(ArrayList<Message> userMessages){
        String[] columns = {"Sender", "Date", "Message"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (Message userMessage : userMessages) {
            Object[] rowData = new Object[3];
            rowData[0] = userMessage.getSender();
            rowData[1] = DateTimeFormatter(userMessage.getDate());
            rowData[2] = userMessage.getMessage();
            model.addRow(rowData);
        }
        return model;
    }

    /**
     * Takes a LocalDateTime object and outputs something more readable for users to see
     * @param timeToFormat The time object in which to convert
     * @return Returns a "pretty" version of the time, in the format specified
     */
    public static String DateTimeFormatter(LocalDateTime timeToFormat){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return timeToFormat.format(formatter);
    }

    /**
     * Create a ComboBox model with all doctors in
     * @return DefaultComboBoxModel of all doctors
     * @author Josh Franklin
     */
    public static DefaultComboBoxModel CreateDoctorComboBoxModel(){
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        for (int i = 0; i < UserData.DoctorUsers.size(); i++)
            model.insertElementAt(UserData.DoctorUsers.get(i).getName(), i);
        return model;
    }

    /**
     * Create a ComboBox model with all patients in
     * @return DefaultComboBoxModel of all patients
     * @author Josh Franklin
     */
    public static DefaultComboBoxModel CreatePatientComboBoxModel(){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (int i = 0; i < UserData.PatientUsers.size(); i++)
            model.insertElementAt(UserData.PatientUsers.get(i).getName(), i);
        return model;
    }

    /**
     * Create a ComboBox model with all medicines in
     * @return DefaultComboBoxModel of all medicine
     * @author Josh Franklin
     */
    public static DefaultComboBoxModel OutputMedicineComboBoxModel(){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (int i = 0; i < SystemData.medicines.size(); i++)
            model.insertElementAt(SystemData.medicines.get(i).getMedicineName(), i);
        return model;
    }

    /**
     * Returns all ratings for a specified doctor
     * @param doctor Specified doctor
     * @return DefaultTableModel with all ratings in for specific doctor
     * @author Josh Franklin
     */
    public static DefaultTableModel OutputDoctorRatings(Doctor doctor){
        String[] columns = {"Doctor", "Rating", "Feedback notes"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (int i = 0; i < doctor.getFeedback().size(); i++) {
            Object[] rowData = new Object[3];
            rowData[0] = doctor.getFeedback().get(i).getDoctor().getName();
            rowData[1] = doctor.getFeedback().get(i).getRating();
            rowData[2] = doctor.getFeedback().get(i).getFeedbackNotes();
            model.addRow(rowData);
        }
        return model;
    }

    /**
     * Returns all medicine in table form
     * @return DefaultTableModel of all medicine
     * @author Josh Franklin
     */
    public static DefaultTableModel OutputAllMedicine() {
        String[] columns = {"Name", "Stock"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Medicine medicine : SystemData.medicines) {
            Object[] rowData = new Object[3];
            rowData[0] = medicine.getMedicineName();
            rowData[1] = medicine.getStock();
            model.addRow(rowData);
        }
        return model;
    }

    /**
     * This allows me to change a users password, regardless of the type of user they are by checking the state
     * @param newPassword The new password
     * @return A boolean value, true if successfully, false if not
     * @author Josh Franklin
     */
    public static boolean PasswordChange(JPasswordField newPassword){
        switch(Logon.getState()){
            case 1:
                Password.ChangePassword(String.valueOf(newPassword.getPassword()), Logon.getCurrentAdmin());
                Serialization.SaveAll();
                JOptionPane.showMessageDialog(null, "Password changed successfully");
                return true;
            case 2:
                Password.ChangePassword(String.valueOf(newPassword.getPassword()), Logon.getCurrentDoctor());
                Serialization.SaveAll();
                JOptionPane.showMessageDialog(null, "Password changed successfully");
                return true;
            case 3:
                Password.ChangePassword(String.valueOf(newPassword.getPassword()), Logon.getCurrentPatient());
                Serialization.SaveAll();
                JOptionPane.showMessageDialog(null, "Password changed successfully");
                return true;
            case 4:
                Password.ChangePassword(String.valueOf(newPassword.getPassword()), Logon.getCurrentSecretary());
                Serialization.SaveAll();
                JOptionPane.showMessageDialog(null, "Password changed successfully");
                return true;
            default:
                JOptionPane.showMessageDialog(null, "Unable to change password");
                return false;
        }
    }

    /**
     * Finds a medicine object based on it's name
     * @param medicineName The name of the medicine to find
     * @return The medicine object that matches
     * @author Josh Franklin
     */
    public static Medicine FindMedicine(String medicineName){
        for (Medicine medicine : SystemData.medicines){
            if (medicine.getMedicineName().equals(medicineName)){
                System.out.println("Found medicine");
                return medicine;
            }
        }
        System.out.println("Unable to find medicine");
        return null;
    }
}
