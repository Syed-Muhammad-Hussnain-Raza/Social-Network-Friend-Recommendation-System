package org.example.friendrecommendationsystem.model;

import java.sql.Timestamp;

public class Post {
    private int postId;
    private String title;
    private String content;
    private int userId;
    private String username;
    private Timestamp createdAt;

    public Post(int postId, String title, String content, int userId, String username, Timestamp createdAt) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.userId = userId;
        this.username = username;
        this.createdAt = createdAt;
    }

    // Getters and setters
    public int getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
