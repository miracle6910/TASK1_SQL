package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static UserService userService = new UserServiceImpl();

    public static void main(String[] args) throws SQLException {

        userService.createUsersTable();
        System.out.println("Таблица Users создана.");


        userService.saveUser("Иван", "Иванов", (byte) 25);
        userService.saveUser("Эльван", "Эльванов", (byte) 30);
        userService.saveUser("Хайван", "Хайванов", (byte) 28);
        userService.saveUser("Байван", "Байванов", (byte) 28);
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