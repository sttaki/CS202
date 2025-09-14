package com.realestate.controller;

import com.realestate.database.KlijentDAO;
import com.realestate.model.Klijent;
import com.realestate.util.AlertHelper;
import com.realestate.util.Validator;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller klasa za upravljanje klijentima
 * @author Student
 * @version 1.0
 */
public class KlijentController {
    /** DAO objekat za rad sa klijentima */
    private KlijentDAO klijentDAO;

    /**
     * Konstruktor - inicijalizuje DAO objekat
     */
    public KlijentController() {
        this.klijentDAO = new KlijentDAO();
    }

    /**
     * Dodaje novog klijenta uz validaciju
     * @param ime ime klijenta
     * @param prezime prezime klijenta
     * @param telefon telefon klijenta
     * @param email email klijenta
     * @param tipKlijenta tip klijenta
     * @return true ako je klijent uspešno dodan, false inače
     */
    public boolean dodajKlijenta(String ime, String prezime, String telefon, String email, String tipKlijenta) {
        try {
            // Validacija osnovnih polja
            if (Validator.jePrazan(ime)) {
                AlertHelper.prikaziGresku("Neispravni podaci", "Ime mora biti unešeno!");
                return false;
            }

            if (Validator.jePrazan(prezime)) {
                AlertHelper.prikaziGresku("Neispravni podaci", "Prezime mora biti unešeno!");
                return false;
            }

            if (Validator.jePrazan(telefon)) {
                AlertHelper.prikaziGresku("Neispravni podaci", "Telefon mora biti unešen!");
                return false;
            }

            if (Validator.jePrazan(email)) {
                AlertHelper.prikaziGresku("Neispravni podaci", "Email mora biti unešen!");
                return false;
            }

            if (Validator.jePrazan(tipKlijenta)) {
                AlertHelper.prikaziGresku("Neispravni podaci", "Tip klijenta mora biti izabran!");
                return false;
            }

            // Validacija email-a
            if (!Validator.validirajEmail(email)) {
                AlertHelper.prikaziGresku("Neispravan email",
                        "Molimo unesite validan email (npr. marko@gmail.com)!");
                return false;
            }

            // Validacija telefona
            if (!Validator.validirajTelefon(telefon)) {
                AlertHelper.prikaziGresku("Neispravan telefon",
                        "Molimo unesite validan broj telefona (8-15 cifara)!");
                return false;
            }

            // Kreiranje i čuvanje klijenta
            Klijent klijent = new Klijent(ime, prezime, telefon, email, tipKlijenta);
            klijentDAO.dodajKlijenta(klijent);
            AlertHelper.prikaziUspeh("Uspeh", "Klijent je uspešno dodan!");
            return true;

        } catch (SQLException e) {
            AlertHelper.prikaziGresku("Greška baze podataka",
                    "Klijent nije mogao biti dodan: " + e.getMessage());
            return false;
        } catch (Exception e) {
            AlertHelper.prikaziGresku("Neočekivana greška",
                    "Došlo je do neočekivane greške: " + e.getMessage());
            return false;
        }
    }

    /**
     * Vraća sve klijente iz baze podataka
     * @return lista klijenata ili null u slučaju greške
     */
    public List<Klijent> sviKlijenti() {
        try {
            return klijentDAO.sviKlijenti();
        } catch (SQLException e) {
            AlertHelper.prikaziGresku("Greška baze podataka",
                    "Klijenti nisu mogli biti učitani: " + e.getMessage());
            return null;
        } catch (Exception e) {
            AlertHelper.prikaziGresku("Neočekivana greška",
                    "Došlo je do greške pri učitavanju: " + e.getMessage());
            return null;
        }
    }

    /**
     * Briše klijenta iz baze podataka
     * @param id ID klijenta za brisanje
     * @return true ako je brisanje uspešno, false inače
     */
    public boolean obrisiKlijenta(int id) {
        try {
            // Potvrda brisanja
            boolean potvrda = AlertHelper.prikaziPotvrdu("Potvrda brisanja",
                    "Da li ste sigurni da želite da obrišete ovog klijenta?");

            if (!potvrda) {
                return false;
            }

            klijentDAO.obrisiKlijenta(id);
            AlertHelper.prikaziUspeh("Uspeh", "Klijent je uspešno obrisan!");
            return true;
        } catch (SQLException e) {
            AlertHelper.prikaziGresku("Greška baze podataka",
                    "Klijent nije mogao biti obrisan: " + e.getMessage());
            return false;
        } catch (Exception e) {
            AlertHelper.prikaziGresku("Neočekivana greška",
                    "Došlo je do greške pri brisanju: " + e.getMessage());
            return false;
        }
    }

    /**
     * Ažurira postojećeg klijenta
     * @param klijent klijent sa ažuriranim podacima
     * @return true ako je ažuriranje uspešno, false inače
     */
    public boolean azurirajKlijenta(Klijent klijent) {
        try {
            // Validacija osnovnih polja
            if (Validator.jePrazan(klijent.getIme())) {
                AlertHelper.prikaziGresku("Neispravni podaci", "Ime mora biti unešeno!");
                return false;
            }

            if (Validator.jePrazan(klijent.getPrezime())) {
                AlertHelper.prikaziGresku("Neispravni podaci", "Prezime mora biti unešeno!");
                return false;
            }

            if (!Validator.validirajEmail(klijent.getEmail())) {
                AlertHelper.prikaziGresku("Neispravan email", "Molimo unesite validan email!");
                return false;
            }

            if (!Validator.validirajTelefon(klijent.getTelefon())) {
                AlertHelper.prikaziGresku("Neispravan telefon", "Molimo unesite validan broj telefona!");
                return false;
            }

            klijentDAO.azurirajKlijenta(klijent);
            AlertHelper.prikaziUspeh("Uspeh", "Klijent je uspešno ažuriran!");
            return true;
        } catch (SQLException e) {
            AlertHelper.prikaziGresku("Greška baze podataka",
                    "Klijent nije mogao biti ažuriran: " + e.getMessage());
            return false;
        } catch (Exception e) {
            AlertHelper.prikaziGresku("Neočekivana greška",
                    "Došlo je do greške pri ažuriranju: " + e.getMessage());
            return false;
        }
    }

    /**
     * Pronalazi klijenta po ID-ju
     * @param id ID klijenta
     * @return klijent ili null ako nije pronađen
     */
    public Klijent pronadjiKlijenta(int id) {
        try {
            return klijentDAO.pronadjiKlijenta(id);
        } catch (SQLException e) {
            AlertHelper.prikaziGresku("Greška baze podataka",
                    "Klijent nije mogao biti pronađen: " + e.getMessage());
            return null;
        } catch (Exception e) {
            AlertHelper.prikaziGresku("Greška",
                    "Došlo je do greške pri pronalaženju: " + e.getMessage());
            return null;
        }
    }
}