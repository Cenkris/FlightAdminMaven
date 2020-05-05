package Application.Controller;

import Application.Model.User;
import Application.Service.UserDAO;
import DataBase.DatabaseConnection;

import java.sql.Connection;

public class UserController {
    private UserDAO userDAO;

    UserController(){
        Connection connection = DatabaseConnection.getConnection();
        userDAO = new UserDAO(connection);
    }

    public void insertUser(User user){
        userDAO.insertUser(user);
    }

    public User getUserByUsername(String username){
        return userDAO.selectUser(username);
    }
}
