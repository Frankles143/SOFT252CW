package PatientManagementSystem.View;

import PatientManagementSystem.Controller.ControllerUtils;
import PatientManagementSystem.Controller.PatientController;
import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.Users.UserData;
import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;

public class PatientPage {
    private static JFrame patientFrame;
    private JTabbedPane tabPatientTabs;
    private JPanel pnlMain;
    private JTable tblMessages;
    private JTable tblHistory;
    private JButton btnDeleteMessage;
    private JTabbedPane tabApptViewBook;
    private JTable tblAppointments;
    private JComboBox cmbDoctors;
    private JButton btnSubmitAppointment;
    private DateTimePicker pickerDateOne;
    private DateTimePicker pickerDateTwo;
    private DateTimePicker pickerDateThree;
    private JLabel lblApptOutput;
    private JTabbedPane tabRateViewDoctor;
    private JTable tblDoctorRatings;
    private JComboBox cmbChooseDoctor;
    private JComboBox cmbDoctorFeedback;
    private JComboBox cmbDoctorRating;
    private JFormattedTextField txtUserFeedbackNotes;
    private JButton btnSubmitFeedback;
    private JTable tblPrescriptions;
    private JButton btnChangePassword;
    private JButton btnAccountTermination;
    private JButton btnLogout;
    private JPasswordField txtPasswordOne;
    private JPasswordField txtPasswordTwo;
    private JLabel lblPasswordMustMatch;

    public PatientPage() {
        tabPatientTabs.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                DefaultComboBoxModel comboModel = ControllerUtils.CreateDoctorComboBoxModel();
                tblHistory.setModel(PatientController.OutputPatientHistory());
                tblMessages.setModel(PatientController.OutputPatientMessagesTable());
                tblMessages.setAutoResizeMode( JTable.AUTO_RESIZE_ALL_COLUMNS );
                tblMessages.setDefaultEditor(Object.class, null);
                tblAppointments.setModel(PatientController.OutputPatientAppointments());
                tblPrescriptions.setModel(PatientController.OutputPatientPrescriptions());
                cmbDoctors.setModel(comboModel);
                cmbChooseDoctor.setModel(comboModel);
                cmbDoctorFeedback.setModel(comboModel);
            }
        });
        btnDeleteMessage.addActionListener(e -> {
            if (tblMessages.getSelectedRow() >= 0) {
                PatientController.DeleteMessage(tblMessages.getSelectedRow());
                tblMessages.setModel(PatientController.OutputPatientMessagesTable());
            }
        });
        btnSubmitAppointment.addActionListener(e -> PatientController.AppointmentCreationChecks(pickerDateOne, pickerDateTwo, pickerDateThree, cmbDoctors, lblApptOutput));
        cmbChooseDoctor.addActionListener (e -> tblDoctorRatings.setModel(ControllerUtils.OutputDoctorRatings(UserData.DoctorUsers.get(cmbChooseDoctor.getSelectedIndex()))));
        btnSubmitFeedback.addActionListener(e -> {
            if (cmbDoctorFeedback.getSelectedIndex() >= 0 && cmbDoctorRating.getSelectedIndex() >= 0 && txtUserFeedbackNotes.getText() != null){
                PatientController.SubmitDoctorFeedback(cmbDoctorFeedback, cmbDoctorRating, txtUserFeedbackNotes);
            }
        });
        btnChangePassword.addActionListener(e -> {
            if (!String.valueOf(txtPasswordOne.getPassword()).equals("") && !String.valueOf(txtPasswordTwo.getPassword()).equals("") && Arrays.equals(txtPasswordOne.getPassword(), txtPasswordTwo.getPassword())) {
                ControllerUtils.PasswordChange(txtPasswordOne);
                txtPasswordOne.setText("");
                txtPasswordTwo.setText("");
                lblPasswordMustMatch.setText("");
            } else {
                lblPasswordMustMatch.setText("Passwords must match!");
            }
        });
        btnAccountTermination.addActionListener(e -> {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you want to request an account termination?","Warning",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION){
                try {
                    Logon.getCurrentPatient().RequestAccountTermination();
                } catch (Exception exe) {
                    System.out.println("Unable to request account termination: " + e);
                }
            }
        });
        btnLogout.addActionListener(e -> {
            Logon.Logout();
            LoginPage.LoginFrameDispose();
            patientFrame.dispose();
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
    }

    public static void setPatientFrame(JFrame frame){
        patientFrame = frame;
    }

    public static void DisposePatientFrame(){
        if (patientFrame != null){
            patientFrame.dispose();
        }
    }

    public JPanel getPnlMain() {
        return pnlMain;
    }
}
