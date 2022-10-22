package com.example.foodservice.repositories;

import com.example.foodservice.models.Food;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FoodRepositoryImpl implements FoodRepository {
    private final String url;
    private final String username;
    private final String password;

    public FoodRepositoryImpl(Environment env) {
        this.username = env.getProperty("spring.datasource.username");
        this.password = env.getProperty("spring.datasource.password");
        this.url = env.getProperty("spring.datasource.url");
    }

    @Override
    public Food getById(int id) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement prepState = conn.prepareStatement("SELECT * FROM food WHERE id=?")
        ) {
            prepState.setInt(1, id);
            try (ResultSet set = prepState.executeQuery()) {
                if (set.next()) {
                    int foodId = set.getInt(1);
                    String foodName = set.getString(2);
                    float foodPrice = set.getFloat(3);
                    return new Food(foodId, foodName, foodPrice);
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
             ResultSet set = state.executeQuery("SELECT id FROM food")
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
    public void add(Food food) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement prepState =
                     conn.prepareStatement("INSERT INTO food(name, price) VALUES (?,?);")
        ) {
            prepState.setString(1, food.getName());
            prepState.setFloat(2, food.getPrice());
            prepState.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Food food) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement prepState =
                     conn.prepareStatement("UPDATE food SET name=?, price=? WHERE id=?")
        ) {
            prepState.setString(1, food.getName());
            prepState.setFloat(2, food.getPrice());
            prepState.setInt(3, food.getId());
            prepState.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement prepState =
                     conn.prepareStatement("DELETE FROM food WHERE id=?")
        ) {
            prepState.setInt(1, id);
            prepState.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
