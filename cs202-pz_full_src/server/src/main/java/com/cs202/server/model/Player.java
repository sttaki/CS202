package com.cs202.server.model;

import java.io.Serializable;

public class Player implements Serializable {
    private int id;
    private String nickname;
    private Integer teamId;

    public Player() {}

    public Player(int id, String nickname, Integer teamId) {
        this.id = id;
        this.nickname = nickname;
        this.teamId = teamId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }
    public Integer getTeamId() { return teamId; }
    public void setTeamId(Integer teamId) { this.teamId = teamId; }
}
