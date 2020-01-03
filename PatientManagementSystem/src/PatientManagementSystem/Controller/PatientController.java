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

    /**
     * Returns history of currently logged in patient
     * @return DefaultTableModel of all consultation notes
     * @author Josh Franklin
     */
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

    /**
     * Gets the current Doctors messages from SystemData
     * @return returns an ArrayList of Message objects
     * @author Josh Franklin
     */
    public static ArrayList<Message> OutputPatientMessages(){
        ArrayList<Message> userMessages = new ArrayList<>();
        for (Message message : SystemData.messages) {
            if (Logon.getCurrentPatient().getId().equals(message.getReceiver().getId())){
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
    public static DefaultTableModel OutputPatientMessagesTable(){
        ArrayList<Message> userMessages = OutputPatientMessages();
        return ControllerUtils.OutputMessagesTable(userMessages);
    }

    /**
     * Outputs all the appointments for the currently logged in patient
     * @return DefaultTableModel of users appointments
     */
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

    /**
     * Deletes users messages, using the same index that they were output with
     * @param messageToDelete the index at which the message needs to be removed
     * @author Josh Franklin
     */
    public static void DeleteMessage(int messageToDelete){
        ArrayList<Message> userMessages = OutputPatientMessages();

        SystemData.messages.remove(userMessages.get(messageToDelete));
        Serialization.SaveSystemData();
    }

    /**
     * Creates a new appointment request using a selection of dates
     * @param dates ArrayList of dates
     * @param doctorIndex Doctor patient has chosen to see
     * @return Boolean, true if successful, false if not
     */
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

    /**
     * Takes data from the swing objects and makes sure that the information is sufficient to make a new request
     * @param one First date
     * @param two Second date
     * @param three Third date
     * @param comboBox Choosing a doctor
     * @param label Given feedback to user
     * @author Josh Franklin
     */
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
                label.setText("");
            }
        } else {
            label.setText("Please pick three dates and times and choose a doctor!");
        }
    }

    /**
     * Creates new doctor feedback for a specific doctor picked from a combo box
     * @param doctor Who the feedback is about
     * @param rating A numerical rating
     * @param feedbackNotes A review of the doctor
     * @author Josh Franklin
     */
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

    /**
     * Gets all prescriptions for the currently logged in patient
     * @return DefaultTableModel of users prescriptions
     */
    @SuppressWarnings("DuplicatedCode")
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
