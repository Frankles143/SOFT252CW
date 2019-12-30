package PatientManagementSystem.View;

import PatientManagementSystem.Controller.LoginController;
import PatientManagementSystem.Model.Gender;
import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.Serialization;
import PatientManagementSystem.Model.Users.Admin;
import PatientManagementSystem.Model.Users.Patient;
import PatientManagementSystem.Model.Users.UserData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage {
    private static JFrame frame = new JFrame("Login Page");
    private JPanel pnlLogin;
    private JButton btnExitProgram;
    private JButton btnCreateAccount;
    private JTabbedPane paneLoginDetails;
    private JPasswordField txtUserPassword;
    private JFormattedTextField txtUserId;
    private JButton btnLogin;
    private JPanel tabLogin;
    private JPanel tabCreateAccount;
    private JFormattedTextField txtUserAddress;
    private JFormattedTextField txtUserName;
    private JSpinner spnUserAge;
    private JComboBox cmbUserGender;
    private JLabel lblResponse;
    private JLabel lblLoginResponse;

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
                if (LoginController.UserLogin(txtUserId.getText(), txtUserPassword.getText())) {
                    int state = Logon.getState();
                    JFrame newFrame;
                    switch (state){
                        case 1:
                            //AdminPage
                            break;
                        case 2:
                            //DoctorPage
                            break;
                        case 3:
                            //PatientPage
                            frame.dispose();
                            newFrame = new JFrame("Patient page");
                            newFrame.setContentPane(new PatientPage().pnlMain);
                            newFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            newFrame.pack();
                            newFrame.setVisible(true);
                            break;
                        case 4:
                            //Secretary page
                            break;
                    }
                } else {
                    lblLoginResponse.setText("Unable to log in!");
                }
            }
        });
        btnCreateAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtUserName.getText().length() > 0 && txtUserAddress.getText().length() > 0) {
                    if (LoginController.CreateNewUser(txtUserName, txtUserAddress, cmbUserGender, spnUserAge)){
                        lblResponse.setText("Account request successful!");
                    } else {
                        lblResponse.setText("Unable to create new account request");
                    }
                } else {
                    lblResponse.setText("New patients need a name, address, age and gender to continue");
                }
            }
        });
    }

    public static void main(String[] args) {
        Serialization.LoadUserData();
        Serialization.LoadSystemData();
        frame.setContentPane(new LoginPage().pnlLogin);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
