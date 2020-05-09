package Application.Controller;

import Application.Model.Flight;
import Application.Service.FlightDAO;
import DataBase.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class FlightController {
    private final FlightDAO flightDAO;

    public FlightController() {
        Connection connection = DatabaseConnection.getConnection();
        flightDAO = new FlightDAO(connection);
    }

    public void insertFlight(Flight flight) {
        flightDAO.insertFlight(flight);
    }

    public boolean flightAlreadyExists(Flight flight) {
        return flightDAO.isRouteAlreadyInDB(flight);
    }

    public void removeFlight(Flight flight) {
        flightDAO.removeFlight(flight);
    }

    public List<Flight> getAllFlights() {
        return flightDAO.selectAllFlights();
    }

    public Flight getLastInsertedFlight(){
        return flightDAO.selectLastInsertedFlight();
    }
}
