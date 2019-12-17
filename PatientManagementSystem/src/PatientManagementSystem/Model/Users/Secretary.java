package PatientManagementSystem.Model.Users;

public class Secretary extends AbstractPerson {

    public Secretary(String id, String name, String address) {
        super(id, name, address);
    }


    public void RemovePatient(Patient patientToBeRemoved){
        UserData.PatientUsers.remove(patientToBeRemoved);
    }
}
