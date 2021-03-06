package Application.View;

import Application.Controller.AuditController;
import Application.Controller.UserController;
import Application.Model.Audit;
import Application.Model.User;
import Helper.AccountConstraints;
import Helper.BackButton;
import Helper.LoggedUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AccountPage extends JPanel {
    private final UserController userController = new UserController();
    //Swing
    private JLabel welcomeMessageLabel;
    private User loggedUser = LoggedUser.getLoggedUser();
    private JButton changeUsernameButton, changeEmailButton, changePasswordButton, showHistoryButton;
    private JTextField newEmailTextField, newUsernameTextField;
    private JPanel newEmailPanel, newUsernamePanel;
    private final Dimension TEXT_FIELD_DIMENSION = new Dimension(200, 25);
    private final Dimension BUTTON_FIELD_DIMENSION = new Dimension(150, 25);
    private boolean notClickedUsername = true;
    private boolean notClickedEmail = true;
    private String welcomeMessage = "";


    public AccountPage() {
        writeMessage();
        initBackButton();
        initWelcomeMessage();
        initNewEmailPanel();
        initNewUsernamePanel();
        initChangePasswordAndHistoryButton();
        initPanelDefaultValues();
    }

    private void initBackButton() {
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.add(new BackButton());
        add(backButtonPanel);
    }

    private void initPanelDefaultValues() {
        setLayout(new GridLayout(5, 1));
        setName("AccountPage");
    }

    private void initChangePasswordAndHistoryButton() {
        //panel
        JPanel passwordAndHistoryPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));


        // change password button
        changePasswordButton = new JButton("Change Password");
        changePasswordButton.setPreferredSize(BUTTON_FIELD_DIMENSION);
        changePasswordButton.addActionListener(event -> {
            ChangePasswordPage changePasswordPage = ChangePasswordPage.getInstance();
            changePasswordPage.parent(this);
            changePasswordPage.setVisible(true);
        });

        //show history button
        showHistoryButton = new JButton("Show Account History");
        showHistoryButton.setPreferredSize(TEXT_FIELD_DIMENSION);
        showHistoryButton.addActionListener(event -> {
            AuditController.saveEvent(LoggedUser.getLoggedUser(), Audit.AUDIT);
            AccountHistoryPage accountHistoryPage = AccountHistoryPage.getInstance();
            accountHistoryPage.setVisible(true);
        });

        //add components
        passwordAndHistoryPanel.add(changePasswordButton);
        passwordAndHistoryPanel.add(showHistoryButton);
        add(passwordAndHistoryPanel);
    }

    private void initNewUsernamePanel() {
        //panel
        newUsernamePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //text field
        newUsernameTextField = new JTextField("Input a new username");
        newUsernameTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
        MouseAdapter usernameMouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (notClickedUsername) {
                    newUsernameTextField.setText("");
                    notClickedUsername = false;
                }
            }
        };
        newUsernameTextField.addMouseListener(usernameMouseAdapter);

        //button
        changeUsernameButton = new JButton("Change username");
        changeUsernameButton.setPreferredSize(BUTTON_FIELD_DIMENSION);
        changeUsernameButton.addActionListener(event -> changeUsername());

        //add components
        newUsernamePanel.add(changeUsernameButton);
        newUsernamePanel.add(newUsernameTextField);
        add(newUsernamePanel);
    }

    private void changeEmail() {
        String inputEmail = newEmailTextField.getText();
        if (!inputEmail.isEmpty() && userController.isNewEmail(inputEmail) && inputEmail.matches(AccountConstraints.EMAIL_VALIDATOR)) {
            userController.updateEmail(loggedUser.getEmail(), inputEmail);

            JOptionPane.showMessageDialog(null, "email " + loggedUser.getEmail()
                    + " was changed to " + inputEmail);


            newEmailTextField.setText("");
            LoggedUser.loggedUser = userController.getUserByEmail(inputEmail);
            loggedUser = LoggedUser.getLoggedUser();
            AuditController.saveEvent(loggedUser, Audit.EMAIL_CHANGED);
            writeMessage();
            welcomeMessageLabel.setText(welcomeMessage);
            int messageLengthDimension = getWelcomeMessageLength();
            welcomeMessageLabel.setPreferredSize(new Dimension(messageLengthDimension, 30));
            repaint();
            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
//            topFrame.pack();

        } else if (inputEmail.isEmpty() || inputEmail.equals("Input a new email")) {

            JOptionPane.showMessageDialog(null, "Please input an email address");
            newEmailTextField.setText("");
            newEmailTextField.requestFocus();

        } else if (!inputEmail.matches(AccountConstraints.EMAIL_VALIDATOR)) {

            JOptionPane.showMessageDialog(null, "Please input a valid email address");
            newEmailTextField.setText("");
            newEmailTextField.requestFocus();

        } else {

            JOptionPane.showMessageDialog(null, "Email already exists!");
            newEmailTextField.setText("");
            newEmailTextField.requestFocus();
        }
    }

    private int getWelcomeMessageLength() {
        return welcomeMessage.length() * 7;
    }

    private void initNewEmailPanel() {
        //panel
        newEmailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        //text field
        newEmailTextField = new JTextField("Input a new email");
        newEmailTextField.setPreferredSize(TEXT_FIELD_DIMENSION);
        MouseAdapter emailMouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (notClickedEmail) {
                    newEmailTextField.setText("");
                    notClickedEmail = false;
                }

            }
        };
        newEmailTextField.addMouseListener(emailMouseAdapter);

        //button
        changeEmailButton = new JButton("Change email");
        changeEmailButton.setPreferredSize(BUTTON_FIELD_DIMENSION);
        changeEmailButton.addActionListener(event -> changeEmail());

        //add components
        newEmailPanel.add(changeEmailButton);
        newEmailPanel.add(newEmailTextField);
        add(newEmailPanel);
    }

    private void changeUsername() {
        String inputUsername = newUsernameTextField.getText();
        if (!inputUsername.isEmpty() && userController.isNewUser(inputUsername) && !inputUsername.equals("Input a new username")) {
            userController.updateUsername(loggedUser.getUsername(), inputUsername);
            JOptionPane.showMessageDialog(null, "Username " + loggedUser.getUsername()
                    + " was changed to " + inputUsername);
            newUsernameTextField.setText("");
            LoggedUser.loggedUser = userController.getUserByUsername(inputUsername);
            loggedUser = LoggedUser.getLoggedUser();
            AuditController.saveEvent(loggedUser, Audit.USERNAME_CHANGED);
            writeMessage();
            welcomeMessageLabel.setText(welcomeMessage);
        } else if (inputUsername.isEmpty() || inputUsername.equals("Input a new username")) {
            JOptionPane.showMessageDialog(null, "Please input a username");
            newUsernameTextField.setText("");
            newUsernameTextField.requestFocus();
        } else {
            JOptionPane.showMessageDialog(null, "Username already exists!");
            newUsernameTextField.setText("");
            newUsernameTextField.requestFocus();
        }
    }

    private void writeMessage() {
        welcomeMessage = "Hello " + loggedUser.getUsername() + "! " +
                "You are logged with " + loggedUser.getEmail();
    }

    private void initWelcomeMessage() {

        //calculations for text dimension
        int messageLengthDimension = getWelcomeMessageLength();
        Dimension textDimension = new Dimension(messageLengthDimension, 30);

        //Jlable props
        welcomeMessageLabel = new JLabel(welcomeMessage);
        welcomeMessageLabel.setPreferredSize(textDimension);
        welcomeMessageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeMessageLabel);
    }
}
