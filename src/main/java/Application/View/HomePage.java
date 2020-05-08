package Application.View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class HomePage extends JPanel {
    private JLabel clockLabel;
    private JPanel clockPanel;
    private JTable fligthTable;
    private JButton addFlightButton;
    private String[][] data = {{"Bucuresti", "Londra", "7:40", "10:25", "Luni, Marti, Joi", "1040"},
            {"Londra", "Bucuresti", "13:05", "15:40", "Marti, Sambate", "965"}};
    private String[] colNames = {"Sursa", "Destinatie", "Ora Plecare", "Ora Sosire", "Zile", "Pret"};

    public HomePage() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        initClock();
        initTablePanel();
        initAddFlightButton();
    }

    private void initAddFlightButton() {
        JPanel buttonPannel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addFlightButton = new JButton("Add Flight");
        addFlightButton.setPreferredSize(new Dimension(500, 30));
        addFlightButton.addActionListener(event -> addRow());

        //add component
        buttonPannel.add(addFlightButton);
        add(buttonPannel);
    }

    private void addRow() {

    }

    private void initTablePanel() {
        DefaultTableModel flightTableModel = new DefaultTableModel(){
        };
        fligthTable = new JTable(flightTableModel);

        //set column names
        for(String colName : colNames){
            flightTableModel.addColumn(colName);
        }

        //add rows
        flightTableModel.addRow(data[0]);
//        fligthTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane scrollPane = new JScrollPane(fligthTable);
        JScrollBar scrollBar = new JScrollBar();
        scrollPane.setVerticalScrollBar(scrollBar);
        scrollPane.setPreferredSize(new Dimension(500,100));

        //add component
        add(scrollPane);
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
