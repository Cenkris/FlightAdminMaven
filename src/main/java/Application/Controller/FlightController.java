package Application.Controller;

import Application.Service.FlightDAO;
import DataBase.DatabaseConnection;

import java.sql.Connection;

public class FlightController {
    private final FlightDAO flightDAO;

    FlightController(){
        Connection connection = DatabaseConnection.getConnection();
        flightDAO = new FlightDAO(connection);
    }
}
