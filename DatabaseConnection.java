package com.realestate.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Klasa za upravljanje konekcijom sa bazom podataka
 * @author Student
 * @version 1.0
 */
public class DatabaseConnection {
    private static final String DB_URL = "jdbc:sqlite:realestate.db";
    private static Connection connection;

    /**
     * Uspostavlja konekciju sa bazom podataka
     * @return Connection objekat
     * @throws SQLException ako dođe do greške pri konekciji
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
        }
        return connection;
    }

    /**
     * Kreira potrebne tabele u bazi podataka
     */
    public static void createTables() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {


            stmt.execute("CREATE TABLE IF NOT EXISTS agenti (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "ime TEXT NOT NULL," +
                    "prezime TEXT NOT NULL," +
                    "telefon TEXT NOT NULL," +
                    "email TEXT NOT NULL," +
                    "provizija REAL NOT NULL)");


            stmt.execute("CREATE TABLE IF NOT EXISTS klijenti (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "ime TEXT NOT NULL," +
                    "prezime TEXT NOT NULL," +
                    "telefon TEXT NOT NULL," +
                    "email TEXT NOT NULL," +
                    "tip_klijenta TEXT NOT NULL)");


            stmt.execute("CREATE TABLE IF NOT EXISTS nekretnine (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "tip TEXT NOT NULL," +
                    "adresa TEXT NOT NULL," +
                    "cena REAL NOT NULL," +
                    "agent_id INTEGER NOT NULL," +
                    "status TEXT NOT NULL," +
                    "FOREIGN KEY (agent_id) REFERENCES agenti(id))");


            stmt.execute("CREATE TABLE IF NOT EXISTS transakcije (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nekretnina_id INTEGER NOT NULL," +
                    "klijent_id INTEGER NOT NULL," +
                    "agent_id INTEGER NOT NULL," +
                    "cena_transakcije REAL NOT NULL," +
                    "datum_transakcije TEXT NOT NULL," +
                    "tip_transakcije TEXT NOT NULL," +
                    "FOREIGN KEY (nekretnina_id) REFERENCES nekretnine(id)," +
                    "FOREIGN KEY (klijent_id) REFERENCES klijenti(id)," +
                    "FOREIGN KEY (agent_id) REFERENCES agenti(id))");


            stmt.execute("CREATE TABLE IF NOT EXISTS pregledi (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nekretnina_id INTEGER NOT NULL," +
                    "klijent_id INTEGER NOT NULL," +
                    "agent_id INTEGER NOT NULL," +
                    "datum_pregleda TEXT NOT NULL," +
                    "napomena TEXT," +
                    "FOREIGN KEY (nekretnina_id) REFERENCES nekretnine(id)," +
                    "FOREIGN KEY (klijent_id) REFERENCES klijenti(id)," +
                    "FOREIGN KEY (agent_id) REFERENCES agenti(id))");

            System.out.println("Tabele su uspešno kreirane!");

        } catch (SQLException e) {
            System.err.println("Greška pri kreiranju tabela: " + e.getMessage());
        }
    }
}