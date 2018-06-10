package org.softuni.fdmc.data.repos;

import org.softuni.fdmc.data.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRepository {
    private final Map<String, User> users;

    public UserRepository() {
        this.users = new HashMap<>();
    }

    public User getByUsername(final String username) {
        return this.users.get(username);
    }

    public boolean addUser(final User user) {
        if (user == null) {
            return false;
        }

        return this.users.putIfAbsent(user.getUsername(), user) == null;
    }

    public boolean isValidCredentials(final String username, final String password) {
        final User user = this.getByUsername(username);
        return user != null && user.isPasswordValid(password);
    }

    public boolean isAdmin(final String username) {
        final User user = this.getByUsername(username);
        return user != null && user.isAdmin();
    }
}
