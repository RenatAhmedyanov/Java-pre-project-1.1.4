package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl user = new UserServiceImpl();
        user.createUsersTable();
        user.saveUser("Homer", "Simpson", (byte)39);
        user.saveUser("Marge", "Simpson", (byte)34);
        user.saveUser("Lisa", "Simpson", (byte)8);
        user.saveUser("Bart", "Simpson", (byte)10);
        user.getAllUsers();
        user.cleanUsersTable();
        user.dropUsersTable();
    }
}
