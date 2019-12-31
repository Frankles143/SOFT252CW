package PatientManagementSystem.Model.System;

import PatientManagementSystem.Model.Observer.Observable;
import PatientManagementSystem.Model.Users.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Message implements Serializable, Observable {
    private ArrayList<AbstractPerson> observers;
    private String sender;
    private AbstractPerson receiver;
    private Date date;
    private String message;

    public Message(String sender, AbstractPerson receiver, String message) {
        observers = new ArrayList<>();
        this.sender = sender;
        this.receiver = receiver;
        this.date = new Date();
        this.message = message;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static void DeleteMessage(Message message){
        SystemData.messages.remove(message);
    }

    public static void CreateMessage(String sender, AbstractPerson receiver, String message){
        Message newMessage = new Message(sender, receiver, message);
        SystemData.messages.add(newMessage);
    }

    public static void CreateMessage(String sender, String receiver, String message) {
        switch (receiver) {
            case "Admin":
                for (Admin person : UserData.AdminUsers){
                    Message newMessage = new Message(sender, person, message);
                    SystemData.messages.add(newMessage);
                }
                break;
            case "Doctor":
                for (Doctor person : UserData.DoctorUsers){
                    Message newMessage = new Message(sender, person, message);
                    SystemData.messages.add(newMessage);
                }
                break;
            case "Patient":
                for (Patient person : UserData.PatientUsers){
                    Message newMessage = new Message(sender, person, message);
                    SystemData.messages.add(newMessage);
                }
                break;
            case "Secretary":
                for (Secretary person : UserData.SecretaryUsers){
                    Message newMessage = new Message(sender, person, message);
                    SystemData.messages.add(newMessage);
                }
                break;
        }
    }

}
