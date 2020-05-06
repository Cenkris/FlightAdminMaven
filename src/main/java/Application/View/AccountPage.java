package Application.View;

import Application.Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccountPage extends JPanel {
    private JLabel welcomeMessageLabel;
    private User loggedUser = new User("marcel", "pass", "v@yahoo.com"); //TODO: replace with UserAudit.getLoggedUser();
    private JButton changeUsernameButton, changeEmailButton;
    private JTextField newEmailTextField, newUsernameTextField;
    private JPanel newEmailPanel, newUsernamePanel;
    private final Dimension TEXT_FIELD_DIMENSION = new Dimension(200,25);
    private final Dimension BUTTON_FIELD_DIMENSION = new Dimension(150,25);
    private boolean notClickedUsername = true;
    private boolean notClickedEmail = true;

    public AccountPage() {
        setLayout(new GridLayout(4,1));
        initWelcomeMessage();
        initNewEmailPanel();
        initNewUsernamePanel();
        initChangePasswordButton();
    }

    private void initChangePasswordButton() {
        changeEmailButton = new JButton("Change Password");
        add(changeEmailButton);
        System.out.println(changeEmailButton.getSize());
    }

    private void initNewUsernamePanel() {
        //panel
        newUsernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //text field
        newUsernameTextField = new JTextField("Input the new username ");
        newUsernameTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
        MouseAdapter usernameMouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(notClickedUsername){
                    newUsernameTextField.setText("");
                    notClickedUsername = false;
                }
            }
        };
        newUsernameTextField.addMouseListener(usernameMouseAdapter);

        //button
        changeEmailButton = new JButton("Change username");
        changeEmailButton.setPreferredSize(BUTTON_FIELD_DIMENSION);

        //add components
        newUsernamePanel.add(changeEmailButton);
        newUsernamePanel.add(newUsernameTextField);
        add(newUsernamePanel);
    }

    private void initNewEmailPanel() {
        //panel
        newEmailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //text field
        newEmailTextField = new JTextField("Input new email ");
        newEmailTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
        MouseAdapter emailMouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(notClickedEmail){
                    newEmailTextField.setText("");
                    notClickedEmail = false;
                }

            }
        };
        newEmailTextField.addMouseListener(emailMouseAdapter);

        //button
        changeUsernameButton = new JButton("Change email");
        changeUsernameButton.setPreferredSize(BUTTON_FIELD_DIMENSION);

        //add components
        newEmailPanel.add(changeUsernameButton);
        newEmailPanel.add(newEmailTextField);
        add(newEmailPanel);
    }

    private void initWelcomeMessage() {
        //message
        String message = "Hello " + loggedUser.getUsername() + "! " +
                "You are logged with " + loggedUser.getEmail();

        //calculations for text dimension
        int messageLengthDimension = message.length() * 7;
        Dimension textDimension = new Dimension(messageLengthDimension, 30);

        //Jlable props
        welcomeMessageLabel = new JLabel(message);
        welcomeMessageLabel.setPreferredSize(textDimension);
        welcomeMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeMessageLabel);
    }
}
