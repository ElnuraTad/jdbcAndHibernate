package peaksoft.dao;

import peaksoft.model.User;
import peaksoft.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class UserDaoJdbcImpl implements UserDao {

    public UserDaoJdbcImpl() {

    }

    public void createUsersTable() {
        String CREATE_SQL = "CREATE TABLE if NOT EXISTS users" +
                "(id serial, " +
                "name VARCHAR (225) NOT NULL, " +
                "last_name VARCHAR(255) NOT NULL," +
                "age INT NOT NULL," +
                "PRIMARY KEY (id))";
        try (Connection conn = Util.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(CREATE_SQL);
            System.out.println("Create table");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String DROP_SQL = "DROP TABLE if EXISTS users";
        try (Connection conn = Util.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(DROP_SQL);
            System.out.println("Drop table");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String SAVE_SQL = "INSERT INTO users(name, last_name, age) VALUES (?, ?, ?)";
        try (Connection conn = Util.connect();
             PreparedStatement statement = conn.prepareStatement(SAVE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println(name + " " + "add to dataBase");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String REMOVE_USERS_SQL = "DELETE FROM  users WHERE id = ?";
        try (Connection conn = Util.connect();
             PreparedStatement statement = conn.prepareStatement(REMOVE_USERS_SQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println(id + " " + "User delete by id");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        String GET_ALL_SQL = "SELECT * FROM users";
        List<User> userList = new ArrayList<>();
        try (Connection conn = Util.connect();
             PreparedStatement stmt = conn.prepareStatement(GET_ALL_SQL);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getLong("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("last_name"));
                user.setAge(rs.getByte("age"));
                userList.add(user);
                System.out.println(userList);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return userList;
    }

    public void cleanUsersTable() {
        String CLEAN_SQL = "truncate users";
        try (Connection conn = Util.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(CLEAN_SQL);
            System.out.println("Cleaned users table");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
}








