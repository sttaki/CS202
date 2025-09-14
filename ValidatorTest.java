package com.realestate.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za Validator klasu
 * @author Student
 * @version 1.0
 */
public class ValidatorTest {

    /**
     * Test validacije email adrese sa validnim email-ovima
     */
    @Test
    @DisplayName("Test validacije validnih email adresa")
    public void testValidirajEmailValidni() {

        assertTrue(Validator.validirajEmail("test@example.com"),
                "test@example.com treba da bude validan email");
        assertTrue(Validator.validirajEmail("user.name@domain.co.rs"),
                "user.name@domain.co.rs treba da bude validan email");
        assertTrue(Validator.validirajEmail("test123@gmail.com"),
                "test123@gmail.com treba da bude validan email");
        assertTrue(Validator.validirajEmail("marko@yahoo.com"),
                "marko@yahoo.com treba da bude validan email");
        assertTrue(Validator.validirajEmail("ana.petrovic@uns.ac.rs"),
                "ana.petrovic@uns.ac.rs treba da bude validan email");
    }

    /**
     * Test validacije email adrese sa nevalidnim email-ovima
     */
    @Test
    @DisplayName("Test validacije nevalidnih email adresa")
    public void testValidirajEmailNevalidni() {

        assertFalse(Validator.validirajEmail("invalid-email"),
                "invalid-email ne treba da bude validan email");
        assertFalse(Validator.validirajEmail("test@"),
                "test@ ne treba da bude validan email");
        assertFalse(Validator.validirajEmail("test.com"),
                "test.com ne treba da bude validan email");
        assertFalse(Validator.validirajEmail(""),
                "prazan string ne treba da bude validan email");
        assertFalse(Validator.validirajEmail(null),
                "null ne treba da bude validan email");
    }

    /**
     * Test validacije telefona sa validnim brojevima
     */
    @Test
    @DisplayName("Test validacije validnih brojeva telefona")
    public void testValidirajTelefonValidni() {

        assertTrue(Validator.validirajTelefon("064123456"),
                "064123456 treba da bude validan telefon");
        assertTrue(Validator.validirajTelefon("+381641234567"),
                "+381641234567 treba da bude validan telefon");
        assertTrue(Validator.validirajTelefon("063-123-456"),
                "063-123-456 treba da bude validan telefon");
        assertTrue(Validator.validirajTelefon("011 123 4567"),
                "011 123 4567 treba da bude validan telefon");
        assertTrue(Validator.validirajTelefon("(011) 123-4567"),
                "(011) 123-4567 treba da bude validan telefon");
    }

    /**
     * Test validacije telefona sa nevalidnim brojevima
     */
    @Test
    @DisplayName("Test validacije nevalidnih brojeva telefona")
    public void testValidirajTelefonNevalidni() {

        assertFalse(Validator.validirajTelefon("123"),
                "123 ne treba da bude validan telefon (prekratak)");
        assertFalse(Validator.validirajTelefon("abcd1234"),
                "abcd1234 ne treba da bude validan telefon (sadrži slova)");
        assertFalse(Validator.validirajTelefon(""),
                "prazan string ne treba da bude validan telefon");
        assertFalse(Validator.validirajTelefon(null),
                "null ne treba da bude validan telefon");
        assertFalse(Validator.validirajTelefon("12345678901234567890"),
                "predugačak broj ne treba da bude validan telefon");
    }

    /**
     * Test provere da li je string prazan
     */
    @Test
    @DisplayName("Test provere praznog stringa")
    public void testJePrazan() {

        assertTrue(Validator.jePrazan(null),
                "null treba da bude prepoznat kao prazan");
        assertTrue(Validator.jePrazan(""),
                "prazan string treba da bude prepoznat kao prazan");
        assertTrue(Validator.jePrazan("   "),
                "string sa samo belinama treba da bude prepoznat kao prazan");
        assertTrue(Validator.jePrazan("\t\n"),
                "string sa tab i newline treba da bude prepoznat kao prazan");


        assertFalse(Validator.jePrazan("test"),
                "test ne treba da bude prepoznat kao prazan");
        assertFalse(Validator.jePrazan("   test   "),
                "string sa sadržajem ne treba da bude prepoznat kao prazan");
        assertFalse(Validator.jePrazan("a"),
                "jedan karakter ne treba da bude prepoznat kao prazan");
    }

    /**
     * Test validacije cene
     */
    @Test
    @DisplayName("Test validacije cene nekretnine")
    public void testValidirajCenu() {

        assertTrue(Validator.validirajCenu(100.0),
                "100.0 treba da bude validna cena");
        assertTrue(Validator.validirajCenu(50000.5),
                "50000.5 treba da bude validna cena");
        assertTrue(Validator.validirajCenu(0.1),
                "0.1 treba da bude validna cena");
        assertTrue(Validator.validirajCenu(1000000),
                "1000000 treba da bude validna cena");


        assertFalse(Validator.validirajCenu(0),
                "0 ne treba da bude validna cena");
        assertFalse(Validator.validirajCenu(-100),
                "-100 ne treba da bude validna cena");
        assertFalse(Validator.validirajCenu(-0.1),
                "-0.1 ne treba da bude validna cena");
    }

    /**
     * Test validacije da li je string broj
     */
    @Test
    @DisplayName("Test provere da li je string broj")
    public void testJeBroj() {

        assertTrue(Validator.jeBroj("123"),
                "123 treba da bude prepoznat kao broj");
        assertTrue(Validator.jeBroj("123.45"),
                "123.45 treba da bude prepoznat kao broj");
        assertTrue(Validator.jeBroj("-123"),
                "-123 treba da bude prepoznat kao broj");
        assertTrue(Validator.jeBroj("0"),
                "0 treba da bude prepoznat kao broj");
        assertTrue(Validator.jeBroj("0.0"),
                "0.0 treba da bude prepoznat kao broj");


        assertFalse(Validator.jeBroj("abc"),
                "abc ne treba da bude prepoznat kao broj");
        assertFalse(Validator.jeBroj(""),
                "prazan string ne treba da bude prepoznat kao broj");
        assertFalse(Validator.jeBroj(null),
                "null ne treba da bude prepoznat kao broj");
        assertFalse(Validator.jeBroj("12.34.56"),
                "12.34.56 ne treba da bude prepoznat kao broj");
        assertFalse(Validator.jeBroj("12abc"),
                "12abc ne treba da bude prepoznat kao broj");
    }

    /**
     * Test validacije da li je string ceo broj
     */
    @Test
    @DisplayName("Test provere da li je string ceo broj")
    public void testJeCeoBroj() {

        assertTrue(Validator.jeCeoBroj("123"),
                "123 treba da bude prepoznat kao ceo broj");
        assertTrue(Validator.jeCeoBroj("-123"),
                "-123 treba da bude prepoznat kao ceo broj");
        assertTrue(Validator.jeCeoBroj("0"),
                "0 treba da bude prepoznat kao ceo broj");


        assertFalse(Validator.jeCeoBroj("123.45"),
                "123.45 ne treba da bude prepoznat kao ceo broj");
        assertFalse(Validator.jeCeoBroj("abc"),
                "abc ne treba da bude prepoznat kao ceo broj");
        assertFalse(Validator.jeCeoBroj(""),
                "prazan string ne treba da bude prepoznat kao ceo broj");
        assertFalse(Validator.jeCeoBroj(null),
                "null ne treba da bude prepoznat kao ceo broj");
    }
}