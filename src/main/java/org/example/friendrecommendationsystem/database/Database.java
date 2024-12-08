package org.example.friendrecommendationsystem.database;

import org.example.friendrecommendationsystem.model.Post;
import org.example.friendrecommendationsystem.model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Database {
    /**
     * Method to check validity for login credentials, username and password.
     * @param username is the username of account to be logged in.
     * @param password is the passcode for given username.
     * @return This method will return true if the credential are correct.
     */
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

    /**
     * This method helps in adding friend.
     * @param userId is the user id of user who send request.
     * @param friendUsername name of friend to be added in friend list.
     * @return true on successful addition of friend else false.
     */
    public static boolean addFriend(int userId, String friendUsername) {
        String queryUser = "SELECT user_id FROM users WHERE BINARY username = ?";
        String queryInsert = "INSERT INTO friends (user_id, friend_id) VALUES (?, ?)";

        try (Connection connection = new DatabaseConnection().getConnection()) {
            // Get friend's user ID
            try (PreparedStatement userStatement = connection.prepareStatement(queryUser)) {
                userStatement.setString(1, friendUsername);
                ResultSet userResultSet = userStatement.executeQuery();
                if (userResultSet.next()) {
                    int friendId = userResultSet.getInt("user_id");

                    // Insert friend relationship for current user
                    try (PreparedStatement insertStatement = connection.prepareStatement(queryInsert)) {
                        insertStatement.setInt(1, userId);
                        insertStatement.setInt(2, friendId);
                        insertStatement.executeUpdate();
                    }

                    // Insert friend relationship for the friend (bidirectional)
                    try (PreparedStatement insertStatement = connection.prepareStatement(queryInsert)) {
                        insertStatement.setInt(1, friendId);
                        insertStatement.setInt(2, userId);
                        insertStatement.executeUpdate();
                    }

                    return true; // Friend successfully added in both directions
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Returns false if friend could not be added
    }

    /**
     * This method used to get record of a specific user on base of name and password.
     * @param username is the name of account holder.
     * @param password is the passcode for security of account.
     * @return It will return full record of a specific user on base of username and password.
     */
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

    /**
     * This method help in getting list of recommended friends on base of common friend's friend
     * @param currentUserId is the id of user for which we have to recommend friends.
     * @return will be arraylist of recommended friends of type String.
     */
    public static ArrayList<String> getRecommendedFriends(int currentUserId) {
        ArrayList<String> recommendedFriends = new ArrayList<>();

        String query = "SELECT DISTINCT u.username " +
                "FROM users u " +
                "WHERE u.user_id IN (" +
                "    SELECT f2.friend_id " +
                "    FROM friends f1 " +
                "    JOIN friends f2 ON f1.friend_id = f2.user_id " +
                "    WHERE f1.user_id = ? AND f2.friend_id != ?" +
                ") AND u.user_id NOT IN (" +
                "    SELECT friend_id FROM friends WHERE user_id = ?" +
                ")";

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, currentUserId);
            statement.setInt(2, currentUserId);
            statement.setInt(3, currentUserId);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                recommendedFriends.add(resultSet.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recommendedFriends;
    }

    /**
     * Method to add post in database for user.
     * @param title is the post title
     * @param content is the content of post - only text yet.
     * @param userId is the id of user who is posting this post.
     * @param username is the name of user who is posting this post.
     * @return true on successful posting and false on failure.
     */
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

    /**
     * This method return users records from database.
     * @return will be an arraylist of type User.
     */
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

    /**
     * This method checks the existence of a user in system.
     * @param username is the name of user whom existence we have to check.
     * @return will be true if user found in system else false.
     */
    public static boolean isUserExists(String username) {
        String query = "SELECT 1 FROM users WHERE BINARY username = ?";
        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next(); // Returns true if a record is found
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Returns false if user doesn't exist or an error occurs
    }


    /**
     * This method retrieve list of friends on base of userId of user.
     * @param userId is the id of user whose friends we have to retrieve.
     * @return ArrayList will be return containing all friends.
     */
    public static ArrayList<String> getFriends(int userId) {
        ArrayList<String> friends = new ArrayList<>();

        String query = "SELECT u.username FROM users u " +
                "JOIN friends f ON u.user_id = f.friend_id WHERE f.user_id = ?";

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                friends.add(resultSet.getString("username")); // Add friend username to the list
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return friends; // Return the list of friend usernames
    }

    // remove friend
    public static void removeFriendship(int userId, String friendUsername) {
        // SQL query to find the friend's user ID using the friend's username
        String query = "SELECT user_id FROM users WHERE username = ?";

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, friendUsername);
            var resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int friendId = resultSet.getInt("user_id");

                // SQL query to remove both friendships (userId -> friendId and friendId -> userId)
                String removeFriendshipQuery = "DELETE FROM friends WHERE (user_id = ? AND friend_id = ?) OR (user_id = ? AND friend_id = ?)";

                try (PreparedStatement removeStatement = connection.prepareStatement(removeFriendshipQuery)) {
                    removeStatement.setInt(1, userId);
                    removeStatement.setInt(2, friendId);
                    removeStatement.setInt(3, friendId);
                    removeStatement.setInt(4, userId);
                    removeStatement.executeUpdate();
                    System.out.println("Friendship removed between user " + userId + " and " + friendId);
                }
            } else {
                System.out.println("Friend not found: " + friendUsername);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<Post> getPosts() {
        List<Post> posts = new ArrayList<>();
        String query = "SELECT * FROM posts ORDER BY post_id DESC"; // Adjust ORDER BY as needed

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int postId = resultSet.getInt("post_id");
                String title = resultSet.getString("title");
                String content = resultSet.getString("content");
                int userId = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                Timestamp timestamp = resultSet.getTimestamp("created_at");

                posts.add(new Post(postId, title, content, userId, username, timestamp));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return posts;
    }


}
