package PatientManagementSystem.Controller;

import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.Message;
import PatientManagementSystem.Model.System.Serialization;
import PatientManagementSystem.Model.System.SystemData;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public abstract class AdminController {

    public static ArrayList<Message> OutputAdminMessages(){
        ArrayList<Message> userMessages = new ArrayList<Message>();
        for (Message message : SystemData.messages) {
            if (Logon.getCurrentAdmin().getId().equals(message.getReceiver().getId())){
                userMessages.add(message);
            }
        }
        return userMessages;
    }

    public static DefaultTableModel OutputAdminMessagesTable(){
        ArrayList<Message> userMessages = OutputAdminMessages();
        return ControllerUtils.OutputMessagesTable(userMessages);
    }

    public static void DeleteMessage(int messageToDelete){
        ArrayList<Message> userMessages = OutputAdminMessages();

        SystemData.messages.remove(userMessages.get(messageToDelete));
        Serialization.SaveSystemData();
    }
}
