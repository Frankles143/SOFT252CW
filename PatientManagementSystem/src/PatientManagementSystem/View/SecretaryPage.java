package PatientManagementSystem.View;

import PatientManagementSystem.Controller.ControllerUtils;
import PatientManagementSystem.Controller.SecretaryController;
import PatientManagementSystem.Model.State.Logon;
import PatientManagementSystem.Model.System.SystemData;
import PatientManagementSystem.Model.Users.UserData;
import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.Objects;

public class SecretaryPage {
    private static JFrame secretaryFrame;
    private JPanel pnlMain;
    private JTabbedPane tabSecretaryTab;
    private JTable tblMessage;
    private JButton btnDeleteMessage;
    private JPasswordField txtPasswordOne;
    private JPasswordField txtPasswordTwo;
    private JLabel lblPasswordMustMatch;
    private JButton btnLogout;
    private JButton btnChangePassword;
    private JTabbedPane tabAppointments;
    private JTabbedPane tabAccounts;
    private JTable tblAccountRequests;
    private JButton btnApproveNewAccount;
    private JButton btnDenyNewAccount;
    private JTable tblAccountTerminations;
    private JButton btnApproveTermination;
    private JButton btnDenyTermination;
    private JTabbedPane tabbedPane1;
    private JTable tblPrescriptions;
    private JButton btnGivePrescription;
    private JTable tblMedicine;
    private JButton btnOrderMedicine;
    private JSpinner spnMedicineQuantity;
    private JLabel lblMedicineOutput;
    private JTable tblAppointmentRequests;
    private JButton btnApproveAppointment;
    private JButton btnDenyAppointment;
    private JComboBox cmbDoctors;
    private JComboBox cmbPatients;
    private JButton btnCreateAppointment;
    private DateTimePicker pickAppointmentDateTimePicker;
    private JLabel lblCreateAppointmentFeedback;
    private JComboBox cmbApproveAppointmentDatepicker;
    private JLabel lblAppointmentApproval;
    private JLabel lblNewAccountOutput;
    private JLabel lblAccountTermination;
    private JComboBox cmbPrescriptionsPatients;
    private JLabel lblPrescriptionOutput;

