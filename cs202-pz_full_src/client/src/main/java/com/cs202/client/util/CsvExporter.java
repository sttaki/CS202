package com.cs202.client.util;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.nio.file.Files;
import java.nio.file.Path;

public class CsvExporter {
    public static <T> void exportTable(TableView<T> table, String file){
        try{
            StringBuilder sb = new StringBuilder();
            for (TableColumn<T,?> c : table.getColumns()) sb.append(c.getText()).append(',');
            sb.append('\n');
            for (T row : table.getItems()){
                for (TableColumn<T,?> c : table.getColumns()){
                    Object v = c.getCellObservableValue(row).getValue();
                    sb.append(v==null? "" : v.toString()).append(',');
                }
                sb.append('\n');
            }
            Files.writeString(Path.of(file), sb.toString());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
