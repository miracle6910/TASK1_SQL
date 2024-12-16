package jm.task.core.jdbc.util;
import jm.task.core.jdbc.model.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import java.sql.*;

import java.util.Properties;


public class Util {

    private static final String url = "jdbc:postgresql://localhost:5432/java";
    private static final String user = "sa";
    private static final String password = "55546123qwerty";

    private static SessionFactory sessionFactory = initSessionFactory();

    private static SessionFactory initSessionFactory() {
        try {
            Properties properties = new Properties();
            properties.setProperty("hibernate.connection.url", url);
            properties.setProperty("hibernate.connection.username", user);
            properties.setProperty("hibernate.connection.password", password);
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
            properties.setProperty("hibernate.hbm2ddl.auto", "create");

            return new Configuration()
                    .addProperties(properties)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ExceptionInInitializerError("Initial SessionFactory creation failed." + e);
        }
    }
    public static Session getSession() throws HibernateException {
        return sessionFactory.openSession();
    }
    public static void close(Session session) {
        if (session != null) {
            session.close();
        }
    }
}
//   ___JDBC___
//    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection(
//                "jdbc:sqlserver://localhost:55776;databaseName=excelParser;encrypt=true;trustServerCertificate=true;",
//                "miracle",
//                "55546123qwertY");
//    }

//    private static final String PROPERTIES_FILE = "db.properties";
//    private static String url;
//    private static String username;
//    private static String password;
//
//
//
//    private static SessionFactory sessionFactory;
//
//    static {
//        loadPropertyConnection();
//        loadSessionFactory();
//    }
//
//    private static void loadSessionFactory() {
//        try {
//            Configuration configuration = new Configuration();
//            configuration.setProperty("hibernate.connection.url", url);
//            configuration.setProperty("hibernate.connection.username", username);
//            configuration.setProperty("hibernate.connection.password", password);
//            configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
//            configuration.setProperty("hibernate.hbm2ddl.auto", "update");
//            sessionFactory = configuration.buildSessionFactory();
//        } catch (Throwable e) {
//            System.err.println("Создание SessionFactory не удалось " + e.getMessage());
//            throw new ExceptionInInitializerError(e);
//        }
//    }
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    private static void loadPropertyConnection() {
//        Properties properties = new Properties();
//        try (InputStream input = Util.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)) {
//            if (input == null) {
//                System.err.println("Файл конфигурации отсутствует: " + PROPERTIES_FILE);
//                return;
//            }
//            properties.load(input);
//            url = properties.getProperty("db.url");
//            username = properties.getProperty("db.username");
//            password = properties.getProperty("db.password");
//        } catch (IOException e) {
//            System.err.println("Ошибка чтения файла конфигурации БД: " + e.getMessage());
//        }
//    }
//    public static Connection getConnection() {
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(url, username, password);
//            if (connection == null) {
//                throw new SQLException("Нет соединения с БД");
//            }
//        } catch (SQLException e) {
//            System.err.println("Ошибка соединения: " + e.getMessage());
//        }
//        return connection;
//    }
//
//}
//
//
//
//
//
// ___from XML (NOT WORKING)___
//public class Util {
//    private static final SessionFactory sessionFactory;
//
//    static {
//        try {
//            Configuration configuration = new Configuration().configure();
//            sessionFactory = configuration.buildSessionFactory();
//        } catch (Throwable e) {
//            System.err.println("Initial SessionFactory creation failed." + e);
//            throw new ExceptionInInitializerError(e);
//        }
//    }
//
//    public static SessionFactory getSessionFactory() {
//        return sessionFactory;
//    }
//
//    public static void shutdown() {
//        getSessionFactory().close();
//    }
//}