    public SecretaryPage() {
        tabSecretaryTab.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                tblMessage.setModel(SecretaryController.OutputSecretaryMessagesTable());
                tblMessage.setDefaultEditor(Object.class, null);
                tblAppointmentRequests.setModel(SecretaryController.OutputAppointmentRequests());
                tblAppointmentRequests.setDefaultEditor(Object.class, null);
                tblAccountRequests.setModel(SecretaryController.OutputAccountRequests());
                tblAccountRequests.setDefaultEditor(Object.class, null);
                tblAccountTerminations.setModel(SecretaryController.OutputAccountTerminationRequests());
                tblAccountTerminations.setDefaultEditor(Object.class, null);
                tblMedicine.setModel(ControllerUtils.OutputAllMedicine());
                tblMedicine.setDefaultEditor(Object.class, null);
                cmbPatients.setModel(ControllerUtils.CreatePatientComboBoxModel());
                cmbPrescriptionsPatients.setModel(ControllerUtils.CreatePatientComboBoxModel());
                cmbDoctors.setModel(ControllerUtils.CreateDoctorComboBoxModel());
            }
        });
        btnDeleteMessage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblMessage.getSelectedRow() >= 0) {
                    SecretaryController.DeleteMessage(tblMessage.getSelectedRow());
                    tblMessage.setModel(SecretaryController.OutputSecretaryMessagesTable());
                }
            }
        });
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Logon.Logout();
                LoginPage.LoginFrameDispose();
                secretaryFrame.dispose();
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
        btnCreateAppointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmbDoctors.getSelectedIndex() >= 0 && cmbPatients.getSelectedIndex() >= 0 && pickAppointmentDateTimePicker.getDateTimeStrict() != null) {
                    SecretaryController.CreateAppointment(cmbDoctors.getSelectedIndex(), cmbPatients.getSelectedIndex(), pickAppointmentDateTimePicker.getDateTimeStrict());
                    lblCreateAppointmentFeedback.setText("");
                } else {
                    lblCreateAppointmentFeedback.setText("Make sure to select patient, doctor, date and time");
                }
            }
        });
        btnApproveAppointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmbApproveAppointmentDatepicker.getSelectedIndex() >= 0 && tblAppointmentRequests.getSelectedRow() >= 0) {
                    SecretaryController.ApproveAppointment(tblAppointmentRequests.getSelectedRow(), cmbApproveAppointmentDatepicker.getSelectedIndex());
                    lblAppointmentApproval.setText("");
                    tblAppointmentRequests.setModel(SecretaryController.OutputAppointmentRequests());
                } else {
                    lblAppointmentApproval.setText("Please pick an appointment and a date");
                }
            }
        });
        btnDenyAppointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblAppointmentRequests.getSelectedRow() >= 0) {
                    SecretaryController.DenyAppointment(tblAppointmentRequests.getSelectedRow());
                    lblAppointmentApproval.setText("");
                    tblAppointmentRequests.setModel(SecretaryController.OutputAppointmentRequests());
                } else {
                    lblAppointmentApproval.setText("Please pick an appointment");
                }
            }
        });
        btnApproveNewAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblAccountRequests.getSelectedRow() >= 0) {
                    SecretaryController.ApproveNewAccount(tblAccountRequests.getSelectedRow());
                    lblNewAccountOutput.setText("");
                    tblAccountRequests.setModel(SecretaryController.OutputAccountRequests());
                } else {
                    lblNewAccountOutput.setText("Please select an account");
                }
            }
        });
        btnDenyNewAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblAccountRequests.getSelectedRow() >= 0) {
                    SecretaryController.DenyNewAccount(tblAccountRequests.getSelectedRow());
                    lblNewAccountOutput.setText("");
                    tblAccountRequests.setModel(SecretaryController.OutputAccountRequests());
                } else {
                    lblNewAccountOutput.setText("Please select an account");
                }
            }
        });
        btnApproveTermination.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblAccountTerminations.getSelectedRow() >= 0) {
                    SecretaryController.ApproveAccountTermination(tblAccountTerminations.getSelectedRow());
                    lblAccountTermination.setText("");
                    tblAccountTerminations.setModel(SecretaryController.OutputAccountTerminationRequests());
                } else {
                    lblAccountTermination.setText("Please select an account");
                }
            }
        });
        btnDenyTermination.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblAccountTerminations.getSelectedRow() >= 0) {
                    SecretaryController.DenyAccountTermination(tblAccountTerminations.getSelectedRow());
                    lblAccountTermination.setText("");
                    tblAccountTerminations.setModel(SecretaryController.OutputAccountTerminationRequests());
                } else {
                    lblAccountTermination.setText("Please select an account");
                }
            }
        });
        btnGivePrescription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmbPrescriptionsPatients.getSelectedIndex() >= 0 && tblPrescriptions.getSelectedRow() >= 0) {
                    int currentStock = Objects.requireNonNull(ControllerUtils.FindMedicine(tblPrescriptions.getValueAt(tblPrescriptions.getSelectedRow(), 2).toString())).getStock();
                    int stockWanted = (int) tblPrescriptions.getValueAt(tblPrescriptions.getSelectedRow(), 3);
                    if (currentStock >= stockWanted){
                        SecretaryController.GivePatientPrescription(cmbPrescriptionsPatients.getSelectedIndex(), tblPrescriptions.getSelectedRow());
                        tblPrescriptions.setModel(SecretaryController.OutputPrescriptions(cmbPrescriptionsPatients.getSelectedIndex()));
                        lblPrescriptionOutput.setText("");
                    } else {
                        lblPrescriptionOutput.setText("Not enough medicine in stock");
                    }
                } else {
                    lblPrescriptionOutput.setText("Choose a patient and prescription!");
                }
            }
        });
        btnOrderMedicine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblMedicine.getSelectedRow() >= 0 && (int) spnMedicineQuantity.getValue() > 0) {
                    SecretaryController.OrderMedicine(tblMedicine.getSelectedRow(), (int) spnMedicineQuantity.getValue());
                    tblMedicine.setModel(ControllerUtils.OutputAllMedicine());
                    lblMedicineOutput.setText("");
                } else {
                    lblMedicineOutput.setText("Select a medicine and enter a value greater than 0");
                }
            }
        });
        cmbPrescriptionsPatients.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblPrescriptions.setModel(SecretaryController.OutputPrescriptions(cmbPrescriptionsPatients.getSelectedIndex()));
                tblPrescriptions.setDefaultEditor(Object.class, null);
            }
        });
    }

    public static void setSecretaryFrame(JFrame secretaryFrame) {
        SecretaryPage.secretaryFrame = secretaryFrame;
    }

    public static void DisposeSecretaryFrame(){
        if (secretaryFrame != null){
            secretaryFrame.dispose();
        }
    }

    public JPanel getPnlMain() {
        return pnlMain;
    }
}
