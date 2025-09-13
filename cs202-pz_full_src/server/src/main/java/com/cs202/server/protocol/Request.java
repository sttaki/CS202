package com.cs202.server.protocol;

import java.io.Serializable;
import java.util.Map;

public class Request implements Serializable {
    private String entity; // TEAM, PLAYER, GAME, ARENA, TOURNAMENT, MATCH, SERVICE
    private String action; // LIST, CREATE, UPDATE, DELETE, SCHEDULE, RANKING
    private Map<String,Object> data;

    public Request(){}

    public Request(String entity, String action, Map<String,Object> data){
        this.entity = entity;
        this.action = action;
        this.data = data;
    }

    public String getEntity(){ return entity; }
    public String getAction(){ return action; }
    public Map<String,Object> getData(){ return data; }
}
