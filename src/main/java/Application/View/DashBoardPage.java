package Application.View;

import javax.swing.*;
import java.awt.*;

public class DashBoardPage extends JFrame {

    DashBoardPage(){

        initDefaultValues();
    }

    private void initDefaultValues() {
//        setLayout(new GridLayout(6, 1));
        setTitle("DashBoard Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
//        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
