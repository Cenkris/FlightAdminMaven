package Application.View;

import Application.Model.Audit;

import javax.swing.*;

public class AccountHistoryPage extends JFrame {
    private JLabel textLabel;

    AccountHistoryPage() {
        initText();
        initDefaultValues();
    }

    private void initText() {
        textLabel = new JLabel();

        //add component
        add(textLabel);
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