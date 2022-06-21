package model;

import java.sql.SQLException;

public interface User {
    boolean isLoginSuccessful(String mail_Id, String password) throws SQLException;
    boolean isRegistrationSuccessful(String name, String number, String mail_id, String password);
}
