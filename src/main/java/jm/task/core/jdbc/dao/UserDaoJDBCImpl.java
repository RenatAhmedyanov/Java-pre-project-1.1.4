package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private Connection connection = Util.getInstance().getConnection();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try {
            connection.prepareStatement("CREATE TABLE IF NOT EXISTS userTable (id BIGINT NOT NULL AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age TINYINT(100), PRIMARY KEY (id))")
                    .executeUpdate();
        } catch (SQLException e) {
            System.out.println("Table creation error");
        }
    }

    public void dropUsersTable() {
        try {
            connection.prepareStatement("DROP TABLE IF EXISTS userTable").executeUpdate();
        } catch (SQLException e) {
            System.out.println("Сan't remove table");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try {
            connection.prepareStatement("INSERT INTO userTable (name, lastName, age) VALUES ('"+name+"', '"+lastName+"', '"+age+"')").executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            System.out.println("Сan't add user");
        }
    }

    public void removeUserById(long id) {
        try {
            connection.prepareStatement("DELETE FROM userTable WHERE id = '"+id+"'").executeUpdate();
        } catch (SQLException e) {
            System.out.println("Сan't remove user");
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        try {
            ResultSet resultSet = connection.createStatement().executeQuery("SELECT * FROM userTable");
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                userList.add(user);
                System.out.println(user);
            }
        } catch (SQLException e) {
            System.out.println("Сan't get user list");
        }
        return userList;
    }

    public void cleanUsersTable() {
        try {
            connection.prepareStatement("DELETE FROM userTable").executeUpdate();
        } catch (SQLException e) {
            System.out.println("Can't clear users table");
        }
    }
}
