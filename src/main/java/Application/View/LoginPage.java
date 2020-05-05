package Application.View;

import javax.swing.*;

public class LoginPage extends JFrame {
    LoginPage(){
        initDefaultValues();
    }

    private void initDefaultValues() {
        setTitle("Login Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300,200);
        setLocationRelativeTo(null);
        setResizable(false);
    }

}
