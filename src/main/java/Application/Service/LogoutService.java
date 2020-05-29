package Application.Service;

import Application.Controller.AuditController;
import Application.Model.Audit;
import Application.View.LoginPage;
import Helper.LoggedUser;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.time.temporal.ChronoUnit.MINUTES;

public class LogoutService {

    //TODO: close accounthistroy when autologout
    //TODO: newtable exceptions

    private static LocalTime lastAction;
    private static int waitTime = 2;
    private static ExecutorService service;
    private final JFrame baseFrame;

    public LogoutService(JFrame baseFrame) {
        this.baseFrame = baseFrame;
        logAction();
        startTimer();
    }

    public static void setWaitTime(int waitTime) {
        LogoutService.waitTime = waitTime;
    }

    public static void serviceShutDown() {
        service.shutdownNow();
    }

    private void startTimer() {

        //15 - last action  = difference
        //sleep difference
        // check again if >= 15 logout
        // else loop 15 or less

        Runnable counter = () -> {
            while (MINUTES.between(lastAction, LocalTime.now()) < waitTime) {
                try {
                    long timeDifferenceInMinutes = (waitTime - MINUTES.between(lastAction, LocalTime.now())) * 60000;
//                    System.out.println(Instant.now() + ": Waiting for: " + timeDifferenceInMinutes / 60000 + " minutes");
                    Thread.sleep(timeDifferenceInMinutes);
                } catch (InterruptedException e) {
                    System.exit(0);
                }
            }
//            System.out.println(Instant.now() + ": Should log out now!");
            logout();

        };
        service = Executors.newSingleThreadExecutor();
        service.execute(counter);
    }

    private void logout() {
        baseFrame.dispose();
        AuditController.saveEvent(LoggedUser.getLoggedUser(), Audit.LOGOUT);
        service.shutdownNow();
        closeAllOpenedWindows();
        LoginPage loginPage = new LoginPage();
        loginPage.setVisible(true);
    }

    private void closeAllOpenedWindows() {
        Window[] windows = Window.getWindows();
        for (Window window : windows) {
            window.setVisible(false);
        }
    }

    public static void logAction() {
        lastAction = LocalTime.now();
    }
}
