package model;

import repository.FoodAppRepoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImpl implements User {

    @Override
    public boolean isLoginSuccessful(String mail_Id, String password) throws SQLException {
        ResultSet resultSet = new FoodAppRepoImpl().isLoginSuccessful(mail_Id, password);
        return resultSet.next();
    }

    @Override
    public boolean isRegistrationSuccessful(String name, String number, String mail_id, String password) {
        if (name.equals("") || number.equals("") || mail_id.equals("") || password.equals("") || number.length() != 10) {
            new FoodAppRepoImpl().registerUser(name, number, mail_id, password);
            return true;
        } else {
            return false;
        }
    }
}
