package org.example.friendrecommendationsystem.model;

import java.util.*;

public class FriendManager {
    // Adjacency list where each user has a list of their friends (using user_id)
    public static Map<Integer, Set<Integer>> adjacencyList;

    public FriendManager() {
        // Initialize the adjacency list (a map from user_id to set of friends)
        adjacencyList = new HashMap<>();
    }

    public void addUser(int userId) {
        adjacencyList.put(userId, new HashSet<>());
    }

    // Method to add a friend
    public void addFriend(int userId, int friendId) {
        // Ensure both users exist in the adjacency list
        adjacencyList.putIfAbsent(userId, new HashSet<>());
        adjacencyList.putIfAbsent(friendId, new HashSet<>());

        // Add each other as friends
        adjacencyList.get(userId).add(friendId);
        adjacencyList.get(friendId).add(userId);
    }

    // Method to remove a friend
    public void removeFriend(int userId, int friendId) {
        // Remove the friendship from both users' adjacency lists
        if (adjacencyList.containsKey(userId)) {
            adjacencyList.get(userId).remove(friendId);
        }
        if (adjacencyList.containsKey(friendId)) {
            adjacencyList.get(friendId).remove(userId);
        }
    }

    // Method to show all friends of a given user
    public Set<Integer> showFriends(int userId) {
        // Return the set of friends for the user
        return adjacencyList.getOrDefault(userId, Collections.emptySet());
    }

    // Method to display mutual friends between two users
    public Set<Integer> showMutualFriends(int userId, int friendId) {
        Set<Integer> mutualFriends = new HashSet<>();

        // Get the friends of both users
        Set<Integer> userFriends = adjacencyList.getOrDefault(userId, Collections.emptySet());
        Set<Integer> friendFriends = adjacencyList.getOrDefault(friendId, Collections.emptySet());

        // Find mutual friends by intersecting the two sets
        for (Integer friend : userFriends) {
            if (friendFriends.contains(friend)) {
                mutualFriends.add(friend);
            }
        }
        return mutualFriends;
    }

    // Method to check if two users are friends
    public boolean areFriends(int userId, int friendId) {
        Set<Integer> friends = adjacencyList.getOrDefault(userId, Collections.emptySet());
        return friends.contains(friendId);
    }

    // Method to display all users and their friends (for debugging)
    public void displayAllUsersAndFriends() {
        for (Map.Entry<Integer, Set<Integer>> entry : adjacencyList.entrySet()) {
            System.out.println("User " + entry.getKey() + " has friends: " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        FriendManager system = new FriendManager();
        User user1 = new User(1, "user1", "pass1");
        User user2 = new User(2, "user2", "pass2");
        User user3 = new User(3, "user3", "pass3");
        User user4 = new User(4, "user4", "pass4");
        User user5 = new User(5, "user5", "pass5");

        system.addFriend(user1.getUserId(), user2.getUserId());
        system.addFriend(user1.getUserId(), user3.getUserId());
        system.addFriend(user1.getUserId(), user4.getUserId());

        system.addFriend(user2.getUserId(), user3.getUserId());
        system.addFriend(user2.getUserId(), user5.getUserId());

        system.displayAllUsersAndFriends();
    }
}

