package PatientManagementSystem.Model.Users;

import PatientManagementSystem.Model.System.*;

import java.text.DecimalFormat;
import java.util.Date;

public class Secretary extends AbstractPerson {
    private static int count = 0;

    public Secretary(String id, String name, String address, String password) {
        super(id, name, address, password);
    }

    @Override
    public void update(AbstractPerson person) {
        for (Secretary allSecretarys: UserData.SecretaryUsers) {
            //Notify Secretaries on the GUI
        }
    }

    public static String CreateId(){
        DecimalFormat formatter = new DecimalFormat("000");

        return "S" + formatter.format(++count);
    }

    public void RemovePatient(Patient patientToBeRemoved){
        UserData.PatientUsers.remove(patientToBeRemoved);
    }

    /**
     * Checks to see if there is enough medicine in stock and that the prescription has not yet been received before reducing stock and changing the received status
     * @param prescription prescription object, to get access to the quantity required and medicine stock
     * @author Josh Franklin
     */
    public void GiveMedicine(Prescription prescription){
        try {
            if (!prescription.isReceived() && prescription.getQuantity() <= prescription.getMedicine().getStock()) {
                prescription.getMedicine().ReduceStock(prescription.getQuantity());
                prescription.PrescriptionReceived();
            } else {
                System.out.println("Prescription has already been received");
            }
        } catch (Exception e) {
            System.out.println("Could not give out medicine: " + e);
        }
    }

    /**
     * Increase the quantity of medicine stock
     * @author Josh Franklin
     */
    public void OrderMedicine(Medicine medicineToOrder, int quantityToOrder){
        try {
            medicineToOrder.OrderStock(quantityToOrder);
        } catch (Exception e) {
            System.out.println("Unable to order medicine: " + e);
        }

    }

    /**
     * If an appointment is approved then update the appointment with the confirmedDate, add to patient and remove the initial request from the list
     * @author Josh Franklin
     */
    public void ApproveAppointment(Appointment appointment, Date confirmedDate){
        try {
            Appointment confirmedAppointment = new Appointment(appointment.getDoctor(), appointment.getPatient(), confirmedDate);
            appointment.getPatient().addAppointment(confirmedAppointment);
            SystemData.appointmentRequests.remove(appointment);

            System.out.println("Appointment approved");
            // Create message for patient which will trigger update()
        } catch (Exception e) {
            System.out.println("Unable to approve appointment: " + e);
        }
    }

    /**
     * Deny an appointment, and remove the request from the ArrayList
     * @author Josh Franklin
     */
    public void DenyAppointment(Appointment appointment){
        try {
            SystemData.appointmentRequests.remove(appointment);

            System.out.println("Appointment denied");
            // Create message for patient which will trigger update()
        } catch (Exception e) {
            System.out.println("Unable to deny appointment" + e);
        }
    }

    /**
     * Creates an appointment for a specific date, with specific patient and doctor
     * @author Josh Franklin
     */
    public void CreateAppointment(Doctor doctor, Patient patient, Date date){
        try {
            //Free date checking should be done with the controller and view
            Appointment newAppointment = new Appointment(doctor, patient, date);

            patient.addAppointment(newAppointment);
        } catch (Exception e) {
            System.out.println("Unable to create appointment: " + e);
        }
    }

    /**
     * Take AccountRequest object, assign them a new ID, create the user and add to UserData list, new Patients do not have a password, they must create their own
     * @param newPatientRequest new patient object to be checked
     * @author Josh Franklin
     */
    public void ApprovePatientAccount(AccountRequest newPatientRequest){
        try {
            Patient newPatient = new Patient(Patient.CreateId(), newPatientRequest.getName(), newPatientRequest.getAddress(), newPatientRequest.getGender(), newPatientRequest.getAge());
            UserData.PatientUsers.add(newPatient);

            //Send new Patient a message

            System.out.println("New Patient added successfully");
        } catch (Exception e) {
            System.out.println("Unable to approve patient account: " + e);
        }
    }

    /**
     * Removes AccountRequest from the accountRequests list
     * @param newPatientRequest
     * @author Josh Franklin
     */
    public void DenyPatientAccount(AccountRequest newPatientRequest){
        try {
            SystemData.accountRequests.remove(newPatientRequest);

            System.out.println("Request denied");
        } catch (Exception e) {
            System.out.println("Unable to deny patient account request: " + e);
        }
    }

    public void ApproveAccountTermination(Patient patientToBeRemoved){
        try {
            UserData.PatientUsers.remove(patientToBeRemoved);
            SystemData.accountTerminationRequests.remove(patientToBeRemoved);

            System.out.println("Patient account terminated successfully");
        } catch (Exception e) {
            System.out.println("Unable to terminate account : " + e);
        }
    }

    public void DenyAccountTermination(Patient patient){
        try {
            SystemData.accountTerminationRequests.remove(patient);

            //Send a message to the patient

            System.out.println("Patient termination request denied");
        } catch (Exception e) {
            System.out.println("Unable to deny termination request");
        }
    }

}













