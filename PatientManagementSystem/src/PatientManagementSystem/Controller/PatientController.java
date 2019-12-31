package PatientManagementSystem.Controller;

import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.ConsultationNote;
import PatientManagementSystem.Model.System.Message;
import PatientManagementSystem.Model.System.Serialization;
import PatientManagementSystem.Model.System.SystemData;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public abstract class PatientController {

    public static DefaultTableModel OutputPatientHistory(){
        ArrayList<ConsultationNote> notes =  Logon.getCurrentPatient().getConsultationNotes();

        String columns[] = {"Doctor", "Date", "Notes"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (int i = 0; i < notes.size(); i++) {
            Object[] rowData = new Object[3];
            rowData[0] = notes.get(i).getDoctor().getName();
            rowData[1] = notes.get(i).getDate();
            rowData[2] = notes.get(i).getNotes();
            model.addRow(rowData);
        }
        return model;
    }

    public static ArrayList<Message> OutputPatientMessages(){
        ArrayList<Message> userMessages = new ArrayList<Message>();
        for (Message message : SystemData.messages) {
            if (Logon.getCurrentPatient().getId().equals(message.getReceiver().getId())){
                userMessages.add(message);
            }
        }
        return userMessages;
    }

    public static DefaultTableModel OutputPatientMessagesTable(){
        ArrayList<Message> userMessages = OutputPatientMessages();

        String columns[] = {"Sender", "Date", "Message"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (int i = 0; i < userMessages.size(); i++) {
            Object[] rowData = new Object[3];
            rowData[0] = userMessages.get(i).getSender();
            rowData[1] = userMessages.get(i).getDate();
            rowData[2] = userMessages.get(i).getMessage();
            model.addRow(rowData);
        }
        return model;
    }

    public static void DeleteMessage(int messageToDelete){
        ArrayList<Message> userMessages = OutputPatientMessages();

        SystemData.messages.remove(userMessages.get(messageToDelete));
        Serialization.SaveSystemData();
    }
}
