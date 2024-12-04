package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (Connection connection = Util.getConnection()) {
            UserDao userDao = new UserDaoJDBCImpl(connection);


            userDao.createUsersTable();
            System.out.println("Таблица Users создана.");



            userDao.saveUser("Имя1", "Фамилия1", (byte) 30);
            System.out.println("User с именем Имя1 добавлен в базу данных.");
            userDao.saveUser("Имя2", "Фамилия2", (byte) 31);
            System.out.println("User с именем Имя2 добавлен в базу данных.");
            userDao.saveUser("Имя3", "Фамилия3", (byte) 33);
            System.out.println("User с именем Имя3 добавлен в базу данных.");
            userDao.saveUser("пиздюк", "пиздец", (byte) 12);
            System.out.println("User с именем пиздюк добавлен в базу данных.");


            List<User> users = userDao.getAllUsers();
            System.out.println("Все пользователи в базе данных:");
            for (User user : users) {
                System.out.println(user);
            }


//            userDao.cleanUsersTable();
//            System.out.println("Таблица Users очищена.");
//

//            userDao.dropUsersTable();
//            System.out.println("Таблица Users удалена.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}