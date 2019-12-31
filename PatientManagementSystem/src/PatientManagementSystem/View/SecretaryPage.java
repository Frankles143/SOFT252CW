package PatientManagementSystem.View;

import PatientManagementSystem.Controller.SecretaryController;

import javax.swing.*;
import java.awt.event.*;

public class SecretaryPage {
    private JPanel pnlMain;
    private JTabbedPane tabSecretaryTab;
    private JTable tblMessage;
    private JButton btnDeleteMessage;

    public SecretaryPage() {
        tabSecretaryTab.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                tblMessage.setModel(SecretaryController.OutputSecretaryMessagesTable());
                tblMessage.setDefaultEditor(Object.class, null);
            }
        });
        btnDeleteMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblMessage.getSelectedRow() >= 0) {
                    SecretaryController.DeleteMessage(tblMessage.getSelectedRow());
                    tblMessage.setModel(SecretaryController.OutputSecretaryMessagesTable());
                }
            }
        });
    }

    public JPanel getPnlMain() {
        return pnlMain;
    }
}
