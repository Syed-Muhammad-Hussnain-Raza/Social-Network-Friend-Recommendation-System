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

    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Connection connection = databaseConnection.getConnection();

        if (connection != null) {
            try {
                Statement statement =connection.createStatement();

                String query = "SELECT * FROM users";
                ResultSet resultSet =statement.executeQuery(query);
                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getInt("user_id"));
                    System.out.println("Username: " + resultSet.getString("username"));
                    System.out.println("Email: " + resultSet.getString("email"));
                    System.out.println("Phone: " + resultSet.getString("phone"));
                    System.out.println("-------------------------");
                }

                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
