package com.cs202.client.ui;

import com.cs202.client.net.ServerApi;
import com.cs202.client.util.CsvExporter;
import com.cs202.server.model.Team;
import com.cs202.server.protocol.Request;
import com.cs202.server.protocol.Response;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TeamTab extends VBox {
    private final TableView<Team> table = new TableView<>();
    private final TextField tfName = new TextField();
    private final ComboBox<Integer> cbGameId = new ComboBox<>();
    private final ServerApi api;

    public TeamTab(ServerApi api){
        this.api = api;
        setSpacing(10);
        setPadding(new Insets(10));

        TableColumn<Team,Integer> cId = new TableColumn<>("ID");
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Team,String> cName = new TableColumn<>("Name");
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Team,Integer> cGame = new TableColumn<>("GameId");
        cGame.setCellValueFactory(new PropertyValueFactory<>("gameId"));
        table.getColumns().addAll(cId,cName,cGame);

        HBox form = new HBox(10);
        tfName.setPromptText("Team name");
        cbGameId.setPromptText("game id (optional)");
        Button bCreate = new Button("Create");
        Button bUpdate = new Button("Update");
        Button bDelete = new Button("Delete");
        Button bExport = new Button("Export CSV");
        form.getChildren().addAll(new Label("Name:"), tfName, new Label("GameId:"), cbGameId, bCreate,bUpdate,bDelete,bExport);

        getChildren().addAll(table, form);

        bCreate.setOnAction(e -> {
            Map<String,Object> d = new HashMap<>();
            d.put("name", tfName.getText());
            d.put("gameId", cbGameId.getValue());
            try {
                Response r = api.send(new Request("TEAM","CREATE", d));
                if (r.isOk()) refresh(); else alert(r.getMessage());
            } catch (Exception ex){ alert(ex.getMessage()); }
        });
        bUpdate.setOnAction(e -> {
            Team sel = table.getSelectionModel().getSelectedItem();
            if (sel == null) { alert("select row"); return; }
            Map<String,Object> d = new HashMap<>();
            d.put("id", sel.getId());
            d.put("name", tfName.getText().isBlank()? sel.getName() : tfName.getText());
            d.put("gameId", cbGameId.getValue());
            try {
                Response r = api.send(new Request("TEAM","UPDATE", d));
                if (r.isOk()) refresh(); else alert(r.getMessage());
            } catch (Exception ex){ alert(ex.getMessage()); }
        });
        bDelete.setOnAction(e -> {
            Team sel = table.getSelectionModel().getSelectedItem();
            if (sel == null) { alert("select row"); return; }
            try {
                Response r = api.send(new Request("TEAM","DELETE", Map.of("id", sel.getId())));
                if (r.isOk()) refresh(); else alert(r.getMessage());
            } catch (Exception ex){ alert(ex.getMessage()); }
        });
        bExport.setOnAction(e -> CsvExporter.exportTable(table, "teams.csv"));

        table.setOnMouseClicked(e -> {
            Team t = table.getSelectionModel().getSelectedItem();
            if (t != null) {
                tfName.setText(t.getName());
                cbGameId.setValue(t.getGameId());
            }
        });

        refresh();
        loadGameIds();
    }

    private void refresh(){
        try {
            Response r = api.send(new Request("TEAM","LIST", Map.of()));
            if (r.isOk()) {
                @SuppressWarnings("unchecked")
                List<Team> list = (List<Team>) r.getPayload();
                table.setItems(FXCollections.observableArrayList(list));
            } else alert(r.getMessage());
        } catch (Exception ex){ alert(ex.getMessage()); }
    }

    private void loadGameIds(){
        try {
            Response r = api.send(new Request("GAME","LIST", Map.of()));
            if (r.isOk()) {
                @SuppressWarnings("unchecked")
                List<com.cs202.server.model.Game> games = (List<com.cs202.server.model.Game>) r.getPayload();
                cbGameId.getItems().setAll(games.stream().map(com.cs202.server.model.Game::getId).toList());
            }
        } catch (Exception ignored){}
    }

    private void alert(String m){ new Alert(Alert.AlertType.ERROR, m).showAndWait(); }
}
