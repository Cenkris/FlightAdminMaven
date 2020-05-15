package Application.Service;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.time.temporal.ChronoUnit.MINUTES;

public class LogoutService {

    public static LocalTime lastAction = LocalTime.parse("19:20:00");

    public LogoutService() {
        startTimer();
        logAction();
    }

    private static void startTimer() {
        ExecutorService service = Executors.newFixedThreadPool(1);

        Runnable counter = () -> {
            while (MINUTES.between(lastAction, LocalTime.now()) < 15) {
                System.out.println(MINUTES.between(lastAction, LocalTime.now()));
                try {
                    long timeDifferenceInSeconds = (15 - MINUTES.between(lastAction, LocalTime.now())) * 1000;
                    System.out.println("Waiting " + timeDifferenceInSeconds + "ms");
                    Thread.sleep(timeDifferenceInSeconds);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            logout();
        };

        service.submit(counter);
        service.shutdown();
    }

    private static void logout() {
        System.out.println("GG");
    }

    public static void logAction() {
        lastAction = LocalTime.now();
    }

    public static void main(String[] args) {
        startTimer();
    }
}
