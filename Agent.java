package com.realestate.model;

/**
 * Klasa koja predstavlja agenta nekretnina
 * @author Student
 * @version 1.0
 */
public class Agent {
    private int id;
    private String ime;
    private String prezime;
    private String telefon;
    private String email;
    private double provizija;

    /**
     * Konstruktor za kreiranje novog agenta
     * @param ime ime agenta
     * @param prezime prezime agenta
     * @param telefon telefon agenta
     * @param email email agenta
     * @param provizija procenat provizije agenta
     */
    public Agent(String ime, String prezime, String telefon, String email, double provizija) {
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.email = email;
        this.provizija = provizija;
    }

    /**
     * Vraća ID agenta
     * @return ID agenta
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja ID agenta
     * @param id novi ID agenta
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Vraća ime agenta
     * @return ime agenta
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime agenta
     * @param ime novo ime agenta
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * Vraća prezime agenta
     * @return prezime agenta
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime agenta
     * @param prezime novo prezime agenta
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    /**
     * Vraća telefon agenta
     * @return telefon agenta
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * Postavlja telefon agenta
     * @param telefon novi telefon agenta
     */
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    /**
     * Vraća email agenta
     * @return email agenta
     */
    public String getEmail() {
        return email;
    }

    /**
     * Postavlja email agenta
     * @param email novi email agenta
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Vraća procenat provizije agenta
     * @return provizija agenta
     */
    public double getProvizija() {
        return provizija;
    }

    /**
     * Postavlja procenat provizije agenta
     * @param provizija nova provizija agenta
     */
    public void setProvizija(double provizija) {
        this.provizija = provizija;
    }
}