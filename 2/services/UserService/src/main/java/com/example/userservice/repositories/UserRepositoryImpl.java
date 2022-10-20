package com.example.userservice.repositories;

import com.example.userservice.models.User;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final String url;
    private final String username;
    private final String password;

    public UserRepositoryImpl(Environment env) throws ClassNotFoundException {
        this.username = env.getProperty("spring.datasource.username");
        this.password = env.getProperty("spring.datasource.password");
        this.url = env.getProperty("spring.datasource.url");
    }

    @Override
    public User getById(int id) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement prepState = conn.prepareStatement("SELECT * FROM users WHERE id=?")
        ) {
            prepState.setInt(1, id);
            try (ResultSet set = prepState.executeQuery()) {
                if (set.next()) {
                    int userId = set.getInt(1);
                    String userName = set.getString(2);
                    String userAddress = set.getString(3);
                    return new User(userId, userName, userAddress);
                }
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Integer> getAllIds() {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement state = conn.createStatement();
             ResultSet set = state.executeQuery("SELECT id FROM users")
        ) {
            List<Integer> list = new ArrayList<>();
            while (set.next()) {
                list.add(set.getInt(1));
            }
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void add(User user) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement prepState =
                     conn.prepareStatement("INSERT INTO users(name, address) VALUES (?,?);")
        ) {
            prepState.setString(1, user.getName());
            prepState.setString(2, user.getAddress());
            prepState.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(User user) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement prepState =
                     conn.prepareStatement("UPDATE users SET name=?, address=? WHERE id=?")
        ) {
            prepState.setString(1, user.getName());
            prepState.setString(2, user.getAddress());
            prepState.setInt(3, user.getId());
            prepState.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement prepState =
                     conn.prepareStatement("DELETE FROM users WHERE id=?")
        ) {
            prepState.setInt(1, id);
            prepState.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
