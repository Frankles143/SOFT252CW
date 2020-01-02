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
    private JPasswordField txtPasswordOne;
    private JPasswordField txtPasswordTwo;
    private JLabel lblPasswordMustMatch;
    private JButton btnLogout;
    private JButton btnChangePassword;
    private JTabbedPane tabManageMedicine;
    private JTable tblAppointments;
    private JTable tblFeedback;
    private JTable tblPatientHistory;
    private JComboBox cmbPatientSelect;
    private JTable tblViewMedicines;
    private JTextArea txtConsultationNotes;
    private JComboBox cmbPickPatientConsultation;
    private JButton btnConsultationSubmit;
    private JComboBox cmbPrescriptionPatient;
    private JFormattedTextField txtPrescriptionNotes;
    private JComboBox cmbPrescriptionMedicine;
    private JFormattedTextField txtPrescriptionDosage;
    private JSpinner spnPrescriptionQuantity;
    private JFormattedTextField txtNewMedicine;
    private JButton btnSubmitNewMedicine;
    private JButton btnSubmitNewPrescription;
    private JLabel lblPrescribeFeedback;
    private JButton btnRequestOrderMedicine;
    private JLabel lblMedicineOrder;

    public DoctorPage() {
        tabDoctorTab.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);
                DefaultComboBoxModel comboModel = ControllerUtils.CreatePatientComboBoxModel();
                tblMessage.setModel(DoctorController.OutputDoctorMessagesTable());
                tblMessage.setDefaultEditor(Object.class, null);
                tblAppointments.setModel(DoctorController.OutputDoctorAppointments());
                tblAppointments.setDefaultEditor(Object.class, null);
                tblFeedback.setModel(DoctorController.OutputDoctorFeedback());
                tblFeedback.setDefaultEditor(Object.class, null);
                tblViewMedicines.setModel(ControllerUtils.OutputAllMedicine());
                tblFeedback.setDefaultEditor(Object.class, null);
                cmbPickPatientConsultation.setModel(comboModel);
                cmbPatientSelect.setModel(comboModel);
                cmbPrescriptionPatient.setModel(comboModel);
                cmbPrescriptionMedicine.setModel(ControllerUtils.OutputMedicineComboBoxModel());

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
        btnConsultationSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cmbPickPatientConsultation.getSelectedIndex() >= 0 && !txtConsultationNotes.getText().equals("")) {
                    DoctorController.CreateConsultationNotes(cmbPickPatientConsultation.getSelectedIndex(), txtConsultationNotes.getText());
                    txtConsultationNotes.setText("");
                }
            }
        });
        cmbPatientSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblPatientHistory.setModel(DoctorController.OutputPatientHistory(cmbPatientSelect.getSelectedIndex()));
            }
        });
        btnSubmitNewPrescription.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int qty = (int) spnPrescriptionQuantity.getValue();
                if (cmbPrescriptionPatient.getSelectedIndex() >= 0 && cmbPrescriptionMedicine.getSelectedIndex() >= 0 && !txtPrescriptionNotes.getText().equals("") && !txtPrescriptionDosage.getText().equals("") && qty != 0){
                    DoctorController.CreateNewPrescription(cmbPrescriptionPatient.getSelectedIndex(), txtPrescriptionNotes.getText(), cmbPrescriptionMedicine.getSelectedIndex(), qty, txtPrescriptionDosage.getText());
                    txtPrescriptionDosage.setText("");
                    txtPrescriptionNotes.setText("");
                    spnPrescriptionQuantity.setValue(0);
                } else {
                    lblPrescribeFeedback.setText("Please provide a value in every box");
                }
            }
        });
        btnRequestOrderMedicine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tblViewMedicines.getSelectedRow() >= 0) {
                    DoctorController.RequestOrderMedicine(tblViewMedicines.getSelectedRow());
                    lblMedicineOrder.setText("");
                } else {
                    lblMedicineOrder.setText("Please select a medicine!");
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
