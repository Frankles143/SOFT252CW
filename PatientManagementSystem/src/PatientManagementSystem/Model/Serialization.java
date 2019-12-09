package PatientManagementSystem.Model;

import PatientManagementSystem.Model.Users.AbstractPerson;
import PatientManagementSystem.Model.Users.Patient;

import java.io.*;
import java.util.ArrayList;

public class Serialization {
    private static final long serialVersionUID = -6470090944414208496L;
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
