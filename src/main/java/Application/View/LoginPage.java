package Application.View;

import Application.Controller.UserController;
import Application.Model.User;
import Audit.UserAudit;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginPage extends JFrame {

    private final UserController userController = new UserController();

    //swing components
    private JPanel usernameOrEmailPanel, passwordPanel, loginPanel;
    private JLabel usernameOrEmailLabel, passwordLabel, messageLabel;
    private JTextField usernameOrEmailTextField;
    private JPasswordField passwordTextField;
    private JButton loginButton, registerButton;
    private final Dimension FIELD_DIMENSIONS = new Dimension(150, 30);
    private Dimension BUTTON_DIMENSION = new Dimension(90, 20);

    LoginPage() {
        initMessage();
        initUsernamePanel();
        initPasswordPanel();
        initLoginPanel();
        initDefaultValues();
    }

    private void initMessage() {
        messageLabel = new JLabel("Please input login credentials or register");
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setBorder(new EmptyBorder(1, 20, 1, 20));
        add(messageLabel);
    }

    private void initLoginPanel() {
        //panel
        loginPanel = new JPanel(new FlowLayout());

        //login
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(BUTTON_DIMENSION);
        loginButton.addActionListener(event -> login());


        //register
        registerButton = new JButton("Register");
        registerButton.setPreferredSize(BUTTON_DIMENSION);
        registerButton.addActionListener(event -> {
            RegisterPage registerPage = new RegisterPage();
            registerPage.setVisible(true);
            dispose();
        });

        //add components
        loginPanel.add(loginButton);
        loginPanel.add(registerButton);
        add(loginPanel);
    }

    private void login() {
        User user = emailOrUsernameInserted();
        String inputPassword = new String(passwordTextField.getPassword());
        String userPassword = user.getPassword();

        if (!user.isEmpty()) {
            if (userPassword.equals(inputPassword)) {
                UserAudit.loggedUser = user;
                DashBoardPage dashBoardPage = new DashBoardPage();
                dashBoardPage.setVisible(true);
                dispose();
            } else {
                JOptionPane.showInternalMessageDialog(null, "Invalid username or password");
                resetFields();
            }
        }
    }

    private User emailOrUsernameInserted() {
        String inputCredentials = usernameOrEmailTextField.getText();
        User user = new User();

        boolean inputIsEmail = false;
        if (inputCredentials.contains("@")) {
            inputIsEmail = true;
        }

        if (usernameOrEmailTextField.getText().isEmpty() || new String(passwordTextField.getPassword()).isEmpty()) {
            JOptionPane.showInternalMessageDialog(null, "Please input both username and password");
            resetFields();
        } else {
            if (inputIsEmail) {
                if (!userController.isNewEmail(inputCredentials)) {
                    user = userController.getUserByEmail(inputCredentials);
                } else {
                    accountNotFound();
                }
            } else {
                if (!userController.isNewUser(inputCredentials)) {
                    user = userController.getUserByUsername(inputCredentials);
                } else {
                    accountNotFound();
                }
            }
        }
        return user;
    }

    private void accountNotFound() {
        JOptionPane.showInternalMessageDialog(null, "Account not found in DB");
        resetFields();
    }

    private void resetFields() {
        usernameOrEmailTextField.requestFocus();
        usernameOrEmailTextField.setText("");
        passwordTextField.setText("");
    }

    private void initUsernamePanel() {
        //panel
        usernameOrEmailPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //label
        usernameOrEmailLabel = new JLabel("username/email: ");

        //field
        usernameOrEmailTextField = new JTextField();
        usernameOrEmailTextField.setPreferredSize(FIELD_DIMENSIONS);
        usernameOrEmailTextField.addActionListener(event -> login());

        //add components
        usernameOrEmailPanel.add(usernameOrEmailLabel);
        usernameOrEmailPanel.add(usernameOrEmailTextField);
        add(usernameOrEmailPanel);
    }

    private void initPasswordPanel() {
        //panel
        passwordPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //label
        passwordLabel = new JLabel("password: ");

        //field
        passwordTextField = new JPasswordField();
        passwordTextField.setPreferredSize(FIELD_DIMENSIONS);
        passwordTextField.addActionListener(event -> login());

        //add components
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTextField);
        add(passwordPanel);
    }

    private void initDefaultValues() {
        setLayout(new GridLayout(4, 1));
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }

}
