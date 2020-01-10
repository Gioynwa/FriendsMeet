package com.example.friendsmeet;

import com.google.firebase.database.IgnoreExtraProperties;

public class User {

    private String id;
    private String name;
    private String email;
    private String password;
    private String phone;

    public User() {

    }

    public User(String id, String username, String email, String password, String phone) {
        this.id = id;
        this.name = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }
}
