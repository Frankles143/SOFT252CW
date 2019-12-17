package src.PatientManagementSystem.Model.Users;

public class Admin extends AbstractPerson {

    public Admin(String id, String name, String address) {
        super(id, name, address);
    }

    /**
     * A class only for creating new Admin accounts
     * @author Josh Franklin
     */
    public Admin CreateAdmin(String id, String name, String address){
        Admin newAdmin = null;
        try {
            newAdmin = new Admin(id, name, address);
            UserData.AdminUsers.add(newAdmin);
        }
        catch (Exception e) {
            System.out.println("There was an error: " + e);
        }
        return newAdmin;
    }

    public void ViewDoctorRatings () {
        //View feedback
        //Return the ArrayList of feedback, the controller will deal with output
    }

    public void EditDoctorRatings() {
        //Edit feedback
        //Changes the text (never the rating number) and saves over the currently open ArrayList index
    }

    public void AttachFeedback() {
        //Adds the Feedback object to the relevant doctors feedback ArrayList
    }

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
