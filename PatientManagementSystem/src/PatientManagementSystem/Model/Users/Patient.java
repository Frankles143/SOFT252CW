package PatientManagementSystem.Model.Users;

import PatientManagementSystem.Model.Gender;

import java.io.Serializable;

public class Patient extends AbstractPerson implements Serializable {

    private Gender gender;
    private int age;

    public Patient(String id, String name, String address, Gender gender, int age) {
        super(id, name, address);
         this.gender = gender;
         this.age = age;
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
