package PatientManagementSystem.Model.Users;

import PatientManagementSystem.Model.System.*;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Comparator;

public class Secretary extends AbstractPerson {

    public Secretary(String id, String name, String address, String password) {
        super(id, name, address, password);
    }

    @Override
    public void update(AbstractPerson person) {
        for (Secretary allSecretarys: UserData.SecretaryUsers) {
            //Notify Secretaries on the GUI
        }
    }

    /**
     * Sorts the arraylist into numerical order by ID and then counts through assigning the new ID to the first available
     * @return returns an unused ID
     * @author Josh Franklin
     */
    public static String CreateId(){
        UserData.SecretaryUsers.sort(Comparator.comparing(AbstractPerson::getId));

        DecimalFormat formatter = new DecimalFormat("0000");
        int idNumber = 1;
        String id = "";

        for (Secretary secretary : UserData.SecretaryUsers) {

            id = "S" + formatter.format(idNumber);

            if (!secretary.getId().equals(id)){
                return id;
            }
            idNumber++;
        }

        id = "S" + formatter.format(idNumber);
        return id;
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
            if (!prescription.isReceived()) {
                if(prescription.getQuantity() <= prescription.getMedicine().getStock()) {
                    prescription.getMedicine().ReduceStock(prescription.getQuantity());
                    prescription.PrescriptionReceived();
                    Message.CreateMessage(Secretary.this.getName(), prescription.getPatient(), "You have filled out your prescription for: " + prescription.getMedicine().getMedicineName());
                } else {
                    System.out.println("Not enough medicine in stock");
                }
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
     * @param medicineToOrder medicine object which needs to be ordered
     * @param quantityToOrder int of medicine wanting to be ordered
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
     * @param appointment appointment object to be approved
     * @param confirmedDate a single date object from the list
     */
    public void ApproveAppointment(Appointment appointment, LocalDateTime confirmedDate){
        try {
            Appointment confirmedAppointment = new Appointment(appointment.getDoctor(), appointment.getPatient(), confirmedDate);
            appointment.getPatient().addAppointment(confirmedAppointment);
            SystemData.appointmentRequests.remove(appointment);
            System.out.println("Appointment approved");

            Message.CreateMessage(Secretary.this.getName(), appointment.getPatient(), "You're appointment has been approved, please see Appointment tab for more details");
        } catch (Exception e) {
            System.out.println("Unable to approve appointment: " + e);
        }
    }

    /**
     * Deny an appointment, and remove the request from the ArrayList
     * @param appointment appointment object to be denied
     * @author Josh Franklin
     */
    public void DenyAppointment(Appointment appointment){
        try {
            SystemData.appointmentRequests.remove(appointment);

            System.out.println("Appointment denied");

            Message.CreateMessage(Secretary.this.getName(), appointment.getPatient(), "Your appointment request has been denied, please try again with different dates");
        } catch (Exception e) {
            System.out.println("Unable to deny appointment" + e);
        }
    }

    /**
     * Creates an appointment for a specific date, with specific patient and doctor
     * @param doctor doctor which the appointment is being created for
     * @param patient patient which the appointment is being created for
     * @param date date object of appointment
     * @author Josh Franklin
     */
    public void CreateAppointment(Doctor doctor, Patient patient, LocalDateTime date){
        try {
            //Free date checking should be done with the controller and view
            Appointment newAppointment = new Appointment(doctor, patient, date);

            patient.addAppointment(newAppointment);

            Message.CreateMessage(Secretary.this.getName(), doctor, "You have a new appointment booked");
            Message.CreateMessage(Secretary.this.getName(), patient, "You have a new appointment booked");
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
            SystemData.accountRequests.remove(newPatientRequest);

            Message.CreateMessage(Secretary.this.getName(), newPatient, "Welcome to Sacred Heart, please use the tabs above to explore our system, and have a good day!");

            System.out.println("New Patient added successfully");
        } catch (Exception e) {
            System.out.println("Unable to approve patient account: " + e);
        }
    }

    /**
     * Removes AccountRequest from the accountRequests list
     * @param newPatientRequest newPatientRequest object to be denied
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

    /**
     * Removes the user from the UserData list and the account termination list
     * @param patientToBeRemoved patient object to be removed
     * @author Josh Franklin
     */
    public void ApproveAccountTermination(Patient patientToBeRemoved){
        try {
            if (UserData.PatientUsers.contains(patientToBeRemoved)){
                UserData.PatientUsers.remove(patientToBeRemoved);
                SystemData.accountTerminationRequests.remove(patientToBeRemoved);

                System.out.println("Patient account terminated successfully");
            } else {
                System.out.println("Patient does not exist");
            }

        } catch (Exception e) {
            System.out.println("Unable to terminate account : " + e);
        }
    }

    /**
     * Deny an account termination request if patient changes their mind
     * @param patient patient to deny request
     * @author Josh Franklin
     */
    public void DenyAccountTermination(Patient patient){
        try {
            SystemData.accountTerminationRequests.remove(patient);

            Message.CreateMessage(Secretary.this.getName(), patient, "We have denied your account termination request");

            System.out.println("Patient termination request denied");
        } catch (Exception e) {
            System.out.println("Unable to deny termination request");
        }
    }

}













