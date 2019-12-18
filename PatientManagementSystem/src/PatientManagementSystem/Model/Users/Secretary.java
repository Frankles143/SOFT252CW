package PatientManagementSystem.Model.Users;

import PatientManagementSystem.Model.System.Medicine;
import PatientManagementSystem.Model.System.Prescription;

public class Secretary extends AbstractPerson {

    public Secretary(String id, String name, String address) {
        super(id, name, address);
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

    public void OrderMedicine(Medicine medicineToOrder, int quantityToOrder){
        medicineToOrder.OrderStock(quantityToOrder);
    }
}
