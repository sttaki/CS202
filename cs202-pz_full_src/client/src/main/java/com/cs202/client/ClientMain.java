package com.cs202.client;

import com.cs202.client.net.ServerApi;
import com.cs202.client.ui.TabsPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientMain extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        ServerApi api = new ServerApi("localhost", 5050);
        TabsPane root = new TabsPane(api);
        Scene scene = new Scene(root, 1000, 650);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("Esports Turnir Manager");
        stage.show();
        stage.setOnCloseRequest(e -> { try { api.close(); } catch (Exception ignored) {} });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
