package PatientManagementSystem.Model.Users;

import PatientManagementSystem.Model.Observer.Observer;
import PatientManagementSystem.Model.System.Password;
import PatientManagementSystem.Model.UserIDRegex;

import java.io.Serializable;

/**
 * Abstract class for all users
 * @author Josh Franklin
 */
public abstract class AbstractPerson implements Serializable, Observer {
    private String id;
    private String name;
    private String address;
    private String salt;
    private String encryptedPassword;

    AbstractPerson(String id, String name, String address, String password) {
        setId(id);
        this.name = name;
        this.address = address;
        this.salt = Password.GenerateSalt(512).get();
        this.encryptedPassword = Password.HashPassword(password, this.salt).get();
    }

    AbstractPerson(String id, String name, String address) {
        setId(id);
        this.name = name;
        this.address = address;
        this.salt = Password.GenerateSalt(512).get();
    }

    public String getId() {
        return id;
    }

    /**
     * Checks whether the ID matches a set regex pattern before setting
     * @author Josh Franklin
     */
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String newPassword) {
        this.encryptedPassword = Password.HashPassword(newPassword, this.salt).get();
    }
}
