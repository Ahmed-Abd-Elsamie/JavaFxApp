/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Ahmed
 */
public class utils {
    
    
    public static void AlertMSG(String msg){
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Button btn = new Button("OK");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialogStage.hide();
            }
        });
        VBox vbox = new VBox(new Text(msg), btn);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();
        
        
    }
    
    public static void PrinterDialog(Node node, String msg){
        ObservableSet<Printer> printers = Printer.getAllPrinters();
        LinkedList<Printer> printerList = new LinkedList<>();
        List<String> strList = new ArrayList();
        PrinterJob job = PrinterJob.createPrinterJob();
        ListView list = new ListView();
        for(Printer printer : printers){
            strList.add(printer.getName());
            printerList.add(printer);
        }
        ObservableList<String> items = FXCollections.observableArrayList (strList);
        list.setItems(items);
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        
        list.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                Printer printer = printerList.get(list.getSelectionModel().getSelectedIndex());
                PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
                job.setPrinter(printer);
                if (job != null/* && job.showPrintDialog(comp().getScene().getWindow())*/) {
                   boolean success = job.printPage(pageLayout, node);
                   if (success) {
                       job.endJob();
                   }
                }
        
                dialogStage.hide();
                AlertMSG(msg);
            }
        });
        
        VBox vbox = new VBox(list);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
        dialogStage.setTitle("طباعه ايصال");
        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();
        
    }


}
