package Application.View;

import javax.swing.*;
import java.awt.*;

public class StartPage extends JFrame {
    private JButton loginButton, registerButton;
    private final Dimension buttonsDimension = new Dimension(120,50);

    public StartPage(){
        initLoginButton();
        initRegisterButton();
        initDefaultValues();
    }

    private void initLoginButton() {
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(buttonsDimension);
        loginButton.addActionListener(event -> {
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
            dispose();
        });
        add(loginButton);
    }

    private void initRegisterButton() {
        registerButton = new JButton("Register");
        registerButton.setPreferredSize(buttonsDimension);
        registerButton.addActionListener(event -> {
            RegisterPage registerPage = new RegisterPage();
            registerPage.setVisible(true);
            dispose();
        });
        add(registerButton);
    }

    private void initDefaultValues() {
        setLayout(new FlowLayout());
        setTitle("Start Page");
        setSize(300,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);
    }
}
