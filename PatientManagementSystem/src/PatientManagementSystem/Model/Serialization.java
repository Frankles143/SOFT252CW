package PatientManagementSystem.Model;

import PatientManagementSystem.Model.Users.AbstractPerson;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serialization {
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

    public static void LoadUserData(){
        
    }
}
