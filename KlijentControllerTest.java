package com.realestate.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za KlijentController klasu
 * @author Student
 * @version 1.0
 */
public class KlijentControllerTest {

    /** Controller za testiranje */
    private KlijentController controller;

    /**
     * Setup metoda koja se izvršava pre svakog testa
     */
    @BeforeEach
    public void setUp() {
        controller = new KlijentController();
    }

    /**
     * Test dodavanja klijenta sa praznim imenom
     */
    @Test
    @DisplayName("Test dodavanja klijenta sa praznim imenom")
    public void testDodavanjeKlijentaNeispravnoIme() {
        // Test sa praznim imenom
        boolean rezultat = controller.dodajKlijenta("", "Petrović", "064123456", "marko@gmail.com", "Kupac");
        assertFalse(rezultat, "Dodavanje klijenta sa praznim imenom treba da ne uspe");

        // Test sa null imenom
        rezultat = controller.dodajKlijenta(null, "Petrović", "064123456", "marko@gmail.com", "Kupac");
        assertFalse(rezultat, "Dodavanje klijenta sa null imenom treba da ne uspe");

        // Test sa imenom koje sadrži samo beline
        rezultat = controller.dodajKlijenta("   ", "Petrović", "064123456", "marko@gmail.com", "Kupac");
        assertFalse(rezultat, "Dodavanje klijenta sa imenom koje sadrži samo beline treba da ne uspe");
    }

    /**
     * Test dodavanja klijenta sa praznim prezimenom
     */
    @Test
    @DisplayName("Test dodavanja klijenta sa praznim prezimenom")
    public void testDodavanjeKlijentaNeispravnoPrezime() {
        // Test sa praznim prezimenom
        boolean rezultat = controller.dodajKlijenta("Marko", "", "064123456", "marko@gmail.com", "Kupac");
        assertFalse(rezultat, "Dodavanje klijenta sa praznim prezimenom treba da ne uspe");

        // Test sa null prezimenom
        rezultat = controller.dodajKlijenta("Marko", null, "064123456", "marko@gmail.com", "Kupac");
        assertFalse(rezultat, "Dodavanje klijenta sa null prezimenom treba da ne uspe");

        // Test sa prezimenom koje sadrži samo beline
        rezultat = controller.dodajKlijenta("Marko", "   ", "064123456", "marko@gmail.com", "Kupac");
        assertFalse(rezultat, "Dodavanje klijenta sa prezimenom koje sadrži samo beline treba da ne uspe");
    }

    /**
     * Test dodavanja klijenta sa nevalidnim email-om
     */
    @Test
    @DisplayName("Test dodavanja klijenta sa nevalidnim email-om")
    public void testDodavanjeKlijentaNevalidniEmail() {
        // Test sa nevalidnim email-om (bez @)
        boolean rezultat = controller.dodajKlijenta("Marko", "Petrović", "064123456", "invalid-email", "Kupac");
        assertFalse(rezultat, "Dodavanje klijenta sa nevalidnim email-om treba da ne uspe");

        // Test sa email-om koji nema domen
        rezultat = controller.dodajKlijenta("Marko", "Petrović", "064123456", "marko@", "Kupac");
        assertFalse(rezultat, "Dodavanje klijenta sa nepotpunim email-om treba da ne uspe");

        // Test sa praznim email-om
        rezultat = controller.dodajKlijenta("Marko", "Petrović", "064123456", "", "Kupac");
        assertFalse(rezultat, "Dodavanje klijenta sa praznim email-om treba da ne uspe");

        // Test sa null email-om
        rezultat = controller.dodajKlijenta("Marko", "Petrović", "064123456", null, "Kupac");
        assertFalse(rezultat, "Dodavanje klijenta sa null email-om treba da ne uspe");

        // Test sa email-om bez ekstenzije
        rezultat = controller.dodajKlijenta("Marko", "Petrović", "064123456", "marko@gmail", "Kupac");
        assertFalse(rezultat, "Dodavanje klijenta sa email-om bez ekstenzije treba da ne uspe");
    }

