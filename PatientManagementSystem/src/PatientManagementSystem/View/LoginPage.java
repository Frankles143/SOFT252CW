package PatientManagementSystem.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    private JPanel pnlLogin;
    private JButton btnExitProgram;
    private JButton btnCreateAccount;
    private JTabbedPane paneLoginDetails;
    private JPasswordField passwordField1;
    private JFormattedTextField formattedTextField1;

    public LoginPage() {
        btnExitProgram.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
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
