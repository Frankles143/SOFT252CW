package PatientManagementSystem.Controller;

import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.Message;
import PatientManagementSystem.Model.System.Password;
import PatientManagementSystem.Model.System.Serialization;
import PatientManagementSystem.Model.Users.Doctor;
import PatientManagementSystem.Model.Users.UserData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class ControllerUtils {

    public static DefaultTableModel OutputMessagesTable(ArrayList<Message> userMessages){
        String columns[] = {"Sender", "Date", "Message"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (int i = 0; i < userMessages.size(); i++) {
            Object[] rowData = new Object[3];
            rowData[0] = userMessages.get(i).getSender();
            rowData[1] = DateTimeFormatter(userMessages.get(i).getDate());
            rowData[2] = userMessages.get(i).getMessage();
            model.addRow(rowData);
        }
        return model;
    }

    public static String DateTimeFormatter(LocalDateTime timeToFormat){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedTime = timeToFormat.format(formatter);

        return formattedTime;
    }

    public static DefaultComboBoxModel CreateDoctorComboboxModel(){
        DefaultComboBoxModel model = new DefaultComboBoxModel();

        for (int i = 0; i < UserData.DoctorUsers.size(); i++)
            model.insertElementAt(UserData.DoctorUsers.get(i).getName(), i);
        return model;
    }

    public static DefaultComboBoxModel CreatePatientComboboxModel(){
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        for (int i = 0; i < UserData.PatientUsers.size(); i++)
            model.insertElementAt(UserData.PatientUsers.get(i).getName(), i);
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
