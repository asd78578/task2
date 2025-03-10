package jm.task.core.jdbc.dao;

import com.mysql.cj.Session;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.SessionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();  // Создаем объект класса
    private Connection connection = util.getConnection();  // метод для получения соединения с базой данных


    public UserDaoJDBCImpl() {

    }

    // создать таблицу пользователей
    public void createUsersTable() {
        try (Statement statement = connection.createStatement()){
            // метод executeUpdate() интерфейса Statement используется для выполнения обновлений. Он выполняет такие команды, как INSERT, UPDATE, DELETE, CREATE TABLE, DROP TABLE.
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), last_name VARCHAR(50), age INT)");
        } catch (
                SQLException e) {
            e.printStackTrace();   // Создание таблицы для User(ов) — не должно приводить к исключению, если такая таблица уже существует
        }
    }

    // удаление таблицы пользователей
    public void dropUsersTable() {

        try (Statement statement = connection.createStatement()) {
            // Метод executeUpdate() в интерфейсе Statement используется для выполнения запросов, которые изменяют данные в таблице (операторы INSERT, UPDATE, DELETE).
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // сохранить пользователя
    // SQL запросы компилируются один раз
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (" +
                "name, last_name, age) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();   // SQL-запрос, который изменяют данные в базе
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        try {
//            SessionFactory sessionFactory = null;
//            Session session = (Session) sessionFactory.getCurrentSession();
//            User user = new User(null, name, lastName, age);
//            session.save(user);
//        } catch (Exception e){
//            e.printStackTrace();
//        }

    }

    // удалить Пользователя по идентификатору
    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM users WHERE id = ?")) {
            preparedStatement.setLong(1,id);

            preparedStatement.executeUpdate();  // возвращает количество изменённых строк.
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // получение пользователя из таблицы
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        // ResultSet resultSet позволяет извлекать данные из базы данных после выполнения запроса
        try (ResultSet resultSet=connection.createStatement()
                .executeQuery("SELECT * FROM users")){

            while (resultSet.next()){
                User user=new User(resultSet.getString("name")
                ,resultSet.getString("last_name"),resultSet.getByte("age"));
            user.setId(resultSet.getLong("id"));
            userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // очистить таблицу пользователей
    public void cleanUsersTable() {
        try (Statement statement= connection.createStatement()){
            statement.executeUpdate("TRUNCATE TABLE users");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
