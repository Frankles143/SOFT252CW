package PatientManagementSystem.Model;

/**
 * A class that can referenced to check the layout of the user IDs, to make sure they conform to a standard
 * @author Josh Franklin
 */
public abstract class UserIDRegex {

    public static String getRegex() {
        return "^[ADPS]\\d{4}$";
    }
}
