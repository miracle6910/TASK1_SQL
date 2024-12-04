package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:sqlserver://localhost:55776;databaseName=excelParser;encrypt=true;trustServerCertificate=true;",
                "miracle",
                "55546123qwertY");
    }
}







