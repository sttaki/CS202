package com.realestate.database;

import com.realestate.model.Nekretnina;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO klasa za rad sa nekretninama u bazi podataka
 * @author Student
 * @version 1.0
 */
public class NekretninaDAO {

    /**
     * Dodaje novu nekretninu u bazu podataka
     * @param nekretnina nekretnina za dodavanje
     * @throws SQLException ako dođe do greške pri radu sa bazom
     */
    public void dodajNekretninu(Nekretnina nekretnina) throws SQLException {
        String sql = "INSERT INTO nekretnine (tip, adresa, cena, agent_id, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nekretnina.getTip());
            pstmt.setString(2, nekretnina.getAdresa());
            pstmt.setDouble(3, nekretnina.getCena());
            pstmt.setInt(4, nekretnina.getAgentId());
            pstmt.setString(5, nekretnina.getStatus());
            pstmt.executeUpdate();
        }
    }

    /**
     * Vraća sve nekretnine iz baze podataka
     * @return lista svih nekretnina
     * @throws SQLException ako dođe do greške pri radu sa bazom
     */
    public List<Nekretnina> sveNekretnine() throws SQLException {
        List<Nekretnina> nekretnine = new ArrayList<>();
        String sql = "SELECT * FROM nekretnine";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Nekretnina nekretnina = new Nekretnina(
                        rs.getString("tip"),
                        rs.getString("adresa"),
                        rs.getDouble("cena"),
                        rs.getInt("agent_id"),
                        rs.getString("status")
                );
                nekretnina.setId(rs.getInt("id"));
                nekretnine.add(nekretnina);
            }
        }
        return nekretnine;
    }

    /**
     * Ažurira postojeću nekretninu
     * @param nekretnina nekretnina sa ažuriranim podacima
     * @throws SQLException ako dođe do greške pri radu sa bazom
     */
    public void azurirajNekretninu(Nekretnina nekretnina) throws SQLException {
        String sql = "UPDATE nekretnine SET tip=?, adresa=?, cena=?, agent_id=?, status=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nekretnina.getTip());
            pstmt.setString(2, nekretnina.getAdresa());
            pstmt.setDouble(3, nekretnina.getCena());
            pstmt.setInt(4, nekretnina.getAgentId());
            pstmt.setString(5, nekretnina.getStatus());
            pstmt.setInt(6, nekretnina.getId());
            pstmt.executeUpdate();
        }
    }

    /**
     * Briše nekretninu iz baze podataka
     * @param id ID nekretnine za brisanje
     * @throws SQLException ako dođe do greške pri radu sa bazom
     */
    public void obrisiNekretninu(int id) throws SQLException {
        String sql = "DELETE FROM nekretnine WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    /**
     * Pronalazi nekretninu po ID-ju
     * @param id ID nekretnine
     * @return nekretnina ili null ako nije pronađena
     * @throws SQLException ako dođe do greške pri radu sa bazom
     */
    public Nekretnina pronadjiNekretninu(int id) throws SQLException {
        String sql = "SELECT * FROM nekretnine WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Nekretnina nekretnina = new Nekretnina(
                        rs.getString("tip"),
                        rs.getString("adresa"),
                        rs.getDouble("cena"),
                        rs.getInt("agent_id"),
                        rs.getString("status")
                );
                nekretnina.setId(rs.getInt("id"));
                return nekretnina;
            }
        }
        return null;
    }
}