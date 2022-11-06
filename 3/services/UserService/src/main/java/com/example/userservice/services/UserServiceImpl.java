package com.example.userservice.services;

import com.example.userservice.models.User;
import com.example.userservice.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User getById(int id) {
        return repository.getById(id);
    }

    @Override
    public List<Integer> getAllIds() {
        return repository.getAllIds();
    }

    @Override
    public void addUser(User user) {
        repository.add(user);
    }

    @Override
    public void updateUser(int id, User user) {
        user.setId(id);
        repository.update(user);
    }

    @Override
    public void deleteUser(int id) {
        repository.delete(id);
    }
}
