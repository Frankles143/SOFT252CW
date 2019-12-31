package PatientManagementSystem.View;

import PatientManagementSystem.Controller.PatientController;
import com.github.lgooddatepicker.components.DateTimePicker;

import javax.swing.*;
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
        tabApptViewBook.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

            }
        });
        btnSubmitAppointment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDateTime date1 = pickerDateOne.getDateTimePermissive();
                LocalDateTime date2 = pickerDateTwo.getDateTimePermissive();
                LocalDateTime date3 = pickerDateThree.getDateTimePermissive();

                if (date1 != null && date2 != null && date3 != null){
                    ArrayList<LocalDateTime> dates = new ArrayList<>();
                    dates.add(date1);
                    dates.add(date2);
                    dates.add(date3);

                    int doctor = cmbDoctors.getSelectedIndex();
                    PatientController.CreatingAppointment(dates, doctor);
                } else {
                    lblApptOutput.setText("Please pick three dates and times!");
                }
            }
        });
    }

    public JPanel getPnlMain() {
        return pnlMain;
    }
}
