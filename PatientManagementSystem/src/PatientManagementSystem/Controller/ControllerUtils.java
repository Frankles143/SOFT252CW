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

    public static String DateTimeFormatter(LocalDateTime timeToFormat){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return timeToFormat.format(formatter);
    }

    public static DefaultComboBoxModel CreateDoctorComboBoxModel(){
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        for (int i = 0; i < UserData.DoctorUsers.size(); i++)
            model.insertElementAt(UserData.DoctorUsers.get(i).getName(), i);
        return model;
    }

    public static DefaultComboBoxModel CreatePatientComboBoxModel(){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (int i = 0; i < UserData.PatientUsers.size(); i++)
            model.insertElementAt(UserData.PatientUsers.get(i).getName(), i);
        return model;
    }

    public static DefaultComboBoxModel OutputMedicineComboBoxModel(){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (int i = 0; i < SystemData.medicines.size(); i++)
            model.insertElementAt(SystemData.medicines.get(i).getMedicineName(), i);
        return model;
    }

    public static DefaultTableModel OutputDoctorRatings(Doctor doctor){
        String columns[] = {"Doctor", "Rating", "Feedback notes"};
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

    public static boolean PasswordChange(JPasswordField pw1){
        switch(Logon.getState()){
            case 1:
                Password.ChangePassword(String.valueOf(pw1.getPassword()), Logon.getCurrentAdmin());
                Serialization.SaveAll();
                JOptionPane.showMessageDialog(null, "Password changed successfully");
                return true;
            case 2:
                Password.ChangePassword(String.valueOf(pw1.getPassword()), Logon.getCurrentDoctor());
                Serialization.SaveAll();
                JOptionPane.showMessageDialog(null, "Password changed successfully");
                return true;
            case 3:
                Password.ChangePassword(String.valueOf(pw1.getPassword()), Logon.getCurrentPatient());
                Serialization.SaveAll();
                JOptionPane.showMessageDialog(null, "Password changed successfully");
                return true;
            case 4:
                Password.ChangePassword(String.valueOf(pw1.getPassword()), Logon.getCurrentSecretary());
                Serialization.SaveAll();
                JOptionPane.showMessageDialog(null, "Password changed successfully");
                return true;
            default:
                JOptionPane.showMessageDialog(null, "Unable to change password");
                return false;
        }
    }
}
