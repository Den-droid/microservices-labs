package com.example.userservice.services;

import com.example.userservice.models.User;

import java.util.List;

public interface UserService {
    User getById(int id);
    List<Integer> getAllIds();

    void addUser(User user);

    void updateUser(int id, User user);

    void deleteUser(int id);
}
