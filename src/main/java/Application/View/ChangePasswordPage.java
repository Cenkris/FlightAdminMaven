package Application.View;

import Application.Controller.UserController;
import Application.Model.User;
import Audit.UserAudit;

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
    private Dimension BUTTON_DIMENSION = new Dimension(90, 20);

    ChangePasswordPage() {
        initMessage();
        initPasswordPanel();
        initConfirmPasswordPanel();
        initChangePasswordButton();
        initDefaultValues();
    }

    private void initChangePasswordButton() {
        changePasswordButton = new JButton("Change password");
        add(changePasswordButton);
    }

    private void initMessage() {
        messageLabel = new JLabel("Please input your new password");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBorder(new EmptyBorder(1, 20, 1, 20));
        add(messageLabel);
    }

    private void changePassword() {

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

}
