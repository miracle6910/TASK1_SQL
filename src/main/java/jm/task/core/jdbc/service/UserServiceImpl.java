package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
//import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import java.sql.*;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDaoHibernate = new UserDaoHibernateImpl();
//  private final UserDao userDao = new UserDaoJDBCImpl();


    public void createUsersTable() throws SQLException {
        userDaoHibernate.createUsersTable();

    }

    public void dropUsersTable() throws SQLException {
        userDaoHibernate.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDaoHibernate.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) throws SQLException {
        userDaoHibernate.removeUserById(id);

    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users =  userDaoHibernate.getAllUsers();
        for (User user : users) {
            System.out.println(user);
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        userDaoHibernate.cleanUsersTable();
    }
}