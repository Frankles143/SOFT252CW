package PatientManagementSystem.Controller;

import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.*;
import PatientManagementSystem.Model.Users.Patient;
import PatientManagementSystem.Model.Users.UserData;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.util.ArrayList;

public abstract class SecretaryController {

    public static ArrayList<Message> OutputSecretaryMessages(){
        ArrayList<Message> userMessages = new ArrayList<Message>();
        for (Message message : SystemData.messages) {
            if (Logon.getCurrentSecretary().getId().equals(message.getReceiver().getId())){
                userMessages.add(message);
            }
        }
        return userMessages;
    }

    public static DefaultTableModel OutputSecretaryMessagesTable(){
        ArrayList<Message> userMessages = OutputSecretaryMessages();
        return ControllerUtils.OutputMessagesTable(userMessages);
    }

    public static void DeleteMessage(int messageToDelete){
        ArrayList<Message> userMessages = OutputSecretaryMessages();

        SystemData.messages.remove(userMessages.get(messageToDelete));
        Serialization.SaveSystemData();
    }

    public static void CreateAppointment(int doctorIndex, int patientIndex, LocalDateTime date){
        try {
            Logon.getCurrentSecretary().CreateAppointment(UserData.DoctorUsers.get(doctorIndex), UserData.PatientUsers.get(patientIndex), date);
            Serialization.SaveAll();
            JOptionPane.showMessageDialog(null, "Appointment created successfully");
        } catch (Exception e) {
            System.out.println("Unable to create new appointment: " + e);
        }
    }

    public static DefaultTableModel OutputAppointmentRequests(){
        Object[] columns = {"Patient", "Doctor", "Date One", "Date Two", "Date Three"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Appointment appointment : SystemData.appointmentRequests) {
            Object[] rowData = new Object[5];
            rowData[0] = appointment.getPatient().getName();
            rowData[1] = appointment.getDoctor().getName();
            rowData[2] = ControllerUtils.DateTimeFormatter(appointment.getPossibleDates().get(0));
            rowData[3] = ControllerUtils.DateTimeFormatter(appointment.getPossibleDates().get(1));
            rowData[4] = ControllerUtils.DateTimeFormatter(appointment.getPossibleDates().get(2));
            model.addRow(rowData);
        }
        return model;
    }

    public static DefaultTableModel OutputAccountRequests(){
        Object[] columns = {"Name", "Address", "Gender", "Age"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (AccountRequest user : SystemData.accountRequests) {
            Object[] rowData = new Object[4];
            rowData[0] = user.getName();
            rowData[1] = user.getAddress();
            rowData[2] = user.getGender();
            rowData[3] = user.getAge();
            model.addRow(rowData);
        }
        return model;
    }

    public static DefaultTableModel OutputAccountTerminationRequests(){
        Object[] columns = {"Name", "Address", "Gender", "Age"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (Patient user : SystemData.accountTerminationRequests) {
            Object[] rowData = new Object[4];
            rowData[0] = user.getName();
            rowData[1] = user.getAddress();
            rowData[2] = user.getGender();
            rowData[3] = user.getAge();
            model.addRow(rowData);
        }
        return model;
    }

    public static DefaultTableModel OutputPrescriptions(int patientIndex){
        String[] columns = {"Doctor", "Notes", "Medicine", "Quantity", "Dosage", "Received"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);
        Patient patient = UserData.PatientUsers.get(patientIndex);
        Object[] rowData = new Object[6];
            for (Prescription prescription : patient.getPrescriptions()) {
                if (!prescription.isReceived()) {
                    rowData[0] = prescription.getDoctor().getName();
                    rowData[1] = prescription.getDoctorNotes();
                    rowData[2] = prescription.getMedicine().getMedicineName();
                    rowData[3] = prescription.getQuantity();
                    rowData[4] = prescription.getDosage();
                    rowData[5] = prescription.isReceived();
                    model.addRow(rowData);
                }
            }
        return model;
    }

    public static void ApproveAppointment(int appointmentIndex, int dateIndex){
        try {
            LocalDateTime date = SystemData.appointmentRequests.get(appointmentIndex).getPossibleDates().get(dateIndex);
            Logon.getCurrentSecretary().ApproveAppointment(SystemData.appointmentRequests.get(appointmentIndex), date);
            Serialization.SaveAll();
            JOptionPane.showMessageDialog(null, "Appointment approved successfully");
        } catch (Exception e) {
            System.out.println("Unable to approve appointment: " + e);
        }
    }

    public static void DenyAppointment(int appointmentIndex){
        try {
            Logon.getCurrentSecretary().DenyAppointment(SystemData.appointmentRequests.get(appointmentIndex));
            Serialization.SaveAll();
            JOptionPane.showMessageDialog(null, "Appointment denied successfully");
        } catch (Exception e) {
            System.out.println("Unable to deny appointment: " + e);
        }
    }

    public static void ApproveNewAccount(int patientIndex){
        try {
            Logon.getCurrentSecretary().ApprovePatientAccount(SystemData.accountRequests.get(patientIndex));
            Serialization.SaveAll();
            JOptionPane.showMessageDialog(null, "Account approved successfully");
        } catch (Exception e) {
            System.out.println("Unable to approve new account: " + e);
        }
    }

    public static void DenyNewAccount(int patientIndex){
        try {
            Logon.getCurrentSecretary().DenyPatientAccount(SystemData.accountRequests.get(patientIndex));
            Serialization.SaveAll();
            JOptionPane.showMessageDialog(null, "Account denied successfully");
        } catch (Exception e) {
            System.out.println("Unable to approve new account: " + e);
        }
    }

    public static void ApproveAccountTermination(int patientIndex){
        try {
            Logon.getCurrentSecretary().ApproveAccountTermination(SystemData.accountTerminationRequests.get(patientIndex));
            Serialization.SaveAll();
            JOptionPane.showMessageDialog(null, "Account terminated successfully");
        } catch (Exception e) {
            System.out.println("Unable to terminate user account: " + e);
        }
    }

    public static void DenyAccountTermination(int patientIndex){
        try {
            Logon.getCurrentSecretary().DenyAccountTermination(SystemData.accountTerminationRequests.get(patientIndex));
            Serialization.SaveAll();
            JOptionPane.showMessageDialog(null, "Account termination denied");
        } catch (Exception e) {
            System.out.println("Unable to deny user termination request: " + e);
        }
    }

    public static void GivePatientPrescription(int patient, int prescription){
        try {
            Logon.getCurrentSecretary().GiveMedicine(UserData.PatientUsers.get(patient).getPrescriptions().get(prescription));
            Serialization.SaveAll();
            JOptionPane.showMessageDialog(null, "Successfully gave patient prescription");
        } catch (Exception e) {
            System.out.println("Unable to give patient prescription: " + e);
        }
    }

    public static void OrderMedicine(int medicineIndex, int quantity){
        try {
            Logon.getCurrentSecretary().OrderMedicine(SystemData.medicines.get(medicineIndex), quantity);
            Serialization.SaveAll();
            JOptionPane.showMessageDialog(null, "Successfully ordered more " + SystemData.medicines.get(medicineIndex).getMedicineName());
        } catch (Exception e) {
            System.out.println("Could not order more medicine: " + e);
        }
    }

}
