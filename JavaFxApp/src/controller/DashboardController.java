/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafxapp.Main;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Ahmed
 */
public class DashboardController implements Initializable {
    
    
    @FXML
    private Label num_hard; 
    
    @FXML
    private Label num_soft; 
        
    @FXML
    private Label num_print; 
    
    @FXML
    private Label num_hard_done; 
    
    @FXML
    private Label num_soft_done; 
        
    @FXML
    private Label num_print_done; 

    @FXML
    private Label num_hard_sub; 
    
    @FXML
    private Label num_soft_sub; 
        
    @FXML
    private Label num_print_sub; 

    @FXML
    private Label num_hard_not; 
    
    @FXML
    private Label num_soft_not; 
        
    @FXML
    private Label num_print_not;     
    
    
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
    
    @FXML
    private void BtnRefresh(ActionEvent event) throws IOException {
        System.out.println("Refresh");
        JSONArray analysis = new JSONArray();
        analysis = database.RemoteDB.getDevicesAnalysis();
        JSONObject obj = analysis.getJSONObject(0);
        
        num_hard.setText(obj.get("num_hardware") + "");
        num_soft.setText(obj.get("num_software") + "");
        num_print.setText(obj.get("num_printer") + "");
        
        num_hard_done.setText(obj.get("num_hardware_done") + "");
        num_soft_done.setText(obj.get("num_software_done") + "");
        num_print_done.setText(obj.get("num_printer_done") + "");

        num_hard_sub.setText(obj.get("num_hardware_submitted") + "");
        num_soft_sub.setText(obj.get("num_software_submitted") + "");
        num_print_sub.setText(obj.get("num_printer_submitted") + "");

        num_hard_not.setText(obj.get("num_hardware_not") + "");
        num_soft_not.setText(obj.get("num_software_not") + "");
        num_print_not.setText(obj.get("num_printer_not") + "");    

        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                
        JSONArray analysis = new JSONArray();
        try {
            analysis = database.RemoteDB.getDevicesAnalysis();
        } catch (IOException ex) {
            Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        JSONObject obj = analysis.getJSONObject(0);
        
        num_hard.setText(obj.get("num_hardware") + "");
        num_soft.setText(obj.get("num_software") + "");
        num_print.setText(obj.get("num_printer") + "");
        
        num_hard_done.setText(obj.get("num_hardware_done") + "");
        num_soft_done.setText(obj.get("num_software_done") + "");
        num_print_done.setText(obj.get("num_printer_done") + "");

        num_hard_sub.setText(obj.get("num_hardware_submitted") + "");
        num_soft_sub.setText(obj.get("num_software_submitted") + "");
        num_print_sub.setText(obj.get("num_printer_submitted") + "");

        num_hard_not.setText(obj.get("num_hardware_not") + "");
        num_soft_not.setText(obj.get("num_software_not") + "");
        num_print_not.setText(obj.get("num_printer_not") + "");        

    }
    
}