package Application.View;

import Application.Controller.AuditController;
import Application.Controller.FlightController;
import Application.Model.Audit;
import Application.Model.Flight;
import Helper.BackButton;
import Helper.LoggedUser;

import javax.swing.*;
import java.awt.*;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class AddFlightPage extends JFrame {
    private JLabel sourceLabel, destinationLabel, departureHourLabel, durationLabel, daysLabel, priceLabel;
    private JTextField sourceTextField, destinationTextField, departureHourTextField, durationTextField, priceTextField;
    private JPanel sourcePanel, destinationPanel, departureHourPanel, durationPanel, daysPanel, pricePanel, buttonsPanel;
    private JButton addFlightButton, abortButton;
    private List<JCheckBox> daysCheckBox;
    private final Dimension TEXTFIELD_DIMENSIONS = new Dimension(200, 30);
    private final FlightController flightController = new FlightController();
    private static AddFlightPage instance;


    private AddFlightPage() {

        initSourcePanel();
        initDestinationPanel();
        initLeaveHourPanel();
        initDurationPanel();
        initDaysPanel();
        initPricePanel();
        initButtonsPanel();
        initDefaultValues();
    }

    public static AddFlightPage getInstance() {
        if (instance == null) {
            instance = new AddFlightPage();
        }
        BackButton.addPage(instance);
        refreshPage();
        return instance;
    }

    private static void refreshPage() {
        instance.destinationTextField.setText("");
        instance.departureHourTextField.setText("");
        instance.sourceTextField.setText("");
        instance.durationTextField.setText("");
        instance.priceTextField.setText("");
        instance.daysCheckBox.forEach(checkBox -> checkBox.setSelected(false));
    }

    private void initButtonsPanel() {
        //Panel
        buttonsPanel = new JPanel();

        //Buttons
        addFlightButton = new JButton("Add Flight");
        addFlightButton.addActionListener(event -> addFlight());

        abortButton = new JButton("Abort");
        abortButton.addActionListener(event -> dispose());

        //add components
        buttonsPanel.add(addFlightButton);
        buttonsPanel.add(abortButton);
        add(buttonsPanel);
    }

    private void initPricePanel() {
        //Panel
        pricePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //Label
        priceLabel = new JLabel("Price: ");

        //text field
        priceTextField = new JTextField();
        priceTextField.setPreferredSize(TEXTFIELD_DIMENSIONS);

        //add components
        pricePanel.add(priceLabel);
        pricePanel.add(priceTextField);
        add(pricePanel);
    }

    private void initDaysPanel() {
        //Panel
        daysPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        daysPanel.setPreferredSize(new Dimension(200, 60));

        //Label
        daysLabel = new JLabel("Days: ");

        //checkBoxes
        daysCheckBox = Arrays.asList(new JCheckBox("Monday"),
                new JCheckBox("Tuesday"),
                new JCheckBox("Wednesday"),
                new JCheckBox("Thursday"),
                new JCheckBox("Friday"),
                new JCheckBox("Saturday"),
                new JCheckBox("Sunday"));
        //TODO make text smaller

        //add components
        daysPanel.add(daysLabel);
        for (JCheckBox checkbox : daysCheckBox) {
            daysPanel.add(checkbox);
        }

        add(daysPanel);
    }

    private void initDurationPanel() {
        //Panel
        durationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //Label
        durationLabel = new JLabel("Duration(HH:mm): ");

        //text field
        durationTextField = new JTextField();
        durationTextField.setPreferredSize(TEXTFIELD_DIMENSIONS);

        //add components
        durationPanel.add(durationLabel);
        durationPanel.add(durationTextField);
        add(durationPanel);
    }

    private void initLeaveHourPanel() {
        //Panel
        departureHourPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //Label
        departureHourLabel = new JLabel("Departure hour(HH:mm): ");

        //text field
        departureHourTextField = new JTextField();
        departureHourTextField.setPreferredSize(TEXTFIELD_DIMENSIONS);

        //add components
        departureHourPanel.add(departureHourLabel);
        departureHourPanel.add(departureHourTextField);
        add(departureHourPanel);
    }

    private void initDestinationPanel() {
        //Panel
        destinationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //Label
        destinationLabel = new JLabel("Destination: ");

        //text field
        destinationTextField = new JTextField();
        destinationTextField.setPreferredSize(TEXTFIELD_DIMENSIONS);

        //add components
        destinationPanel.add(destinationLabel);
        destinationPanel.add(destinationTextField);
        add(destinationPanel);
    }

    private void initSourcePanel() {
        //Panel
        sourcePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //Label
        sourceLabel = new JLabel("Source: ");

        //text field
        sourceTextField = new JTextField();
        sourceTextField.setPreferredSize(TEXTFIELD_DIMENSIONS);

        //add components
        sourcePanel.add(sourceLabel);
        sourcePanel.add(sourceTextField);
        add(sourcePanel);
    }

    private void initDefaultValues() {
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        setTitle("Add Flight");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 900);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void addFlight() {
        String source = sourceTextField.getText();
        String destination = destinationTextField.getText();
        String departureHour = checkAndFormatTime(departureHourTextField.getText());
        String duration = durationTextField.getText();
        String price = priceTextField.getText();


        if (someFieldsAreEmpty()) {
            JOptionPane.showMessageDialog(null, "Please complete all fields");
        } else {
            if (allFieldsAreValid()) {
                String landingHour = calculateLandingHour();
                int intPrice = Integer.parseInt(price);
                String days = checkedDaysToString();

                Flight flight = new Flight(source, destination, departureHour, landingHour, days, intPrice);

                flightController.insertFlight(flight);
                AuditController.saveEvent(LoggedUser.getLoggedUser(), Audit.ADD_FLIGHT);

                JOptionPane.showMessageDialog(null, "Flight was added to database");
                dispose();

            } else if (noDaysSelected()) {
                JOptionPane.showMessageDialog(null, "You must select at least one day");
            } else if (!cityHasMoreThanThreeLetters(source)) {
                JOptionPane.showMessageDialog(null, "Source must be at least 3 letters long");
                sourceTextField.requestFocus();
            } else if (!cityHasMoreThanThreeLetters(destination)) {
                JOptionPane.showMessageDialog(null, "Destination must be at least 3 letters long");
                destinationTextField.requestFocus();
            } else if (isSourceSameAsDestination()) {
                JOptionPane.showMessageDialog(null, "Source and destination can't be the same");
                sourceTextField.requestFocus();
            } else if (flightAlreadyExists(source, destination)) {
                JOptionPane.showMessageDialog(null, "Flight route already in db");
                destinationTextField.requestFocus();
            } else if (!isValidHourFormat(departureHour)) {
                JOptionPane.showMessageDialog(null, "Departure field hour needs to have HH:mm(0-23:00-59) format");
                departureHourTextField.requestFocus();
            } else if (!isValidHourFormat(duration)) {
                JOptionPane.showMessageDialog(null, "Duration field needs to have HH:mm(0-23:00-59) format");
                durationTextField.requestFocus();
            } else if (!isPricePositiveNumber()) {
                JOptionPane.showMessageDialog(null, "Price value must be a number more than 0");
                priceTextField.requestFocus();
            }
        }

    }

    private boolean flightAlreadyExists(String source, String destination) {
        Flight flight = new Flight(source, destination);
        return flightController.flightAlreadyExists(flight);
    }

    private String checkedDaysToString() {
        StringJoiner stringJoiner = new StringJoiner(", ");
        for (JCheckBox box : daysCheckBox) {
            if (box.isSelected()) {
                String dayOfTheWeek = box.getText().substring(0, 2);
                stringJoiner.add(dayOfTheWeek);
            }
        }
        return stringJoiner.toString();
    }

    private String calculateLandingHour() {
        String departureHour = departureHourTextField.getText();
        String duration = durationTextField.getText();

        departureHour = checkAndFormatTime(departureHour);
        duration = checkAndFormatTime(duration);

        LocalTime departureTime = LocalTime.parse(departureHour);
        LocalTime durationTime = LocalTime.parse(duration);

        int hourDuration = durationTime.getHour();
        int minutesDuration = durationTime.getMinute();

        LocalTime landingTime;
        landingTime = departureTime.plusHours(hourDuration).plusMinutes(minutesDuration);
        return landingTime.toString();
    }

    private String checkAndFormatTime(String time) {
        if (time.length() == 5) {
            return time;
        } else {
            String[] split = time.split(":");
            StringJoiner stringJoiner = new StringJoiner(":");

            for (String component : split) {
                if (component.length() < 2) {
                    stringJoiner.add("0" + component);
                } else {
                    stringJoiner.add(component);
                }
            }
            return stringJoiner.toString();
        }
    }

    private boolean allFieldsAreValid() {
        return !isSourceSameAsDestination() &&
                cityHasMoreThanThreeLetters(sourceTextField.getText()) &&
                cityHasMoreThanThreeLetters(destinationTextField.getText()) &&
                isValidHourFormat(departureHourTextField.getText()) &&
                isValidHourFormat(durationTextField.getText()) &&
                isPricePositiveNumber() && !noDaysSelected() && !flightAlreadyExists(sourceTextField.getText(), destinationTextField.getText());
    }

    private boolean isSourceSameAsDestination() {
        String source = sourceTextField.getText();
        String destination = destinationTextField.getText();
        return source.equals(destination);
    }

    private boolean cityHasMoreThanThreeLetters(String string) {
        return string.length() >= 3;
    }

    private boolean isPricePositiveNumber() {
        String price = priceTextField.getText();
        boolean result = false;

        if (price.matches("^\\d+$")) {
            int intPrice = Integer.parseInt(price);
            if (intPrice > 0) {
                result = true;
            }
        }
        return result;
    }

    private boolean isValidHourFormat(String hour) {
        return hour.matches("^(2[0-3]|1[0-9]|[0-9]|0[0-9]):(5[0-9]|4[0-9]|3[0-9]|2[0-9]|1[0-9]|[0-9]|0[0-9])$");
    }

    private boolean someFieldsAreEmpty() {
        return sourceTextField.getText().isEmpty() || destinationTextField.getText().isEmpty() ||
                departureHourTextField.getText().isEmpty() || durationTextField.getText().isEmpty() ||
                priceTextField.getText().isEmpty();
    }

    private boolean noDaysSelected() {
        boolean result = true;
        for (JCheckBox box : daysCheckBox) {
            if (box.isSelected()) {
                result = false;
                break;
            }
        }
        return result;
    }
}
