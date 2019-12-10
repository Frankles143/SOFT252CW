package PatientManagementSystem.Model;

import PatientManagementSystem.Model.Users.AbstractPerson;
import PatientManagementSystem.Model.Users.Patient;

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
     * @param user user object to save
     */
    public static void SaveUserData(ArrayList<AbstractPerson> user) {
        String filepath = "UserObject.ser";
        try {
            FileOutputStream fos = new FileOutputStream(filepath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            // write object to file
            oos.writeObject(user);
            System.out.println("Done");
            // closing resources
            oos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads an ArrayList&lt;Abstract Person&gt; from a text file
     * @author Josh Franklin
     * @return returns an ArrayList&lt;AbstractPerson&gt;
     */
    public static ArrayList<AbstractPerson> LoadUserData() {
        String filepath = "UserObject.ser";
        ArrayList<AbstractPerson> user = null;
        try {
            FileInputStream is = new FileInputStream(filepath);
            ObjectInputStream ois = new ObjectInputStream(is);

            user = (ArrayList<AbstractPerson>) ois.readObject();

            ois.close();
            is.close();
            System.out.println(user.toString());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return user;
    }
}
