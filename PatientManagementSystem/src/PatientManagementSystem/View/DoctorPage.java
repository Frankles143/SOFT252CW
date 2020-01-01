package PatientManagementSystem.View;

import PatientManagementSystem.Controller.ControllerUtils;
import PatientManagementSystem.Controller.DoctorController;
import PatientManagementSystem.Model.State.Logon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;

public class DoctorPage {
    private static JFrame doctorFrame;
    public JPanel pnlMain;
    private JTabbedPane tabDoctorTab;
    private JTable tblMessage;
    private JButton btnDeleteMessage;
    private JButton btnAccountTermination;
    private JPasswordField txtPasswordOne;
    private JPasswordField txtPasswordTwo;
    private JLabel lblPasswordMustMatch;
    private JButton btnLogout;
    private JButton btnChangePassword;

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
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logon.Logout();
                LoginPage.LoginFrameDispose();
                doctorFrame.dispose();
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

    public static void setDoctorFrame(JFrame doctorFrame) {
        DoctorPage.doctorFrame = doctorFrame;
    }

    public static void DisposeDoctorFrame(){
        if (doctorFrame != null){
            doctorFrame.dispose();
        }
    }

    public JPanel getPnlMain() {
        return pnlMain;
    }
}
