package com.realestate.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za Klijent model klasu
 * @author Student
 * @version 1.0
 */
public class KlijentTest {

    /** Test klijent */
    private Klijent klijent;

    /**
     * Setup metoda koja se izvršava pre svakog testa
     */
    @BeforeEach
    public void setUp() {
        klijent = new Klijent("Marko", "Petrović", "064123456", "marko@gmail.com", "Kupac");
    }

    /**
     * Test kreiranja klijenta sa validnim podacima
     */
    @Test
    @DisplayName("Test kreiranja klijenta sa validnim podacima")
    public void testKreiranjeKlijenta() {
        assertNotNull(klijent, "Klijent treba da bude kreiran");
        assertEquals("Marko", klijent.getIme(), "Ime klijenta treba da bude Marko");
        assertEquals("Petrović", klijent.getPrezime(), "Prezime klijenta treba da bude Petrović");
        assertEquals("064123456", klijent.getTelefon(), "Telefon klijenta treba da bude 064123456");
        assertEquals("marko@gmail.com", klijent.getEmail(), "Email klijenta treba da bude marko@gmail.com");
        assertEquals("Kupac", klijent.getTipKlijenta(), "Tip klijenta treba da bude Kupac");
    }

    /**
     * Test setovanja ID-ja klijenta
     */
    @Test
    @DisplayName("Test setovanja ID-ja klijenta")
    public void testSetovanjeId() {

        assertEquals(0, klijent.getId(), "Početni ID treba da bude 0");


        klijent.setId(10);
        assertEquals(10, klijent.getId(), "ID treba da bude postavljen na 10");


        klijent.setId(999);
        assertEquals(999, klijent.getId(), "ID treba da bude postavljen na 999");
    }

    /**
     * Test menjanja imena klijenta
     */
    @Test
    @DisplayName("Test menjanja imena klijenta")
    public void testMenjanjeImena() {

        assertEquals("Marko", klijent.getIme());


        klijent.setIme("Stefan");
        assertEquals("Stefan", klijent.getIme(), "Ime treba da bude promenjeno na Stefan");


        klijent.setIme("Ana Marija");
        assertEquals("Ana Marija", klijent.getIme(), "Složeno ime treba da bude podržano");


        klijent.setIme("Ja");
        assertEquals("Ja", klijent.getIme(), "Kratko ime treba da bude podržano");
    }

    /**
     * Test menjanja prezimena klijenta
     */
    @Test
    @DisplayName("Test menjanja prezimena klijenta")
    public void testMenjanjePrezimena() {

        assertEquals("Petrović", klijent.getPrezime());


        klijent.setPrezime("Nikolić");
        assertEquals("Nikolić", klijent.getPrezime(), "Prezime treba da bude promenjeno na Nikolić");


        klijent.setPrezime("Mitrović-Jovanović");
        assertEquals("Mitrović-Jovanović", klijent.getPrezime(), "Složeno prezime treba da bude podržano");
    }

    /**
     * Test menjanja telefona klijenta
     */
    @Test
    @DisplayName("Test menjanja telefona klijenta")
    public void testMenjanjeTelefona() {

        assertEquals("064123456", klijent.getTelefon());


        klijent.setTelefon("063987654");
        assertEquals("063987654", klijent.getTelefon(), "Telefon treba da bude promenjen na 063987654");


        klijent.setTelefon("+381-64-123-456");
        assertEquals("+381-64-123-456", klijent.getTelefon(), "Formatirani telefon treba da bude podržan");


        klijent.setTelefon("011-123-4567");
        assertEquals("011-123-4567", klijent.getTelefon(), "Fiksni telefon treba da bude podržan");
    }

    /**
     * Test menjanja email-a klijenta
     */
    @Test
    @DisplayName("Test menjanja email-a klijenta")
    public void testMenjanjeEmaila() {

        assertEquals("marko@gmail.com", klijent.getEmail());


        klijent.setEmail("stefan@yahoo.com");
        assertEquals("stefan@yahoo.com", klijent.getEmail(), "Email treba da bude promenjen na stefan@yahoo.com");


        klijent.setEmail("ana.petrovic@company.rs");
        assertEquals("ana.petrovic@company.rs", klijent.getEmail(), "Poslovni email treba da bude podržan");


        klijent.setEmail("student@uns.ac.rs");
        assertEquals("student@uns.ac.rs", klijent.getEmail(), "Univerzitetski email treba da bude podržan");
    }

