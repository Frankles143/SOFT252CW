package PatientManagementSystem.View;

import PatientManagementSystem.Controller.PatientController;

import javax.swing.*;
import java.awt.event.*;

public class PatientPage {
    private JTabbedPane tabPatientTabs;
    private JPanel pnlMain;
    private JTable tblMessages;
    private JTable tblHistory;
    private JButton btnDeleteMessage;

    public PatientPage() {
        tabPatientTabs.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                tblHistory.setModel(PatientController.OutputPatientHistory());
                tblMessages.setModel(PatientController.OutputPatientMessagesTable());
                tblMessages.setDefaultEditor(Object.class, null);
            }
        });
        btnDeleteMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientController.DeleteMessage(tblMessages.getSelectedRow());
                tblMessages.setModel(PatientController.OutputPatientMessagesTable());
            }
        });
    }

    public JPanel getPnlMain() {
        return pnlMain;
    }
}
