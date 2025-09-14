package com.realestate.database;

import com.realestate.model.Klijent;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO klasa za rad sa klijentima u bazi podataka
 * @author Student
 * @version 1.0
 */
public class KlijentDAO {

    /**
     * Dodaje novog klijenta u bazu podataka
     * @param klijent klijent za dodavanje
     * @throws SQLException ako dođe do greške pri radu sa bazom
     */
    public void dodajKlijenta(Klijent klijent) throws SQLException {
        String sql = "INSERT INTO klijenti (ime, prezime, telefon, email, tip_klijenta) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, klijent.getIme());
            pstmt.setString(2, klijent.getPrezime());
            pstmt.setString(3, klijent.getTelefon());
            pstmt.setString(4, klijent.getEmail());
            pstmt.setString(5, klijent.getTipKlijenta());
            pstmt.executeUpdate();
        }
    }

    /**
     * Vraća sve klijente iz baze podataka
     * @return lista svih klijenata
     * @throws SQLException ako dođe do greške pri radu sa bazom
     */
    public List<Klijent> sviKlijenti() throws SQLException {
        List<Klijent> klijenti = new ArrayList<>();
        String sql = "SELECT * FROM klijenti";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Klijent klijent = new Klijent(
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("telefon"),
                        rs.getString("email"),
                        rs.getString("tip_klijenta")
                );
                klijent.setId(rs.getInt("id"));
                klijenti.add(klijent);
            }
        }
        return klijenti;
    }

    /**
     * Ažurira postojećeg klijenta
     * @param klijent klijent sa ažuriranim podacima
     * @throws SQLException ako dođe do greške pri radu sa bazom
     */
    public void azurirajKlijenta(Klijent klijent) throws SQLException {
        String sql = "UPDATE klijenti SET ime=?, prezime=?, telefon=?, email=?, tip_klijenta=? WHERE id=?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, klijent.getIme());
            pstmt.setString(2, klijent.getPrezime());
            pstmt.setString(3, klijent.getTelefon());
            pstmt.setString(4, klijent.getEmail());
            pstmt.setString(5, klijent.getTipKlijenta());
            pstmt.setInt(6, klijent.getId());
            pstmt.executeUpdate();
        }
    }

    /**
     * Briše klijenta iz baze podataka
     * @param id ID klijenta za brisanje
     * @throws SQLException ako dođe do greške pri radu sa bazom
     */
    public void obrisiKlijenta(int id) throws SQLException {
        String sql = "DELETE FROM klijenti WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }
    }

    /**
     * Pronalazi klijenta po ID-ju
     * @param id ID klijenta
     * @return klijent ili null ako nije pronađen
     * @throws SQLException ako dođe do greške pri radu sa bazom
     */
    public Klijent pronadjiKlijenta(int id) throws SQLException {
        String sql = "SELECT * FROM klijenti WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                Klijent klijent = new Klijent(
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("telefon"),
                        rs.getString("email"),
                        rs.getString("tip_klijenta")
                );
                klijent.setId(rs.getInt("id"));
                return klijent;
            }
        }
        return null;
    }
}