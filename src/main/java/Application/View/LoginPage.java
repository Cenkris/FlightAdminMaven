package Application.View;

import javax.swing.*;

public class LoginPage extends JFrame {

    public LoginPage(){
        initDefaultValues();
    }

    private void initDefaultValues() {
        setTitle("Login Page");
        setSize(300,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
