package com.example.userservice.repositories;

import com.example.userservice.models.User;

import java.util.List;

public interface UserRepository {
    User getById(int id);

    List<Integer> getAllIds();

    void add(User user);

    void update(User user);

    void delete(int id);
}
