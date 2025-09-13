package com.cs202.client.ui;

import com.cs202.client.net.ServerApi;
import com.cs202.server.model.Tournament;
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

public class TournamentTab extends VBox {
    private final TableView<Tournament> table = new TableView<>();
    private final TextField tfName = new TextField();
    private final TextField tfStart = new TextField();
    private final ServerApi api;

    public TournamentTab(ServerApi api){
        this.api = api;
        setSpacing(10); setPadding(new Insets(10));

        TableColumn<Tournament,Integer> cId = new TableColumn<>("ID");
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Tournament,String> cName = new TableColumn<>("Name");
        cName.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn<Tournament,String> cDate = new TableColumn<>("StartDate");
        cDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        table.getColumns().addAll(cId,cName,cDate);

        HBox form = new HBox(10);
        tfName.setPromptText("Tournament name");
        tfStart.setPromptText("YYYY-MM-DD");
        Button bCreate=new Button("Create"), bUpdate=new Button("Update"), bDelete=new Button("Delete");
        form.getChildren().addAll(new Label("Name:"), tfName, new Label("Start:"), tfStart, bCreate,bUpdate,bDelete);
        getChildren().addAll(table, form);

        bCreate.setOnAction(e -> {
            Map<String,Object> d = new HashMap<>();
            d.put("name", tfName.getText());
            d.put("startDate", tfStart.getText());
            try { Response r = api.send(new Request("TOURNAMENT","CREATE", d)); if(r.isOk()) refresh(); else alert(r.getMessage()); }
            catch(Exception ex){ alert(ex.getMessage()); }
        });
        bUpdate.setOnAction(e -> {
            Tournament sel = table.getSelectionModel().getSelectedItem();
            if(sel==null){ alert("select row"); return; }
            Map<String,Object> d = new HashMap<>();
            d.put("id", sel.getId());
            d.put("name", tfName.getText().isBlank()? sel.getName(): tfName.getText());
            d.put("startDate", tfStart.getText().isBlank()? (sel.getStartDate()==null? "" : sel.getStartDate().toString()) : tfStart.getText());
            try { Response r = api.send(new Request("TOURNAMENT","UPDATE", d)); if(r.isOk()) refresh(); else alert(r.getMessage()); }
            catch(Exception ex){ alert(ex.getMessage()); }
        });
        bDelete.setOnAction(e -> {
            Tournament sel = table.getSelectionModel().getSelectedItem();
            if(sel==null){ alert("select row"); return; }
            try { Response r = api.send(new Request("TOURNAMENT","DELETE", Map.of("id", sel.getId()))); if(r.isOk()) refresh(); else alert(r.getMessage()); }
            catch(Exception ex){ alert(ex.getMessage()); }
        });

        table.setOnMouseClicked(e -> {
            Tournament t = table.getSelectionModel().getSelectedItem();
            if(t!=null){
                tfName.setText(t.getName());
                tfStart.setText(t.getStartDate()==null? "" : t.getStartDate().toString());
            }
        });

        refresh();
    }

    private void refresh(){
        try{
            Response r = api.send(new Request("TOURNAMENT","LIST", Map.of()));
            if(r.isOk()){
                @SuppressWarnings("unchecked") List<Tournament> list = (List<Tournament>) r.getPayload();
                table.setItems(FXCollections.observableArrayList(list));
            } else alert(r.getMessage());
        }catch(Exception ex){ alert(ex.getMessage()); }
    }

    private void alert(String m){ new Alert(Alert.AlertType.ERROR, m).showAndWait(); }
}
