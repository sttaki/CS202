package com.cs202.server.dao;

import com.cs202.server.db.Database;
import com.cs202.server.model.Game;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDao {
    public List<Game> findAll() throws Exception {
        List<Game> list = new ArrayList<>();
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement("SELECT id,name FROM game");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Game(rs.getInt(1), rs.getString(2)));
            }
        }
        return list;
    }

    public Game insert(Game g) throws Exception {
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO game(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, g.getName());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) g.setId(keys.getInt(1));
            }
            return g;
        }
    }

    public void update(Game g) throws Exception {
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement("UPDATE game SET name=? WHERE id=?")) {
            ps.setString(1, g.getName());
            ps.setInt(2, g.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement("DELETE FROM game WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
