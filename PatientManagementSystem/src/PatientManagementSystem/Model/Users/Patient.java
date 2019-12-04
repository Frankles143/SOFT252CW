package PatientManagementSystem.Model.Users;

import PatientManagementSystem.Gender;

public class Patient extends AbstractPerson {

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
