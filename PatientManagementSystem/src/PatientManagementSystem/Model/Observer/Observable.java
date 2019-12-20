package PatientManagementSystem.Model.Observer;

public interface Observable {
    public void RegisterObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}
