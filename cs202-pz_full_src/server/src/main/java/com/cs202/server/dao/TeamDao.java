package com.cs202.server.dao;

import com.cs202.server.db.Database;
import com.cs202.server.model.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDao {
    public List<Team> findAll() throws Exception {
        List<Team> list = new ArrayList<>();
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement("SELECT id,name,game_id FROM team");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Team(rs.getInt(1), rs.getString(2), (Integer) rs.getObject(3)));
            }
        }
        return list;
    }

    public Team insert(Team t) throws Exception {
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO team(name,game_id) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, t.getName());
            if (t.getGameId() == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, t.getGameId());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) t.setId(keys.getInt(1));
            }
            return t;
        }
    }

    public void update(Team t) throws Exception {
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement(
                     "UPDATE team SET name=?, game_id=? WHERE id=?")) {
            ps.setString(1, t.getName());
            if (t.getGameId() == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, t.getGameId());
            ps.setInt(3, t.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement("DELETE FROM team WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
