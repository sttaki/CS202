package com.realestate.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit testovi za Nekretnina model klasu
 * @author Student
 * @version 1.0
 */
public class NekretninaTest {

    /** Test nekretnina */
    private Nekretnina nekretnina;

    /**
     * Setup metoda koja se izvršava pre svakog testa
     */
    @BeforeEach
    public void setUp() {
        nekretnina = new Nekretnina("Stan", "Bulevar Nemanjića 1", 50000.0, 1, "Dostupna");
    }

    /**
     * Test kreiranja nekretnine sa validnim podacima
     */
    @Test
    @DisplayName("Test kreiranja nekretnine sa validnim podacima")
    public void testKreiranjeNekretnine() {
        assertNotNull(nekretnina, "Nekretnina treba da bude kreirana");
        assertEquals("Stan", nekretnina.getTip(), "Tip nekretnine treba da bude Stan");
        assertEquals("Bulevar Nemanjića 1", nekretnina.getAdresa(), "Adresa treba da bude Bulevar Nemanjića 1");
        assertEquals(50000.0, nekretnina.getCena(), 0.01, "Cena treba da bude 50000.0");
        assertEquals(1, nekretnina.getAgentId(), "Agent ID treba da bude 1");
        assertEquals("Dostupna", nekretnina.getStatus(), "Status treba da bude Dostupna");
    }

    /**
     * Test setovanja ID-ja nekretnine
     */
    @Test
    @DisplayName("Test setovanja ID-ja nekretnine")
    public void testSetovanjeId() {

        assertEquals(0, nekretnina.getId(), "Početni ID treba da bude 0");


        nekretnina.setId(5);
        assertEquals(5, nekretnina.getId(), "ID treba da bude postavljen na 5");


        nekretnina.setId(-1);
        assertEquals(-1, nekretnina.getId(), "ID može biti i negativan");
    }

    /**
     * Test menjanja tipa nekretnine
     */
    @Test
    @DisplayName("Test menjanja tipa nekretnine")
    public void testMenjanjeTipa() {

        assertEquals("Stan", nekretnina.getTip());


        nekretnina.setTip("Kuća");
        assertEquals("Kuća", nekretnina.getTip(), "Tip treba da bude promenjen na Kuća");


        nekretnina.setTip("Lokal");
        assertEquals("Lokal", nekretnina.getTip(), "Tip treba da bude promenjen na Lokal");

        nekretnina.setTip("Plac");
        assertEquals("Plac", nekretnina.getTip(), "Tip treba da bude promenjen na Plac");
    }

    /**
     * Test menjanja statusa nekretnine
     */
    @Test
    @DisplayName("Test menjanja statusa nekretnine")
    public void testMenjanjeStatusa() {

        assertEquals("Dostupna", nekretnina.getStatus());


        nekretnina.setStatus("Rezervisana");
        assertEquals("Rezervisana", nekretnina.getStatus(), "Status treba da bude promenjen na Rezervisana");


        nekretnina.setStatus("Prodana");
        assertEquals("Prodana", nekretnina.getStatus(), "Status treba da bude promenjen na Prodana");
    }

    /**
     * Test menjanja cene nekretnine
     */
    @Test
    @DisplayName("Test menjanja cene nekretnine")
    public void testMenjanjeCene() {

        assertEquals(50000.0, nekretnina.getCena(), 0.01);


        nekretnina.setCena(75000.0);
        assertEquals(75000.0, nekretnina.getCena(), 0.01, "Cena treba da bude promenjena na 75000.0");


        nekretnina.setCena(42500.50);
        assertEquals(42500.50, nekretnina.getCena(), 0.01, "Cena treba da bude 42500.50");


        nekretnina.setCena(0.0);
        assertEquals(0.0, nekretnina.getCena(), 0.01, "Cena može biti postavljena na 0");
    }

    /**
     * Test menjanja adrese nekretnine
     */
    @Test
    @DisplayName("Test menjanja adrese nekretnine")
    public void testMenjanjeAdrese() {

        assertEquals("Bulevar Nemanjića 1", nekretnina.getAdresa());


        nekretnina.setAdresa("Miloša Obilića 5");
        assertEquals("Miloša Obilića 5", nekretnina.getAdresa(),
                "Adresa treba da bude promenjena na Miloša Obilića 5");


        String dugaAdresa = "Knez Mihailova 10, stan 15, sprat 3, Stari grad, Beograd";
        nekretnina.setAdresa(dugaAdresa);
        assertEquals(dugaAdresa, nekretnina.getAdresa(), "Adresa treba da bude postavljena na dugu adresu");
    }

    /**
     * Test menjanja Agent ID-ja
     */
    @Test
    @DisplayName("Test menjanja Agent ID-ja")
    public void testMenjanjeAgentId() {

        assertEquals(1, nekretnina.getAgentId());


        nekretnina.setAgentId(2);
        assertEquals(2, nekretnina.getAgentId(), "Agent ID treba da bude promenjen na 2");

        nekretnina.setAgentId(10);
        assertEquals(10, nekretnina.getAgentId(), "Agent ID treba da bude promenjen na 10");
    }

    /**
     * Test kreiranja različitih tipova nekretnina
     */
    @Test
    @DisplayName("Test kreiranja različitih tipova nekretnina")
    public void testRazlicitiTipoviNekretnina() {

        Nekretnina kuca = new Nekretnina("Kuća", "Cara Dušana 15", 120000.0, 2, "Dostupna");
        assertEquals("Kuća", kuca.getTip());
        assertEquals(120000.0, kuca.getCena(), 0.01);


        Nekretnina lokal = new Nekretnina("Lokal", "Knez Mihailova 25", 200000.0, 3, "Rezervisana");
        assertEquals("Lokal", lokal.getTip());
        assertEquals("Rezervisana", lokal.getStatus());


        Nekretnina plac = new Nekretnina("Plac", "Periferija bb", 30000.0, 1, "Prodana");
        assertEquals("Plac", plac.getTip());
        assertEquals("Prodana", plac.getStatus());
    }

    /**
     * Test sa graničnim vrednostima
     */
    @Test
    @DisplayName("Test sa graničnim vrednostima")
    public void testGranicneVrednosti() {

        nekretnina.setCena(1000000.0);
        assertEquals(1000000.0, nekretnina.getCena(), 0.01, "Visoka cena treba da bude podržana");


        nekretnina.setCena(0.01);
        assertEquals(0.01, nekretnina.getCena(), 0.001, "Mala cena treba da bude podržana");


        nekretnina.setAgentId(999999);
        assertEquals(999999, nekretnina.getAgentId(), "Veliki Agent ID treba da bude podržan");
    }
}