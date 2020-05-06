package Audit;

import Application.Model.User;

public class UserAudit {
    public static User loggedUser;

    public static User getLoggedUser(){
        return loggedUser;
    }
}
