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

public class UserDAO {

    private PreparedStatement insertQuery;
    private PreparedStatement selectUserByNameQuery, selectUserByEmailQuery;
    private PreparedStatement updateUsernameQuery, updateEmailQuery, updatePasswordQuery;


    public UserDAO(Connection connection) {
        try {
            insertQuery = connection.prepareStatement("INSERT INTO users VALUES (null, ?, ?, ?)");
            selectUserByNameQuery = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            selectUserByEmailQuery = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            updateUsernameQuery = connection.prepareStatement("UPDATE users SET username = ? WHERE username = ?");
            updateEmailQuery = connection.prepareStatement("UPDATE users SET email = ? WHERE email = ?");
            updatePasswordQuery = connection.prepareStatement("UPDATE users SET password = ? WHERE username = ?");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void insertUser(User user) {
        try {
            insertQuery.setString(1, user.getUsername());
            insertQuery.setString(2, user.getPassword());
            insertQuery.setString(3, user.getEmail());
            insertQuery.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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

    public void updateUsername(String oldUsername, String newUsername) {
        try {
            updateUsernameQuery.setString(1, newUsername);
            updateUsernameQuery.setString(2, oldUsername);
            updateUsernameQuery.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateEmail(String oldEmail, String newEmail) {
        try {
            updateEmailQuery.setString(1, newEmail);
            updateEmailQuery.setString(2, oldEmail);
            updateEmailQuery.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updatePassword(User user, String newPassword) {
        try {
            updatePasswordQuery.setString(1, newPassword);
            updatePasswordQuery.setString(2, user.getUsername());
            updatePasswordQuery.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

