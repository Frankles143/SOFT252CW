package PatientManagementSystem.Model;

import PatientManagementSystem.Model.System.Medicine;
import PatientManagementSystem.Model.System.SystemData;
import PatientManagementSystem.Model.System.DoctorFeedback;
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
        String filepath = "UserObject.ser";
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
        String filepath = "UserObject.ser";

        try {
            FileInputStream is = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(is);

            //Reads objects in from file
            UserData.AdminUsers = (ArrayList<Admin>) ois.readObject();
            UserData.DoctorUsers = (ArrayList<Doctor>) ois.readObject();
            UserData.PatientUsers = (ArrayList<Patient>) ois.readObject();
            UserData.SecretaryUsers = (ArrayList<Secretary>) ois.readObject();

            //Closes resources
            ois.close();
            is.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Could not read objects!");
            e.printStackTrace();
        }
    }

    /**
     * Saving thr system data
     * @author Josh Franklin
     */
    public static void SaveSystemData(){
        String filepath = "SystemData.ser";

        try{
            FileOutputStream fos = new FileOutputStream(filepath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(SystemData.uncheckedFeedback);
            oos.writeObject(SystemData.medicines);

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
     * Loading the system data back into arraylists from a file
     * @author Josh Franklin
     */
    public static void LoadSystemData(){
        String filepath = "SystemData.ser";

        try {
            FileInputStream is = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(is);

            SystemData.uncheckedFeedback = (ArrayList<DoctorFeedback>) ois.readObject();
            SystemData.medicines = (ArrayList<Medicine>) ois.readObject();

            ois.close();
            is.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Could not read objects!");
            e.printStackTrace();
        }
    }

}










