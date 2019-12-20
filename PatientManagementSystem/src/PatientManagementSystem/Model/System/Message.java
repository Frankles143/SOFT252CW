package PatientManagementSystem.Model.System;

import PatientManagementSystem.Model.Observer.Observable;
import PatientManagementSystem.Model.Observer.Observer;
import PatientManagementSystem.Model.Users.AbstractPerson;

public class Message implements Observable {
    private String sender;
    private AbstractPerson receiver;
    private String message;

    public Message(String sender, AbstractPerson receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
    }

    public void RegisterObserver(Observer o){

    }
    public void removeObserver(Observer o){

    }
    public void notifyObservers(){

    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public AbstractPerson getReceiver() {
        return receiver;
    }

    public void setReceiver(AbstractPerson receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
