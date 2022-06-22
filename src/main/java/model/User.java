package model;

import java.sql.SQLException;

public interface User {
    boolean isLoginSuccessful(String mailId, String password);

    boolean isRegistrationSuccessful(String name, String number, String mailId, String password);
}
