package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;
    private static volatile Util instance;

    private Util() {

    }
    public static Util getInstance() {
        if (instance == null) {
            synchronized (Util.class) {
                if (instance == null) {
                    instance = new Util();
                }
            }
        }
        return instance;
    }

    public static Connection getConnection() {
       try {
           return connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
       } catch (SQLException e) {
           System.out.println("Соединение не установлено!");
       }
       return connection;
    }
}
