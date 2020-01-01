package PatientManagementSystem.Controller;

import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.Appointment;
import PatientManagementSystem.Model.System.Message;
import PatientManagementSystem.Model.System.Serialization;
import PatientManagementSystem.Model.System.SystemData;
import PatientManagementSystem.Model.Users.UserData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public abstract class DoctorController {

    public static ArrayList<Message> OutputDoctorMessages(){
        ArrayList<Message> userMessages = new ArrayList<Message>();
        for (Message message : SystemData.messages) {
            if (Logon.getCurrentDoctor().getId().equals(message.getReceiver().getId())){
                userMessages.add(message);
            }
        }
        return userMessages;
    }

    public static DefaultTableModel OutputDoctorMessagesTable(){
        ArrayList<Message> userMessages = OutputDoctorMessages();
        return ControllerUtils.OutputMessagesTable(userMessages);
    }

    public static void DeleteMessage(int messageToDelete){
        ArrayList<Message> userMessages = OutputDoctorMessages();

        SystemData.messages.remove(userMessages.get(messageToDelete));
        Serialization.SaveSystemData();
    }

    public static DefaultTableModel OutputDoctorAppointments(){
        ArrayList<Appointment> appointments = Logon.getCurrentDoctor().ViewAppointments();
        String columns[] = {"Patient", "Date"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (int i = 0; i < appointments.size(); i++){
            Object[] rowData = new Object[2];
            rowData[0] = appointments.get(i).getPatient().getName();
            rowData[1] = ControllerUtils.DateTimeFormatter(appointments.get(i).getConfirmedDate());
            model.addRow(rowData);
        }
        return model;
    }

    public static void CreateConsultationNotes(int patientIndex, String notes){
        try {
            Logon.getCurrentDoctor().CreateConsultationNotes(UserData.PatientUsers.get(patientIndex), notes);
            Serialization.SaveAll();
            JOptionPane.showMessageDialog(null, "Consultation saved");
        } catch (Exception e) {
            System.out.println("Unable to create consultation note: " + e);
        }

    }
}
