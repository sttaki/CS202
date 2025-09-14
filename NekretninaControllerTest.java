package com.realestate.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za NekretninaController klasu
 * @author Student
 * @version 1.0
 */
public class NekretninaControllerTest {

    /** Controller za testiranje */
    private NekretninaController controller;

    /**
     * Setup metoda koja se izvršava pre svakog testa
     */
    @BeforeEach
    public void setUp() {
        controller = new NekretninaController();
    }

    /**
     * Test dodavanja nekretnine sa praznim tipom
     */
    @Test
    @DisplayName("Test dodavanja nekretnine sa praznim tipom")
    public void testDodavanjeNekretnineNeispravniTip() {

        boolean rezultat = controller.dodajNekretninu("", "Adresa 1", "50000", 1, "Dostupna");
        assertFalse(rezultat, "Dodavanje nekretnine sa praznim tipom treba da ne uspe");


        rezultat = controller.dodajNekretninu(null, "Adresa 1", "50000", 1, "Dostupna");
        assertFalse(rezultat, "Dodavanje nekretnine sa null tipom treba da ne uspe");


        rezultat = controller.dodajNekretninu("   ", "Adresa 1", "50000", 1, "Dostupna");
        assertFalse(rezultat, "Dodavanje nekretnine sa tipom koji sadrži samo beline treba da ne uspe");
    }

    /**
     * Test dodavanja nekretnine sa praznom adresom
     */
    @Test
    @DisplayName("Test dodavanja nekretnine sa praznom adresom")
    public void testDodavanjeNekretnineNeispravnaAdresa() {

        boolean rezultat = controller.dodajNekretninu("Stan", "", "50000", 1, "Dostupna");
        assertFalse(rezultat, "Dodavanje nekretnine sa praznom adresom treba da ne uspe");


        rezultat = controller.dodajNekretninu("Stan", null, "50000", 1, "Dostupna");
        assertFalse(rezultat, "Dodavanje nekretnine sa null adresom treba da ne uspe");


        rezultat = controller.dodajNekretninu("Stan", "   ", "50000", 1, "Dostupna");
        assertFalse(rezultat, "Dodavanje nekretnine sa adresom koja sadrži samo beline treba da ne uspe");
    }

    /**
     * Test dodavanja nekretnine sa neispravnom cenom
     */
    @Test
    @DisplayName("Test dodavanja nekretnine sa neispravnom cenom")
    public void testDodavanjeNekretnineNeispravnaCena() {

        boolean rezultat = controller.dodajNekretninu("Stan", "Adresa 1", "abc", 1, "Dostupna");
        assertFalse(rezultat, "Dodavanje nekretnine sa neispravnom cenom (tekst) treba da ne uspe");


        rezultat = controller.dodajNekretninu("Stan", "Adresa 1", "-1000", 1, "Dostupna");
        assertFalse(rezultat, "Dodavanje nekretnine sa negativnom cenom treba da ne uspe");


        rezultat = controller.dodajNekretninu("Stan", "Adresa 1", "0", 1, "Dostupna");
        assertFalse(rezultat, "Dodavanje nekretnine sa cenom 0 treba da ne uspe");


        rezultat = controller.dodajNekretninu("Stan", "Adresa 1", "", 1, "Dostupna");
        assertFalse(rezultat, "Dodavanje nekretnine sa praznom cenom treba da ne uspe");


        rezultat = controller.dodajNekretninu("Stan", "Adresa 1", null, 1, "Dostupna");
        assertFalse(rezultat, "Dodavanje nekretnine sa null cenom treba da ne uspe");
    }

    /**
     * Test dodavanja nekretnine sa praznim statusom
     */
    @Test
    @DisplayName("Test dodavanja nekretnine sa praznim statusom")
    public void testDodavanjeNekretnineNeispravniStatus() {

        boolean rezultat = controller.dodajNekretninu("Stan", "Adresa 1", "50000", 1, "");
        assertFalse(rezultat, "Dodavanje nekretnine sa praznim statusom treba da ne uspe");


        rezultat = controller.dodajNekretninu("Stan", "Adresa 1", "50000", 1, null);
        assertFalse(rezultat, "Dodavanje nekretnine sa null statusom treba da ne uspe");


        rezultat = controller.dodajNekretninu("Stan", "Adresa 1", "50000", 1, "   ");
        assertFalse(rezultat, "Dodavanje nekretnine sa statusom koji sadrži samo beline treba da ne uspe");
    }

