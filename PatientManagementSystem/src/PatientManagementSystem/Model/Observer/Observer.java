package PatientManagementSystem.Model.Observer;

import PatientManagementSystem.Model.Users.AbstractPerson;

public interface Observer {
    public void update(AbstractPerson person);
}
