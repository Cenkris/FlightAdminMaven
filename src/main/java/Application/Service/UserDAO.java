package Application.Service;

import Application.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection connection;
    private PreparedStatement insertQuery;
    private PreparedStatement selectQuery;

    public UserDAO(Connection connection) {
        this.connection = connection;
        try {
            insertQuery = connection.prepareStatement("INSERT INTO users VALUES (null, ?, ?)");
            selectQuery = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean insertUser(User user) {
        try {
            insertQuery.setString(1, user.getUsername());
            insertQuery.setString(2, user.getPassword());
            return insertQuery.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public User selectUser(String name) {
        User user = new User();
        try {
            selectQuery.setString(1, name);
            ResultSet result = selectQuery.executeQuery();
            user.setUsername(result.getString("username"));
            user.setPassword(result.getString("password"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
}

