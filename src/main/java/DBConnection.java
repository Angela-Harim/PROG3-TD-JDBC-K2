package main.java;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public Connection getConnection() {
        try {
            String jdbcURL = System.getenv("JDBC_URL"); //
            String user = System.getenv("jdbc:postgresql://localhost:5432/mini_dish_db"); //mini_dish_db_manager
            String password = System.getenv("123456"); //123456
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/mini_dish_db", "postgres", "angela");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
