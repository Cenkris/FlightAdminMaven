package Application.View;

import Application.Controller.AuditController;
import Application.Model.Audit;
import Application.Service.LogoutService;
import Helper.LoggedUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DashBoardPage extends JFrame {
    private HomePage homePage = new HomePage();
    private AccountPage accountPage = new AccountPage();
    private JPanel contentPanel;
    private JMenuBar menuBar;
    private JMenu optionsMenu, settingsMenu;
    private JMenuItem homeMenuItem, accountMenuItem, logOutMenuItem, aboutMenuItem, changeLogOutTimerItem;


    public DashBoardPage() {
        initLogout();
        initContentPanel();
        switchPane(homePage);
        initOptionsMenu();
        initDefaultValues();
    }

    private void initLogout() {
        LogoutService logoutService = new LogoutService(this);
    }

    private void switchPane(JPanel panel) {
        String lastAction = AuditController.getLastActionName();
        String componentName = "";


        if (panel instanceof AccountPage) {
            if (!lastAction.equals(Audit.ACCOUNT.toString()))
                AuditController.saveEvent(LoggedUser.getLoggedUser(), Audit.ACCOUNT);
        } else {
            if (!lastAction.equals(Audit.HOME.toString()))
                AuditController.saveEvent(LoggedUser.getLoggedUser(), Audit.HOME);
        }

        Component[] components = contentPanel.getComponents();
        for (Component component : components) {
            componentName = component.getName();
        }

        if (!componentName.equals(panel.getName())) {
            contentPanel.removeAll();
            contentPanel.add(panel);
            contentPanel.revalidate();
            contentPanel.repaint();
            pack();
        }
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
                LogoutService.logAction();
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
                AuditController.saveEvent(LoggedUser.getLoggedUser(), Audit.LOGOUT);
                dispose();
            }
        };
        logOutMenuItem.addMouseListener(onClickLogOutMouseAdapter);


        //changeLogOut
        changeLogOutTimerItem = new JMenuItem("Change log out timer");
        MouseAdapter onClickChangeTimerMouseAdapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                String inputText = JOptionPane.showInputDialog("Input the number of minutes for auto logout function. Default is 15. " +
                        "\nPlease make it natural number > 0");

                if (inputText != null) {
                    if (inputText.matches("\\d+")) {
                        LogoutService.setWaitTime(Integer.parseInt(inputText));
                        JOptionPane.showMessageDialog(null, "A new logout timer is set.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid input");
                    }
                }
            }
        };
        changeLogOutTimerItem.addMouseListener(onClickChangeTimerMouseAdapter);

        //options menu
        optionsMenu = new JMenu("Options");
        optionsMenu.add(aboutMenuItem);
        optionsMenu.add(homeMenuItem);
        optionsMenu.add(accountMenuItem);
        optionsMenu.add(logOutMenuItem);

        //settings menu
        settingsMenu = new JMenu("Experimental Settings");
        settingsMenu.add(changeLogOutTimerItem);

        //add menu to menu bar
        menuBar = new JMenuBar();
        menuBar.add(optionsMenu);
        menuBar.add(settingsMenu);
        setJMenuBar(menuBar);
    }

    private void initDefaultValues() {
        WindowAdapter windowAdapter = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (!AuditController.isLastAction(Audit.LOGOUT)) {
                    LogoutService.serviceShutDown();
                    AuditController.saveEvent(LoggedUser.getLoggedUser(), Audit.LOGOUT);
                }
            }
        };

        addWindowListener(windowAdapter);
        setName("DashBoard");
        setTitle("DashBoard Page");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 500);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
    }
}
