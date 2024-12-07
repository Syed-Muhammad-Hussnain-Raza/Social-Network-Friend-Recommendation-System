package org.example.friendrecommendationsystem.database;

import org.example.friendrecommendationsystem.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

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

    public static ArrayList<User> getUsersData() {
        String query = "SELECT * FROM users";

        ArrayList<User> users = new ArrayList<>();

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                String gender = resultSet.getString("gender");
                Date dob = resultSet.getDate("dob");
                String address = resultSet.getString("address");
                String aboutMe = resultSet.getString("about_me");

                users.add(new User(userId, username, password, gender, dob, address, aboutMe));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public static User getUserByUsernameAndPassword(String username, String password) {
        String query = "SELECT * FROM users WHERE BINARY username = ? AND BINARY password = ?";

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String gender = resultSet.getString("gender");
                Date dob = resultSet.getDate("dob");
                String address = resultSet.getString("address");
                String aboutMe = resultSet.getString("about_me");

                return new User(userId, username, password, gender, dob, address, aboutMe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Return null if no user is found
    }

    public static void main(String[] args) {
        System.out.println(getUsersData());
    }
}
