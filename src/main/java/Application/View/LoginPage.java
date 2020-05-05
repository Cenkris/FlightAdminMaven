package Application.View;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {
    private JPanel usernamePanel, passwordPanel;
    private JLabel usernameLabel, passwordLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordTextField;
    private final Dimension FIELD_DIMENSIONS = new Dimension(150, 30);

    LoginPage() {
        initUsernamePanel();
        initPasswordPanel();
        initDefaultValues();
    }

    private void initUsernamePanel() {
        //panel
        usernamePanel = new JPanel(new FlowLayout());

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

    private void initPasswordPanel() {
        //panel
        passwordPanel = new JPanel(new FlowLayout());

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

    private void initDefaultValues() {
        setLayout(new GridLayout(2,1));
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        pack();
        setResizable(false);
    }

}
