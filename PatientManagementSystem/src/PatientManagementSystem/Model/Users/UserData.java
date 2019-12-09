package PatientManagementSystem.Model.Users;

import java.io.*;
import java.util.ArrayList;

public class UserData implements Serializable {
    public static ArrayList<AbstractPerson> AdminUsers = new ArrayList<>();
    public static ArrayList<AbstractPerson> DoctorUsers = new ArrayList<>();
    public static ArrayList<AbstractPerson> PatientUsers = new ArrayList<>();
    public static ArrayList<AbstractPerson> SecretaryUsers = new ArrayList<>();
}
