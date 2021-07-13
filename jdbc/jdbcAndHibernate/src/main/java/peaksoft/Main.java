package peaksoft;

import org.hibernate.HibernateException;
import peaksoft.model.User;
import peaksoft.service.UserService;
import peaksoft.service.UserServiceImpl;
import peaksoft.util.Util;

import java.sql.SQLException;
import java.util.List;


public class Main {
    public static void main(String[] args) throws HibernateException {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        User user1 = new User("Nazgul", "Kubanychbekova", (byte) 25);
        userService.saveUser(user1.getName(), user1.getLastName(), user1.getAge());

        User user2 = new User("Aida", "Imanalieva", (byte) 26);
        userService.saveUser(user2.getName(), user2.getLastName(), user2.getAge());

        User user3 = new User("Ulukbek", "Iunusov", (byte) 27);
        userService.saveUser(user3.getName(), user3.getLastName(), user3.getAge());

        User user4 = new User("Atabek", "Isakunov", (byte) 28);
        userService.saveUser(user4.getName(), user4.getLastName(), user4.getAge());

        User user5 = new User("Muktarbek", "Kubanov", (byte) 29);
        userService.saveUser(user5.getName(), user5.getLastName(), user5.getAge());

        List<User> userList = userService.getAllUsers();
        for (User user : userList) {
            System.out.println(user);
        }

        userService.cleanUsersTable();
        userService.dropUsersTable();

        Util.shutDown();
    }
}
