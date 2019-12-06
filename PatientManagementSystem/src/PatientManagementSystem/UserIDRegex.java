package PatientManagementSystem;

public abstract class UserIDRegex {
    private static String regex = "^[ADPS]\\d{4}$";

    public static String getRegex() {
        return regex;
    }
}
