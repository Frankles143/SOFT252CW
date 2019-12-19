package PatientManagementSystem.Model.System;

import PatientManagementSystem.Model.Gender;

/**
 * An account request object to hold relevant information for secretaries to check
 * @author Josh Franklin
 */
public class AccountRequest {
    private String name;
    private String address;
    private Gender gender;
    private int age;

    public AccountRequest(String name, String address) {
        this.name = name;
        this.address = address;
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
}
