package com.example.userservice.repositories;

import com.example.userservice.models.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final List<User> users;

    public UserRepositoryImpl() {
        this.users = new ArrayList<>(Arrays.asList(
                new User(1, "Denys", "Schaslyva"),
                new User(2, "Dima", "Soborna"),
                new User(3, "Oleg", "Schevchenka")));
    }

    @Override
    public User getById(int id) {
        return users.stream()
                .filter(x -> x.getId() == id)
                .findAny()
                .orElseThrow(() -> new NoSuchElementException("No such user exists"));
    }

    @Override
    public List<Integer> getAllIds() {
        return users.stream()
                .mapToInt(User::getId)
                .boxed()
                .collect(Collectors.toList());
    }
}
