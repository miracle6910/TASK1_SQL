package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.*;


public class UserDaoHibernateImpl implements UserDao {
    private static final Logger logger = LoggerFactory.getLogger(UserDaoHibernateImpl.class);
    private final SessionFactory sessionFactory;
    private Session session;

    public UserDaoHibernateImpl() {
        this.sessionFactory = Util.getSessionFactory();
        this.session = sessionFactory.openSession();
    }

    @Override
    public void createUsersTable() {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS users (" +
                    "id SERIAL PRIMARY KEY, " +
                    "name VARCHAR(30), " +
                    "lastName VARCHAR(30), " +
                    "age SMALLINT)";

            session.createNativeQuery(sql).executeUpdate();

            transaction.commit();
            logger.info("Таблица users создана успешно.");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Ошибка при создании таблицы Users", e);
        }
    }

    @Override
    public void dropUsersTable() {
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery(" DROP TABLE IF EXISTS users");
            query.executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {

            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            logger.info("Пользователь {} {} сохранен.", name, lastName);
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Ошибка при вставке пользователя", e);
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = session.load(User.class, id);
            session.delete(user);
            transaction.commit();
            logger.info("Пользователь с ID {} удален.", id);
        } catch (ObjectNotFoundException e) {
            logger.warn("Пользователь с ID {} не найден.", id);
            if (transaction != null) {
                transaction.rollback();
            }
        } catch (HibernateException e) {
            logger.error("Ошибка при удалении пользователя с ID " + id, e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query<User> query = session.createQuery("from User", User.class);
            userList = query.list();
            transaction.commit();
            logger.info("Получен список пользователей.");
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            logger.error("Ошибка при получении списка пользователей", e);
        }
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Query query = session.createSQLQuery("TRUNCATE TABLE users");
            query.executeUpdate();
            transaction.commit();
            logger.info("Таблица пользователей очищена с помощью TRUNCATE.");
        } catch (HibernateException e) {
            logger.error("Ошибка при очищении таблицы пользователей", e);
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
