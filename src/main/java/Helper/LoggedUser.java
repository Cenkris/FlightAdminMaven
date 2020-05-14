package Helper;

import Application.Model.User;

public class LoggedUser {
    public static User loggedUser;

    public static User getLoggedUser(){
        return loggedUser;
    }
}
