package Helper;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;

public class BackButton extends JButton {
    private static final Deque<JFrame> storage = new ArrayDeque<>();
    private static JFrame lastFrame;

    public BackButton() {
        initActionListener();
        initDefaultValues();
    }

    public static void addPage(JFrame page) {
        storage.addFirst(page);
        System.out.println("Page added!");
        System.out.println(page);
    }

    private JFrame getLastFrame() {
        return storage.pop();
    }

    private void initActionListener() {
        addActionListener(event -> {
            if (!storage.isEmpty()) {
                lastFrame = getLastFrame();
                lastFrame.setVisible(true);
            }
        });
    }

    private void initDefaultValues() {
        setText("Back");
        setPreferredSize(new Dimension(70, 20));
    }
}
