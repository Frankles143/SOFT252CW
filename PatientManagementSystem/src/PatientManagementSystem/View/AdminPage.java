package PatientManagementSystem.View;

import PatientManagementSystem.Controller.AdminController;
import PatientManagementSystem.Model.State.Logon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AdminPage {
    private static JFrame adminFrame;
    private JPanel pnlMain;
    private JTabbedPane tabAdminTab;
    private JTable tblMessage;
    private JButton btnDeleteMessage;
    private JButton btnChangePassword;
    private JButton btnLogout;

    public AdminPage() {
        tabAdminTab.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                super.focusGained(e);

                tblMessage.setModel(AdminController.OutputAdminMessagesTable());
                tblMessage.setDefaultEditor(Object.class, null);
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
