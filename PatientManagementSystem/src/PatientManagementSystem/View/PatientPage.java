package PatientManagementSystem.View;

import PatientManagementSystem.Controller.PatientController;
import PatientManagementSystem.Model.System.ConsultationNote;
import PatientManagementSystem.Model.Users.Doctor;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class PatientPage {
    private JTabbedPane tabbedPane1;
    private JPanel pnlMain;
    private JTable tblMessages;
    private JTable tblHistory;
    private JButton btnUpdate;


    public PatientPage() {
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<ConsultationNote> consultationNotes = PatientController.GetPatientHistory();
                String columns[] = {"Doctor", "Date", "Notes"};
                DefaultTableModel model = new DefaultTableModel(columns, 0);
                Object[] rowData = new Object[3];
                rowData[0] = "John Dorian";
                rowData[1] = "Right now";
                rowData[2] = "You're gonna be fine pal";
                model.addRow(rowData);
                tblHistory.setModel(model);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("PatientPage");
        frame.setContentPane(new PatientPage().pnlMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public JPanel getPnlMain() {
        return pnlMain;
    }
}
