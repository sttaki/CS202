package com.realestate.util;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.util.Optional;

/**
 * Utility klasa za prikaz alert poruka
 * @author Student
 * @version 1.0
 */
public class AlertHelper {

    /**
     * Prikazuje poruku o grešci
     * @param naslov naslov alert prozora
     * @param poruka sadržaj poruke
     */
    public static void prikaziGresku(String naslov, String poruka) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Greška");
        alert.setHeaderText(naslov);
        alert.setContentText(poruka);
        alert.showAndWait();
    }

    /**
     * Prikazuje poruku o uspešno izvršenoj operaciji
     * @param naslov naslov alert prozora
     * @param poruka sadržaj poruke
     */
    public static void prikaziUspeh(String naslov, String poruka) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacija");
        alert.setHeaderText(naslov);
        alert.setContentText(poruka);
        alert.showAndWait();
    }

    /**
     * Prikazuje poruku upozorenja
     * @param naslov naslov alert prozora
     * @param poruka sadržaj poruke
     */
    public static void prikaziUpozorenje(String naslov, String poruka) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Upozorenje");
        alert.setHeaderText(naslov);
        alert.setContentText(poruka);
        alert.showAndWait();
    }

    /**
     * Prikazuje poruku za potvrdu i vraća rezultat
     * @param naslov naslov alert prozora
     * @param poruka sadržaj poruke
     * @return true ako je korisnik potvrdio, false inače
     */
    public static boolean prikaziPotvrdu(String naslov, String poruka) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potvrda");
        alert.setHeaderText(naslov);
        alert.setContentText(poruka);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == ButtonType.OK;
    }

    /**
     * Prikazuje informativnu poruku
     * @param naslov naslov alert prozora
     * @param poruka sadržaj poruke
     */
    public static void prikaziInformaciju(String naslov, String poruka) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informacija");
        alert.setHeaderText(naslov);
        alert.setContentText(poruka);
        alert.showAndWait();
    }
}