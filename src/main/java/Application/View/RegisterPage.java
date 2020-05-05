package Application.View;

import Application.Controller.UserController;
import Application.Model.User;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class RegisterPage extends JFrame {
    private final UserController userController = new UserController();

    //swing components
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

        registerButton.addActionListener(event -> register());

        //add components
        registerButtonPanel.add(registerButton);
        add(registerButtonPanel);
    }

    private void register() {
        String username = usernameTextField.getText();
        String password = new String(passwordTextField.getPassword());
        String email = emailTextField.getText();

        if (allFieldsValid()) {
            User user = new User(username, password, email);
            userController.insertUser(user);
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
            dispose();
        } else {
            if (someFieldsAreEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill all the fields");
            } else if (!userController.isNewUser(usernameTextField.getText())) {
                JOptionPane.showMessageDialog(null, "Username already exists");
                usernameTextField.requestFocus();
            } else if (!isValidPassword()) {
                JOptionPane.showMessageDialog(null, "For security reasons password must be: \n" +
                        "\t - minimum 6 characters; \n" +
                        "\t - at least a capital letter; \n" +
                        "\t - at least a non-capital letter \n" +
                        "\t - at leas one digit;");
                passwordTextField.requestFocus();
            } else if (!arePasswordsMatch()) {
                JOptionPane.showMessageDialog(null, "Passwords must match");
                confirmPasswordField.requestFocus();
            } else if (!isEmailPatternValid()) {
                JOptionPane.showMessageDialog(null, "Please input a valid email address");
                emailTextField.requestFocus();
            } else if (!userController.isNewEmail(emailTextField.getText())) {
                JOptionPane.showMessageDialog(null, "Email already exists!");
                emailTextField.requestFocus();
            }
        }
    }

    private boolean allFieldsValid() {
        return isValidPassword() &&
                arePasswordsMatch() &&
                userController.isNewUser(usernameTextField.getText()) &&
                !someFieldsAreEmpty() &&
                isValidEmail();
    }

    private boolean isValidPassword() {
        String password = new String(passwordTextField.getPassword());
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$");
    }

    private boolean isEmailPatternValid() {
        String email = emailTextField.getText();
        return email.matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{1,3}$");
    }

    private boolean isValidEmail() {
        return isEmailPatternValid() && userController.isNewEmail(emailTextField.getText());
    }

    private boolean someFieldsAreEmpty() {
        return usernameTextField.getText().isEmpty() ||
                new String(passwordTextField.getPassword()).isEmpty() ||
                new String(confirmPasswordField.getPassword()).isEmpty() ||
                emailTextField.getText().isEmpty();
    }

    private boolean arePasswordsMatch() {
        char[] password = passwordTextField.getPassword();
        char[] confirmedPassword = confirmPasswordField.getPassword();
        return Arrays.equals(password, confirmedPassword);
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
