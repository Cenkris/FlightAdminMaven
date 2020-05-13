package Application.View;

import Application.Controller.AuditController;
import Application.Controller.UserController;
import Application.Model.Audit;
import Audit.UserAudit;
import Helper.AccountConstraints;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ChangePasswordPage extends JFrame {
    private final UserController userController = new UserController();

    //swing components
    private JPanel confirmPasswordPanel, passwordPanel;
    private JLabel confirmPasswordLabel, passwordLabel, messageLabel;
    private JPasswordField passwordTextField, confirmPasswordTextField;
    private JButton changePasswordButton;
    private final Dimension FIELD_DIMENSIONS = new Dimension(150, 30);
    private AccountPage accountPage;

    ChangePasswordPage() {
        initMessage();
        initPasswordPanel();
        initConfirmPasswordPanel();
        initChangePasswordButton();
        initDefaultValues();
    }

    private void initChangePasswordButton() {
        changePasswordButton = new JButton("Change password");
        changePasswordButton.addActionListener(event -> changePassword());
        add(changePasswordButton);
    }

    private void initMessage() {
        messageLabel = new JLabel("Please input your new password");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBorder(new EmptyBorder(1, 20, 1, 20));
        add(messageLabel);
    }

    private void changePassword() {
        String password = new String(passwordTextField.getPassword());
        String confirmPassword = new String(confirmPasswordTextField.getPassword());

        if (samePassword(password, confirmPassword) && password.matches(AccountConstraints.PASSWORD_VALIDATOR)) {
            JOptionPane.showMessageDialog(null, "Password successfully changed!");
            userController.updatePassword(UserAudit.getLoggedUser(), password);
            dispose();
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(accountPage);
            topFrame.dispose();
            AuditController.saveEvent(UserAudit.getLoggedUser(), Audit.PASSWORD_CHANGED);
            AuditController.saveEvent(UserAudit.getLoggedUser(), Audit.LOGOUT);
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
        } else if (password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please add input in both fields");
            resetFields();
        } else if (!samePassword(password, confirmPassword)) {
            JOptionPane.showMessageDialog(null, "Password and confirm password field must be identical");
            resetFields();
        } else {
            JOptionPane.showMessageDialog(null, "For security reasons password must be: \n" +
                    "\t - minimum 6 characters; \n" +
                    "\t - at least a capital letter; \n" +
                    "\t - at least a non-capital letter \n" +
                    "\t - at least one digit;");
            resetFields();
        }

    }

    private boolean samePassword(String password, String confirmedPassword) {
        return password.equals(confirmedPassword);
    }

    private void resetFields() {
        passwordTextField.requestFocus();
        confirmPasswordTextField.setText("");
        passwordTextField.setText("");
    }

    private void initConfirmPasswordPanel() {
        //panel
        confirmPasswordPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //label
        confirmPasswordLabel = new JLabel("confirm password: ");

        //field
        confirmPasswordTextField = new JPasswordField();
        confirmPasswordTextField.setPreferredSize(FIELD_DIMENSIONS);
        confirmPasswordTextField.addActionListener(event -> changePassword());

        //add components
        confirmPasswordPanel.add(confirmPasswordLabel);
        confirmPasswordPanel.add(confirmPasswordTextField);
        add(confirmPasswordPanel);
    }

    private void initPasswordPanel() {
        //panel
        passwordPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //label
        passwordLabel = new JLabel("password: ");

        //field
        passwordTextField = new JPasswordField();
        passwordTextField.setPreferredSize(FIELD_DIMENSIONS);
        passwordTextField.addActionListener(event -> changePassword());

        //add components
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTextField);
        add(passwordPanel);
    }

    private void initDefaultValues() {
        setLayout(new GridLayout(4, 1));
        setTitle("Password Change");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public void parent(AccountPage accountPage) {
        this.accountPage = accountPage;
    }
}
