/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafxapp.Main;
import model.DeviceItem;
import model.PrinterItem;

/**
 *
 * @author Ahmed
 */
public class DevicePreviewController implements Initializable {
    
    @FXML
    private TextField txt_device_num;
    
    @FXML
    private TextField txt_customer_name;
    
    @FXML
    private TextField txt_phone_num;
    
    @FXML
    private TextField txt_prob;
    
    @FXML
    private CheckBox Cwin7;
    
    @FXML
    private CheckBox Cwin8;
    
    @FXML
    private CheckBox Cwin10;
    
     @FXML
    private CheckBox Cfill_printer;
    
    @FXML
    private CheckBox Ccheck_all;
    
    @FXML
    private CheckBox Cchange_dram;
    
    @FXML
    private CheckBox Ccable_power;
    
    @FXML
    private CheckBox Ccable_data;
    
    @FXML
    private CheckBox Csoftware;
    
    @FXML
    private Label txt_date_time;
    
    @FXML
    private CheckBox Chardware;
    
    @FXML
    private CheckBox Cbattery;
    
    @FXML
    private CheckBox Ccharger;
    
    @FXML
    private CheckBox Cbag;
    
    @FXML
    private CheckBox Ccd;
    
    @FXML
    private TextField other;
    
    @FXML
    private TextField device_name;
    
    @FXML
    private Button btn_submit;
    
    @FXML
    private Button btn_done;
        
    @FXML
    private Button btn_not_done;
    
    @FXML
    private Label txt_state;
    
    
    private int win7 = 0;
    private int win8 = 0;
    private int win10 = 0;
    private int check_all = 0;
    private int software = 0;
    private int hardware = 0;
    private int battery = 0;
    private int charger = 0;
    private int bag = 0;
    private int cd = 0;
    private int fill_printer = 0;
    private int change_dram = 0;
    private int cable_power = 0;
    private int cable_data = 0;
   
    
    
