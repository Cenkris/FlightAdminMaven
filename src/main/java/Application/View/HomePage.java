package Application.View;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class HomePage extends JPanel {
    private JLabel clockLabel;
    private JPanel clockPanel, tablePanel;
    private JTable fligthTable;
    private String[][] data = {{"Bucuresti", "Londra", "7:40", "10:25", "Luni, Marti, Joi", "1040"},
            {"Londra", "Bucuresti", "13:05", "15:40", "Marti, Sambate", "965"}};
    private String[] colNames = {"Sursa", "Destinatie", "Ora Plecare", "Ora Sosire", "Zile", "Pret"};
    private int counter = 3;

    public HomePage() {
        setLayout(new GridLayout(3, 1));
        initClock();
        initTablePanel();
    }

    private void initTablePanel() {
        tablePanel = new JPanel();
        JButton increaseRows = new JButton("increase");
        increaseRows.addActionListener(e -> {
            counter++;
            setLayout(new GridLayout(counter, 1));
            add(new JButton());
            validate();
            repaint();
        });


        JButton decreaseRows = new JButton("decrease");
        decreaseRows.addActionListener(e -> {
            counter--;
            setLayout(new GridLayout(counter, 1));

            validate();
            repaint();
        });

        tablePanel.add(increaseRows);
        tablePanel.add(decreaseRows);
        add(tablePanel);
    }

    private void initClock() {
        //panel
        clockPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //label
        clockLabel = new JLabel();

        SwingWorker<String, Void> worker = new SwingWorker<>() {
            @Override
            protected String doInBackground() {
                while (true) {
                    Date date = Date.from(Instant.now());
                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    clockLabel.setText("Date & Time: " + formatter.format(date));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        worker.execute();


        //add Components
        clockPanel.add(clockLabel);
        add(clockPanel);
    }
}
