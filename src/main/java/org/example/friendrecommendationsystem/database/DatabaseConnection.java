package org.example.friendrecommendationsystem.database;

import java.sql.*;

public class DatabaseConnection {
    String URL = "jdbc:mysql://localhost:3306/friend_recommendation_system";
    String USER = "root";
    String PASSWORD = "admin";

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
