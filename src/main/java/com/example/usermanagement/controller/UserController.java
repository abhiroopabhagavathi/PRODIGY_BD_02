package com.example.usermanagement.controller;

import com.example.usermanagement.model.User;
import com.example.usermanagement.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return service.addUser(user);
    }

    @GetMapping
    public List<User> all() {
        return service.getAllUsers();
    }

    @GetMapping("/{id}")
    public User one(@PathVariable UUID id) {
        return service.getUser(id);
    }

    @PutMapping("/{id}")
    public User update(@PathVariable UUID id, @RequestBody User user) {
        return service.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable UUID id) {
        return service.deleteUser(id) ? "Deleted" : "Not Found";
    }
}
