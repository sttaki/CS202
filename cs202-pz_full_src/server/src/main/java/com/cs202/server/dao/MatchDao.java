package com.cs202.server.dao;

import com.cs202.server.db.Database;
import com.cs202.server.model.Match;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatchDao {
    public List<Match> findAll() throws Exception {
        List<Match> list = new ArrayList<>();
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement(
                     "SELECT id,tournament_id,home_team_id,away_team_id,arena_id,match_date,home_score,away_score,status FROM match_tbl");
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                LocalDate d = rs.getDate(6) != null ? rs.getDate(6).toLocalDate() : null;
                list.add(new Match(
                        rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4),
                        (Integer) rs.getObject(5), d, rs.getInt(7), rs.getInt(8), rs.getString(9)
                ));
            }
        }
        return list;
    }

    public Match insert(Match m) throws Exception {
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO match_tbl(tournament_id,home_team_id,away_team_id,arena_id,match_date,home_score,away_score,status) VALUES(?,?,?,?,?,?,?,?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, m.getTournamentId());
            ps.setInt(2, m.getHomeTeamId());
            ps.setInt(3, m.getAwayTeamId());
            if (m.getArenaId() == null) ps.setNull(4, Types.INTEGER); else ps.setInt(4, m.getArenaId());
            if (m.getMatchDate() == null) ps.setNull(5, Types.DATE); else ps.setDate(5, java.sql.Date.valueOf(m.getMatchDate()));
            ps.setInt(6, m.getHomeScore());
            ps.setInt(7, m.getAwayScore());
            ps.setString(8, m.getStatus());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) m.setId(keys.getInt(1));
            }
            return m;
        }
    }

    public void update(Match m) throws Exception {
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement(
                     "UPDATE match_tbl SET tournament_id=?, home_team_id=?, away_team_id=?, arena_id=?, match_date=?, home_score=?, away_score=?, status=? WHERE id=?")) {
            ps.setInt(1, m.getTournamentId());
            ps.setInt(2, m.getHomeTeamId());
            ps.setInt(3, m.getAwayTeamId());
            if (m.getArenaId() == null) ps.setNull(4, Types.INTEGER); else ps.setInt(4, m.getArenaId());
            if (m.getMatchDate() == null) ps.setNull(5, Types.DATE); else ps.setDate(5, java.sql.Date.valueOf(m.getMatchDate()));
            ps.setInt(6, m.getHomeScore());
            ps.setInt(7, m.getAwayScore());
            ps.setString(8, m.getStatus());
            ps.setInt(9, m.getId());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        try (Connection c = Database.get();
             PreparedStatement ps = c.prepareStatement("DELETE FROM match_tbl WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
