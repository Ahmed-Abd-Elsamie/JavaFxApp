/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafxapp.Main;
import model.DeviceItem;
import model.PrinterItem;

/**
 *
 * @author Ahmed
 */
public class ReportsController implements Initializable {

    private int last = 0;
    
    @FXML
    private ListView<String> list = new ListView<String>();
    
    @FXML
    private void BtnCancel(ActionEvent event) {
        System.out.println("Cancel");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/fxml/Main.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Main.setContent(root, "");
            
        } catch (Exception e) {
            
        }
    }
    
    @FXML
    private void BtnHardware(ActionEvent event) {
        getDevice("hardware_fix");
        utils.genVars.hard_soft = 1; // hardware
        last = 0;
    }
    
    @FXML
    private void BtnSoftware(ActionEvent event) {
        getDevice("software_fix");
        utils.genVars.hard_soft = 2; // software
        last = 1;
    }
    
    @FXML
    private void BtnPrinters(ActionEvent event) {
        getPrinter();
        last = 3;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getLastView();
    }    
    
    
    private void getDevice(String table){
       
        Image red  = new Image("/res/images/red.png");
        Image green  = new Image("/res/images/green.png");
        Image blue  = new Image("/res/images/blue.png");
        Image right  = new Image("/res/images/true.png");

        List<DeviceItem> listDev = new ArrayList<>();
        if(table.equals("software_fix")){
            listDev = database.DatabaseHelper.getSoftwareDevices(table);
        }else{
            listDev = database.DatabaseHelper.getHardwareDevices(table);
        }
        List<String> strList = new ArrayList<>();
        for(int i = 0; i < listDev.size(); i++){
            String state = "";
            if(listDev.get(i).getState() == 0){
                state = "في الانتظار";
            }else if(listDev.get(i).getState() == 1){
                state = "تمت الصيانه";
            }else if(listDev.get(i).getState() == 2){ // 2
                state = "لم تتم الصيانه";
            }else{
                state = "تم التسليم";
            }
            strList.add("  اسم الجهاز : " + listDev.get(i).getDevice_name() + "\n "
                    + "  رقم التليفون:  " + listDev.get(i).getTxt_phone_num() + "\n"
                    + "   التاريخ:  " + listDev.get(i).getDate_time() + "\n"
                    + " الحاله: " + state);
        }
        
        ObservableList<String> items = FXCollections.observableArrayList (strList);
        list.setItems(items);

        list.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if(item.contains("في الانتظار"))
                        imageView.setImage(blue);
                    else if(item.contains("تمت الصيانه"))
                        imageView.setImage(green);
                    else if(item.contains("تم التسليم"))
                        imageView.setImage(right);
                    else
                        imageView.setImage(red);
                    
                    setText(item);
                    setGraphic(imageView);
                }
            }
        });
        
        List<DeviceItem> listDevTemp = listDev;
        list.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                 System.out.println(list.getSelectionModel().getSelectedIndex());
                 utils.genVars.deviceItem = listDevTemp.get(list.getSelectionModel().getSelectedIndex());
                 utils.genVars.type = 0;  //type of clicking on item to show nex window as a Device not printer
                 
                 // open new window
                 try {
                     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/fxml/DevicePreview.fxml"));
                     Parent root = (Parent) fxmlLoader.load();
                     Main.setContent(root, "");
            
                 } catch (Exception e) {
                     
                 }
                 
            }
        });
    }

    private void getPrinter() {
        
        Image red  = new Image("/res/images/red.png");
        Image green  = new Image("/res/images/green.png");
        Image blue  = new Image("/res/images/blue.png");
        Image right  = new Image("/res/images/true.png");
        
        List<PrinterItem> listDev = new ArrayList<>();
        listDev = database.DatabaseHelper.getPrinerDevices("printer_fix");
        List<String> strList = new ArrayList<>();
        for(int i = 0; i < listDev.size(); i++){
            String state = "";
            if(listDev.get(i).getState() == 0){
                state = "في الانتظار";
            }else if(listDev.get(i).getState() == 1){
                state = "تمت الصيانه";
            }else if(listDev.get(i).getState() == 2){ // 2
                state = "لم تتم الصيانه";
            }else{
                state = "تم التسليم";
            }
            strList.add("  اسم الجهاز : " + listDev.get(i).getDevice_name() + "\n "
                    + "  رقم التليفون:  " + listDev.get(i).getTxt_phone_num() + "\n"
                    + "   التاريخ:  " + listDev.get(i).getDate_time() + "\n"
                    + " الحاله: " + state);
        }
        
        ObservableList<String> items = FXCollections.observableArrayList (strList);
        list.setItems(items);

        list.setCellFactory(param -> new ListCell<String>() {
            private ImageView imageView = new ImageView();
            @Override
            public void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    if(item.contains("في الانتظار"))
                        imageView.setImage(blue);
                    else if(item.contains("تمت الصيانه"))
                        imageView.setImage(green);
                    else if(item.contains("تم التسليم"))
                        imageView.setImage(right);
                    else
                        imageView.setImage(red);
                    
                    setText(item);
                    setGraphic(imageView);
                }
            }
        });
        
        List<PrinterItem> listDevTemp = listDev;
        list.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                 System.out.println(list.getSelectionModel().getSelectedIndex());
                 utils.genVars.printerItem = listDevTemp.get(list.getSelectionModel().getSelectedIndex());
                 utils.genVars.type = 1; //type of clicking on item to show nex window as a printer not device
                 
                 // open new window
                 try {
                     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/fxml/DevicePreview.fxml"));
                     Parent root = (Parent) fxmlLoader.load();
                     Main.setContent(root, "");
            
                 } catch (Exception e) {
                     
                 }
            }
        });

    }
    
    
    private void getLastView(){
        if(last == 1){ // hardware
            getDevice("hardware_fix");
            utils.genVars.hard_soft = 0; // hardware
        }else if(last == 2){ // software
            getDevice("software_fix");
            utils.genVars.hard_soft = 1; // software
        }else if(last == 3){ // printer
            getPrinter();
        }
    }
    
}