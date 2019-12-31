package PatientManagementSystem.View;

import PatientManagementSystem.Controller.DoctorController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class DoctorPage {
    public JPanel pnlMain;
    private JTabbedPane tabDoctorTab;
    private JTable tblMessage;
    private JButton btnDeleteMessage;

    public DoctorPage() {
        tabDoctorTab.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                tblMessage.setModel(DoctorController.OutputDoctorMessagesTable());
                tblMessage.setDefaultEditor(Object.class, null);
            }
        });
        btnDeleteMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblMessage.getSelectedRow() >= 0) {
                    DoctorController.DeleteMessage(tblMessage.getSelectedRow());
                    tblMessage.setModel(DoctorController.OutputDoctorMessagesTable());
                }
            }
        });
    }

    public JPanel getPnlMain() {
        return pnlMain;
    }
}
