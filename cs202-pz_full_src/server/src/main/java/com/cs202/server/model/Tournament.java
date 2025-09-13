package com.cs202.server.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Tournament implements Serializable {
    private int id;
    private String name;
    private LocalDate startDate;

    public Tournament() {}

    public Tournament(int id, String name, LocalDate startDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
}