    /**
     * Test menjanja tipa klijenta
     */
    @Test
    @DisplayName("Test menjanja tipa klijenta")
    public void testMenjanjeTipaKlijenta() {

        assertEquals("Kupac", klijent.getTipKlijenta());


        klijent.setTipKlijenta("Prodavac");
        assertEquals("Prodavac", klijent.getTipKlijenta(), "Tip klijenta treba da bude promenjen na Prodavac");


        klijent.setTipKlijenta("Kupac");
        assertEquals("Kupac", klijent.getTipKlijenta(), "Tip klijenta treba da bude vraćen na Kupac");
    }

    /**
     * Test kreiranja različitih tipova klijenata
     */
    @Test
    @DisplayName("Test kreiranja različitih tipova klijenata")
    public void testRazlicitiTipoviKlijenata() {

        Klijent prodavac = new Klijent("Ana", "Nikolić", "063987654", "ana@yahoo.com", "Prodavac");
        assertEquals("Ana", prodavac.getIme());
        assertEquals("Prodavac", prodavac.getTipKlijenta());


        Klijent kupac = new Klijent("Stefan", "Jovanović", "065555666", "stefan@hotmail.com", "Kupac");
        assertEquals("Stefan", kupac.getIme());
        assertEquals("Kupac", kupac.getTipKlijenta());
    }

    /**
     * Test sa različitim formatima podataka
     */
    @Test
    @DisplayName("Test sa različitim formatima podataka")
    public void testRazlicitiFormatiPodataka() {

        klijent.setIme("MARKO"); // Velika slova
        assertEquals("MARKO", klijent.getIme(), "Ime sa velikim slovima treba da bude podržano");

        klijent.setIme("marko"); // Mala slova
        assertEquals("marko", klijent.getIme(), "Ime sa malim slovima treba da bude podržano");


        klijent.setPrezime("Đorđević");
        assertEquals("Đorđević", klijent.getPrezime(), "Prezime sa specijalnim karakterima treba da bude podržano");

        klijent.setPrezime("Čović");
        assertEquals("Čović", klijent.getPrezime(), "Prezime sa č treba da bude podržano");
    }

    /**
     * Test kompletnog ažuriranja podataka klijenta
     */
    @Test
    @DisplayName("Test kompletnog ažuriranja podataka klijenta")
    public void testKompletoAzuriranje() {

        klijent.setIme("Nova");
        klijent.setPrezime("Klijent");
        klijent.setTelefon("069111222");
        klijent.setEmail("nova@klijent.com");
        klijent.setTipKlijenta("Prodavac");
        klijent.setId(555);


        assertEquals("Nova", klijent.getIme(), "Ime treba da bude ažurirano");
        assertEquals("Klijent", klijent.getPrezime(), "Prezime treba da bude ažurirano");
        assertEquals("069111222", klijent.getTelefon(), "Telefon treba da bude ažuriran");
        assertEquals("nova@klijent.com", klijent.getEmail(), "Email treba da bude ažuriran");
        assertEquals("Prodavac", klijent.getTipKlijenta(), "Tip klijenta treba da bude ažuriran");
        assertEquals(555, klijent.getId(), "ID treba da bude ažurirano");
    }

    /**
     * Test kreiranja klijenta sa minimalnim podacima
     */
    @Test
    @DisplayName("Test kreiranja klijenta sa minimalnim podacima")
    public void testMinimalniPodaci() {
        Klijent minKlijent = new Klijent("A", "B", "12345678", "a@b.c", "Kupac");

        assertEquals("A", minKlijent.getIme(), "Minimalno ime treba da bude podržano");
        assertEquals("B", minKlijent.getPrezime(), "Minimalno prezime treba da bude podržano");
        assertEquals("12345678", minKlijent.getTelefon(), "Minimalni telefon treba da bude podržan");
        assertEquals("a@b.c", minKlijent.getEmail(), "Minimalni email treba da bude podržan");
        assertEquals("Kupac", minKlijent.getTipKlijenta(), "Tip klijenta treba da bude postavljen");
    }
}