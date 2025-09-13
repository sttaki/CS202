package com.cs202.client.ui;

import com.cs202.client.net.ServerApi;
import com.cs202.server.model.Arena;
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

public class ArenaTab extends VBox {
    private final TableView<Arena> table = new TableView<>();
    private final TextField tfName = new TextField();
    private final TextField tfCity = new TextField();
    private final ServerApi api;

    public ArenaTab(ServerApi api){
        this.api = api;
        setSpacing(10); setPadding(new Insets(10));

        TableColumn<Arena,Integer> cId = new TableColumn<>("ID");
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Arena,String> cName = new TableColumn<>("Name");
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Arena,String> cCity = new TableColumn<>("City");
        cCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        table.getColumns().addAll(cId,cName,cCity);

        HBox form = new HBox(10);
        tfName.setPromptText("Arena name");
        tfCity.setPromptText("City");
        Button bCreate=new Button("Create"), bUpdate=new Button("Update"), bDelete=new Button("Delete");
        form.getChildren().addAll(new Label("Name:"), tfName, new Label("City:"), tfCity, bCreate,bUpdate,bDelete);
        getChildren().addAll(table, form);

        bCreate.setOnAction(e -> {
            Map<String,Object> d = new HashMap<>();
            d.put("name", tfName.getText()); d.put("city", tfCity.getText());
            try { Response r = api.send(new Request("ARENA","CREATE", d)); if(r.isOk()) refresh(); else alert(r.getMessage()); }
            catch(Exception ex){ alert(ex.getMessage()); }
        });
        bUpdate.setOnAction(e -> {
            Arena sel = table.getSelectionModel().getSelectedItem();
            if(sel==null){ alert("select row"); return; }
            Map<String,Object> d = new HashMap<>();
            d.put("id", sel.getId());
            d.put("name", tfName.getText().isBlank()? sel.getName(): tfName.getText());
            d.put("city", tfCity.getText().isBlank()? sel.getCity(): tfCity.getText());
            try { Response r = api.send(new Request("ARENA","UPDATE", d)); if(r.isOk()) refresh(); else alert(r.getMessage()); }
            catch(Exception ex){ alert(ex.getMessage()); }
        });
        bDelete.setOnAction(e -> {
            Arena sel = table.getSelectionModel().getSelectedItem();
            if(sel==null){ alert("select row"); return; }
            try { Response r = api.send(new Request("ARENA","DELETE", Map.of("id", sel.getId()))); if(r.isOk()) refresh(); else alert(r.getMessage()); }
            catch(Exception ex){ alert(ex.getMessage()); }
        });

        table.setOnMouseClicked(e -> {
            Arena a = table.getSelectionModel().getSelectedItem();
            if(a!=null){ tfName.setText(a.getName()); tfCity.setText(a.getCity()); }
        });

        refresh();
    }

    private void refresh(){
        try{
            Response r = api.send(new Request("ARENA","LIST", Map.of()));
            if(r.isOk()){
                @SuppressWarnings("unchecked") List<Arena> list = (List<Arena>) r.getPayload();
                table.setItems(FXCollections.observableArrayList(list));
            } else alert(r.getMessage());
        }catch(Exception ex){ alert(ex.getMessage()); }
    }

    private void alert(String m){ new Alert(Alert.AlertType.ERROR, m).showAndWait(); }
}
