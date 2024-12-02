package org.example.friendrecommendationsystem.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    public static boolean isValidLogin(String username, String password) {
        boolean isValid = false;

        String query = "SELECT password FROM users WHERE BINARY username = ?";

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String storedPassword = resultSet.getString("password");

                // Compare the provided password with the stored password
                if (storedPassword.equals(password)) {
                    isValid = true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return isValid;
    }

    public static boolean addPostToDatabase(String title, String content, int userId, String username) {
        String query = "INSERT INTO posts (title, content, user_id, username) VALUES (?, ?, ?, ?)";

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, title);
            statement.setString(2, content);
            statement.setInt(3, userId); // Assuming you have the user ID from the session or user object
            statement.setString(4, username); // Similarly for the username

            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
