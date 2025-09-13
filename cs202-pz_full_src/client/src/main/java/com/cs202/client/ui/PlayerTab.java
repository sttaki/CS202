package com.cs202.client.ui;

import com.cs202.client.net.ServerApi;
import com.cs202.server.model.Player;
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

public class PlayerTab extends VBox {
    private final TableView<Player> table = new TableView<>();
    private final TextField tfNick = new TextField();
    private final ComboBox<Integer> cbTeamId = new ComboBox<>();
    private final ServerApi api;

    public PlayerTab(ServerApi api){
        this.api = api;
        setSpacing(10);
        setPadding(new Insets(10));

        TableColumn<Player,Integer> cId = new TableColumn<>("ID");
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Player,String> cNick = new TableColumn<>("Nick");
        cNick.setCellValueFactory(new PropertyValueFactory<>("nickname"));
        TableColumn<Player,Integer> cTeam = new TableColumn<>("TeamId");
        cTeam.setCellValueFactory(new PropertyValueFactory<>("teamId"));
        table.getColumns().addAll(cId,cNick,cTeam);

        HBox form = new HBox(10);
        tfNick.setPromptText("nickname");
        cbTeamId.setPromptText("team id");
        Button bCreate = new Button("Create");
        Button bUpdate = new Button("Update");
        Button bDelete = new Button("Delete");
        form.getChildren().addAll(new Label("Nick:"), tfNick, new Label("TeamId:"), cbTeamId, bCreate, bUpdate, bDelete);
        getChildren().addAll(table, form);

        bCreate.setOnAction(e -> {
            Map<String,Object> d = new HashMap<>();
            d.put("nickname", tfNick.getText());
            d.put("teamId", cbTeamId.getValue());
            try {
                Response r = api.send(new Request("PLAYER","CREATE", d));
                if (r.isOk()) refresh(); else alert(r.getMessage());
            } catch (Exception ex){ alert(ex.getMessage()); }
        });
        bUpdate.setOnAction(e -> {
            Player sel = table.getSelectionModel().getSelectedItem();
            if (sel == null) { alert("select row"); return; }
            Map<String,Object> d = new HashMap<>();
            d.put("id", sel.getId());
            d.put("nickname", tfNick.getText().isBlank()? sel.getNickname() : tfNick.getText());
            d.put("teamId", cbTeamId.getValue());
            try {
                Response r = api.send(new Request("PLAYER","UPDATE", d));
                if (r.isOk()) refresh(); else alert(r.getMessage());
            } catch (Exception ex){ alert(ex.getMessage()); }
        });
        bDelete.setOnAction(e -> {
            Player sel = table.getSelectionModel().getSelectedItem();
            if (sel == null) { alert("select row"); return; }
            try {
                Response r = api.send(new Request("PLAYER","DELETE", Map.of("id", sel.getId())));
                if (r.isOk()) refresh(); else alert(r.getMessage());
            } catch (Exception ex){ alert(ex.getMessage()); }
        });

        table.setOnMouseClicked(e -> {
            Player p = table.getSelectionModel().getSelectedItem();
            if (p != null) {
                tfNick.setText(p.getNickname());
                cbTeamId.setValue(p.getTeamId());
            }
        });

        refresh();
        loadTeamIds();
    }

    private void refresh(){
        try {
            Response r = api.send(new Request("PLAYER","LIST", Map.of()));
            if (r.isOk()) {
                @SuppressWarnings("unchecked")
                List<Player> list = (List<Player>) r.getPayload();
                table.setItems(FXCollections.observableArrayList(list));
            } else alert(r.getMessage());
        } catch (Exception ex){ alert(ex.getMessage()); }
    }

    private void loadTeamIds(){
        try {
            Response r = api.send(new Request("TEAM","LIST", Map.of()));
            if (r.isOk()) {
                @SuppressWarnings("unchecked")
                List<com.cs202.server.model.Team> teams = (List<com.cs202.server.model.Team>) r.getPayload();
                cbTeamId.getItems().setAll(teams.stream().map(com.cs202.server.model.Team::getId).toList());
            }
        } catch (Exception ignored){}
    }

    private void alert(String m){ new Alert(Alert.AlertType.ERROR, m).showAndWait(); }
}
