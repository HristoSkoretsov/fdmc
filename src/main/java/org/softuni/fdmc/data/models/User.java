package org.softuni.fdmc.data.models;

import java.util.Objects;

public class User {
    private String username;

    private String password;

    private final UserRole role;

    public User(String username, String password, final UserRole role) {
        this.setUsername(username);
        this.setPassword(password);
        this.role = Objects.requireNonNull(role);
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

    public String getUsername() {
        return this.username;
    }

    public boolean isPasswordValid(final String password) {
        return this.password.equals(password);
    }

    public boolean isAdmin() {
        return this.role == UserRole.ADMIN;
    }
}
