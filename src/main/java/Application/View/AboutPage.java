package Application.View;

import Helper.BackButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AboutPage extends JFrame {
    private JLabel aboutLabel;
    private JPanel aboutPanel;
    private static AboutPage instance;

    private AboutPage(){

        initAbout();
        initDefaultValues();
    }

    public static AboutPage getInstance(){
        if (instance == null) {
            instance = new AboutPage();
        }
        BackButton.addComponent(instance);
        return instance;
    }

    private void initAbout() {
        aboutPanel = new JPanel(new FlowLayout());
        aboutLabel = new JLabel("Created by Traian Butaru for Java 1 Professional Exam 2020");
        aboutLabel.setPreferredSize(new Dimension(360,60));
        aboutLabel.setBorder(new EmptyBorder(1,10,1,1));

        aboutPanel.add(aboutLabel);
        add(aboutPanel);
    }

    private void initDefaultValues() {
        setTitle("About Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(50, 200);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
