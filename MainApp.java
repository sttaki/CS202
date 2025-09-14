package com.realestate.view;

import com.realestate.database.DatabaseConnection;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Glavna aplikacija za sistem upravljanja agencijom za nekretnine
 * @author Student
 * @version 1.0
 */
public class MainApp extends Application {

    /**
     * Početak JavaFX aplikacije
     * @param primaryStage glavni stage aplikacije
     */
    @Override
    public void start(Stage primaryStage) {
        // Kreiranje tabela u bazi podataka
        System.out.println("Kreiranje baze podataka...");
        DatabaseConnection.createTables();

        // Kreiranje glavnog layout-a
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        // Naslov aplikacije
        Label naslov = new Label("Sistem za upravljanje agencijom za nekretnine");

        // Kreiranje glavnih dugmića
        Button btnNekretnine = new Button("Upravljanje nekretninama");
        Button btnKlijenti = new Button("Upravljanje klijentima");
        Button btnAgenti = new Button("Upravljanje agentima");

        // Postavljanje veličine dugmića
        btnNekretnine.setPrefWidth(250);
        btnKlijenti.setPrefWidth(250);
        btnAgenti.setPrefWidth(250);

        // Event handleri za dugmiće
        btnNekretnine.setOnAction(e -> otvoriNekretnineProzor());
        btnKlijenti.setOnAction(e -> otvoriKlijenteProzor());
        btnAgenti.setOnAction(e -> otvoriAgenteProzor());

        // Dodavanje svih elemenata u layout
        root.getChildren().addAll(naslov, btnNekretnine, btnKlijenti, btnAgenti);

        // Kreiranje i prikaz scene
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setTitle("Agencija za nekretnine");
        primaryStage.setScene(scene);
        primaryStage.show();

        System.out.println("Aplikacija je uspešno pokrenuta!");
    }

    /**
     * Otvara prozor za upravljanje nekretninama
     */
    private void otvoriNekretnineProzor() {
        try {
            NekretnineProzor nekretnineProzor = new NekretnineProzor();
            nekretnineProzor.show();
        } catch (Exception e) {
            System.err.println("Greška pri otvaranju prozora za nekretnine: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Otvara prozor za upravljanje klijentima
     */
    private void otvoriKlijenteProzor() {
        try {
            KlijentiProzor klijentiProzor = new KlijentiProzor();
            klijentiProzor.show();
        } catch (Exception e) {
            System.err.println("Greška pri otvaranju prozora za klijente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Otvara prozor za upravljanje agentima (jednostavan demo)
     */
    private void otvoriAgenteProzor() {
        Stage stage = new Stage();

        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);

        Label label = new Label("Upravljanje agentima");
        Label infoLabel = new Label("Ova funkcionalnost će biti dodana u budućim verzijama.");

        Button zatvoriBtn = new Button("Zatvori");
        zatvoriBtn.setOnAction(e -> stage.close());

        vbox.getChildren().addAll(label, infoLabel, zatvoriBtn);

        Scene scene = new Scene(vbox, 300, 150);
        stage.setScene(scene);
        stage.setTitle("Agenti");
        stage.show();
    }

    /**
     * Main metoda - ulazna tačka aplikacije
     * @param args argumenti komandne linije
     */
    public static void main(String[] args) {
        System.out.println("Pokretanje aplikacije...");
        launch(args);
    }
}