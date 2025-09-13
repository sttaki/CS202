package com.cs202.client.ui;

import com.cs202.client.net.ServerApi;
import com.cs202.server.model.Game;
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

public class GameTab extends VBox {
    private final TableView<Game> table = new TableView<>();
    private final TextField tfName = new TextField();
    private final ServerApi api;

    public GameTab(ServerApi api){
        this.api = api;
        setSpacing(10); setPadding(new Insets(10));

        TableColumn<Game,Integer> cId = new TableColumn<>("ID");
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Game,String> cName = new TableColumn<>("Name");
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        table.getColumns().addAll(cId,cName);

        HBox form = new HBox(10);
        tfName.setPromptText("Game name");
        Button bCreate=new Button("Create"), bUpdate=new Button("Update"), bDelete=new Button("Delete");
        form.getChildren().addAll(new Label("Name:"), tfName, bCreate,bUpdate,bDelete);
        getChildren().addAll(table, form);

        bCreate.setOnAction(e -> {
            Map<String,Object> d = new HashMap<>();
            d.put("name", tfName.getText());
            try { Response r = api.send(new Request("GAME","CREATE", d)); if(r.isOk()) refresh(); else alert(r.getMessage()); }
            catch(Exception ex){ alert(ex.getMessage()); }
        });
        bUpdate.setOnAction(e -> {
            Game sel = table.getSelectionModel().getSelectedItem();
            if(sel==null){ alert("select row"); return; }
            Map<String,Object> d = new HashMap<>();
            d.put("id", sel.getId());
            d.put("name", tfName.getText().isBlank()? sel.getName(): tfName.getText());
            try { Response r = api.send(new Request("GAME","UPDATE", d)); if(r.isOk()) refresh(); else alert(r.getMessage()); }
            catch(Exception ex){ alert(ex.getMessage()); }
        });
        bDelete.setOnAction(e -> {
            Game sel = table.getSelectionModel().getSelectedItem();
            if(sel==null){ alert("select row"); return; }
            try { Response r = api.send(new Request("GAME","DELETE", Map.of("id", sel.getId()))); if(r.isOk()) refresh(); else alert(r.getMessage()); }
            catch(Exception ex){ alert(ex.getMessage()); }
        });

        table.setOnMouseClicked(e -> {
            Game g = table.getSelectionModel().getSelectedItem();
            if(g!=null) tfName.setText(g.getName());
        });

        refresh();
    }

    private void refresh(){
        try{
            Response r = api.send(new Request("GAME","LIST", Map.of()));
            if(r.isOk()){
                @SuppressWarnings("unchecked") List<Game> list = (List<Game>) r.getPayload();
                table.setItems(FXCollections.observableArrayList(list));
            } else alert(r.getMessage());
        }catch(Exception ex){ alert(ex.getMessage()); }
    }

    private void alert(String m){ new Alert(Alert.AlertType.ERROR, m).showAndWait(); }
}
