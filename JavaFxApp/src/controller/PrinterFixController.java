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
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafxapp.Main;

/**
 *
 * @author Ahmed
 */
public class PrinterFixController   implements Initializable {
    
    @FXML
    public TextField txt_loading;
    
    @FXML
    private TextField txt_device_num;
    
    @FXML
    private TextField txt_customer_name;
    
    @FXML
    private TextField txt_phone_num;
    
    @FXML
    private TextField txt_prob;
    
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
    private CheckBox Ccd;
    
    @FXML
    private TextField other;
    
    @FXML
    private TextField device_name;
    
    private int fill_printer = 0;
    private int change_dram = 0;
    private int check_all = 0;
    private int cable_power = 0;
    private int cable_data = 0;
    private int cd = 0;
    
    @FXML
    private void BtnSave(ActionEvent event) throws ClassNotFoundException {
        System.out.println("Save");
        database.DatabaseHelper.makeDB();
        // check CheckBoxes
        checkCheckBoxes();
        database.DatabaseHelper.insertPrinterDevice(Integer.parseInt(txt_device_num.getText().toString())
                , txt_customer_name.getText().toString()
                , Integer.parseInt(txt_phone_num.getText().toString())
                , txt_prob.getText().toString(), fill_printer, change_dram, check_all, cable_power, cable_data, cd
                , other.getText().toString(), device_name.getText().toString(), 0);
        
        emptfyForm();
    }
    
    @FXML
    private void BtnCancel(ActionEvent event) {
        System.out.println("Cancel");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/fxml/Main.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Main.setContent(root, "Student home");
            
        } catch (Exception e) {
            
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void checkCheckBoxes() {
        if(Cfill_printer.isSelected()){
            fill_printer = 1;
        }
        if(Cchange_dram.isSelected()){
            change_dram = 1;
        }
        if(Ccheck_all.isSelected()){
            check_all = 1;
        }
        if(Ccable_power.isSelected()){
            cable_power = 1;
        }
        if(Ccable_data.isSelected()){
            cable_data = 1;
        }
        if(Ccd.isSelected()){
            cd = 1;
        }
        
        
        
    }
    
          
    public  void emptfyForm(){
        txt_customer_name.setText("");
        txt_device_num.setText("");
        txt_prob.setText("");
        device_name.setText("");
        txt_phone_num.setText("");
        other.setText("");
        Ccable_data.setSelected(false);
        Ccable_power.setSelected(false);
        Cchange_dram.setSelected(false);
        Ccheck_all.setSelected(false);
        Ccd.setSelected(false);
    }
    
}