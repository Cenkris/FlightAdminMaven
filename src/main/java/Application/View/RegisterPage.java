package Application.View;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class RegisterPage extends JFrame {
    private JPanel usernamePanel, passwordPanel, confirmPasswordPanel, emailPanel, registerButtonPanel;
    private JLabel usernameLabel, passwordLabel, confirmaPasswordLabel, emailLabel, helpMessage;
    private JTextField usernameTextField, emailTextField;
    private JPasswordField passwordTextField, confirmPasswordField;
    private JButton registerButton;
    private final Dimension FIELD_DIMENSIONS = new Dimension(150, 30);

    RegisterPage() {
        initHelpMessage();
        initUsernamePanel();
        initPasswordPanel();
        initConfirmPasswordPanel();
        initEmailPanel();
        initRegisterButton();
        initDefaultValues();
    }

    private void initRegisterButton() {
        registerButtonPanel = new JPanel(new FlowLayout());
        registerButton = new JButton("Register");

        registerButton.addActionListener(event -> {
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
            dispose();
        });

        //add components
        registerButtonPanel.add(registerButton);
        add(registerButtonPanel);
    }

    private void initHelpMessage() {
        helpMessage = new JLabel("Please input your credentials below to register.");
        helpMessage.setHorizontalAlignment(SwingConstants.CENTER);
        add(helpMessage);
    }

    private void initConfirmPasswordPanel() {
        //panel
        confirmPasswordPanel = new JPanel(new FlowLayout());

        //label
        confirmaPasswordLabel = new JLabel("confirm password: ");

        //field
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setPreferredSize(FIELD_DIMENSIONS);

        //add components
        confirmPasswordPanel.add(confirmaPasswordLabel);
        confirmPasswordPanel.add(confirmPasswordField);
        add(confirmPasswordPanel);
    }

    private void initEmailPanel() {
        //panel
        emailPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //label
        emailLabel = new JLabel("email: ");

        //field
        emailTextField = new JTextField();
        emailTextField.setPreferredSize(FIELD_DIMENSIONS);

        //add components
        emailPanel.add(emailLabel);
        emailPanel.add(emailTextField);
        add(emailPanel);
    }

    private void initPasswordPanel() {
        //panel
        passwordPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //label
        passwordLabel = new JLabel("password: ");

        //field
        passwordTextField = new JPasswordField();
        passwordTextField.setPreferredSize(FIELD_DIMENSIONS);

        //add components
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordTextField);
        add(passwordPanel);
    }

    private void initUsernamePanel() {
        //panel
        usernamePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //label
        usernameLabel = new JLabel("username: ");

        //field
        usernameTextField = new JTextField();
        usernameTextField.setPreferredSize(FIELD_DIMENSIONS);

        //add components
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);
        add(usernamePanel);
    }

    private void initDefaultValues() {
        setLayout(new GridLayout(6, 1));
        setTitle("Register Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 900);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
