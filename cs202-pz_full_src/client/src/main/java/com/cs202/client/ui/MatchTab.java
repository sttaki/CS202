package com.cs202.client.ui;

import com.cs202.client.net.ServerApi;
import com.cs202.server.model.Match;
import com.cs202.server.model.Team;
import com.cs202.server.protocol.Request;
import com.cs202.server.protocol.Response;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchTab extends VBox {
    private final TableView<Match> table = new TableView<>();
    private final ComboBox<Integer> cbTournament = new ComboBox<>();
    private final ComboBox<Integer> cbHomeTeam = new ComboBox<>();
    private final ComboBox<Integer> cbAwayTeam = new ComboBox<>();
    private final ComboBox<Integer> cbArena = new ComboBox<>();
    private final TextField tfDate = new TextField();
    private final TextField tfHS = new TextField();
    private final TextField tfAS = new TextField();
    private final ServerApi api;

    public MatchTab(ServerApi api){
        this.api = api;
        setSpacing(10);
        setPadding(new Insets(10));

        TableColumn<Match,Integer> cId = new TableColumn<>("ID");
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Match,Integer> cT = new TableColumn<>("TournamentId");
        cT.setCellValueFactory(new PropertyValueFactory<>("tournamentId"));
        TableColumn<Match,Integer> cH = new TableColumn<>("Home");
        cH.setCellValueFactory(new PropertyValueFactory<>("homeTeamId"));
        TableColumn<Match,Integer> cA = new TableColumn<>("Away");
        cA.setCellValueFactory(new PropertyValueFactory<>("awayTeamId"));
        TableColumn<Match,String> cS = new TableColumn<>("Status");
        cS.setCellValueFactory(new PropertyValueFactory<>("status"));
        table.getColumns().addAll(cId,cT,cH,cA,cS);

        HBox form = new HBox(8);
        tfDate.setPromptText("YYYY-MM-DD");
        tfHS.setPromptText("home score");
        tfAS.setPromptText("away score");
        Button bCreate=new Button("Create"), bUpdate=new Button("Update"), bDelete=new Button("Delete");
        Button bSchedule=new Button("Schedule RR"), bRanking=new Button("Ranking Chart");
        form.getChildren().addAll(new Label("T:"), cbTournament, new Label("H:"), cbHomeTeam,
                new Label("A:"), cbAwayTeam, new Label("Arena:"), cbArena, new Label("Date:"), tfDate,
                new Label("HS:"), tfHS, new Label("AS:"), tfAS, bCreate,bUpdate,bDelete,bSchedule,bRanking);
        getChildren().addAll(table, form);

        bCreate.setOnAction(e -> {
            Map<String,Object> d = new HashMap<>();
            d.put("tournamentId", cbTournament.getValue());
            d.put("homeTeamId", cbHomeTeam.getValue());
            d.put("awayTeamId", cbAwayTeam.getValue());
            d.put("arenaId", cbArena.getValue());
            d.put("matchDate", tfDate.getText());
            d.put("homeScore", Integer.parseInt(tfHS.getText().isBlank()? "0" : tfHS.getText()));
            d.put("awayScore", Integer.parseInt(tfAS.getText().isBlank()? "0" : tfAS.getText()));
            d.put("status", "SCHEDULED");
            try {
                Response r = api.send(new Request("MATCH","CREATE", d));
                if (r.isOk()) refresh(); else alert(r.getMessage());
            } catch (Exception ex){ alert(ex.getMessage()); }
        });
        bUpdate.setOnAction(e -> {
            Match m = table.getSelectionModel().getSelectedItem();
            if (m == null) { alert("select row"); return; }
            Map<String,Object> d = new HashMap<>();
            d.put("id", m.getId());
            d.put("tournamentId", cbTournament.getValue());
            d.put("homeTeamId", cbHomeTeam.getValue());
            d.put("awayTeamId", cbAwayTeam.getValue());
            d.put("arenaId", cbArena.getValue());
            d.put("matchDate", tfDate.getText());
            d.put("homeScore", Integer.parseInt(tfHS.getText().isBlank()? "0" : tfHS.getText()));
            d.put("awayScore", Integer.parseInt(tfAS.getText().isBlank()? "0" : tfAS.getText()));
            d.put("status", m.getStatus());
            try {
                Response r = api.send(new Request("MATCH","UPDATE", d));
                if (r.isOk()) refresh(); else alert(r.getMessage());
            } catch (Exception ex){ alert(ex.getMessage()); }
        });
        bDelete.setOnAction(e -> {
            Match m = table.getSelectionModel().getSelectedItem();
            if (m == null) { alert("select row"); return; }
            try {
                Response r = api.send(new Request("MATCH","DELETE", Map.of("id", m.getId())));
                if (r.isOk()) refresh(); else alert(r.getMessage());
            } catch (Exception ex){ alert(ex.getMessage()); }
        });

        bSchedule.setOnAction(e -> {
            Integer tid = cbTournament.getValue();
            if (tid == null) { alert("select tournament"); return; }
            try {
                List<Integer> teamIds = loadAllTeamIds();
                Map<String,Object> d = new HashMap<>();
                d.put("tournamentId", tid);
                d.put("teamIds", teamIds);
                Response r = api.send(new Request("SERVICE","SCHEDULE", d));
                if(!r.isOk()) alert(r.getMessage());
                refresh();
            } catch (Exception ex){ alert(ex.getMessage()); }
        });

        bRanking.setOnAction(e -> {
            Integer tid = cbTournament.getValue();
            if (tid == null) { alert("select tournament"); return; }
            try {
                Response r = api.send(new Request("SERVICE","RANKING", Map.of("tournamentId", tid)));
                if(!r.isOk()){ alert(r.getMessage()); return; }
                @SuppressWarnings("unchecked")
                List<Map<String,Object>> tableData = (List<Map<String,Object>>) r.getPayload();
                showRankingChart(tableData);
            } catch (Exception ex){ alert(ex.getMessage()); }
        });

        table.setOnMouseClicked(e -> {
            Match m = table.getSelectionModel().getSelectedItem();
            if (m != null) {
                cbTournament.setValue(m.getTournamentId());
                cbHomeTeam.setValue(m.getHomeTeamId());
                cbAwayTeam.setValue(m.getAwayTeamId());
                cbArena.setValue(m.getArenaId());
                tfDate.setText(m.getMatchDate()==null? "" : m.getMatchDate().toString());
                tfHS.setText(String.valueOf(m.getHomeScore()));
                tfAS.setText(String.valueOf(m.getAwayScore()));
            }
        });

        refresh();
        loadCombos();
    }

    private void refresh(){
        try{
            Response r = api.send(new Request("MATCH","LIST", Map.of()));
            if(r.isOk()){
                @SuppressWarnings("unchecked") List<Match> list = (List<Match>) r.getPayload();
                table.setItems(FXCollections.observableArrayList(list));
            } else alert(r.getMessage());
        }catch(Exception ex){ alert(ex.getMessage()); }
    }

    private void loadCombos(){
        try{
            Response rt = api.send(new Request("TOURNAMENT","LIST", Map.of()));
            if(rt.isOk()) cbTournament.getItems().setAll(extractIds(rt.getPayload()));
        }catch(Exception ignored){}
        try{
            Response ra = api.send(new Request("ARENA","LIST", Map.of()));
            if(ra.isOk()) cbArena.getItems().setAll(extractIds(ra.getPayload()));
        }catch(Exception ignored){}
        try{
            var ids = loadAllTeamIds();
            cbHomeTeam.getItems().setAll(ids);
            cbAwayTeam.getItems().setAll(ids);
        }catch(Exception ignored){}
    }

    private List<Integer> loadAllTeamIds() throws Exception {
        Response r = api.send(new Request("TEAM","LIST", Map.of()));
        if(!r.isOk()) return java.util.List.of();
        @SuppressWarnings("unchecked") List<com.cs202.server.model.Team> teams = (List<com.cs202.server.model.Team>) r.getPayload();
        return teams.stream().map(Team::getId).toList();
    }

    @SuppressWarnings("unchecked")
    private java.util.List<Integer> extractIds(Object payload){
        return ((java.util.List<Object>)payload).stream().map(o -> {
            try {
                var f = o.getClass().getMethod("getId");
                return ((Number)f.invoke(o)).intValue();
            } catch (Exception ex){ return 0; }
        }).filter(i -> i != 0).toList();
    }

    private void alert(String m){ new Alert(Alert.AlertType.ERROR, m).showAndWait(); }

    private void showRankingChart(java.util.List<java.util.Map<String,Object>> ranking){
        CategoryAxis x = new CategoryAxis();
        NumberAxis y = new NumberAxis();
        BarChart<String,Number> chart = new BarChart<>(x,y);
        chart.setTitle("Wins by Team");
        x.setLabel("teamId");
        y.setLabel("wins");
        XYChart.Series<String,Number> s = new XYChart.Series<>();
        s.setName("Wins");
        for (var row : ranking) {
            s.getData().add(new XYChart.Data<>(String.valueOf(row.get("teamId")), (Number) row.get("wins")));
        }
        chart.getData().add(s);
        Dialog<Void> dlg = new Dialog<>();
        dlg.setTitle("Ranking");
        dlg.getDialogPane().setContent(chart);
        dlg.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
        dlg.showAndWait();
    }
}
