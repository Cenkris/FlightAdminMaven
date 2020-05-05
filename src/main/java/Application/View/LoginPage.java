package Application.View;

import Application.Controller.UserController;
import Application.Model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginPage extends JFrame {
    private final UserController userController = new UserController();

    //swing components
    private JPanel usernamePanel, passwordPanel, loginPanel;
    private JLabel usernameLabel, passwordLabel, messageLabel;
    private JTextField usernameTextField;
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
        String inputUsername = usernameTextField.getText();
        String inputPassword = new String(passwordTextField.getPassword());

        if (!userController.isNewUser(inputUsername)) {
            User user = userController.getUserByUsername(inputUsername);
            String userPassword = user.getPassword();
            if (userPassword.equals(inputPassword)) {
                JOptionPane.showMessageDialog(null, "Connected!");
                resetFields();
            } else {
                JOptionPane.showInternalMessageDialog(null, "Invalid username or password");
                resetFields();
            }
        } else {
            JOptionPane.showInternalMessageDialog(null, "Please input both username and password");
            resetFields();
        }
    }

    private void resetFields() {
        usernameTextField.requestFocus();
        usernameTextField.setText("");
        passwordTextField.setText("");
    }

    private void initUsernamePanel() {
        //panel
        usernamePanel = new JPanel(new FlowLayout());

        //label
        usernameLabel = new JLabel("username: ");

        //field
        usernameTextField = new JTextField();
        usernameTextField.setPreferredSize(FIELD_DIMENSIONS);
        usernameTextField.addActionListener(event -> login());

        //add components
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);
        add(usernamePanel);
    }

    private void initPasswordPanel() {
        //panel
        passwordPanel = new JPanel(new FlowLayout());

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
