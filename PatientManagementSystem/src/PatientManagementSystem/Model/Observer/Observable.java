package PatientManagementSystem.Model.Observer;

import PatientManagementSystem.Model.Users.AbstractPerson;

public interface Observable {
    public void RegisterObserver(AbstractPerson person);
    public void removeObserver(AbstractPerson person);
    public void notifyObservers();
}
