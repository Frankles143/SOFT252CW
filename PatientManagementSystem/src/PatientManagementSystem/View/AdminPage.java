package PatientManagementSystem.View;

import PatientManagementSystem.Controller.AdminController;
import PatientManagementSystem.Controller.ControllerUtils;
import PatientManagementSystem.Model.State.Logon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.Arrays;

public class AdminPage {
    private static JFrame adminFrame;
    private JPanel pnlMain;
    private JTabbedPane tabAdminTab;
    private JTable tblMessage;
    private JButton btnDeleteMessage;
    private JPasswordField txtPasswordOne;
    private JPasswordField txtPasswordTwo;
    private JLabel lblPasswordMustMatch;
    private JButton btnChangePassword;
    private JButton btnLogout;
    private JTabbedPane tabbedPane1;
    private JTable tblViewUsers;
    private JButton btnDeleteUser;
    private JComboBox cmbNewUserType;
    private JFormattedTextField txtNewUserName;
    private JFormattedTextField txtNewUserAddress;
    private JButton btnCreateNewUser;
    private JLabel lblNewUserWarning;
    private JPasswordField txtNewUserPassword;
    private JTable tblDoctorFeedback;
    private JButton btnConfirmFeedbackRow;
    private JButton btnSaveFeedbackRow;
    private JButton btnCancelChanges;
    private JLabel lblFeedbackOutput;

    public AdminPage() {
        tabAdminTab.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                tblMessage.setModel(AdminController.OutputAdminMessagesTable());
                tblMessage.setDefaultEditor(Object.class, null);
                tblViewUsers.setModel(AdminController.OutputUsers());
                tblViewUsers.setDefaultEditor(Object.class, null);
                tblDoctorFeedback.setModel(AdminController.OutputUncheckedFeedback());
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
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logon.Logout();
                LoginPage.LoginFrameDispose();
                adminFrame.dispose();
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
        btnDeleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tblViewUsers.getSelectedRow(), column = 2;
                if (row != -1) {
                    AdminController.DeleteUser(tblViewUsers.getModel().getValueAt(row, column).toString());
                    tblViewUsers.setModel(AdminController.OutputUsers());
                }
            }
        });
        btnCreateNewUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmbNewUserType.getSelectedIndex() >= 0 && !txtNewUserName.getText().equals("") && !txtNewUserAddress.getText().equals("") && !String.valueOf(txtNewUserPassword.getPassword()).equals("")) {
                    AdminController.CreateNewUser(cmbNewUserType.getSelectedIndex(), txtNewUserName.getText(), txtNewUserAddress.getText(), String.valueOf(txtNewUserPassword.getPassword()));
                } else {
                    lblNewUserWarning.setText("Make sure you enter a name, address and password, and choose a user type!");
                }
            }
        });
        btnSaveFeedbackRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tblDoctorFeedback.getSelectedRow(), column = 2;

                if (row >= 0) {
                    String newNotes = tblDoctorFeedback.getModel().getValueAt(row, column).toString();
                    if (!newNotes.equals("")){
                        AdminController.SaveFeedbackRow(row, newNotes);
                    } else {
                        lblFeedbackOutput.setText("Note cannot be empty");
                    }
                } else {
                    lblFeedbackOutput.setText("Select a row");
                }
                tblDoctorFeedback.setModel(AdminController.OutputUncheckedFeedback());
            }
        });
        btnConfirmFeedbackRow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = tblDoctorFeedback.getSelectedRow(), column = 2;
                if (row >= 0){
                    String notes = tblDoctorFeedback.getModel().getValueAt(row, column).toString();
                    if (!notes.equals("")) {
                        AdminController.AttachFeedback(row);
                        tblDoctorFeedback.setModel(AdminController.OutputUncheckedFeedback());
                    } else {
                        lblFeedbackOutput.setText("Notes cannot be empty");
                    }
                } else {
                    lblFeedbackOutput.setText("Select a row");
                }
            }
        });
        btnCancelChanges.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblDoctorFeedback.setModel(AdminController.OutputUncheckedFeedback());
            }
        });
    }

    public static void setAdminFrame(JFrame adminFrame) {
        AdminPage.adminFrame = adminFrame;
    }

    public static void DisposeAdminFrame(){
        if (adminFrame != null){
            adminFrame.dispose();
        }
    }

    public JPanel getPnlMain() {
        return pnlMain;
    }
}
