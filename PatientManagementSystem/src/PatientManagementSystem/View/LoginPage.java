package PatientManagementSystem.View;

import PatientManagementSystem.Controller.LoginController;
import PatientManagementSystem.Model.Users.Admin;
import PatientManagementSystem.Model.Users.UserData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    private JPanel pnlLogin;
    private JButton btnExitProgram;
    private JButton btnCreateAccount;
    private JTabbedPane paneLoginDetails;
    private JPasswordField txtUserPassword;
    private JFormattedTextField txtUserId;
    private JButton btnLogin;

    public LoginPage() {
        btnExitProgram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Admin admin = new Admin("A1234", "Tupac", "East side", "gunz");
                UserData.AdminUsers.add(admin);
                LoginController.UserLogin(txtUserId.getText(), txtUserPassword.getText());
            }
        });
    }

    public static void main(String[] args) {
         JFrame frame = new JFrame("LoginPage");
         frame.setContentPane(new LoginPage().pnlLogin);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.pack();
         frame.setVisible(true);
    }
}
