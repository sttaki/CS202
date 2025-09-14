package com.realestate.model;

import java.time.LocalDateTime;

/**
 * Klasa koja predstavlja pregled nekretnine
 * @author Student
 * @version 1.0
 */
public class Pregled {
    private int id;
    private int nekretninaId;
    private int klijentId;
    private int agentId;
    private LocalDateTime datumPregleda;
    private String napomena;

    /**
     * Konstruktor za kreiranje novog pregleda
     * @param nekretninaId ID nekretnine
     * @param klijentId ID klijenta
     * @param agentId ID agenta
     * @param datumPregleda datum i vreme pregleda
     * @param napomena napomena o pregledu
     */
    public Pregled(int nekretninaId, int klijentId, int agentId, LocalDateTime datumPregleda, String napomena) {
        this.nekretninaId = nekretninaId;
        this.klijentId = klijentId;
        this.agentId = agentId;
        this.datumPregleda = datumPregleda;
        this.napomena = napomena;
    }

    /**
     * Vraća ID pregleda
     * @return ID pregleda
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja ID pregleda
     * @param id novi ID pregleda
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Vraća ID nekretnine
     * @return ID nekretnine
     */
    public int getNekretninaId() {
        return nekretninaId;
    }

    /**
     * Postavlja ID nekretnine
     * @param nekretninaId novi ID nekretnine
     */
    public void setNekretninaId(int nekretninaId) {
        this.nekretninaId = nekretninaId;
    }

    /**
     * Vraća ID klijenta
     * @return ID klijenta
     */
    public int getKlijentId() {
        return klijentId;
    }

    /**
     * Postavlja ID klijenta
     * @param klijentId novi ID klijenta
     */
    public void setKlijentId(int klijentId) {
        this.klijentId = klijentId;
    }

    /**
     * Vraća ID agenta
     * @return ID agenta
     */
    public int getAgentId() {
        return agentId;
    }

    /**
     * Postavlja ID agenta
     * @param agentId novi ID agenta
     */
    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    /**
     * Vraća datum i vreme pregleda
     * @return datum pregleda
     */
    public LocalDateTime getDatumPregleda() {
        return datumPregleda;
    }

    /**
     * Postavlja datum i vreme pregleda
     * @param datumPregleda novi datum pregleda
     */
    public void setDatumPregleda(LocalDateTime datumPregleda) {
        this.datumPregleda = datumPregleda;
    }

    /**
     * Vraća napomenu o pregledu
     * @return napomena o pregledu
     */
    public String getNapomena() {
        return napomena;
    }

    /**
     * Postavlja napomenu o pregledu
     * @param napomena nova napomena o pregledu
     */
    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }
}