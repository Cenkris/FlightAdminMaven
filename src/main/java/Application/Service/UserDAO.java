package Application.Service;

import Application.Model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    private PreparedStatement insertQuery;
    private PreparedStatement selectUserByNameQuery, selectUserByEmailQuery;
    private PreparedStatement updateUsernameQuery, updateEmailQuery;

    public UserDAO(Connection connection) {
        try {
            insertQuery = connection.prepareStatement("INSERT INTO users VALUES (null, ?, ?, ?)");
            selectUserByNameQuery = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            selectUserByEmailQuery = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            updateUsernameQuery = connection.prepareStatement("UPDATE users SET username = ? WHERE username = ?");
            updateEmailQuery = connection.prepareStatement("UPDATE users SET email = ? where email = ?");
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

    public boolean updateUsername(String oldUsername, String newUsername) {
        try {
            updateUsernameQuery.setString(1, newUsername);
            updateUsernameQuery.setString(2, oldUsername);
            return updateUsernameQuery.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean updateEmail(String oldEmail, String newEmail) {
        try {
            updateUsernameQuery.setString(1, newEmail);
            updateUsernameQuery.setString(2, oldEmail);
            return updateUsernameQuery.executeUpdate() != 0;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}

