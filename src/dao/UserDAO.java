package dao;

import database.DatabaseConnection;
import model.User;

import java.sql.*;

public class UserDAO {

    public User findByUsernameAndPassword(String username, String password) throws Exception {
        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement stmt = conn.prepareStatement(
                "SELECT * FROM users WHERE username=? AND password=?");

        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setRole(rs.getString("role"));
            conn.close();
            return user;
        }

        conn.close();
        return null;
    }

    public void createUser(User user) throws Exception {
        Connection conn = DatabaseConnection.getConnection();

        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO users (username, password, role) VALUES (?, ?, ?)");

        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        stmt.setString(3, user.getRole());

        stmt.executeUpdate();
        conn.close();
    }
}