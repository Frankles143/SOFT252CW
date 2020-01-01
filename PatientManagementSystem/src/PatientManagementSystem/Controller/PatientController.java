package PatientManagementSystem.Controller;

import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.*;
import PatientManagementSystem.Model.Users.Doctor;
import PatientManagementSystem.Model.Users.UserData;
import com.github.lgooddatepicker.components.DateTimePicker;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.util.ArrayList;


public abstract class PatientController {

    public static DefaultTableModel OutputPatientHistory(){
        ArrayList<ConsultationNote> notes =  Logon.getCurrentPatient().getConsultationNotes();

        String[] columns = {"Doctor", "Date", "Notes"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        for (ConsultationNote note : notes) {
            Object[] rowData = new Object[3];
            rowData[0] = note.getDoctor().getName();
            rowData[1] = ControllerUtils.DateTimeFormatter(note.getDate());
            rowData[2] = note.getNotes();
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
        String[] columns = {"Doctor", "Date"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Appointment patientAppointment : patientAppointments) {
            Object[] rowData = new Object[2];
            rowData[0] = patientAppointment.getDoctor().getName();
            rowData[1] = ControllerUtils.DateTimeFormatter(patientAppointment.getConfirmedDate());
            model.addRow(rowData);
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
            if (Logon.getCurrentPatient().AppointmentRequest(doctor, dates)) {
                Serialization.SaveAll();
                return true;
            }
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
                comboBox.setSelectedIndex(0);
            }
        } else {
            label.setText("Please pick three dates and times and choose a doctor!");
        }
    }

    public static void SubmitDoctorFeedback(JComboBox doctor, JComboBox rating, JFormattedTextField feedbackNotes){
        try {
            Logon.getCurrentPatient().CreateFeedback(UserData.DoctorUsers.get(doctor.getSelectedIndex()), rating.getSelectedIndex() + 1, feedbackNotes.getText());
            Serialization.SaveAll();
            doctor.setSelectedIndex(0);
            rating.setSelectedIndex(0);
            feedbackNotes.setText("");
            JOptionPane.showMessageDialog(null, "Feedback accepted");
        } catch (Exception e) {
            System.out.println("Could not submit doctor feedback: " + e);
        }
    }

    public static DefaultTableModel OutputPatientPrescriptions(){
        ArrayList<Prescription> patientPrescriptions = Logon.getCurrentPatient().getPrescriptions();
        String[] columns = {"Doctor", "Notes", "Medicine", "Quantity", "Dosage", "Received"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Prescription patientPrescription : patientPrescriptions) {
            Object[] rowData = new Object[6];
            rowData[0] = patientPrescription.getDoctor().getName();
            rowData[1] = patientPrescription.getDoctorNotes();
            rowData[2] = patientPrescription.getMedicine().getMedicineName();
            rowData[3] = patientPrescription.getQuantity();
            rowData[4] = patientPrescription.getDosage();
            rowData[5] = patientPrescription.isReceived();
            model.addRow(rowData);
        }
        return model;
    }
}
