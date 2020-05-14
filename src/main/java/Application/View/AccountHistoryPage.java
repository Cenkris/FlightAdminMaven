package Application.View;

import Application.Controller.AuditController;
import Application.Model.AuditEvent;
import Helper.LoggedUser;

import javax.swing.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class AccountHistoryPage extends JFrame {
    private JLabel textLabel;
    private String messageToDisplay;

    AccountHistoryPage() {
        initMessage();
        initTextLabel();
        initDefaultValues();
    }

    private void initMessage() {
        List<AuditEvent> eventList = AuditController.getLastTenActions(LoggedUser.getLoggedUser());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        for (AuditEvent event : eventList) {
            String message = createMessage(event);
            stringBuilder.append(message).append("<br/>");
        }
        stringBuilder.append("</html>");

        messageToDisplay = stringBuilder.toString();
    }

    private String createMessage(AuditEvent event) {
        return event.getTimeStamp();
    }

    private void initTextLabel() {
        textLabel = new JLabel(messageToDisplay);

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
