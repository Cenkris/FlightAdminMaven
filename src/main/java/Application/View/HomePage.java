package Application.View;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class HomePage extends JPanel {
    private JLabel clockLabel;
    private JPanel clockPanel;

    public HomePage() {
        setLayout(new GridLayout(3, 1));
        initClock();
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
