package Helper;

public class AccountConstraints {
    public static final String PASSWORD_VALIDATOR = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$";
    public static final String EMAIL_VALIDATOR = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{1,3}$";
}