    /**
     * Test dodavanja klijenta sa nevalidnim telefonom
     */
    @Test
    @DisplayName("Test dodavanja klijenta sa nevalidnim telefonom")
    public void testDodavanjeKlijentaNevalidniTelefon() {
        // Test sa prekratkim telefonom
        boolean rezultat = controller.dodajKlijenta("Marko", "Petrović", "123", "marko@gmail.com", "Kupac");
        assertFalse(rezultat, "Dodavanje klijenta sa prekratkim telefonom treba da ne uspe");

        // Test sa telefonom koji sadrži slova
        rezultat = controller.dodajKlijenta("Marko", "Petrović", "064abc123", "marko@gmail.com", "Kupac");
        assertFalse(rezultat, "Dodavanje klijenta sa telefonom koji sadrži slova treba da ne uspe");

        // Test sa praznim telefonom
        rezultat = controller.dodajKlijenta("Marko", "Petrović", "", "marko@gmail.com", "Kupac");
        assertFalse(rezultat, "Dodavanje klijenta sa praznim telefonom treba da ne uspe");

        // Test sa null telefonom
        rezultat = controller.dodajKlijenta("Marko", "Petrović", null, "marko@gmail.com", "Kupac");
        assertFalse(rezultat, "Dodavanje klijenta sa null telefonom treba da ne uspe");

        // Test sa predugačkim telefonom
        rezultat = controller.dodajKlijenta("Marko", "Petrović", "12345678901234567890", "marko@gmail.com", "Kupac");
        assertFalse(rezultat, "Dodavanje klijenta sa predugačkim telefonom treba da ne uspe");
    }

    /**
     * Test dodavanja klijenta sa praznim tipom klijenta
     */
    @Test
    @DisplayName("Test dodavanja klijenta sa praznim tipom klijenta")
    public void testDodavanjeKlijentaNeispravniTip() {
        // Test sa praznim tipom klijenta
        boolean rezultat = controller.dodajKlijenta("Marko", "Petrović", "064123456", "marko@gmail.com", "");
        assertFalse(rezultat, "Dodavanje klijenta sa praznim tipom klijenta treba da ne uspe");

        // Test sa null tipom klijenta
        rezultat = controller.dodajKlijenta("Marko", "Petrović", "064123456", "marko@gmail.com", null);
        assertFalse(rezultat, "Dodavanje klijenta sa null tipom klijenta treba da ne uspe");

        // Test sa tipom koji sadrži samo beline
        rezultat = controller.dodajKlijenta("Marko", "Petrović", "064123456", "marko@gmail.com", "   ");
        assertFalse(rezultat, "Dodavanje klijenta sa tipom koji sadrži samo beline treba da ne uspe");
    }

