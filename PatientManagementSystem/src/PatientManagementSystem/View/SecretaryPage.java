package PatientManagementSystem.View;

import PatientManagementSystem.Controller.SecretaryController;
import PatientManagementSystem.Model.State.Logon;

import javax.swing.*;
import java.awt.event.*;

public class SecretaryPage {
    private static JFrame secretaryFrame;
    private JPanel pnlMain;
    private JTabbedPane tabSecretaryTab;
    private JTable tblMessage;
    private JButton btnDeleteMessage;
    private JButton btnChangePassword;
    private JButton btnLogout;

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
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logon.Logout();
                LoginPage.LoginFrameDispose();
                secretaryFrame.dispose();
                JFrame frame = new JFrame("Login Page");
                frame.setContentPane(new LoginPage().getPnlLogin());
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
                LoginPage.setLoginFrame(frame);
            }
        });
    }

    public static void setSecretaryFrame(JFrame secretaryFrame) {
        SecretaryPage.secretaryFrame = secretaryFrame;
    }

    public static void DisposeSecretaryFrame(){
        if (secretaryFrame != null){
            secretaryFrame.dispose();
        }
    }

    public JPanel getPnlMain() {
        return pnlMain;
    }
}
