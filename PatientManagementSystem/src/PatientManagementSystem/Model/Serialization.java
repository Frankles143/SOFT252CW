package PatientManagementSystem.Model;

import PatientManagementSystem.Model.System.*;
import PatientManagementSystem.Model.Users.*;

import java.io.*;
import java.util.ArrayList;

/**
 * A class to hold the serialization methods
 * @author Josh Franklin
 */
public class Serialization {

    /**
     * Saving of the user ArrayLists to file
     * @author Josh Franklin
     */
    public static void SaveUserData() {
        String filepath = "UserData.ser";
        try {
            FileOutputStream fos = new FileOutputStream(filepath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            //Write objects to file
            oos.writeObject(UserData.AdminUsers);
            oos.writeObject(UserData.DoctorUsers);
            oos.writeObject(UserData.PatientUsers);
            oos.writeObject(UserData.SecretaryUsers);

            //Closes resources
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not save objects to file!");
            e.printStackTrace();
        }
    }

    /**
     * Reads all user ArrayLists from a text file
     * @author Josh Franklin
     */
    public static void LoadUserData() {
        String filepath = "UserData.ser";

        try {
            FileInputStream is = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(is);

            //Reads objects in from file
            UserData.AdminUsers.addAll((ArrayList<Admin>) ois.readObject());
            UserData.DoctorUsers.addAll((ArrayList<Doctor>) ois.readObject());
            UserData.PatientUsers.addAll((ArrayList<Patient>) ois.readObject());
            UserData.SecretaryUsers.addAll((ArrayList<Secretary>) ois.readObject());

            //Closes resources
            ois.close();
            is.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Could not read objects!");
            e.printStackTrace();
        }
    }

    /**
     * Saving the system data
     * @author Josh Franklin
     */
    public static void SaveSystemData(){
        String filepath = "SystemData.ser";

        try{
            FileOutputStream fos = new FileOutputStream(filepath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(SystemData.uncheckedFeedback);
            oos.writeObject(SystemData.medicines);
            oos.writeObject(SystemData.accountRequests);
            oos.writeObject(SystemData.accountTerminationRequests);
            oos.writeObject(SystemData.appointmentRequests);
            oos.writeObject(SystemData.messages);

            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Could not save objects to file!");
            e.printStackTrace();
        }

    }

    /**
     * Loading the system data back into ArrayLists from a file
     * @author Josh Franklin
     */
    public static void LoadSystemData(){
        String filepath = "SystemData.ser";

        try {
            FileInputStream is = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(is);

            SystemData.uncheckedFeedback.addAll((ArrayList<DoctorFeedback>) ois.readObject());
            SystemData.medicines.addAll((ArrayList<Medicine>) ois.readObject());
            SystemData.accountRequests.addAll((ArrayList<AccountRequest>) ois.readObject());
            SystemData.accountTerminationRequests.addAll((ArrayList<Patient>) ois.readObject());
            SystemData.appointmentRequests.addAll((ArrayList<Appointment>) ois.readObject());
            SystemData.messages.addAll((ArrayList<Message>) ois.readObject());

            ois.close();
            is.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Could not read objects!");
            e.printStackTrace();
        }
    }

}










