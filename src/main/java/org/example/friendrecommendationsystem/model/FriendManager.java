package org.example.friendrecommendationsystem.model;

import java.util.*;

public class FriendManager {
    private static Map<Integer, Set<Integer>> adjacencyList;

    public FriendManager() {
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
        // Remove the friendship from both user's adjacency lists
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
}
