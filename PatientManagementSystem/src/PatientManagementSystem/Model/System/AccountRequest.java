package PatientManagementSystem.Model.System;

import PatientManagementSystem.Model.Gender;

import java.io.Serializable;

/**
 * An account request object to hold relevant information for secretaries to check
 * @author Josh Franklin
 */
public class AccountRequest implements Serializable {
    private String name;
    private String address;
    private Gender gender;
    private int age;
    private String encryptedPassword;
    private String salt;

    public AccountRequest(String name, String address, Gender gender, int age, String password) {
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.age = age;
        this.salt = Password.GenerateSalt(512).get();
        this.encryptedPassword = Password.HashPassword(password, salt).get();
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
