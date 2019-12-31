package PatientManagementSystem.Controller;

import PatientManagementSystem.Model.System.Message;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class ControllerUtils {

    public static DefaultTableModel OutputMessagesTable(ArrayList<Message> userMessages){
        String columns[] = {"Sender", "Date", "Message"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        for (int i = 0; i < userMessages.size(); i++) {
            Object[] rowData = new Object[3];
            rowData[0] = userMessages.get(i).getSender();
            rowData[1] = DateTimeFormatter(userMessages.get(i).getDate());
            rowData[2] = userMessages.get(i).getMessage();
            model.addRow(rowData);
        }
        return model;
    }

    public static String DateTimeFormatter(LocalDateTime timeToFormat){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String formattedTime = timeToFormat.format(formatter);

        return formattedTime;
    }
}
