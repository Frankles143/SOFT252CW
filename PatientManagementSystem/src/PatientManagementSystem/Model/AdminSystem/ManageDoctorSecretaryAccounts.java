package PatientManagementSystem.Model.AdminSystem;

import PatientManagementSystem.Model.Users.AbstractPerson;
import PatientManagementSystem.Model.Users.Doctor;
import PatientManagementSystem.Model.Users.Secretary;
import PatientManagementSystem.Model.Users.UserData;

/**
 * A class for the addition and removal of Doctors and Secretaries
 * @author Josh Franklin
 */
public abstract class ManageDoctorSecretaryAccounts {

    /**
     * Method to create new doctor user
     * @param id
     * @param name
     * @param address
     * @return returns the newly created doctor for testing/confirmation purposes
     * @author Josh Franklin
     */
    public Doctor CreateDoctor(String id, String name, String address){
        Doctor newDoctor = null;
        try {
            newDoctor = new Doctor(id, name, address);
            UserData.DoctorUsers.add(newDoctor);
        }
        catch (Exception e) {
            System.out.println("There was an error: " + e);
        }
        return newDoctor;
    }

    /**
     * Removes a doctor from the ArrayList
     * @param doctorToBeRemoved
     * @author Josh Franklin
     */
    public void RemoveDoctor(Doctor doctorToBeRemoved){
        try {
            UserData.DoctorUsers.remove(doctorToBeRemoved);
        }
        catch (Exception e) {
            System.out.println("Could not remove this Doctor");
        }
    }

    /**
     * Method to create a new secretary user
     * @param id
     * @param name
     * @param address
     * @return returns the newly created secretary for testing/confirmation purposes
     * @author Josh Franklin
     */
    public Secretary CreateSecretary(String id, String name, String address){
        Secretary newSecretary = null;
        try {
            newSecretary = new Secretary(id, name, address);
            UserData.SecretaryUsers.add(newSecretary);
        }
        catch (Exception e) {
            System.out.println("There was an error: " + e);
        }
        return newSecretary;
    }

    /**
     * Removes secretary from ArrayList
     * @param secretaryToBeRemoved
     * @author Josh Franklin
     */
    public void RemoveSecretary(Secretary secretaryToBeRemoved){
        try {
            UserData.SecretaryUsers.remove(secretaryToBeRemoved);
        }
        catch (Exception e) {
            System.out.println("Could not remove this Secretary");
        }
    }
}
