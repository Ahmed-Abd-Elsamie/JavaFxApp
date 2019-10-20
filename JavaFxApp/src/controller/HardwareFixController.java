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
public class HardwareFixController  implements Initializable {
    
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
    private CheckBox Cwin7;
    
    @FXML
    private CheckBox Cwin8;
    
    @FXML
    private CheckBox Cwin10;
    
    @FXML
    private CheckBox Ccheck_all;
    
    @FXML
    private CheckBox Csoftware;
    
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
    
    
    @FXML
    private void BtnSave(ActionEvent event) throws ClassNotFoundException {
        System.out.println("Save");
        database.DatabaseHelper.makeDB();
        // check CheckBoxes
        checkCheckBoxes();
        database.DatabaseHelper.insertHardwareDevice(Integer.parseInt(txt_device_num.getText().toString())
                , txt_customer_name.getText().toString()
                , Integer.parseInt(txt_phone_num.getText().toString())
                , txt_prob.getText().toString(), win7, win8, win10, check_all, software, hardware, battery, charger, bag, cd
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
        if(Cwin7.isSelected()){
            win7 = 1;
        }
        if(Cwin8.isSelected()){
            win8 = 1;
        }
        if(Cwin10.isSelected()){
            win10 = 1;
        }
        if(Ccheck_all.isSelected()){
            check_all = 1;
        }
        if(Csoftware.isSelected()){
            software = 1;
        }
        if(Chardware.isSelected()){
            hardware = 1;
        }if(Cbattery.isSelected()){
            battery = 1;
        }if(Ccharger.isSelected()){
            charger = 1;
        }if(Cbag.isSelected()){
            bag = 1;
        }
        if(Ccd.isSelected()){
            cd = 1;
        }
        
    }
    
    public  void emptfyForm(){
        txt_customer_name.setText("");
        txt_device_num.setText("");
        txt_prob.setText("");
        txt_phone_num.setText("");
        other.setText("");
        device_name.setText("");
        
        Cwin7.setSelected(false);
        Cwin8.setSelected(false);;
        Cwin10.setSelected(false);;
        Ccheck_all.setSelected(false);;
        Csoftware.setSelected(false);;
        Chardware.setSelected(false);;
        Cbattery.setSelected(false);;
        Ccharger.setSelected(false);;
        Cbag.setSelected(false);;
        Ccd.setSelected(false);;
    }
    
}
