package PatientManagementSystem.Model.Users;

import PatientManagementSystem.Model.System.*;

import java.text.DecimalFormat;
import java.util.Date;

public class Secretary extends AbstractPerson {
    private static int count = 0;

    public Secretary(String id, String name, String address) {
        super(id, name, address);
    }

    public static String CreateId(){
        DecimalFormat formatter = new DecimalFormat("000");

        String newID = "S" + formatter.format(++count);

        return newID;
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
        if (!prescription.isReceived() && prescription.getQuantity() <= prescription.getMedicine().getStock()) {
            prescription.getMedicine().ReduceStock(prescription.getQuantity());
            prescription.PrescriptionReceived();
        } else {
            System.out.println("Prescription has already been received");
        }
    }

    /**
     * Increase the quantity of medicine stock
     * @author Josh Franklin
     */
    public void OrderMedicine(Medicine medicineToOrder, int quantityToOrder){
        medicineToOrder.OrderStock(quantityToOrder);
    }

    /**
     * If an appointment is approved then update the appointment with the confirmedDate, add to patient and remove the initial request from the list
     * @author Josh Franklin
     */
    public void ApproveAppointment(Appointment appointment, Date confirmedDate){
        Appointment confirmedAppointment = new Appointment(appointment.getDoctor(), appointment.getPatient(), confirmedDate);
        appointment.getPatient().addAppointment(confirmedAppointment);
        SystemData.appointmentRequests.remove(appointment);

        System.out.println("Appointment approved");
        // notify(somehow??) patient
    }

    /**
     * Deny an appointment, and remove the request from the ArrayList
     * @author Josh Franklin
     */
    public void DenyAppointment(Appointment appointment){
        SystemData.appointmentRequests.remove(appointment);

        System.out.println("Appointment denied");
        // notify(somehow??) patient
    }

    /**
     * Creates an appointment for a specific date, with specific patient and doctor
     * @author Josh Franklin
     */
    public void CreateAppointment(Doctor doctor, Patient patient, Date date){
        //Free date checking should be done with the controller and view
        Appointment newAppointment = new Appointment(doctor, patient, date);

        patient.addAppointment(newAppointment);
    }

    public void ApprovePatientAccount(AccountRequest newPatientRequest){
        Patient newPatient = new Patient(Patient.CreateId(), newPatientRequest.getName(), newPatientRequest.getAddress(), newPatientRequest.getGender(), newPatientRequest.getAge());

        UserData.PatientUsers.add(newPatient);
    }

    public void DenyPatientAccount(){
        //Remove from ArrayList
    }

}













