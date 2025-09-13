package com.cs202.server.dao;

import com.cs202.server.db.Database;
import com.cs202.server.model.Tournament;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TournamentDao {
    public List<Tournament> findAll() throws Exception {
        List<Tournament> list = new ArrayList<>();
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement("SELECT id,name,start_date FROM tournament");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                LocalDate d = rs.getDate(3) != null ? rs.getDate(3).toLocalDate() : null;
                list.add(new Tournament(rs.getInt(1), rs.getString(2), d));
            }
        }
        return list;
    }

    public Tournament insert(Tournament t) throws Exception {
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO tournament(name,start_date) VALUES(?,?)", Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, t.getName());
            if (t.getStartDate() == null) ps.setNull(2, Types.DATE); else ps.setDate(2, java.sql.Date.valueOf(t.getStartDate()));
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) t.setId(keys.getInt(1));
            }
            return t;
        }
    }

    public void update(Tournament t) throws Exception {
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement(
                     "UPDATE tournament SET name=?, start_date=? WHERE id=?")) {
            ps.setString(1, t.getName());
            if (t.getStartDate() == null) ps.setNull(2, Types.DATE); else ps.setDate(2, java.sql.Date.valueOf(t.getStartDate()));
            ps.setInt(3, t.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement("DELETE FROM tournament WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
