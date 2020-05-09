package Application.Service;

import Application.Model.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlightDAO {

    private PreparedStatement insertQuery;
    private PreparedStatement selectSameRouteQuery, selectAllFlightsQuery;
    private PreparedStatement removeFlightQuery;

    public FlightDAO(Connection connection) {
        try {
            insertQuery = connection.prepareStatement("INSERT INTO flights VALUES (null, ?, ?, ?, ?, ?, ?)");
            selectSameRouteQuery = connection.prepareStatement("SELECT * FROM flights WHERE source = ? AND destination = ?");
            removeFlightQuery = connection.prepareStatement("DELETE FROM flights WHERE source = ? AND destination = ?");
            selectAllFlightsQuery = connection.prepareStatement("SELECT * FROM flights");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertFlight(Flight flight) {
        try {
            insertQuery.setString(1, flight.getSource());
            insertQuery.setString(2, flight.getDestination());
            insertQuery.setString(3, flight.getDepartureHour());
            insertQuery.setString(4, flight.getLandingHour());
            insertQuery.setString(5, flight.getDays());
            insertQuery.setInt(6, flight.getPrice());
            insertQuery.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean isRouteAlreadyInDB(Flight flight) {
        try {
            selectSameRouteQuery.setString(1, flight.getSource());
            selectSameRouteQuery.setString(2, flight.getDestination());
            ResultSet resultSet = selectSameRouteQuery.executeQuery();
            System.out.println(resultSet.next());
            return resultSet.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public void removeFlight(Flight flight) {
        try {
            removeFlightQuery.setString(1, flight.getSource());
            removeFlightQuery.setString(2, flight.getDestination());
            removeFlightQuery.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Flight> selectAllFlights() {
        List<Flight> flightList = new ArrayList<>();
        try {
            ResultSet result = selectAllFlightsQuery.executeQuery();
            while (result.next()) {
                flightList.add(new Flight(
                        result.getString("source"),
                        result.getString("destination"),
                        result.getString("departure_hour"),
                        result.getString("landing_hour"),
                        result.getString("days"),
                        result.getInt("price")));
            }
            return flightList;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }
}
