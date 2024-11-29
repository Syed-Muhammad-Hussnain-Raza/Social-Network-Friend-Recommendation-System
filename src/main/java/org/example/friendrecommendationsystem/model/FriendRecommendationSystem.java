package org.example.friendrecommendationsystem.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FriendRecommendationSystem {
    private List<User> users;
    private List<List<User>> friends;

    public FriendRecommendationSystem() {
        users = new ArrayList<>();
        friends = new ArrayList<>();
    }

    // add friends
    public void addFriends(User user) {
        users.add(user);
        friends.add(new ArrayList<>());
    }

    // remove friends
    public void removeFriends(User from, User to) {
        int fromIndex = users.indexOf(from);
        int toIndex = users.indexOf(to);

        friends.get(fromIndex).remove(to);
        friends.get(toIndex).remove(from);
    }

    public List<User> getMutualFriends(User user1, User user2) {
        int user1Index = users.indexOf(user1);
        int user2Index = users.indexOf(user2);

        List<User> mutualFriends = new ArrayList<>(friends.get(user1Index));
        mutualFriends.retainAll(friends.get(user2Index)); // Retain only common friends
        return mutualFriends;
    }

    public List<User> recommendFriends(User user) {
        int userIndex = users.indexOf(user);
        Set<User> recommended = new HashSet<>();

        for (User friend : friends.get(userIndex)) {
            int friendIndex = users.indexOf(friend);

            for (User friendOfFriend : friends.get(friendIndex)) {
                if (!friends.get(userIndex).contains(friendOfFriend) && !friendOfFriend.equals(user)) {
                    recommended.add(friendOfFriend);
                }
            }
        }
        return new ArrayList<>();
    }

    public void displayFriends(User user) {
        int userIndex = users.indexOf(user);
        System.out.println(user.getUsername() + "'s friends: " + friends.get(userIndex));
    }
}
