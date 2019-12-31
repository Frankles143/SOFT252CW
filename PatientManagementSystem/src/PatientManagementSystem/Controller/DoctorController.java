package PatientManagementSystem.Controller;

import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.Message;
import PatientManagementSystem.Model.System.Serialization;
import PatientManagementSystem.Model.System.SystemData;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public abstract class DoctorController {

    public static ArrayList<Message> OutputDoctorMessages(){
        ArrayList<Message> userMessages = new ArrayList<Message>();
        for (Message message : SystemData.messages) {
            if (Logon.getCurrentDoctor().getId().equals(message.getReceiver().getId())){
                userMessages.add(message);
            }
        }
        return userMessages;
    }

    public static DefaultTableModel OutputDoctorMessagesTable(){
        ArrayList<Message> userMessages = OutputDoctorMessages();
        return ControllerUtils.OutputMessagesTable(userMessages);
    }

    public static void DeleteMessage(int messageToDelete){
        ArrayList<Message> userMessages = OutputDoctorMessages();

        SystemData.messages.remove(userMessages.get(messageToDelete));
        Serialization.SaveSystemData();
    }
}
