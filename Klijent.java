package com.realestate.model;

/**
 * Klasa koja predstavlja klijenta agencije
 * @author Student
 * @version 1.0
 */
public class Klijent {
    private int id;
    private String ime;
    private String prezime;
    private String telefon;
    private String email;
    private String tipKlijenta; // kupac ili prodavac

    /**
     * Konstruktor za kreiranje novog klijenta
     * @param ime ime klijenta
     * @param prezime prezime klijenta
     * @param telefon telefon klijenta
     * @param email email klijenta
     * @param tipKlijenta tip klijenta (kupac/prodavac)
     */
    public Klijent(String ime, String prezime, String telefon, String email, String tipKlijenta) {
        this.ime = ime;
        this.prezime = prezime;
        this.telefon = telefon;
        this.email = email;
        this.tipKlijenta = tipKlijenta;
    }

    /**
     * Vraća ID klijenta
     * @return ID klijenta
     */
    public int getId() {
        return id;
    }

    /**
     * Postavlja ID klijenta
     * @param id novi ID klijenta
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Vraća ime klijenta
     * @return ime klijenta
     */
    public String getIme() {
        return ime;
    }

    /**
     * Postavlja ime klijenta
     * @param ime novo ime klijenta
     */
    public void setIme(String ime) {
        this.ime = ime;
    }

    /**
     * Vraća prezime klijenta
     * @return prezime klijenta
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Postavlja prezime klijenta
     * @param prezime novo prezime klijenta
     */
    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    /**
     * Vraća telefon klijenta
     * @return telefon klijenta
     */
    public String getTelefon() {
        return telefon;
    }

    /**
     * Postavlja telefon klijenta
     * @param telefon novi telefon klijenta
     */
    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    /**
     * Vraća email klijenta
     * @return email klijenta
     */
    public String getEmail() {
        return email;
    }

    /**
     * Postavlja email klijenta
     * @param email novi email klijenta
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Vraća tip klijenta
     * @return tip klijenta
     */
    public String getTipKlijenta() {
        return tipKlijenta;
    }

    /**
     * Postavlja tip klijenta
     * @param tipKlijenta novi tip klijenta
     */
    public void setTipKlijenta(String tipKlijenta) {
        this.tipKlijenta = tipKlijenta;
    }
}