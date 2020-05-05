package Application.Controller;

import Application.Model.User;
import Application.Service.UserDAO;
import DataBase.DatabaseConnection;

import java.sql.Connection;

public class UserController {
    private UserDAO userDAO;

    public UserController(){
        Connection connection = DatabaseConnection.getConnection();
        userDAO = new UserDAO(connection);
    }

    public void insertUser(User user){
        userDAO.insertUser(user);
    }

    public User getUserByUsername(String username){
        return userDAO.selectUserByName(username);
    }

    public boolean isNewUser(String username){
        return userDAO.selectUserByName(username).getUsername() == null;
    }

    public boolean isNewEmail(String email) {
        return userDAO.selectUserByEmail(email).getEmail() == null;
    }
}
