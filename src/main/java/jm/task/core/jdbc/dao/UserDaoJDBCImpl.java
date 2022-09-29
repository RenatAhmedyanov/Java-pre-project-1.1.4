package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            new Util().getConnection()
                    .prepareStatement("CREATE TABLE IF NOT EXISTS utable (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age TINYINT(100), PRIMARY KEY (id))")
                    .executeUpdate();
        } catch (SQLException e) {
            System.out.println("Table creation error");
        }
    }

    public void dropUsersTable() {
        try {
            new Util().getConnection()
                    .prepareStatement("DROP TABLE IF EXISTS utable")
                    .executeUpdate();
        } catch (SQLException e) {
            System.out.println("Сan't remove table");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            new Util().getConnection()
                    .prepareStatement("INSERT INTO utable (name, lastName, age) VALUES ('"+name+"', '"+lastName+"', '"+age+"')")
                    .executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Сan't add user");
        }
    }

    public void removeUserById(long id) {
        try {
            new Util().getConnection()
                    .prepareStatement("DELETE FROM utable WHERE id = '"+id+"'")
                    .executeUpdate();
        } catch (SQLException e) {
            System.out.println("Сan't remove user");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            ResultSet resultSet = new Util().getConnection()
                    .createStatement()
                    .executeQuery("SELECT * FROM utable");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            System.out.println("Сan't get user list");
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            new Util().getConnection()
                    .prepareStatement("DELETE FROM utable")
                    .executeUpdate();
        } catch (SQLException e) {
            System.out.println("Can't clear users table");
        }
    }
}
