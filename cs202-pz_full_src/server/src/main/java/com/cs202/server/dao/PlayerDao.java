package com.cs202.server.dao;

import com.cs202.server.db.Database;
import com.cs202.server.model.Player;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDao {
    public List<Player> findAll() throws Exception {
        List<Player> list = new ArrayList<>();
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement("SELECT id,nickname,team_id FROM player");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Player(rs.getInt(1), rs.getString(2), (Integer) rs.getObject(3)));
            }
        }
        return list;
    }

    public Player insert(Player p) throws Exception {
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO player(nickname,team_id) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, p.getNickname());
            if (p.getTeamId() == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, p.getTeamId());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) p.setId(keys.getInt(1));
            }
            return p;
        }
    }

    public void update(Player p) throws Exception {
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement(
                     "UPDATE player SET nickname=?, team_id=? WHERE id=?")) {
            ps.setString(1, p.getNickname());
            if (p.getTeamId() == null) ps.setNull(2, Types.INTEGER); else ps.setInt(2, p.getTeamId());
            ps.setInt(3, p.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement("DELETE FROM player WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
