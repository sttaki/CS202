package com.realestate.model;

/**
 * Klasa koja predstavlja nekretninu u sistemu
 * @author Student
 * @version 1.0
 */
public class Nekretnina {
    private int id;
    private String tip;
    private String adresa;
    private double cena;
    private int agentId;
    private String status;

    /**
     * Konstruktor za kreiranje nove nekretnine
     * @param tip tip nekretnine (stan, kuća, lokal)
     * @param adresa adresa nekretnine
     * @param cena cena nekretnine
     * @param agentId ID agenta zaduženog za nekretninu
     * @param status status nekretnine (dostupna, prodana, rezervisana)
     */
    public Nekretnina(String tip, String adresa, double cena, int agentId, String status) {
        this.tip = tip;
        this.adresa = adresa;
        this.cena = cena;
        this.agentId = agentId;
        this.status = status;
    }

    /**
     * Vraća ID nekretnine
     * @return ID nekretnine
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja ID nekretnine
     * @param id novi ID nekretnine
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Vraća tip nekretnine
     * @return tip nekretnine
     */
    public String getTip() {
        return tip;
    }

    /**
     * Postavlja tip nekretnine
     * @param tip novi tip nekretnine
     */
    public void setTip(String tip) {
        this.tip = tip;
    }

    /**
     * Vraća adresu nekretnine
     * @return adresa nekretnine
     */
    public String getAdresa() {
        return adresa;
    }

    /**
     * Postavlja adresu nekretnine
     * @param adresa nova adresa nekretnine
     */
    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    /**
     * Vraća cenu nekretnine
     * @return cena nekretnine
     */
    public double getCena() {
        return cena;
    }

    /**
     * Postavlja cenu nekretnine
     * @param cena nova cena nekretnine
     */
    public void setCena(double cena) {
        this.cena = cena;
    }

    /**
     * Vraća ID agenta zaduženog za nekretninu
     * @return ID agenta
     */
    public int getAgentId() {
        return agentId;
    }

    /**
     * Postavlja ID agenta zaduženog za nekretninu
     * @param agentId novi ID agenta
     */
    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    /**
     * Vraća status nekretnine
     * @return status nekretnine
     */
    public String getStatus() {
        return status;
    }

    /**
     * Postavlja status nekretnine
     * @param status novi status nekretnine
     */
    public void setStatus(String status) {
        this.status = status;
    }
}