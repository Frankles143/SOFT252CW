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

}
