package Application.Controller;

import Application.Model.Audit;
import Application.Model.AuditEvent;
import Application.Model.User;
import Application.Service.AuditDAO;
import DataBase.DatabaseConnection;

import java.sql.Connection;
import java.util.List;

public class AuditController {
    static {
        Connection connection = DatabaseConnection.getConnection();
        auditDAO = new AuditDAO(connection);
    }

    private static final AuditDAO auditDAO;

    public static void saveEvent(User user, Audit audit) {
        auditDAO.saveEvent(user, audit);
    }

    public static String getLastActionName() {
        return auditDAO.getLastEntryInAudit();
    }

    public static boolean isLastAction(Audit audit) {
        return auditDAO.getLastEntryInAudit().equals(audit.toString());
    }

    public static List<AuditEvent> getLastTenActions(User user) {
        return auditDAO.getLastTenEventsDescByUserReversed(user);
    }
}
