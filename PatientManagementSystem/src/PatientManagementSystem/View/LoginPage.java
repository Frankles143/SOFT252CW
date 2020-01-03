package PatientManagementSystem.View;

import PatientManagementSystem.Controller.LoginController;
import PatientManagementSystem.Model.Gender;
import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.Password;
import PatientManagementSystem.Model.System.Serialization;
import PatientManagementSystem.Model.Users.Admin;
import PatientManagementSystem.Model.Users.Patient;
import PatientManagementSystem.Model.Users.UserData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

public class LoginPage {
    private static JFrame loginFrame = new JFrame("Login Page");
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
    private JPasswordField txtNewUserPassword;

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
                if (LoginController.UserLogin(txtUserId.getText(), String.valueOf(txtUserPassword.getPassword()))) {
                    int state = Logon.getState();
                    JFrame newFrame;
                    switch (state){
                        case 1:
                            //AdminPage
                            loginFrame.dispose();
                            AdminPage.DisposeAdminFrame();
                            newFrame = new JFrame("Admin page");
                            newFrame.setContentPane(new AdminPage().getPnlMain());
                            newFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            newFrame.pack();
                            newFrame.setVisible(true);
                            AdminPage.setAdminFrame(newFrame);
                            break;
                        case 2:
                            //DoctorPage
                            loginFrame.dispose();
                            DoctorPage.DisposeDoctorFrame();
                            newFrame = new JFrame("Doctor page");
                            newFrame.setContentPane(new DoctorPage().getPnlMain());
                            newFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            newFrame.pack();
                            newFrame.setVisible(true);
                            DoctorPage.setDoctorFrame(newFrame);
                            break;
                        case 3:
                            //PatientPage
                            loginFrame.dispose();
                            PatientPage.DisposePatientFrame();
                            newFrame = new JFrame("Patient page");
                            newFrame.setContentPane(new PatientPage().getPnlMain());
                            newFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            newFrame.pack();
                            newFrame.setVisible(true);
                            PatientPage.setPatientFrame(newFrame);
                            break;
                        case 4:
                            //Secretary page
                            loginFrame.dispose();
                            SecretaryPage.DisposeSecretaryFrame();
                            newFrame = new JFrame("Secretary page");
                            newFrame.setContentPane(new SecretaryPage().getPnlMain());
                            newFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            newFrame.pack();
                            newFrame.setVisible(true);
                            SecretaryPage.setSecretaryFrame(newFrame);
                            break;
                    }
                } else {
                    lblLoginResponse.setText("Please enter a valid username and password!");
                }
            }
        });
        btnCreateAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtUserName.getText().length() > 0 && txtUserAddress.getText().length() > 0) {
                    if (LoginController.CreateNewUser(txtUserName, txtUserAddress, cmbUserGender, spnUserAge, txtNewUserPassword)){
                        txtUserName.setText("");
                        txtUserAddress.setText("");
                        txtNewUserPassword.setText("");
                        spnUserAge.setValue(0);
                        lblResponse.setText("");
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
        Serialization.LoadAll();
        loginFrame.setContentPane(new LoginPage().pnlLogin);
        loginFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        loginFrame.pack();
        loginFrame.setVisible(true);
    }

    public static void setLoginFrame(JFrame loginFrame) {
        LoginPage.loginFrame = loginFrame;
    }

    public static void LoginFrameDispose(){
        if (loginFrame != null) {
            loginFrame.dispose();
        }
    }

    public JPanel getPnlLogin() {
        return pnlLogin;
    }
}
