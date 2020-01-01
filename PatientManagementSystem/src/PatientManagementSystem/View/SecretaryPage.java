package PatientManagementSystem.View;

import PatientManagementSystem.Controller.ControllerUtils;
import PatientManagementSystem.Controller.SecretaryController;
import PatientManagementSystem.Model.State.Logon;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class SecretaryPage {
    private static JFrame secretaryFrame;
    private JPanel pnlMain;
    private JTabbedPane tabSecretaryTab;
    private JTable tblMessage;
    private JButton btnDeleteMessage;
    private JPasswordField txtPasswordOne;
    private JPasswordField txtPasswordTwo;
    private JLabel lblPasswordMustMatch;
    private JButton btnLogout;
    private JButton btnChangePassword;

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
        btnChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!String.valueOf(txtPasswordOne.getPassword()).equals("") && !String.valueOf(txtPasswordTwo.getPassword()).equals("") && Arrays.equals(txtPasswordOne.getPassword(), txtPasswordTwo.getPassword())){
                    ControllerUtils.PasswordChange(txtPasswordOne);
                    txtPasswordOne.setText("");
                    txtPasswordTwo.setText("");
                    lblPasswordMustMatch.setText("");
                } else {
                    lblPasswordMustMatch.setText("Passwords must match!");
                }
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
