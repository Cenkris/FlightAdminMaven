package Application.Service;

import Application.Model.Audit;
import Application.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public class AuditDAO {

    private PreparedStatement insertAuditQuery;
    private PreparedStatement selectLastEntryQuery;

    public AuditDAO(Connection connection) {
        try {

            insertAuditQuery = connection.prepareStatement("INSERT INTO audit VALUES (null, ?, ?, ?)");
            selectLastEntryQuery = connection.prepareStatement("SELECT * FROM audit WHERE id=(SELECT MAX(id) FROM audit)");

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
}
