package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Homer", "Simpson", (byte)39);
        userService.saveUser("Marge", "Simpson", (byte)34);
        userService.saveUser("Lisa", "Simpson", (byte)8);
        userService.saveUser("Bart", "Simpson", (byte)10);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
