package Application.View;

import javax.swing.*;

public class RegisterPage extends JFrame {

    RegisterPage(){
        initDefaultValues();
    }

    private void initDefaultValues() {
        setTitle("Register Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,900);
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
