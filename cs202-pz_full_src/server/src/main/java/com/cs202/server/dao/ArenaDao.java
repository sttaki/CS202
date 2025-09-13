package com.cs202.server.dao;

import com.cs202.server.db.Database;
import com.cs202.server.model.Arena;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArenaDao {
    public List<Arena> findAll() throws Exception {
        List<Arena> list = new ArrayList<>();
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement("SELECT id,name,city FROM arena");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Arena(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        }
        return list;
    }

    public Arena insert(Arena a) throws Exception {
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO arena(name,city) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, a.getName());
            ps.setString(2, a.getCity());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) a.setId(keys.getInt(1));
            }
            return a;
        }
    }

    public void update(Arena a) throws Exception {
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement(
                     "UPDATE arena SET name=?, city=? WHERE id=?")) {
            ps.setString(1, a.getName());
            ps.setString(2, a.getCity());
            ps.setInt(3, a.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement("DELETE FROM arena WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
