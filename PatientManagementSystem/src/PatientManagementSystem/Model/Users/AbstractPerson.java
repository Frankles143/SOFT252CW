package PatientManagementSystem.Model.Users;

import PatientManagementSystem.UserIDRegex;

import java.io.Serializable;

/**
 * Abstract class for all users
 * @author Josh Franklin
 */
public abstract class AbstractPerson implements Serializable {
    private String id;
    private String name;
    private String address;

    AbstractPerson(String id, String name, String address) {
        setId(id);
        this.name = name;
        this. address = address;
    }

    public String getId() {
        return id;
    }

    //Checks whether the ID matches a set regex pattern before setting
    public void setId(String id) {
        if (id.matches(UserIDRegex.getRegex())) {
            this.id = id;
        } else {
            System.out.println("ID does not match format");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