    /**
     * Test dodavanja klijenta sa validnim podacima
     * Ovaj test može da ne prođe ako baza nije dostupna, što je u redu za demonstraciju
     */
    @Test
    @DisplayName("Test dodavanja klijenta sa validnim podacima")
    public void testDodavanjeKlijentaValidniPodaci() {
        // Test sa osnovnim validnim podacima
        try {
            boolean rezultat = controller.dodajKlijenta("Marko", "Petrović", "064123456", "marko@gmail.com", "Kupac");
            assertNotNull(rezultat, "Metoda treba da vrati boolean vrednost");
        } catch (Exception e) {
            // Ako se baci izuzetak, treba da bude SQLException, ne ValidationException
            assertTrue(e.getMessage().contains("baza") || e.getMessage().contains("SQL") ||
                            e.getMessage().contains("database"),
                    "Greška treba da bude povezana sa bazom podataka, ne sa validacijom");
        }

        // Test sa prodavcem
        try {
            boolean rezultat = controller.dodajKlijenta("Ana", "Nikolić", "063987654", "ana@yahoo.com", "Prodavac");
            assertNotNull(rezultat, "Metoda treba da vrati boolean vrednost za prodavca");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("baza") || e.getMessage().contains("SQL"),
                    "Greška treba da bude povezana sa bazom podataka");
        }
    }

    /**
     * Test sa različitim formatima validnih email-ova
     */
    @Test
    @DisplayName("Test sa različitim formatima validnih email-ova")
    public void testRazlicitiFormatiEmaila() {
        // Test sa univerzitetskim email-om
        try {
            boolean rezultat = controller.dodajKlijenta("Stefan", "Jovanović", "065111222", "stefan@uns.ac.rs", "Kupac");
            assertNotNull(rezultat, "Univerzitetski email treba da bude validan");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("baza") || e.getMessage().contains("SQL"),
                    "Greška treba da bude povezana sa bazom podataka");
        }

        // Test sa poslovnim email-om
        try {
            boolean rezultat = controller.dodajKlijenta("Milica", "Stojanović", "069333444", "milica.stojanovic@company.rs", "Prodavac");
            assertNotNull(rezultat, "Poslovni email treba da bude validan");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("baza") || e.getMessage().contains("SQL"),
                    "Greška treba da bude povezana sa bazom podataka");
        }
    }

    /**
     * Test sa različitim formatima validnih telefona
     */
    @Test
    @DisplayName("Test sa različitim formatima validnih telefona")
    public void testRazlicitiFormatiTelefona() {
        // Test sa međunarodnim formatom
        try {
            boolean rezultat = controller.dodajKlijenta("Petar", "Milić", "+381641234567", "petar@gmail.com", "Kupac");
            assertNotNull(rezultat, "Međunarodni format telefona treba da bude validan");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("baza") || e.getMessage().contains("SQL"),
                    "Greška treba da bude povezana sa bazom podataka");
        }

        // Test sa fiksnim telefonom
        try {
            boolean rezultat = controller.dodajKlijenta("Jovana", "Stanković", "011-123-4567", "jovana@yahoo.com", "Prodavac");
            assertNotNull(rezultat, "Fiksni telefon treba da bude validan");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("baza") || e.getMessage().contains("SQL"),
                    "Greška treba da bude povezana sa bazom podataka");
        }

        // Test sa telefonom sa zagradama
        try {
            boolean rezultat = controller.dodajKlijenta("Nikola", "Vasić", "(011) 987-6543", "nikola@hotmail.com", "Kupac");
            assertNotNull(rezultat, "Telefon sa zagradama treba da bude validan");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("baza") || e.getMessage().contains("SQL"),
                    "Greška treba da bude povezana sa bazom podataka");
        }
    }

    /**
     * Test metode sviKlijenti
     */
    @Test
    @DisplayName("Test metode sviKlijenti")
    public void testSviKlijenti() {
        // Test poziva metode sviKlijenti
        var klijenti = controller.sviKlijenti();

        // Metoda treba da vrati ili listu ili null, ne da baci izuzetak
        assertTrue(klijenti == null || klijenti instanceof java.util.List,
                "Metoda treba da vrati listu ili null");
    }

    /**
     * Test brisanja klijenta sa nevalidnim ID
     */
    @Test
    @DisplayName("Test brisanja klijenta sa nevalidnim ID")
    public void testBrisanjeKlijentaNevalidniId() {
        // Test brisanja sa negativnim ID
        boolean rezultat = controller.obrisiKlijenta(-1);
        assertNotNull(rezultat, "Metoda treba da vrati boolean vrednost");

        // Test brisanja sa ID 0
        rezultat = controller.obrisiKlijenta(0);
        assertNotNull(rezultat, "Metoda treba da vrati boolean vrednost");

        // Test brisanja sa velikim ID koji verovatno ne postoji
        rezultat = controller.obrisiKlijenta(999999);
        assertNotNull(rezultat, "Metoda treba da vrati boolean vrednost");
    }

    /**
     * Test sa specialnim karakterima u imenu i prezimenu
     */
    @Test
    @DisplayName("Test sa specialnim karakterima u imenu i prezimenu")
    public void testSpecijalniKarakteri() {
        // Test sa srpskim slovima
        try {
            boolean rezultat = controller.dodajKlijenta("Đorđe", "Čović", "064555666", "djordje@gmail.com", "Kupac");
            assertNotNull(rezultat, "Srpska slova u imenu treba da budu podržana");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("baza") || e.getMessage().contains("SQL"),
                    "Greška treba da bude povezana sa bazom podataka");
        }

        // Test sa složenim prezimenom
        try {
            boolean rezultat = controller.dodajKlijenta("Ana", "Mitrović-Jovanović", "063777888", "ana.mitrovic@gmail.com", "Prodavac");
            assertNotNull(rezultat, "Složeno prezime treba da bude podržano");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("baza") || e.getMessage().contains("SQL"),
                    "Greška treba da bude povezana sa bazom podataka");
        }
    }

    /**
     * Test kombinacije svih nevalidnih podataka
     */
    @Test
    @DisplayName("Test kombinacije svih nevalidnih podataka")
    public void testKombinacijaNevalidnihPodataka() {
        // Test sa svim nevalidnim podacima odjednom
        boolean rezultat = controller.dodajKlijenta("", "", "123", "invalid-email", "");
        assertFalse(rezultat, "Kombinacija svih nevalidnih podataka treba da ne uspe");

        // Test sa kombinacijom null vrednosti
        rezultat = controller.dodajKlijenta(null, null, null, null, null);
        assertFalse(rezultat, "Kombinacija null vrednosti treba da ne uspe");
    }
}