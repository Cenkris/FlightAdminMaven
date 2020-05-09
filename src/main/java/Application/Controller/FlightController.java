package Application.Controller;

import Application.Model.Flight;
import Application.Service.FlightDAO;
import DataBase.DatabaseConnection;

import java.sql.Connection;

public class FlightController {
    private final FlightDAO flightDAO;

    public FlightController() {
        Connection connection = DatabaseConnection.getConnection();
        flightDAO = new FlightDAO(connection);
    }

    public void insertFlight(Flight flight) {
        flightDAO.insertFlight(flight);
    }
}
