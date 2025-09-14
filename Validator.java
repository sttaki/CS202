package com.realestate.util;

/**
 * Utility klasa za validaciju korisničkih unosa
 * @author Student
 * @version 1.0
 */
public class Validator {

    /**
     * Validira email adresu - proverava da li sadrži @ i tačku
     * @param email email za validaciju
     * @return true ako je email validan, false inače
     */
    public static boolean validirajEmail(String email) {
        if (jePrazan(email)) {
            return false;
        }

        return email.contains("@") && email.contains(".");
    }

    /**
     * Validira broj telefona - proverava dužinu i dozvoljene karaktere
     * @param telefon telefon za validaciju
     * @return true ako je telefon validan, false inače
     */
    public static boolean validirajTelefon(String telefon) {
        if (jePrazan(telefon)) {
            return false;
        }

        String ociscen = telefon.replaceAll("[^0-9+\\-\\s()]", "");

        return ociscen.length() >= 8 && ociscen.length() <= 15;
    }

    /**
     * Proverava da li je string prazan ili null
     * @param str string za proveru
     * @return true ako je string prazan ili null, false inače
     */
    public static boolean jePrazan(String str) {
        return str == null || str.trim().isEmpty();
    }

    /**
     * Validira cenu nekretnine
     * @param cena cena za validaciju
     * @return true ako je cena validna (pozitivna), false inače
     */
    public static boolean validirajCenu(double cena) {
        return cena > 0;
    }

    /**
     * Validira da li je string broj
     * @param str string za validaciju
     * @return true ako je string validan broj, false inače
     */
    public static boolean jeBroj(String str) {
        if (jePrazan(str)) {
            return false;
        }
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validira da li je string ceo broj
     * @param str string za validaciju
     * @return true ako je string validan ceo broj, false inače
     */
    public static boolean jeCeoBroj(String str) {
        if (jePrazan(str)) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}