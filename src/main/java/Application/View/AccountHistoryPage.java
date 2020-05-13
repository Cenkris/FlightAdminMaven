package Application.View;

import javax.swing.*;

public class AccountHistoryPage extends JFrame {

    AccountHistoryPage() {
        initDefaultValues();
    }


    private void initDefaultValues() {

        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        setTitle("Account History");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
