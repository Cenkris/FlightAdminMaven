package Application.Service;

import Application.Model.Audit;
import Application.Model.AuditEvent;
import Application.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class AuditDAO {

    private PreparedStatement insertAuditQuery;
    private PreparedStatement selectLastEntryQuery, selectLastTenActionsByUser;

    public AuditDAO(Connection connection) {
        try {

            insertAuditQuery = connection.prepareStatement("INSERT INTO audit VALUES (null, ?, ?, ?)");
            selectLastEntryQuery = connection.prepareStatement("SELECT * FROM audit WHERE id = (SELECT MAX(id) FROM audit)");
            selectLastTenActionsByUser = connection.prepareStatement("SELECT * FROM audit WHERE user = ?");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void saveEvent(User user, Audit audit) {
        try {
            insertAuditQuery.setString(1, user.getUsername());
            insertAuditQuery.setString(2, audit.toString());

            Date date = Date.from(Instant.now());
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

            insertAuditQuery.setString(3, formatter.format(date));
            insertAuditQuery.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public String getLastEntryInAudit() {
        ResultSet result;
        String resultAction = "";
        try {
            result = selectLastEntryQuery.executeQuery();
            while (result.next()) {
                resultAction = result.getString("action");
            }
            return resultAction;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return resultAction;
    }

    public List<AuditEvent> getLastTenEventsDescByUserReversed(User user) {

        List<AuditEvent> resultList = new ArrayList<>();

        try {
            selectLastTenActionsByUser.setString(1, user.getUsername());
            ResultSet result = selectLastTenActionsByUser.executeQuery();
            while (result.next()) {
                AuditEvent event = new AuditEvent(
                        result.getString("user"),
                        result.getString("action"),
                        result.getString("time_stamp"));

                resultList.add(event);
            }
            Collections.reverse(resultList);
            return resultList;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Collections.emptyList();
    }
}
