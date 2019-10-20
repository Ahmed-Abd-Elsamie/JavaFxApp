/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapp;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;

/**
 *
 * @author Ahmed
 */
public class MainController implements Initializable {
    
    @FXML
    private void BtnHardwareFix(ActionEvent event) {
        System.out.println("Hardware Fix");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/fxml/HardwareFix.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Main.setContent(root, "Student home");
            
        } catch (Exception e) {
            
        }
    }
    
    @FXML
    private void BtnSoftwareFix(ActionEvent event) {
        System.out.println("Software Fix");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/fxml/SoftwareFix.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Main.setContent(root, "");
            
        } catch (Exception e) {
            
        }
    }
    
    @FXML
    private void BtnBackup(ActionEvent event) {
        
    }    
    
    @FXML
    private void BtnPrinterFix(ActionEvent event) {
        System.out.println("Priner Fix");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/fxml/PrinterFix.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Main.setContent(root, "Student home");
            
        } catch (Exception e) {
            
        }
    }
    
    @FXML
    private void BtnReports(ActionEvent event) {
        System.out.println("Reports");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/fxml/Reports.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Main.setContent(root, "Student home");
            
        } catch (Exception e) {
            
        }
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
