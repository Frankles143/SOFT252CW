package PatientManagementSystem.Model.System;

import PatientManagementSystem.Model.Observer.Observable;
import PatientManagementSystem.Model.Observer.Observer;
import PatientManagementSystem.Model.Observer.ObserverData;
import PatientManagementSystem.Model.Users.AbstractPerson;
import PatientManagementSystem.Model.Users.UserData;

import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable, Observable {
    private ArrayList<AbstractPerson> observers;
    private String sender;
    private AbstractPerson receiver;
    private String message;
    private boolean read;

    public Message(String sender, AbstractPerson receiver, String message) {
        observers = new ArrayList<>();
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.read = false;
        RegisterObserver(receiver);
        notifyObservers();
    }

    public void RegisterObserver(AbstractPerson person){
        observers.add(person);
    }
    public void removeObserver(AbstractPerson person){
        observers.remove(person);
    }
    public void notifyObservers(){
        for (AbstractPerson person : observers) {
            person.update(person);
        }
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

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
