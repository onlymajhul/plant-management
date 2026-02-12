package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {

    private static final String URL = "jdbc:h2:./gartenDB";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static Connection getConnection() throws Exception {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        createTables(conn);
        return conn;
    }

    private static void createTables(Connection conn) throws Exception {
        Statement stmt = conn.createStatement();

        stmt.execute("""
            CREATE TABLE IF NOT EXISTS users(
                id INT AUTO_INCREMENT PRIMARY KEY,
                username VARCHAR(50),
                password VARCHAR(50),
                role VARCHAR(20)
            )
        """);

        stmt.execute("""
            CREATE TABLE IF NOT EXISTS pflanzen(
                id INT AUTO_INCREMENT PRIMARY KEY,
                name VARCHAR(50),
                ertrag INT,
                dauer INT,
                groesse DOUBLE
            )
        """);

        stmt.execute("""
            CREATE TABLE IF NOT EXISTS garten(
                id INT AUTO_INCREMENT PRIMARY KEY,
                user_id INT,
                pflanze_id INT
            )
        """);

        // Default Admin
        stmt.execute("""
            MERGE INTO users (id, username, password, role)
            KEY(id)
            VALUES (1, 'admin', 'admin', 'ADMIN')
        """);
    }
}