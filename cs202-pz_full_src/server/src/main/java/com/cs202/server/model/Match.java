package com.cs202.server.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Match implements Serializable {
    private int id;
    private int tournamentId;
    private int homeTeamId;
    private int awayTeamId;
    private Integer arenaId;
    private LocalDate matchDate;
    private int homeScore;
    private int awayScore;
    private String status;

    public Match() {}

    public Match(int id, int tournamentId, int homeTeamId, int awayTeamId, Integer arenaId,
                 LocalDate matchDate, int homeScore, int awayScore, String status) {
        this.id = id;
        this.tournamentId = tournamentId;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.arenaId = arenaId;
        this.matchDate = matchDate;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.status = status;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getTournamentId() { return tournamentId; }
    public void setTournamentId(int tournamentId) { this.tournamentId = tournamentId; }
    public int getHomeTeamId() { return homeTeamId; }
    public void setHomeTeamId(int homeTeamId) { this.homeTeamId = homeTeamId; }
    public int getAwayTeamId() { return awayTeamId; }
    public void setAwayTeamId(int awayTeamId) { this.awayTeamId = awayTeamId; }
    public Integer getArenaId() { return arenaId; }
    public void setArenaId(Integer arenaId) { this.arenaId = arenaId; }
    public LocalDate getMatchDate() { return matchDate; }
    public void setMatchDate(LocalDate matchDate) { this.matchDate = matchDate; }
    public int getHomeScore() { return homeScore; }
    public void setHomeScore(int homeScore) { this.homeScore = homeScore; }
    public int getAwayScore() { return awayScore; }
    public void setAwayScore(int awayScore) { this.awayScore = awayScore; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
