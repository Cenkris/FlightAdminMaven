package Application.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class FlightDAO {

    private PreparedStatement insertQuery;
    private PreparedStatement selectFlightBySourceQuery, selectFlightByDestinationQuery, selectFlightByPriceQuery;
    private PreparedStatement removeFlightQuery;

    public FlightDAO(Connection connection) {

    }
}
