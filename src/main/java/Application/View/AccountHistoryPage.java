package Application.View;

import Application.Controller.AuditController;
import Application.Model.AuditEvent;
import Application.Model.User;
import Helper.BackButton;
import Helper.LoggedUser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.List;

public class AccountHistoryPage extends JFrame {
    private JLabel textLabel;
    private String messageToDisplay;
    private final int numberOfActionsDisplayed = 10;
    private static AccountHistoryPage instance;

    private AccountHistoryPage() {
        initMessage();
        initTextLabel();
        initDefaultValues();

    }

    public static AccountHistoryPage getInstance() {
        if (instance == null) {
            instance = new AccountHistoryPage();
        }
        BackButton.addPage(instance);
        refreshMessage();
        return instance;
    }

    private void initMessage() {
        User user = LoggedUser.getLoggedUser();
        List<AuditEvent> eventList = AuditController.getLastTenActions(user);
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("<html>");
        for (int i = 0; i < numberOfActionsDisplayed; i++) {
            String message = createMessage(eventList.get(i));
            stringBuilder.append(message);
        }
        stringBuilder.append("</html>");

        messageToDisplay = stringBuilder.toString();
    }

    private static void refreshMessage() {
        instance.initMessage();
        instance.textLabel.setText(instance.messageToDisplay);
    }

    private String createMessage(AuditEvent event) {
        String result = "";
        String[] splitTimeStamp = event.getTimeStamp().split(" ");
        String formatedTimestamp = splitTimeStamp[0] + " at " + splitTimeStamp[1];
        switch (event.getAction()) {
            case "LOGIN":
                result += "<p style=\"color:blue\">Logged in on " + formatedTimestamp + "</p>";
                break;
            case "LOGOUT":
                result += "<p style=\"color:red\">Logged out on " + formatedTimestamp + "</p>";
                break;
            case "REGISTER":
                result += "<p>Account created on " + formatedTimestamp + "</p>";
                break;
            case "HOME":
                result += "<p>Accessed Flight Dashboard on " + formatedTimestamp + "</p>";
                break;
            case "ADD_FLIGHT":
                result += "<p style=\"color: rgb(210, 148, 0)\">On " + formatedTimestamp + " new flight was added" + "</p>";
                break;
            case "REMOVE_FLIGHT":
                result += "<p style=\"color: rgb(249, 0, 88)\">On " + formatedTimestamp + " a flight was removed" + "</p>";
                break;
            case "ACCOUNT":
                result += "<p>Accessed Account Settings on " + formatedTimestamp + "</p>";
                break;
            case "AUDIT":
                result += "<p>Accessed Account History on " + formatedTimestamp + "</p>";
                break;
            case "PASSWORD_CHANGED":
                result += "<p>Password was changed on " + formatedTimestamp + "</p>";
                break;
            case "EMAIL_CHANGED":
                result += "<p>Email was changed on " + formatedTimestamp + "</p>";
                break;
            case "USERNAME_CHANGED":
                result += "<p>Username was changed on " + formatedTimestamp + "</p>";
                break;
        }
        return result;
    }

    private void initTextLabel() {
        textLabel = new JLabel(messageToDisplay);
        textLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

        //add component
        add(textLabel);
    }


    private void initDefaultValues() {

        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        setTitle("Account History");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 200);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
