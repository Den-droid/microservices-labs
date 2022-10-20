package com.example.userservice.repositories;

import com.example.userservice.models.User;

import java.util.List;

public interface UserRepository {
    User getById(int id);

    List<Integer> getAllIds();
}
