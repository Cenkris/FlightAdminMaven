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

    private void initRegisterButton() {
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(buttonsDimension);
        add(loginButton);
    }

    private void initLoginButton() {
        registerButton = new JButton("Register");
        registerButton.setPreferredSize(buttonsDimension);
        add(registerButton);
    }

    private void initDefaultValues() {
        setLayout(new FlowLayout());
        setTitle("Login Page");
        setSize(300,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setResizable(false);
        setVisible(true);
    }
}
