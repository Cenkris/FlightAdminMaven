package Application.View;

import Application.Controller.UserController;
import Application.Model.Audit;
import Audit.UserAudit;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashBoardPage extends JFrame {
    private HomePage homePage = new HomePage();
    private AccountPage accountPage = new AccountPage();
    private JPanel contentPanel;
    private JMenuBar menuBar;
    private JMenu optionsMenu;
    private JMenuItem homeMenuItem, accountMenuItem, logOutMenuItem, aboutMenuItem;
    private final UserController userController = new UserController();

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
        setContentPane(contentPanel);
    }

    private void initOptionsMenu() {
        //aboutItem
        aboutMenuItem = new JMenuItem("About");
        MouseAdapter onClickAboutMouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                AboutPage aboutPage = new AboutPage();
                aboutPage.setVisible(true);
            }
        };
        aboutMenuItem.addMouseListener(onClickAboutMouseAdapter);

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
                userController.saveEvent(UserAudit.getLoggedUser(), Audit.LOGOUT);
                dispose();
            }
        };
        logOutMenuItem.addMouseListener(onClickLogOutMouseAdapter);

        //options menu
        optionsMenu = new JMenu("Options");
        optionsMenu.add(aboutMenuItem);
        optionsMenu.add(homeMenuItem);
        optionsMenu.add(accountMenuItem);
        optionsMenu.add(logOutMenuItem);

        //add menu to menu bar
        menuBar = new JMenuBar();
        menuBar.add(optionsMenu);
        setJMenuBar(menuBar);
    }

    private void initDefaultValues() {
        setName("DashBoard");
        setTitle("DashBoard Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 500);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
