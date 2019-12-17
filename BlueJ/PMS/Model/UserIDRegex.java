package Model;

/**
 * A class that can referenced to check the layout of the user IDs, to make sure they conform to a standard
 * @author Josh Franklin
 */
public abstract class UserIDRegex {
    private static String regex = "^[ADPS]\\d{4}$";

    public static String getRegex() {
        return regex;
    }
}
