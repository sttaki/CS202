package com.cs202.client.ui;

import com.cs202.client.net.ServerApi;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

public class TabsPane extends BorderPane {
    public TabsPane(ServerApi api){
        TabPane tp = new TabPane();
        tp.getTabs().addAll(
                make("Teams", new TeamTab(api)),
                make("Players", new PlayerTab(api)),
                make("Games", new GameTab(api)),
                make("Arenas", new ArenaTab(api)),
                make("Tournaments", new TournamentTab(api)),
                make("Matches", new MatchTab(api))
        );
        setCenter(tp);
    }
    private Tab make(String title, javafx.scene.Node content){
        Tab t = new Tab(title, content);
        t.setClosable(false);
        return t;
    }
}
