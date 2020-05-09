package Application.Service;

import Application.Model.Flight;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FlightDAO {

    private PreparedStatement insertQuery;
    private PreparedStatement selectFlightBySourceQuery, selectFlightByDestinationQuery, selectFlightByPriceQuery;
    private PreparedStatement removeFlightQuery;

    public FlightDAO(Connection connection) {
        try {
            insertQuery = connection.prepareStatement("INSERT INTO flights VALUES (null, ?, ?, ?, ?, ?, ?)");
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
}
