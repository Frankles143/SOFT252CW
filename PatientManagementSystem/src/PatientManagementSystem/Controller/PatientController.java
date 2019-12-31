package PatientManagementSystem.Controller;

import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.*;
import PatientManagementSystem.Model.Users.Doctor;
import PatientManagementSystem.Model.Users.UserData;
import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DateTimePicker;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
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

    public static DefaultTableModel OutputPatientAppointments(){
        ArrayList<Appointment> patientAppointments = Logon.getCurrentPatient().getAppointments();
        String columns[] = {"Doctor", "Date"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (int i = 0; i < patientAppointments.size(); i++){
            Object[] rowdata = new Object[2];
            rowdata[0] = patientAppointments.get(i).getDoctor().getName();
            rowdata[1] = ControllerUtils.DateTimeFormatter(patientAppointments.get(i).getConfirmedDate());
            model.addRow(rowdata);
        }
        return model;
    }

    public static void DeleteMessage(int messageToDelete){
        ArrayList<Message> userMessages = OutputPatientMessages();

        SystemData.messages.remove(userMessages.get(messageToDelete));
        Serialization.SaveSystemData();
    }

    public static boolean CreatingAppointment(ArrayList<LocalDateTime> dates, int doctorIndex){
        try {
            Doctor doctor = UserData.DoctorUsers.get(doctorIndex);
            if (Logon.getCurrentPatient().AppointmentRequest(doctor, dates))
                return true;
        } catch (Exception e) {
            System.out.println("Unable to create appointment request: " + e);
            return false;
        }
        return false;
    }

    public static void AppointmentCreationChecks(DateTimePicker one, DateTimePicker two, DateTimePicker three, JComboBox comboBox, JLabel label){
        LocalDateTime date1 = one.getDateTimeStrict();
        LocalDateTime date2 = two.getDateTimeStrict();
        LocalDateTime date3 = three.getDateTimeStrict();

        if (date1 != null && date2 != null && date3 != null && comboBox.getSelectedIndex() >= 0){
            ArrayList<LocalDateTime> dates = new ArrayList<>();
            dates.add(date1);
            dates.add(date2);
            dates.add(date3);

            int doctor = comboBox.getSelectedIndex();
            if (PatientController.CreatingAppointment(dates, doctor)) {
                JOptionPane.showMessageDialog(null, "Appointment created successfully!");
                one.clear();
                two.clear();
                three.clear();
                comboBox.setSelectedIndex(-1);
            }
        } else {
            label.setText("Please pick three dates and times!");
        }
    }


}
