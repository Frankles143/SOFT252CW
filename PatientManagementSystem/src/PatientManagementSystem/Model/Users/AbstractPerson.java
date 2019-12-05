package PatientManagementSystem.Model.Users;

import PatientManagementSystem.UserIDRegex;

public abstract class AbstractPerson {
    private String id;
    private String name;
    private String address;
    private UserIDRegex regex;
    //private final String regex = "^[ADPS]\\d{4}$";  //This will only allow pattern of A0123

    AbstractPerson(String id, String name, String address) {
        setId(id);
        this.name = name;
        this. address = address;
    }

    protected String getId() {
        return id;
    }

    //Checks whether the ID matches a set regex pattern before setting
    protected void setId(String id) {
        if (id.matches(regex.getPattern())) {
            this.id = id;
        } else {
            System.out.println("ID does not match format");
        }
    }

    protected String getName() {
        return name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    protected String getAddress() {
        return address;
    }

    protected void setAddress(String address) {
        this.address = address;
    }
}
