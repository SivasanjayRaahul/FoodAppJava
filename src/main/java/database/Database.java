package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static constants.Constants.mySqlDriver;

public class Database {
    public static final String JDBC_MYSQL_LOCALHOST = "jdbc:mysql://localhost:3306/sample";
    private static Connection connection;
    private Database() {}

    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName(mySqlDriver);
        if (connection == null) {
            connection = DriverManager.getConnection(
                    JDBC_MYSQL_LOCALHOST, "root", "Sruthiar");

        }
        return connection;
    }

}
