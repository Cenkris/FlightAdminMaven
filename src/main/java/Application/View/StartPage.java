package Application.View;

import Helper.BackButton;

import javax.swing.*;
import java.awt.*;

public class StartPage extends JFrame {
    private JButton loginButton, registerButton;
    private final Dimension BUTTON_DIMENSION = new Dimension(120,50);

    public StartPage(){
        BackButton.addPage(this);
        initLoginButton();
        initRegisterButton();
        initDefaultValues();
    }

    private void initLoginButton() {
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(BUTTON_DIMENSION);
        loginButton.addActionListener(event -> {
            LoginPage loginPage = new LoginPage();
            loginPage.setVisible(true);
            dispose();
        });
        add(loginButton);
    }

    private void initRegisterButton() {
        registerButton = new JButton("Register");
        registerButton.setPreferredSize(BUTTON_DIMENSION);
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
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
