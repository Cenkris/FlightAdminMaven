package Application.View;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class AddFlightPage extends JFrame {
    private JLabel sourceLabel, destinationLabel, leaveHourLabel, durationLabel, daysLabel, priceLabel;
    private JTextField sourceTextField, destinationTextField, leaveHourTextField, durationTextField, priceTextField;
    private JPanel sourcePanel, destinationPanel, leaveHourPanel, durationPanel, daysPanel, pricePanel, buttonsPanel;
    private JButton addFlightButton, abortButton;
    private List<JCheckBox> daysCheckBox;
    private final Dimension TEXTFIELD_DIMENSIONS = new Dimension(200, 30);

    public AddFlightPage() {
        initSourcePanel();
        initDestinationPanel();
        initLeaveHourPanel();
        initDurationPanel();
        initDaysPanel();
        initPricePanel();
        initButtonsPanel();
        initDefaultValues();
    }

    private void initButtonsPanel() {
        //Panel
        buttonsPanel = new JPanel();

        //Buttons
        addFlightButton = new JButton("Adauga Zbor");
        addFlightButton.addActionListener(event -> {
        });

        abortButton = new JButton("Anuleaza");
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
        priceLabel = new JLabel("Pret: ");

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
        daysPanel.setPreferredSize(new Dimension(TEXTFIELD_DIMENSIONS.width + leaveHourTextField.getText().length(), 60));

        //Label
        daysLabel = new JLabel("Zile: ");

        //checkBoxes
        daysCheckBox = Arrays.asList(new JCheckBox("Luni"),
                new JCheckBox("Marti"),
                new JCheckBox("Miercuri"),
                new JCheckBox("Joi"),
                new JCheckBox("Vineri"),
                new JCheckBox("Sambata"),
                new JCheckBox("Duminica"));

        //add components
        daysPanel.add(daysLabel);
        for(JCheckBox checkbox : daysCheckBox){
            daysPanel.add(checkbox);
        }

        add(daysPanel);
    }

    private void initDurationPanel() {
        //Panel
        durationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //Label
        durationLabel = new JLabel("Durata: ");

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
        leaveHourPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //Label
        leaveHourLabel = new JLabel("Ora Plecare: ");

        //text field
        leaveHourTextField = new JTextField();
        leaveHourTextField.setPreferredSize(TEXTFIELD_DIMENSIONS);

        //add components
        leaveHourPanel.add(leaveHourLabel);
        leaveHourPanel.add(leaveHourTextField);
        add(leaveHourPanel);
    }

    private void initDestinationPanel() {
        //Panel
        destinationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        //Label
        destinationLabel = new JLabel("Destinatie: ");

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
        sourceLabel = new JLabel("Sursa: ");

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

    private boolean isSourceSameAsDestination() {
        String source = sourceTextField.getText();
        String destination = destinationTextField.getText();
        return source.equals(destination);
    }

    private boolean cityHasMoreThanThreeLetters(String string) {
        return string.length() >= 3;
    }

    private boolean isPricePositive() {
        return Integer.parseInt(priceTextField.getText()) > 0;
    }

    private boolean isValidHourFormat(String hour) {
        return hour.matches("^\\d+:\\d+$");
    }
}
