package com.cs202.server.model;

import java.io.Serializable;

public class Team implements Serializable {
    private int id;
    private String name;
    private Integer gameId;

    public Team() {}

    public Team(int id, String name, Integer gameId) {
        this.id = id;
        this.name = name;
        this.gameId = gameId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Integer getGameId() { return gameId; }
    public void setGameId(Integer gameId) { this.gameId = gameId; }
}
