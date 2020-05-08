package Application.View;

import Application.Model.TableRenderer;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class HomePage extends JPanel {
    private JLabel clockLabel;
    private JPanel clockPanel;
    private JTable fligthTable;
    private JButton addFlightButton, deleteFlightButton;
    private String[] colNames = {"", "Sursa", "Destinatie", "Ora Plecare", "Ora Sosire", "Zile", "Pret"};
    private final int xAxisDimension = 600;
    private DefaultTableModel flightTableModel;


    public HomePage() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        initClock();
        initDeleteFlightButton();
        initTablePanel();
        initAddFlightButton();
    }

    private void initDeleteFlightButton() {
        deleteFlightButton = new JButton();
        deleteFlightButton.setBackground(Color.RED);
        deleteFlightButton.setName("del");
    }

    private void initAddFlightButton() {


        JPanel buttonPannel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addFlightButton = new JButton("Add Flight");
        addFlightButton.setPreferredSize(new Dimension(xAxisDimension, 30));
        addFlightButton.addActionListener(event -> {
            AddFlightPage addFlightPage = new AddFlightPage();
            addFlightPage.setVisible(true);
        });

        //add component
        buttonPannel.add(addFlightButton);
        add(buttonPannel);
    }

    private void addRow() {

    }

    private void initTablePanel() {
        Object[][] data = {{deleteFlightButton, "Bucuresti", "Londra", "7:40", "10:25", "Luni, Marti, Joi", "1040"},
                {deleteFlightButton, "Londra", "Bucuresti", "13:05", "15:40", "Marti, Sambata", "965"}};

        initTableModel();
        fligthTable = new JTable(flightTableModel);
        MouseAdapter deleteMouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getComponent() instanceof JTable) {
                    JTable table = (JTable) e.getComponent();
                    int selectedColumn = table.getSelectedColumn();
                    int selectedRow = table.getSelectedRow();
                    if (selectedColumn == 0 && selectedRow >= 0) {
                        int answer = JOptionPane.showConfirmDialog(null,
                                "Are you sure you want to delete the flight corresponding to this row?",
                                "Delete Flight", JOptionPane.YES_NO_OPTION);
                        if (answer == JOptionPane.YES_OPTION) {
                            flightTableModel.removeRow(selectedRow);
                            //TODO: delete database info
                        }
                    }


                }
            }
        };
        fligthTable.addMouseListener(deleteMouseAdapter);

        setColumnNames();
        addRowsToTable(data);
        setTableColumnPreferences();

        initTableCellRenderer();
        JScrollPane scrollPane = new JScrollPane(fligthTable);
        JScrollBar scrollBar = new JScrollBar();
        scrollPane.setVerticalScrollBar(scrollBar);
        scrollPane.setPreferredSize(new Dimension(xAxisDimension, 100));

        //add component
        add(scrollPane);
    }

    private void setTableColumnPreferences() {
        TableColumnModel columnModel = fligthTable.getColumnModel();
        DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
        centerRender.setHorizontalAlignment(SwingConstants.CENTER);

        //buttons Column
        TableColumn buttonsColumn = columnModel.getColumn(0);
        buttonsColumn.setPreferredWidth(10);

        TableColumn sursaColumn = columnModel.getColumn(1);
        sursaColumn.setPreferredWidth(100);
        sursaColumn.setCellRenderer(centerRender);

        TableColumn destinatieColumn = columnModel.getColumn(2);
        destinatieColumn.setPreferredWidth(100);
        destinatieColumn.setCellRenderer(centerRender);

        TableColumn oraPlecareColumn = columnModel.getColumn(3);
        oraPlecareColumn.setPreferredWidth(80);
        oraPlecareColumn.setCellRenderer(centerRender);

        TableColumn oraSosireColumn = columnModel.getColumn(4);
        oraSosireColumn.setPreferredWidth(80);
        oraSosireColumn.setCellRenderer(centerRender);

        TableColumn zileColumn = columnModel.getColumn(5);
        zileColumn.setPreferredWidth(180);
        zileColumn.setCellRenderer(centerRender);

        TableColumn pretColumn = columnModel.getColumn(6);
        pretColumn.setPreferredWidth(70);
        pretColumn.setCellRenderer(centerRender);

    }

    private void initTableCellRenderer() {
        TableCellRenderer tableCellRenderer = fligthTable.getDefaultRenderer(JButton.class);
        fligthTable.setDefaultRenderer(JButton.class, new TableRenderer(tableCellRenderer));
    }

    private void initTableModel() {
        flightTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }

        };


    }

    private void addRowsToTable(Object[][] data) {
        flightTableModel.addRow(data[0]);
        flightTableModel.addRow(data[1]);
    }

    private void setColumnNames() {
        //set column names
        for (Object colName : colNames) {
            flightTableModel.addColumn(colName);
        }
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