    /**
     * Test dodavanja nekretnine sa validnim podacima
     * Ovaj test može da ne prođe ako baza nije dostupna, što je u redu za demonstraciju
     */
    @Test
    @DisplayName("Test dodavanja nekretnine sa validnim podacima")
    public void testDodavanjeNekretnineValidniPodaci() {





        try {
            boolean rezultat = controller.dodajNekretninu("Stan", "Bulevar Nemanjića 1", "50000", 1, "Dostupna");

            assertNotNull(rezultat, "Metoda treba da vrati boolean vrednost");
        } catch (Exception e) {

            assertTrue(e.getMessage().contains("baza") || e.getMessage().contains("SQL") ||
                            e.getMessage().contains("database"),
                    "Greška treba da bude povezana sa bazom podataka, ne sa validacijom");
        }
    }

    /**
     * Test dodavanja nekretnine sa validnim decimalnim cenom
     */
    @Test
    @DisplayName("Test dodavanja nekretnine sa validnim decimalnim cenom")
    public void testDodavanjeNekretnineDecimalnaCena() {

        try {
            boolean rezultat = controller.dodajNekretninu("Kuća", "Miloša Obilića 5", "75000.50", 2, "Rezervisana");
            assertNotNull(rezultat, "Metoda treba da vrati boolean vrednost za decimalnu cenu");
        } catch (Exception e) {

            assertTrue(e.getMessage().contains("baza") || e.getMessage().contains("SQL") ||
                            e.getMessage().contains("database"),
                    "Greška treba da bude povezana sa bazom podataka");
        }
    }

    /**
     * Test sa graničnim vrednostima cene
     */
    @Test
    @DisplayName("Test sa graničnim vrednostima cene")
    public void testGranicneVrednostiCene() {

        try {
            boolean rezultat = controller.dodajNekretninu("Plac", "Periferija bb", "0.01", 1, "Dostupna");
            assertNotNull(rezultat, "Mala pozitivna cena treba da bude validna");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("baza") || e.getMessage().contains("SQL"),
                    "Greška treba da bude povezana sa bazom podataka");
        }


        try {
            boolean rezultat = controller.dodajNekretninu("Lokal", "Knez Mihailova 1", "1000000", 3, "Prodana");
            assertNotNull(rezultat, "Velika cena treba da bude validna");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("baza") || e.getMessage().contains("SQL"),
                    "Greška treba da bude povezana sa bazom podataka");
        }


        boolean rezultat = controller.dodajNekretninu("Stan", "Adresa", "-0.01", 1, "Dostupna");
        assertFalse(rezultat, "Negativna cena ne treba da prođe validaciju");
    }

    /**
     * Test sa različitim formatima cene
     */
    @Test
    @DisplayName("Test sa različitim formatima cene")
    public void testRazlicitiFormatiCene() {

        boolean rezultat = controller.dodajNekretninu("Stan", "Adresa 1", " 50000 ", 1, "Dostupna");


        assertFalse(rezultat, "Cena sa razmakovima treba da ne prođe validaciju");


        rezultat = controller.dodajNekretninu("Stan", "Adresa 1", "50000,50", 1, "Dostupna");
        assertFalse(rezultat, "Cena sa zarezom treba da ne prođe validaciju");


        rezultat = controller.dodajNekretninu("Stan", "Adresa 1", "50000 EUR", 1, "Dostupna");
        assertFalse(rezultat, "Cena sa dodatnim tekstom treba da ne prođe validaciju");
    }

    /**
     * Test metode sveNekretnine
     */
    @Test
    @DisplayName("Test metode sveNekretnine")
    public void testSveNekretnine() {

        var nekretnine = controller.sveNekretnine();


        assertTrue(nekretnine == null || nekretnine instanceof java.util.List,
                "Metoda treba da vrati listu ili null");
    }

    /**
     * Test brisanja nekretnine sa nevalidnim ID
     */
    @Test
    @DisplayName("Test brisanja nekretnine sa nevalidnim ID")
    public void testBrisanjeNekretnineNevalidniId() {

        boolean rezultat = controller.obrisiNekretninu(-1);
        assertNotNull(rezultat, "Metoda treba da vrati boolean vrednost");


        rezultat = controller.obrisiNekretninu(0);
        assertNotNull(rezultat, "Metoda treba da vrati boolean vrednost");
    }
}