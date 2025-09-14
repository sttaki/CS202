package com.realestate.view;

import com.realestate.controller.KlijentController;
import com.realestate.model.Klijent;
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
 * Prozor za upravljanje klijentima
 * @author Student
 * @version 1.0
 */
public class KlijentiProzor {
    /** Stage ovog prozora */
    private Stage stage;
    /** Controller za rad sa klijentima */
    private KlijentController controller;
    /** Tabela za prikaz klijenata */
    private TableView<Klijent> table;
    /** Observable lista podataka */
    private ObservableList<Klijent> data;

    // Form komponente
    private TextField txtIme;
    private TextField txtPrezime;
    private TextField txtTelefon;
    private TextField txtEmail;
    private ComboBox<String> cmbTipKlijenta;

    /**
     * Konstruktor - inicijalizuje prozor
     */
    public KlijentiProzor() {
        this.controller = new KlijentController();
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
        Label naslov = new Label("Upravljanje klijentima");

        // Forma za dodavanje
        GridPane forma = kreirajFormu();

        // Tabela
        table = kreirajTabelu();

        // Dugmići
        HBox dugmici = kreirajDugmice();

        root.getChildren().addAll(naslov, forma, table, dugmici);

        Scene scene = new Scene(root, 700, 500);
        stage.setTitle("Upravljanje klijentima");
        stage.setScene(scene);

        // Učitaj početne podatke
        ucitajPodatke();
    }

    /**
     * Kreira formu za unos klijenta
     * @return GridPane sa formom
     */
    private GridPane kreirajFormu() {
        GridPane forma = new GridPane();
        forma.setPadding(new Insets(10));
        forma.setHgap(10);
        forma.setVgap(5);

        Label lblForma = new Label("Dodaj novog klijenta:");
        forma.add(lblForma, 0, 0, 2, 1);

        // Ime
        forma.add(new Label("Ime:"), 0, 1);
        txtIme = new TextField();
        txtIme.setPrefWidth(150);
        forma.add(txtIme, 1, 1);

        // Prezime
        forma.add(new Label("Prezime:"), 0, 2);
        txtPrezime = new TextField();
        txtPrezime.setPrefWidth(150);
        forma.add(txtPrezime, 1, 2);

        // Telefon
        forma.add(new Label("Telefon:"), 0, 3);
        txtTelefon = new TextField();
        txtTelefon.setPrefWidth(150);
        forma.add(txtTelefon, 1, 3);

        // Email
        forma.add(new Label("Email:"), 0, 4);
        txtEmail = new TextField();
        txtEmail.setPrefWidth(200);
        forma.add(txtEmail, 1, 4);

        // Tip klijenta
        forma.add(new Label("Tip klijenta:"), 0, 5);
        cmbTipKlijenta = new ComboBox<>(FXCollections.observableArrayList("Kupac", "Prodavac"));
        forma.add(cmbTipKlijenta, 1, 5);

        // Dugme za dodavanje
        Button btnDodaj = new Button("Dodaj klijenta");
        btnDodaj.setOnAction(e -> dodajKlijenta());
        forma.add(btnDodaj, 0, 6, 2, 1);

        return forma;
    }

    /**
     * Kreira tabelu za prikaz klijenata
     * @return TableView tabela
     */
    private TableView<Klijent> kreirajTabelu() {
        TableView<Klijent> table = new TableView<>();

        TableColumn<Klijent, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colId.setPrefWidth(50);

        TableColumn<Klijent, String> colIme = new TableColumn<>("Ime");
        colIme.setCellValueFactory(new PropertyValueFactory<>("ime"));
        colIme.setPrefWidth(80);

        TableColumn<Klijent, String> colPrezime = new TableColumn<>("Prezime");
        colPrezime.setCellValueFactory(new PropertyValueFactory<>("prezime"));
        colPrezime.setPrefWidth(100);

        TableColumn<Klijent, String> colTelefon = new TableColumn<>("Telefon");
        colTelefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        colTelefon.setPrefWidth(100);

        TableColumn<Klijent, String> colEmail = new TableColumn<>("Email");
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colEmail.setPrefWidth(150);

        TableColumn<Klijent, String> colTip = new TableColumn<>("Tip klijenta");
        colTip.setCellValueFactory(new PropertyValueFactory<>("tipKlijenta"));
        colTip.setPrefWidth(100);

        table.getColumns().addAll(colId, colIme, colPrezime, colTelefon, colEmail, colTip);
        table.setPrefHeight(200);

        return table;
    }

    /**
     * Kreira dugmiće za akcije
     * @return HBox sa dugmićima
     */
    private HBox kreirajDugmice() {
        HBox dugmici = new HBox(10);

        Button btnObrisi = new Button("Obriši izabranog");
        Button btnOsvezi = new Button("Osveži listu");
        Button btnOcisti = new Button("Očisti formu");

        // Event handleri
        btnObrisi.setOnAction(e -> obrisiKlijenta());
        btnOsvezi.setOnAction(e -> ucitajPodatke());
        btnOcisti.setOnAction(e -> ocistiFormu());

        dugmici.getChildren().addAll(btnObrisi, btnOsvezi, btnOcisti);
        return dugmici;
    }

    /**
     * Dodaje novog klijenta
     */
    private void dodajKlijenta() {
        String ime = txtIme.getText().trim();
        String prezime = txtPrezime.getText().trim();
        String telefon = txtTelefon.getText().trim();
        String email = txtEmail.getText().trim();
        String tipKlijenta = cmbTipKlijenta.getValue();

        if (controller.dodajKlijenta(ime, prezime, telefon, email, tipKlijenta)) {
            ocistiFormu();
            ucitajPodatke();
        }
    }

    /**
     * Briše izabranog klijenta
     */
    private void obrisiKlijenta() {
        Klijent selected = table.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (controller.obrisiKlijenta(selected.getId())) {
                ucitajPodatke();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Izaberite klijenta iz tabele");
            alert.showAndWait();
        }
    }

    /**
     * Čisti formu
     */
    private void ocistiFormu() {
        txtIme.clear();
        txtPrezime.clear();
        txtTelefon.clear();
        txtEmail.clear();
        cmbTipKlijenta.setValue(null);
    }

    /**
     * Učitava podatke u tabelu
     */
    private void ucitajPodatke() {
        try {
            data = FXCollections.observableArrayList();
            var klijenti = controller.sviKlijenti();
            if (klijenti != null) {
                data.addAll(klijenti);
            }
            table.setItems(data);

            System.out.println("Učitano " + data.size() + " klijenata");
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