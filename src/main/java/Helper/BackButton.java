package Helper;

import Application.View.DashBoardPage;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class BackButton extends JButton {
    private static final Deque<Component> storage = new ArrayDeque<>();
    private static final Deque<Component> currentPanel = new ArrayDeque<>();
    private static Component lastComponent;

    public BackButton() {
        initActionListener();
        initDefaultValues();
    }

    public static void addComponent(Component page) {
        if (page instanceof JFrame) {
            if (!(page == storage.peek())) {
                storage.addFirst(page);
            }
        } else {
            if (currentPanel.isEmpty()) {
                currentPanel.add(page);
            } else {
                storage.addFirst(currentPanel.pop());
                currentPanel.addFirst(page);
            }
        }
    }

    private Component getLastFrame() {
        return storage.pop();
    }

    private void initActionListener() {
        addActionListener(event -> {
            if (!storage.isEmpty()) {
                lastComponent = getLastFrame();
                if (lastComponent instanceof JFrame) {
                    JFrame lastFrame = (JFrame) lastComponent;
                    lastFrame.setVisible(true);
                } else {
                    JPanel lastPanel = (JPanel) lastComponent;
                    DashBoardPage.switchPane(lastPanel);
                }
            }
        });
    }

    private void initDefaultValues() {
        setText("Back");
        setPreferredSize(new Dimension(70, 20));
    }
}
