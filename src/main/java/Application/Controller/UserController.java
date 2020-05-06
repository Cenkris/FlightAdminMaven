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

    public User getUserByEmail(String email){
        return userDAO.selectUserByEmail(email);
    }

    public boolean isNewUser(String username){
        return userDAO.selectUserByName(username).isEmpty();
    }

    public boolean isNewEmail(String email) {
        return userDAO.selectUserByEmail(email).isEmpty();
    }
}
