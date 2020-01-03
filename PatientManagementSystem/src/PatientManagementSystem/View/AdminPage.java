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
        btnDeleteMessage.addActionListener(e -> {
            if (tblMessage.getSelectedRow() >= 0){
                AdminController.DeleteMessage(tblMessage.getSelectedRow());
                tblMessage.setModel(AdminController.OutputAdminMessagesTable());
            }
        });
        btnLogout.addActionListener(e -> {
            Logon.Logout();
            LoginPage.LoginFrameDispose();
            adminFrame.dispose();
            JFrame frame = new JFrame("Login Page");
            frame.setContentPane(new LoginPage().getPnlLogin());
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
            LoginPage.setLoginFrame(frame);
        });
        btnChangePassword.addActionListener(e -> {
            if (!String.valueOf(txtPasswordOne.getPassword()).equals("") && !String.valueOf(txtPasswordTwo.getPassword()).equals("") && Arrays.equals(txtPasswordOne.getPassword(), txtPasswordTwo.getPassword())){
                ControllerUtils.PasswordChange(txtPasswordOne);
                txtPasswordOne.setText("");
                txtPasswordTwo.setText("");
                lblPasswordMustMatch.setText("");
            } else {
                lblPasswordMustMatch.setText("Passwords must match!");
            }
        });
        btnDeleteUser.addActionListener(e -> {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to delete this user?","Warning",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION){
                try {
                    int row = tblViewUsers.getSelectedRow(), column = 2;
                    if (row >= 0) {
                        AdminController.DeleteUser(tblViewUsers.getModel().getValueAt(row, column).toString());
                        tblViewUsers.setModel(AdminController.OutputUsers());
                    }
                } catch (Exception exe) {
                    System.out.println("Unable to delete user: " + e);
                }
            }

        });
        btnCreateNewUser.addActionListener(e -> {
            if (cmbNewUserType.getSelectedIndex() >= 0 && !txtNewUserName.getText().equals("") && !txtNewUserAddress.getText().equals("") && !String.valueOf(txtNewUserPassword.getPassword()).equals("")) {
                AdminController.CreateNewUser(cmbNewUserType.getSelectedIndex(), txtNewUserName.getText(), txtNewUserAddress.getText(), String.valueOf(txtNewUserPassword.getPassword()));
                txtNewUserName.setText("");
                txtNewUserAddress.setText("");
                txtNewUserPassword.setText("");
            } else {
                lblNewUserWarning.setText("Make sure you enter a name, address and password, and choose a user type!");
            }
        });
        btnSaveFeedbackRow.addActionListener(e -> {
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
        });
        btnConfirmFeedbackRow.addActionListener(e -> {
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
        });
        btnCancelChanges.addActionListener(e -> tblDoctorFeedback.setModel(AdminController.OutputUncheckedFeedback()));
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
