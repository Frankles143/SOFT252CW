package PatientManagementSystem.Model.System;

import PatientManagementSystem.Model.Users.AbstractPerson;

public class Message {
    private String sender;
    private AbstractPerson receiver;
    private String message;

    public Message(String sender, AbstractPerson receiver, String message) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
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
