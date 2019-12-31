package PatientManagementSystem.View;

import PatientManagementSystem.Controller.ControllerUtils;
import PatientManagementSystem.Controller.PatientController;
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

    public PatientPage() {
        tabPatientTabs.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                tblHistory.setModel(PatientController.OutputPatientHistory());
                tblMessages.setModel(PatientController.OutputPatientMessagesTable());
                tblMessages.setDefaultEditor(Object.class, null);
                cmbDoctors.setModel(ControllerUtils.CreateDoctorComboboxModel());
                tblAppointments.setModel(PatientController.OutputPatientAppointments());
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
    }

    public JPanel getPnlMain() {
        return pnlMain;
    }
}
