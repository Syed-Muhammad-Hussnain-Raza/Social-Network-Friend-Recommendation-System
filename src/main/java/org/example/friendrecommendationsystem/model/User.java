package org.example.friendrecommendationsystem.model;

import java.time.LocalDate;

public class User {
    private int userId;
    private String  username, password, gender, address, email, aboutMe;
    private LocalDate dob;

    // Full Constructor:
    public User(String username, String password, String gender, LocalDate dob, String address, String email, String aboutMe) {
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.dob = dob;
        this.address = address;
        this.email = email;
        this.aboutMe = aboutMe;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }
}
