package org.example.friendrecommendationsystem.model;

import java.util.Date;

public class User {
    private int userId;
    private String  username, password, gender, address, aboutMe;
    private Date dob;

    public User(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    // Full Constructor:
    public User(int userId, String username, String password, String gender, Date dob, String address, String aboutMe) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.aboutMe = aboutMe;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
                ", dob=" + dob +
                "}\n";
    }
}
