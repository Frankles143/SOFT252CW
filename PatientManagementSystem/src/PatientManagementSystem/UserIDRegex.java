package PatientManagementSystem;

import java.util.regex.Pattern;

public enum UserIDRegex {
    USER_ID_REGEX(("^[ADPS]\\d{4}$"));

    private final Pattern regex;

    private UserIDRegex(final String regex){
        this.regex = Pattern.compile(regex);
    }

    public String getPattern() {
        return this.regex.toString();
    }
}


