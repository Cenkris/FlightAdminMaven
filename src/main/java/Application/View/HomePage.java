package Application.View;

import Application.Controller.AuditController;
import Application.Controller.FlightController;
import Application.Controller.UserController;
import Application.Model.Audit;
import Application.Model.Flight;
import Application.Model.TableRenderer;
import Helper.BackButton;
import Helper.LoggedUser;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HomePage extends JPanel {
    private JLabel clockLabel;
    private JPanel clockPanel;
    private JTable fligthTable;
    private JButton addFlightButton, deleteFlightButton;
    private final String[] colNames = {"", "Source", "Destination", "Departure hour", "Arrival hour", "Days", "Price"};
    private final int xAxisDimension = 600;
    private DefaultTableModel flightTableModel;
    private final FlightController flightController = new FlightController();
    private final UserController userController = new UserController();
    private static boolean running = true;


    public HomePage() {
        initClock();
        initDeleteFlightButton();
        initTablePanel();
        initAddFlightButton();
        initPanelDefaultValues();
    }

    private void initPanelDefaultValues() {
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        setName("HomePage");
    }

    private void initDeleteFlightButton() {
        deleteFlightButton = new JButton();
        deleteFlightButton.setBackground(Color.RED);
        deleteFlightButton.setName("del");
    }

    private void initAddFlightButton() {


        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addFlightButton = new JButton("Add Flight");
        addFlightButton.setPreferredSize(new Dimension(xAxisDimension, 30));
        addFlightButton.addActionListener(event -> {
            AddFlightPage addFlightPage = AddFlightPage.getInstance();
            addFlightPage.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    updateTable();
                }
            });
            addFlightPage.setVisible(true);
        });

        //add component
        buttonPanel.add(addFlightButton);
        add(buttonPanel);
    }

    private void initTablePanel() {

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
                            String source = fligthTable.getValueAt(selectedRow, 1).toString();
                            String destination = flightTableModel.getValueAt(selectedRow, 2).toString();
                            Flight flight = new Flight(source, destination);
                            AuditController.saveEvent(LoggedUser.getLoggedUser(), Audit.REMOVE_FLIGHT);
                            flightController.removeFlight(flight);
                            flightTableModel.removeRow(selectedRow);
                        }
                    }


                }
            }
        };
        fligthTable.addMouseListener(deleteMouseAdapter);

        setColumnNames();
        addRowsToTable();
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
        oraPlecareColumn.setPreferredWidth(105);
        oraPlecareColumn.setCellRenderer(centerRender);

        TableColumn oraSosireColumn = columnModel.getColumn(4);
        oraSosireColumn.setPreferredWidth(90);
        oraSosireColumn.setCellRenderer(centerRender);

        TableColumn zileColumn = columnModel.getColumn(5);
        zileColumn.setPreferredWidth(160);
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

            @SuppressWarnings("rawtypes")
            @Override
            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }

        };

    }

    public void updateTable() {
        Flight flight = flightController.getLastInsertedFlight();
        Object[] row = constructRow(flight);
        if (flightController.numberOfFlights() > 0) {
            try {
                String source = fligthTable.getValueAt(flightTableModel.getRowCount() - 1, 1).toString();
                String destination = flightTableModel.getValueAt(flightTableModel.getRowCount() - 1, 2).toString();
                if (!flight.getSource().equals(source) || !flight.getDestination().equals(destination)) {
                    flightTableModel.addRow(row);
                    revalidate();
                    repaint();
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                flightTableModel.addRow(row);
                revalidate();
                repaint();
            }
        } else if (flight.getSource() != null) {
            flightTableModel.addRow(row);
            revalidate();
            repaint();
        }

    }

    private Object[] constructRow(Flight flight) {
        return new Object[]{
                deleteFlightButton,
                flight.getSource(),
                flight.getDestination(),
                flight.getDepartureHour(),
                flight.getLandingHour(),
                flight.getDays(),
                flight.getPrice()
        };
    }

    private void addRowsToTable() {
        List<Flight> allFlights = flightController.getAllFlights();
        for (Flight flight : allFlights) {
            Object[] row = constructRow(flight);
            flightTableModel.addRow(row);
        }
    }

    private void setColumnNames() {
        //set column names
        for (Object colName : colNames) {
            flightTableModel.addColumn(colName);
        }
    }

    public static void stopClockThread() {
        running = false;
    }

    private void initClock() {
        //panel
        clockPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //label
        clockLabel = new JLabel();

        running = true;
        Runnable clock = () -> {
            while (running) {
                Date date = Date.from(Instant.now());
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                clockLabel.setText("Date & Time: " + formatter.format(date));
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(clock);
        service.shutdown();

        //add Components
        clockPanel.add(new BackButton());
        clockPanel.add(clockLabel);
        add(clockPanel);
    }
}
