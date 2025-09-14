package com.realestate.model;

import java.time.LocalDate;

/**
 * Klasa koja predstavlja transakciju prodaje/kupovine
 * @author Student
 * @version 1.0
 */
public class Transakcija {
    private int id;
    private int nekretninaId;
    private int klijentId;
    private int agentId;
    private double cenaTransakcije;
    private LocalDate datumTransakcije;
    private String tipTransakcije; // prodaja ili izdavanje

    /**
     * Konstruktor za kreiranje nove transakcije
     * @param nekretninaId ID nekretnine
     * @param klijentId ID klijenta
     * @param agentId ID agenta
     * @param cenaTransakcije cena transakcije
     * @param datumTransakcije datum transakcije
     * @param tipTransakcije tip transakcije
     */
    public Transakcija(int nekretninaId, int klijentId, int agentId, double cenaTransakcije,
                       LocalDate datumTransakcije, String tipTransakcije) {
        this.nekretninaId = nekretninaId;
        this.klijentId = klijentId;
        this.agentId = agentId;
        this.cenaTransakcije = cenaTransakcije;
        this.datumTransakcije = datumTransakcije;
        this.tipTransakcije = tipTransakcije;
    }

    /**
     * Vraća ID transakcije
     * @return ID transakcije
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja ID transakcije
     * @param id novi ID transakcije
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
     * Vraća cenu transakcije
     * @return cena transakcije
     */
    public double getCenaTransakcije() {
        return cenaTransakcije;
    }

    /**
     * Postavlja cenu transakcije
     * @param cenaTransakcije nova cena transakcije
     */
    public void setCenaTransakcije(double cenaTransakcije) {
        this.cenaTransakcije = cenaTransakcije;
    }

    /**
     * Vraća datum transakcije
     * @return datum transakcije
     */
    public LocalDate getDatumTransakcije() {
        return datumTransakcije;
    }

    /**
     * Postavlja datum transakcije
     * @param datumTransakcije novi datum transakcije
     */
    public void setDatumTransakcije(LocalDate datumTransakcije) {
        this.datumTransakcije = datumTransakcije;
    }

    /**
     * Vraća tip transakcije
     * @return tip transakcije
     */
    public String getTipTransakcije() {
        return tipTransakcije;
    }

    /**
     * Postavlja tip transakcije
     * @param tipTransakcije novi tip transakcije
     */
    public void setTipTransakcije(String tipTransakcije) {
        this.tipTransakcije = tipTransakcije;
    }
}