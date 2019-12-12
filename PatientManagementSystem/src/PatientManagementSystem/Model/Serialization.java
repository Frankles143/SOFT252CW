package PatientManagementSystem.Model;

import PatientManagementSystem.Model.AdminSystem.FeedbackData;
import PatientManagementSystem.Model.System.DoctorFeedback;
import PatientManagementSystem.Model.Users.*;

import javax.print.Doc;
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
     * Saving of the Feedback data, checked and unchecked
     * @author Josh Franklin
     */
    public static void SaveFeedbackData(){
        String filepath = "FeedbackData.ser";

        try{
            FileOutputStream fos = new FileOutputStream(filepath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            oos.writeObject(FeedbackData.uncheckedFeedback);
            oos.writeObject(FeedbackData.finalFeedback);

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
     * Loading the checked and unchecked feedback data back into the arraylists from file
     * @author Josh Franklin
     */
    public static void LoadFeedbackData(){
        String filepath = "FeedbackData.ser";

        try {
            FileInputStream is = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(is);

            FeedbackData.uncheckedFeedback = (ArrayList<DoctorFeedback>) ois.readObject();
            FeedbackData.finalFeedback = (ArrayList<DoctorFeedback>) ois.readObject();

            ois.close();
            is.close();
        } catch (ClassNotFoundException | IOException e) {
            System.out.println("Could not read objects!");
            e.printStackTrace();
        }
    }

}










