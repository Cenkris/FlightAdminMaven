package Application.View;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashBoardPage extends JFrame {
    private HomePage homePage;
    private MyAccountPage accountPage;
    private JMenuBar menuBar;
    private JMenu optionsMenu;
    private JMenuItem homeMenuItem, accountMenuItem, logOutMenuItem;

    DashBoardPage() {
        initOptionsMenu();
        initDefaultValues();
    }

    private void initOptionsMenu() {
        //homeItem
        homeMenuItem = new JMenuItem("Home");
        MouseAdapter onClickHomeMouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                homePage.setVisible(true);
                accountPage.setVisible(false);
            }
        };
        homeMenuItem.addMouseListener(onClickHomeMouseAdapter);

        //accountItem
        accountMenuItem = new JMenuItem("Account");
        MouseAdapter onClickAccountMouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                accountPage.setVisible(true);
                homePage.setVisible(false);
            }
        };
        homeMenuItem.addMouseListener(onClickAccountMouseAdapter);

        //logoutItem
        logOutMenuItem = new JMenuItem("Log out");
        MouseAdapter onClickLogOutMouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
        menuBar.add(optionsMenu);
        add(menuBar);
    }

    private void initDefaultValues() {
//        setLayout(new GridLayout(6, 1));
        setTitle("DashBoard Page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 500);
//        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
