package com.realestate.view;

import com.realestate.controller.NekretninaController;
import com.realestate.model.Nekretnina;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Prozor za upravljanje nekretninama
 * @author Student
 * @version 1.0
 */
public class NekretnineProzor {
    /** Stage ovog prozora */
    private Stage stage;
    /** Controller za rad sa nekretninama */
    private NekretninaController controller;
    /** Tabela za prikaz nekretnina */
    private TableView<Nekretnina> table;
    /** Observable lista podataka */
    private ObservableList<Nekretnina> data;

    // Form komponente
    private ComboBox<String> cmbTip;
    private TextField txtAdresa;
    private TextField txtCena;
    private TextField txtAgentId;
    private ComboBox<String> cmbStatus;

    /**
     * Konstruktor - inicijalizuje prozor
     */
    public NekretnineProzor() {
        this.controller = new NekretninaController();
        initializeUI();
    }

    /**
     * Inicijalizuje korisnički interfejs
     */
    private void initializeUI() {
        stage = new Stage();

        VBox root = new VBox(10);
        root.setPadding(new Insets(10));

        // Naslov
        Label naslov = new Label("Upravljanje nekretninama");

        // Forma za dodavanje
        GridPane forma = kreirajFormu();

        // Tabela
        table = kreirajTabelu();

        // Dugmići
        HBox dugmici = kreirajDugmice();

        root.getChildren().addAll(naslov, forma, table, dugmici);

        Scene scene = new Scene(root, 800, 500);
        stage.setTitle("Upravljanje nekretninama");
        stage.setScene(scene);

        // Učitaj početne podatke
        ucitajPodatke();
    }

    /**
     * Kreira formu za unos nekretnine
     * @return GridPane sa formom
     */
    private GridPane kreirajFormu() {
        GridPane forma = new GridPane();
        forma.setPadding(new Insets(10));
        forma.setHgap(10);
        forma.setVgap(5);

        Label lblForma = new Label("Dodaj novu nekretninu:");
        forma.add(lblForma, 0, 0, 2, 1);

        // Tip
        forma.add(new Label("Tip:"), 0, 1);
        cmbTip = new ComboBox<>(FXCollections.observableArrayList("Stan", "Kuća", "Lokal", "Plac"));
        forma.add(cmbTip, 1, 1);

        // Adresa
        forma.add(new Label("Adresa:"), 0, 2);
        txtAdresa = new TextField();
        txtAdresa.setPrefWidth(200);
        forma.add(txtAdresa, 1, 2);

        // Cena
        forma.add(new Label("Cena:"), 0, 3);
        txtCena = new TextField();
        forma.add(txtCena, 1, 3);

        // Agent ID
        forma.add(new Label("Agent ID:"), 0, 4);
        txtAgentId = new TextField();
        txtAgentId.setText("1");
        forma.add(txtAgentId, 1, 4);

        // Status
        forma.add(new Label("Status:"), 0, 5);
        cmbStatus = new ComboBox<>(FXCollections.observableArrayList("Dostupna", "Rezervisana", "Prodana"));
        forma.add(cmbStatus, 1, 5);

        // Dugme za dodavanje
        Button btnDodaj = new Button("Dodaj nekretninu");
        btnDodaj.setOnAction(e -> dodajNekretninu());
        forma.add(btnDodaj, 0, 6, 2, 1);

        return forma;
    }

    /**
     * Kreira tabelu za prikaz nekretnina
     * @return TableView tabela
     */
    private TableView<Nekretnina> kreirajTabelu() {
        TableView<Nekretnina> table = new TableView<>();

        TableColumn<Nekretnina, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colId.setPrefWidth(50);

        TableColumn<Nekretnina, String> colTip = new TableColumn<>("Tip");
        colTip.setCellValueFactory(new PropertyValueFactory<>("tip"));
        colTip.setPrefWidth(80);

        TableColumn<Nekretnina, String> colAdresa = new TableColumn<>("Adresa");
        colAdresa.setCellValueFactory(new PropertyValueFactory<>("adresa"));
        colAdresa.setPrefWidth(200);

        TableColumn<Nekretnina, Double> colCena = new TableColumn<>("Cena");
        colCena.setCellValueFactory(new PropertyValueFactory<>("cena"));
        colCena.setPrefWidth(100);

        TableColumn<Nekretnina, Integer> colAgent = new TableColumn<>("Agent ID");
        colAgent.setCellValueFactory(new PropertyValueFactory<>("agentId"));
        colAgent.setPrefWidth(70);

        TableColumn<Nekretnina, String> colStatus = new TableColumn<>("Status");
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colStatus.setPrefWidth(100);

        table.getColumns().addAll(colId, colTip, colAdresa, colCena, colAgent, colStatus);
        table.setPrefHeight(200);

        return table;
    }

    /**
     * Kreira dugmiće za akcije
     * @return HBox sa dugmićima
     */
    private HBox kreirajDugmice() {
        HBox dugmici = new HBox(10);

        Button btnObrisi = new Button("Obriši izabranu");
        Button btnOsvezi = new Button("Osveži listu");
        Button btnOcisti = new Button("Očisti formu");

        // Event handleri
        btnObrisi.setOnAction(e -> obrisiNekretninu());
        btnOsvezi.setOnAction(e -> ucitajPodatke());
        btnOcisti.setOnAction(e -> ocistiFormu());

        dugmici.getChildren().addAll(btnObrisi, btnOsvezi, btnOcisti);
        return dugmici;
    }

    /**
     * Dodaje novu nekretninu
     */
    private void dodajNekretninu() {
        String tip = cmbTip.getValue();
        String adresa = txtAdresa.getText().trim();
        String cena = txtCena.getText().trim();
        String agentIdStr = txtAgentId.getText().trim();
        String status = cmbStatus.getValue();

        if (!agentIdStr.isEmpty()) {
            try {
                int agentId = Integer.parseInt(agentIdStr);
                if (controller.dodajNekretninu(tip, adresa, cena, agentId, status)) {
                    ocistiFormu();
                    ucitajPodatke();
                }
            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Agent ID mora biti broj!");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Molimo unesite Agent ID!");
            alert.showAndWait();
        }
    }

    /**
     * Briše izabranu nekretninu
     */
    private void obrisiNekretninu() {
        Nekretnina selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (controller.obrisiNekretninu(selected.getId())) {
                ucitajPodatke();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Izaberite nekretninu iz tabele");
            alert.showAndWait();
        }
    }

    /**
     * Čisti formu
     */
    private void ocistiFormu() {
        cmbTip.setValue(null);
        txtAdresa.clear();
        txtCena.clear();
        txtAgentId.setText("1");
        cmbStatus.setValue(null);
    }

    /**
     * Učitava podatke u tabelu
     */
    private void ucitajPodatke() {
        try {
            data = FXCollections.observableArrayList();
            var nekretnine = controller.sveNekretnine();
            if (nekretnine != null) {
                data.addAll(nekretnine);
            }
            table.setItems(data);

            System.out.println("Učitano " + data.size() + " nekretnina");
        } catch (Exception e) {
            System.err.println("Greška pri učitavanju podataka: " + e.getMessage());
        }
    }

    /**
     * Prikazuje prozor
     */
    public void show() {
        stage.show();
    }
}