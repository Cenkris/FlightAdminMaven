package Application.Service;

import Application.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private Connection connection;
    private PreparedStatement insertQuery;
    private PreparedStatement selectUserByNameQuery, selectUserByEmailQuery;

    public UserDAO(Connection connection) {
        this.connection = connection;
        try {
            insertQuery = connection.prepareStatement("INSERT INTO users VALUES (null, ?, ?, ?)");
            selectUserByNameQuery = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            selectUserByEmailQuery = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public boolean insertUser(User user) {
        try {
            insertQuery.setString(1, user.getUsername());
            insertQuery.setString(2, user.getPassword());
            insertQuery.setString(3, user.getEmail());
            return insertQuery.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public User selectUserByName(String name) {
        User user = new User();
        try {
            selectUserByNameQuery.setString(1, name);
            ResultSet result = selectUserByNameQuery.executeQuery();
            if (result.next()) {
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }

    public User selectUserByEmail(String email) {
        User user = new User();
        try {
            selectUserByEmailQuery.setString(1, email);
            ResultSet result = selectUserByEmailQuery.executeQuery();
            if (result.next()) {
                user.setUsername(result.getString("username"));
                user.setPassword(result.getString("password"));
                user.setEmail(result.getString("email"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
}

