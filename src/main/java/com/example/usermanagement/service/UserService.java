package com.example.usermanagement.service;

import com.example.usermanagement.model.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {
    private final Map<UUID, User> store = new HashMap<>();

    public User addUser(User user) {
        UUID id = UUID.randomUUID();
        user.setId(id);
        store.put(id, user);
        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(store.values());
    }

    public User getUser(UUID id) {
        return store.get(id);
    }

    public User updateUser(UUID id, User user) {
        if (!store.containsKey(id)) return null;
        user.setId(id);
        store.put(id, user);
        return user;
    }

    public boolean deleteUser(UUID id) {
        return store.remove(id) != null;
    }
}