    @FXML
    private void BtnCancel(ActionEvent event) {
        System.out.println("Cancel");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/fxml/Reports.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Main.setContent(root, "Student home");
            
        } catch (Exception e) {
            
        }
    }
    
    @FXML
    private void BtnDone(ActionEvent event) {
        
        if(utils.genVars.type == 0){ // it is device
            int id = utils.genVars.deviceItem.getId();
            if(utils.genVars.hard_soft == 0){ // it's hardware
                database.DatabaseHelper.updateItem(id, "hardware_fix", 1);
            }else{ // it's software
                database.DatabaseHelper.updateItem(id, "software_fix", 1);
            }
        }else{// it's printer
            int id = utils.genVars.printerItem.getId();
            database.DatabaseHelper.updateItem(id, "printer_fix", 1);

        }
        
        System.out.println("DONE");
        
    }
    
        
    @FXML
    private void BtnNotDone(ActionEvent event) {
                
        if(utils.genVars.type == 0){ // it is device
            int id = utils.genVars.deviceItem.getId();
            if(utils.genVars.hard_soft == 0){ // it's hardware
                database.DatabaseHelper.updateItem(id, "hardware_fix", 2);
            }else{ // it's software
                database.DatabaseHelper.updateItem(id, "software_fix", 2);
            }
        }else{// it's printer
            int id = utils.genVars.printerItem.getId();
            database.DatabaseHelper.updateItem(id, "printer_fix", 2);

        }
        
        System.out.println("NOT DONE");
                
    }
    
    @FXML
    private void BtnSubmit(ActionEvent event) {
        
        if(utils.genVars.type == 0){ // it is device
            int id = utils.genVars.deviceItem.getId();
            if(utils.genVars.hard_soft == 0){ // it's hardware
                database.DatabaseHelper.updateItem(id, "hardware_fix", 3);
            }else{ // it's software
                database.DatabaseHelper.updateItem(id, "software_fix", 3);
            }
        }else{// it's printer
            int id = utils.genVars.printerItem.getId();
            database.DatabaseHelper.updateItem(id, "printer_fix", 3);

        }
        
        System.out.println("SUBMITED");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        
        
        
        
        if(utils.genVars.type == 0){
            
            DeviceItem deviceItem = utils.genVars.deviceItem;
            txt_device_num.setText(deviceItem.getTxt_device_num() + "");
            txt_customer_name.setText(deviceItem.getTxt_customer_name());
            txt_phone_num.setText(deviceItem.getTxt_phone_num() + "");
            txt_prob.setText(deviceItem.getTxt_prob());
            txt_date_time.setText(deviceItem.getDate_time() + "");
            device_name.setText(deviceItem.getDevice_name());
            other.setText(deviceItem.getOther());
            win7 = deviceItem.getWin7();
            win8 = deviceItem.getWin8();
            win10 = deviceItem.getWin10();
            check_all = deviceItem.getCheck_all();
            software = deviceItem.getSoftware();
            hardware = deviceItem.getHardware();
            battery = deviceItem.getBattery();
            charger = deviceItem.getCharger();
            bag = deviceItem.getBag();
            cd = deviceItem.getCd();
            
            if(deviceItem.getState() == 3){
                btn_submit.setDisable(true);
                btn_done.setDisable(true);
                btn_not_done.setDisable(true);
                
                txt_state.setText("تم التسليم");
                
            }
            if(deviceItem.getState() == 1){
                txt_state.setText("تمت الصيانه");
            }
            if(deviceItem.getState() == 2){
                txt_state.setText("لم تتم الصيانه");
            }          
            if(deviceItem.getState() == 0){
                txt_state.setText("في الانتظار");
            }                      
                        
        }else{
            PrinterItem printerItem = utils.genVars.printerItem;
            txt_device_num.setText(printerItem.getTxt_device_num() + "");
            txt_customer_name.setText(printerItem.getTxt_customer_name());
            txt_phone_num.setText(printerItem.getTxt_phone_num() + "");
            txt_prob.setText(printerItem.getTxt_prob());
            txt_date_time.setText(printerItem.getDate_time() + "");
            device_name.setText(printerItem.getDevice_name());
            other.setText(printerItem.getOther());
            fill_printer = printerItem.getFill_printer();
            cable_power = printerItem.getCable_power();
            cable_data = printerItem.getCable_data();
            change_dram = printerItem.getChange_dram();
            check_all = printerItem.getCheck_all();
            cd = printerItem.getCd();
            
            if(printerItem.getState() == 3){
                btn_submit.setDisable(true);
                btn_done.setDisable(true);
                btn_not_done.setDisable(true);
                txt_state.setText("تم التسليم");
            }
            if(printerItem.getState() == 1){
                txt_state.setText("تمت الصيانه");
            }
            if(printerItem.getState() == 2){
                txt_state.setText("لم تتم الصيانه");
            }          
            if(printerItem.getState() == 0){
                txt_state.setText("في الانتظار");
            }    

        }
        
        
        
        checkCheckBoxes();
        
    }    

    private void checkCheckBoxes() {
        if(win7 == 1){
            Cwin7.setSelected(true);
        }
        if(win8 == 1){
            Cwin8.setSelected(true);
        }
        if(win10 == 1){
            Cwin10.setSelected(true);
        }
        if(check_all == 1){
            Ccheck_all.setSelected(true);
        }
        if(software == 1){
            Csoftware.setSelected(true);
        }
        if(hardware == 1){
            Chardware.setSelected(true);
        }if(battery == 1){
            Cbattery.setSelected(true);
        }if(charger == 1){
            Ccharger.setSelected(true);
        }if(bag == 1){
            Cbag.setSelected(true);
        }
        if(cd == 1){
            Ccd.setSelected(true);
        }
        if(fill_printer == 1){
            Cfill_printer.setSelected(true);
        }
        if(change_dram == 1){
            Cchange_dram.setSelected(true);
        }
        if(cable_power == 1){
            Ccable_power.setSelected(true);
        }
        if(cable_data == 1){
            Ccable_data.setSelected(true);
        }

    }
    
}