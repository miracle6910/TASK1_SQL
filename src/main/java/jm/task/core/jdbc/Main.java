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
        UserService userService = new UserServiceImpl();


        userService.dropUsersTable();
        userService.createUsersTable();
        System.out.println("Таблица Users создана.");


        userService.saveUser("User1", "Ivanov1", (byte) 25);
        userService.saveUser("User2", "Ivanov2", (byte) 30);
        userService.saveUser("User3", "Ivanov3", (byte) 28);
        userService.saveUser("User4", "Ivanov4", (byte) 28);
        System.out.println("Users добавлены.");


        List<User> users = userService.getAllUsers();
        System.out.println("Список Users:");
        for (User user : users) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        System.out.println("Таблица Users очищена. Количество Users: " + userService.getAllUsers().size());

        userService.dropUsersTable();
        System.out.println("Таблица Users удалена.");
}
}