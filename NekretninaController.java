package com.realestate.controller;

import com.realestate.database.NekretninaDAO;
import com.realestate.model.Nekretnina;
import com.realestate.util.AlertHelper;
import com.realestate.util.Validator;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller klasa za upravljanje nekretninama
 * @author Student
 * @version 1.0
 */
public class NekretninaController {
    /** DAO objekat za rad sa nekretninama */
    private NekretninaDAO nekretninaDAO;

    /**
     * Konstruktor - inicijalizuje DAO objekat
     */
    public NekretninaController() {
        this.nekretninaDAO = new NekretninaDAO();
    }

    /**
     * Dodaje novu nekretninu uz validaciju
     * @param tip tip nekretnine
     * @param adresa adresa nekretnine
     * @param cena cena nekretnine kao string
     * @param agentId ID agenta
     * @param status status nekretnine
     * @return true ako je nekretnina uspešno dodana, false inače
     */
    public boolean dodajNekretninu(String tip, String adresa, String cena, int agentId, String status) {
        try {
            // Validacija osnovnih polja
            if (Validator.jePrazan(tip)) {
                AlertHelper.prikaziGresku("Neispravni podaci", "Tip nekretnine mora biti izabran!");
                return false;
            }

            if (Validator.jePrazan(adresa)) {
                AlertHelper.prikaziGresku("Neispravni podaci", "Adresa nekretnine mora biti unešena!");
                return false;
            }

            if (Validator.jePrazan(status)) {
                AlertHelper.prikaziGresku("Neispravni podaci", "Status nekretnine mora biti izabran!");
                return false;
            }

            // Validacija cene
            if (!Validator.jeBroj(cena)) {
                AlertHelper.prikaziGresku("Neispravna cena", "Cena mora biti broj!");
                return false;
            }

            double cenaDouble = Double.parseDouble(cena);
            if (!Validator.validirajCenu(cenaDouble)) {
                AlertHelper.prikaziGresku("Neispravna cena", "Cena mora biti pozitivna!");
                return false;
            }

            // Kreiranje i čuvanje nekretnine
            Nekretnina nekretnina = new Nekretnina(tip, adresa, cenaDouble, agentId, status);
            nekretninaDAO.dodajNekretninu(nekretnina);
            AlertHelper.prikaziUspeh("Uspeh", "Nekretnina je uspešno dodana!");
            return true;

        } catch (NumberFormatException e) {
            AlertHelper.prikaziGresku("Neispravni podaci", "Cena mora biti validan broj!");
            return false;
        } catch (SQLException e) {
            AlertHelper.prikaziGresku("Greška baze podataka",
                    "Nekretnina nije mogla biti dodana: " + e.getMessage());
            return false;
        } catch (Exception e) {
            AlertHelper.prikaziGresku("Neočekivana greška",
                    "Došlo je do neočekivane greške: " + e.getMessage());
            return false;
        }
    }

    /**
     * Vraća sve nekretnine iz baze podataka
     * @return lista nekretnina ili null u slučaju greške
     */
    public List<Nekretnina> sveNekretnine() {
        try {
            return nekretninaDAO.sveNekretnine();
        } catch (SQLException e) {
            AlertHelper.prikaziGresku("Greška baze podataka",
                    "Nekretnine nisu mogle biti učitane: " + e.getMessage());
            return null;
        } catch (Exception e) {
            AlertHelper.prikaziGresku("Neočekivana greška",
                    "Došlo je do greške pri učitavanju: " + e.getMessage());
            return null;
        }
    }

    /**
     * Briše nekretninu iz baze podataka
     * @param id ID nekretnine za brisanje
     * @return true ako je brisanje uspešno, false inače
     */
    public boolean obrisiNekretninu(int id) {
        try {
            // Potvrda brisanja
            boolean potvrda = AlertHelper.prikaziPotvrdu("Potvrda brisanja",
                    "Da li ste sigurni da želite da obrišete ovu nekretninu?");

            if (!potvrda) {
                return false;
            }

            nekretninaDAO.obrisiNekretninu(id);
            AlertHelper.prikaziUspeh("Uspeh", "Nekretnina je uspešno obrisana!");
            return true;
        } catch (SQLException e) {
            AlertHelper.prikaziGresku("Greška baze podataka",
                    "Nekretnina nije mogla biti obrisana: " + e.getMessage());
            return false;
        } catch (Exception e) {
            AlertHelper.prikaziGresku("Neočekivana greška",
                    "Došlo je do greške pri brisanju: " + e.getMessage());
            return false;
        }
    }

    /**
     * Ažurira postojeću nekretninu
     * @param nekretnina nekretnina sa ažuriranim podacima
     * @return true ako je ažuriranje uspešno, false inače
     */
    public boolean azurirajNekretninu(Nekretnina nekretnina) {
        try {
            // Validacija osnovnih polja
            if (Validator.jePrazan(nekretnina.getTip())) {
                AlertHelper.prikaziGresku("Neispravni podaci", "Tip nekretnine mora biti izabran!");
                return false;
            }

            if (Validator.jePrazan(nekretnina.getAdresa())) {
                AlertHelper.prikaziGresku("Neispravni podaci", "Adresa nekretnine mora biti unešena!");
                return false;
            }

            if (!Validator.validirajCenu(nekretnina.getCena())) {
                AlertHelper.prikaziGresku("Neispravna cena", "Cena mora biti pozitivna!");
                return false;
            }

            nekretninaDAO.azurirajNekretninu(nekretnina);
            AlertHelper.prikaziUspeh("Uspeh", "Nekretnina je uspešno ažurirana!");
            return true;
        } catch (SQLException e) {
            AlertHelper.prikaziGresku("Greška baze podataka",
                    "Nekretnina nije mogla biti ažurirana: " + e.getMessage());
            return false;
        } catch (Exception e) {
            AlertHelper.prikaziGresku("Neočekivana greška",
                    "Došlo je do greške pri ažuriranju: " + e.getMessage());
            return false;
        }
    }

    /**
     * Pronalazi nekretninu po ID-ju
     * @param id ID nekretnine
     * @return nekretnina ili null ako nije pronađena
     */
    public Nekretnina pronadjiNekretninu(int id) {
        try {
            return nekretninaDAO.pronadjiNekretninu(id);
        } catch (SQLException e) {
            AlertHelper.prikaziGresku("Greška baze podataka",
                    "Nekretnina nije mogla biti pronađena: " + e.getMessage());
            return null;
        } catch (Exception e) {
            AlertHelper.prikaziGresku("Greška",
                    "Došlo je do greške pri pronalaženju: " + e.getMessage());
            return null;
        }
    }
}