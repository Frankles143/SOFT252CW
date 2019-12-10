package PatientManagementSystem.Model.AdminSystem;

import PatientManagementSystem.Model.Users.Admin;
import PatientManagementSystem.Model.Users.UserData;

/**
 * A class only for creating new Admin accounts
 * @author Josh Franklin
 */
public abstract class CreateAdmin {
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
}
