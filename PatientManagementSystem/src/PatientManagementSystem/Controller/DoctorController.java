package PatientManagementSystem.Controller;

import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.*;
import PatientManagementSystem.Model.Users.UserData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public abstract class DoctorController {

    /**
     * Gets the current Doctors messages from SystemData
     * @return returns an ArrayList of Message objects
     * @author Josh Franklin
     */
    public static ArrayList<Message> OutputDoctorMessages(){
        ArrayList<Message> userMessages = new ArrayList<>();
        for (Message message : SystemData.messages) {
            if (Logon.getCurrentDoctor().getId().equals(message.getReceiver().getId())){
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
    public static DefaultTableModel OutputDoctorMessagesTable(){
        ArrayList<Message> userMessages = OutputDoctorMessages();
        return ControllerUtils.OutputMessagesTable(userMessages);
    }

    /**
     * Deletes users messages, using the same index that they were output with
     * @param messageToDelete the index at which the message needs to be removed
     * @author Josh Franklin
     */
    public static void DeleteMessage(int messageToDelete){
        ArrayList<Message> userMessages = OutputDoctorMessages();

        SystemData.messages.remove(userMessages.get(messageToDelete));
        Serialization.SaveSystemData();
    }

    /**
     * Gets all appointments of the currently logged in doctor and outputs to a table
     * @return DefaultTableModel of doctor appointments
     * @author Josh Franklin
     */
    public static DefaultTableModel OutputDoctorAppointments(){
        ArrayList<Appointment> appointments = Logon.getCurrentDoctor().ViewAppointments();
        String[] columns = {"Patient", "Date"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Appointment appointment : appointments) {
            Object[] rowData = new Object[2];
            rowData[0] = appointment.getPatient().getName();
            rowData[1] = ControllerUtils.DateTimeFormatter(appointment.getConfirmedDate());
            model.addRow(rowData);
        }
        return model;
    }

    /**
     * Creates consultation notes for a specific patient
     * @param patientIndex patient to create notes for
     * @param notes the notes to attach the consultation notes
     * @author Josh Franklin
     */
    public static void CreateConsultationNotes(int patientIndex, String notes){
        try {
            Logon.getCurrentDoctor().CreateConsultationNotes(UserData.PatientUsers.get(patientIndex), notes);
            Serialization.SaveAll();
            JOptionPane.showMessageDialog(null, "Consultation saved");
        } catch (Exception e) {
            System.out.println("Unable to create consultation note: " + e);
        }
    }

    /**
     * Gets the checked feedback for the currently logged in doctor
     * @return DefaultTableModel with the doctor feedback inside
     * @author Josh Franklin
     */
    public static DefaultTableModel OutputDoctorFeedback(){
        ArrayList<DoctorFeedback> doctorFeedback = Logon.getCurrentDoctor().getFeedback();
        String[] columns = {"Rating", "Feedback notes"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (DoctorFeedback feedback : doctorFeedback) {
            Object[] rowData = new Object[2];
            rowData[0] = feedback.getRating();
            rowData[1] = feedback.getFeedbackNotes();
            model.addRow(rowData);
        }
        return model;
    }

    /**
     * All the consultation notes of a specific patient
     * @param userIndex The index of the patient to return notes for
     * @return DefaultTableModel of the Patient History
     * @author Josh Franklin
     */
    public static DefaultTableModel OutputPatientHistory(int userIndex){
        ArrayList<ConsultationNote> consultationNotes = UserData.PatientUsers.get(userIndex).getConsultationNotes();
        String[] columns = {"Date", "Notes"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (ConsultationNote note : consultationNotes) {
            Object[] rowData = new Object[2];
            rowData[0] = ControllerUtils.DateTimeFormatter(note.getDate());
            rowData[1] = note.getNotes();
            model.addRow(rowData);
        }
        return model;
    }

    /**
     * Creates a new prescription for a patient
     * @param patientIndex Relevant patient
     * @param notes Additional notes
     * @param medicineIndex The index to be able to find the medicine
     * @param quantity The quantity being given out
     * @param dosage Dosage instructions
     * @author Josh Franklin
     */
    public static void CreateNewPrescription(int patientIndex, String notes, int medicineIndex, int quantity, String dosage){
        try {
            Logon.getCurrentDoctor().PrescribeMedicine(UserData.PatientUsers.get(patientIndex), notes, SystemData.medicines.get(medicineIndex), quantity, dosage);
            Serialization.SaveAll();
            JOptionPane.showMessageDialog(null, "Prescription saved");
        } catch (Exception e) {
            System.out.println("Unable to prescribe medicine: " + e);
        }
    }

    /**
     * Sends a message to the Secretaries asking them to order more of a specific medicine
     * @param medicineIndex Medicine to order more of
     * @author Josh Franklin
     */
    public static void RequestOrderMedicine(int medicineIndex){
        try {
            String medicine = SystemData.medicines.get(medicineIndex).getMedicineName();
            Message.CreateMessage(Logon.getCurrentDoctor().getName(), "Secretary", "Please order more " + medicine);
            JOptionPane.showMessageDialog(null, "Message sent to Secretaries");
        } catch (Exception e) {
            System.out.println("Unable to request a new medicine order: " + e);
        }
    }

    /**
     * Creates a new medicine and sends a message to the Secretaries asking them to order some
     * @param medicineName Name of new medicine
     */
    public static void CreatNewMedicine(String medicineName){
        try {
            Logon.getCurrentDoctor().CreateNewMedicine(medicineName);
            Message.CreateMessage(Logon.getCurrentDoctor().getName(), "Secretary", "New medicine available for order");
            JOptionPane.showMessageDialog(null, "Medicine created and message sent to Secretaries");
        } catch (Exception e) {
            System.out.println("Could not create new medicine: " + e);
        }

    }
}
