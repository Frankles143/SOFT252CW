package PatientManagementSystem.View;

import PatientManagementSystem.Controller.ControllerUtils;
import PatientManagementSystem.Controller.PatientController;
import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.Users.Doctor;
import PatientManagementSystem.Model.Users.UserData;
import com.github.lgooddatepicker.components.DateTimePicker;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.ArrayList;


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

    public PatientPage() {
        tabPatientTabs.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                DefaultComboBoxModel comboModel = ControllerUtils.CreateDoctorComboboxModel();
                tblHistory.setModel(PatientController.OutputPatientHistory());
                tblMessages.setModel(PatientController.OutputPatientMessagesTable());
                tblMessages.setDefaultEditor(Object.class, null);
                tblAppointments.setModel(PatientController.OutputPatientAppointments());
                cmbDoctors.setModel(comboModel);
                cmbChooseDoctor.setModel(comboModel);
                cmbDoctorFeedback.setModel(comboModel);
            }
        });
        btnDeleteMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblMessages.getSelectedRow() >= 0) {
                    PatientController.DeleteMessage(tblMessages.getSelectedRow());
                    tblMessages.setModel(PatientController.OutputPatientMessagesTable());
                }
            }
        });
        btnSubmitAppointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PatientController.AppointmentCreationChecks(pickerDateOne, pickerDateTwo, pickerDateThree, cmbDoctors, lblApptOutput);
            }
        });
        cmbChooseDoctor.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                tblDoctorRatings.setModel(ControllerUtils.OutputDoctorRatings(UserData.DoctorUsers.get(cmbChooseDoctor.getSelectedIndex())));
            }
        });
        btnSubmitFeedback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmbDoctorFeedback.getSelectedIndex() >= 0 && cmbDoctorRating.getSelectedIndex() >= 0 && txtUserFeedbackNotes.getText() != null){
                    PatientController.SubmitDoctorFeedback(cmbDoctorFeedback, cmbDoctorRating, txtUserFeedbackNotes);
                }
            }
        });
        btnChangePassword.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnAccountTermination.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logon.Logout();
                patientFrame.setVisible(false);
                JFrame frame = new JFrame("Login Page");
                frame.setContentPane(new LoginPage().getPnlLogin());
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

    public static void setPatientFrame(JFrame frame){
        patientFrame = frame;
    }

    public JPanel getPnlMain() {
        return pnlMain;
    }
}
