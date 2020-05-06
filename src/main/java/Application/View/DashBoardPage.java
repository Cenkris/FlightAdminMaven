package Application.View;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashBoardPage extends JFrame {
    private HomePage homePage = new HomePage();
    private AccountPage accountPage = new AccountPage();
    private JPanel contentPanel;
    private JMenuBar menuBar;
    private JMenu optionsMenu;
    private JMenuItem homeMenuItem, accountMenuItem, logOutMenuItem;

    public DashBoardPage() {
        initContentPanel();
        switchPane(homePage);
        initOptionsMenu();
        initDefaultValues();
    }

    private void switchPane(JPanel panel) {
        contentPanel.removeAll();
        contentPanel.add(panel);
        contentPanel.revalidate();
        contentPanel.repaint();
        pack();
    }

    private void initContentPanel() {
        contentPanel = new JPanel();
//        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
//        contentPanel.setLayout(new FlowLayout());
        setContentPane(contentPanel);
    }

    private void initOptionsMenu() {
        //homeItem
        homeMenuItem = new JMenuItem("Home");
        MouseAdapter onClickHomeMouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                switchPane(homePage);
            }
        };
        homeMenuItem.addMouseListener(onClickHomeMouseAdapter);

        //accountItem
        accountMenuItem = new JMenuItem("Account");
        MouseAdapter onClickAccountMouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                switchPane(accountPage);
            }
        };
        accountMenuItem.addMouseListener(onClickAccountMouseAdapter);

        //logoutItem
        logOutMenuItem = new JMenuItem("Log out");
        MouseAdapter onClickLogOutMouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                LoginPage loginPage = new LoginPage();
                loginPage.setVisible(true);
                dispose();
            }
        };
        logOutMenuItem.addMouseListener(onClickLogOutMouseAdapter);

        //options menu
        optionsMenu = new JMenu("Options");
        optionsMenu.add(homeMenuItem);
        optionsMenu.add(accountMenuItem);
        optionsMenu.add(logOutMenuItem);

        //add menu to menu bar
        menuBar = new JMenuBar();
        menuBar.add(optionsMenu);
        setJMenuBar(menuBar);
    }

    private void initDefaultValues() {
//        setLayout(new GridLayout(2, 1));
        setName("DashBoard");
        setTitle("DashBoard Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 500);
        pack();
        setLocationRelativeTo(null);
        setVisible(true); //TODO: Delete after testing dashboard
        setResizable(false);
    }
}
