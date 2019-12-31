package PatientManagementSystem.View;

import PatientManagementSystem.Controller.AdminController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AdminPage {
    private JPanel pnlMain;
    private JTabbedPane tabAdminTab;
    private JTable tblMessage;
    private JButton btnDeleteMessage;

    public AdminPage() {
        tabAdminTab.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                tblMessage.setModel(AdminController.OutputAdminMessagesTable());
                tblMessage.setDefaultEditor(Object.class, null);
            }
        });
        btnDeleteMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblMessage.getSelectedRow() >= 0){
                    AdminController.DeleteMessage(tblMessage.getSelectedRow());
                    tblMessage.setModel(AdminController.OutputAdminMessagesTable());
                }
            }
        });
    }

    public JPanel getPnlMain() {
        return pnlMain;
    }
}
