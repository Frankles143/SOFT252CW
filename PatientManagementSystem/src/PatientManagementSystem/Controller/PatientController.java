package PatientManagementSystem.Controller;

import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.*;
import PatientManagementSystem.Model.Users.Doctor;
import PatientManagementSystem.Model.Users.UserData;

import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class PatientController {

    public static DefaultTableModel OutputPatientHistory(){
        ArrayList<ConsultationNote> notes =  Logon.getCurrentPatient().getConsultationNotes();

        String columns[] = {"Doctor", "Date", "Notes"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (int i = 0; i < notes.size(); i++) {
            Object[] rowData = new Object[3];
            rowData[0] = notes.get(i).getDoctor().getName();
            rowData[1] = ControllerUtils.DateTimeFormatter(notes.get(i).getDate());
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
        return ControllerUtils.OutputMessagesTable(userMessages);
    }

    public static void DeleteMessage(int messageToDelete){
        ArrayList<Message> userMessages = OutputPatientMessages();

        SystemData.messages.remove(userMessages.get(messageToDelete));
        Serialization.SaveSystemData();
    }

    public static void CreatingAppointment(ArrayList<LocalDateTime> dates, int doctorIndex){
        try {
            Doctor doctor = UserData.DoctorUsers.get(doctorIndex);
            Logon.getCurrentPatient().AppointmentRequest(doctor, dates);
        } catch (Exception e) {
            System.out.println("Unable to create appointment request: " + e);
        }

    }
}
