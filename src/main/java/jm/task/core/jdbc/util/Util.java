package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(
//                "jdbc:sqlserver://localhost:55776;databaseName=excelParser;encrypt=true;trustServerCertificate=true;",
//                "miracle",
//                "55546123qwertY");
//    }
public class Util {
    private static final String PROPERTIES_FILE = "db.properties";
    private static String url;
    private static String username;
    private static String password;

    static {
        loadPropertyConnection();
    }

    private static void loadPropertyConnection() {
        Properties properties = new Properties();
        try (InputStream input = Util.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
            if (input == null) {
                System.err.println("Файл конфигурации отсутствует: " + PROPERTIES_FILE);
                return;
            }
            properties.load(input);
            url = properties.getProperty("db.url");
            username = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла конфигурации БД: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            if (connection == null) {
                throw new SQLException("Нет соединения с БД");
            }
        } catch (SQLException e) {
            System.err.println("Ошибка соединения: " + e.getMessage());
        }
        return connection;
    }
}









