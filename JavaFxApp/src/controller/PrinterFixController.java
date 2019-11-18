/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxapp.Main;

/**
 *
 * @author Ahmed
 */
public class PrinterFixController   implements Initializable {
    
    @FXML
    private CheckBox check_print;

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
    private void BtnSave(ActionEvent event) throws ClassNotFoundException, IOException {        
        if(database.RemoteDB.checkInternetConnection()){
            submit();
        }else{
            // alert
            utils.utils.AlertMSG("NO INTERNET CONNECTION");
        }
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
        // TODO
        database.DatabaseHelper.getCount();
        txt_device_num.setText(database.DatabaseHelper.getCounter() + 1 + "");
        Main.root.setOnKeyPressed(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent event) {
                switch(event.getCode()){
                    case ENTER:
                        try {
                        submit();
                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(HardwareFixController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(HardwareFixController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    
                }
            }

            
        });
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
        Cfill_printer.setSelected(false);
        Ccheck_all.setSelected(false);
        Ccd.setSelected(false);
        fill_printer = 0;
        change_dram = 0;
        check_all = 0;
        cable_power = 0;
        cable_data = 0;
        cd = 0;
        
        database.DatabaseHelper.getCount();
        txt_device_num.setText(database.DatabaseHelper.getCounter() + 1 + "");
    }
    
    private void ppp() throws IOException{
        PrinterJob job = PrinterJob.createPrinterJob();
        Printer printer = job.getPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.PORTRAIT, Printer.MarginType.HARDWARE_MINIMUM);
        if (job != null) {
           boolean success = job.printPage(pageLayout, comp());
           if (success) {
               job.endJob();
           }
        }
        
    }
    
    private void print() throws IOException{
        /*PrinterJob job = PrinterJob.createPrinterJob();
        Printer printer = job.setPrinter(utils.utils.PrinterDialog());*/
        utils.utils.PrinterDialog(comp(), "تم التسجيل بنجاح");
        
    }
    
    private Node comp() throws IOException{
        BorderPane root;
        root = new BorderPane();
        Label title = new Label("ايصال صيانه احبار\n =========");
        title.setFont(new Font("arial", 20));
        title.setAlignment(Pos.CENTER);
        title.setContentDisplay(ContentDisplay.CENTER);
        title.setTextAlignment(TextAlignment.CENTER);
        
        Label details = new Label(getProblemsString());
        details.setContentDisplay(ContentDisplay.CENTER);
        details.setTextAlignment(TextAlignment.CENTER);

        Label other = new Label("ملحقات \n=====");
        other.setFont(new Font("arial", 20));
        other.setContentDisplay(ContentDisplay.CENTER);
        
        Label others = new Label(getOthersString());
        others.setContentDisplay(ContentDisplay.CENTER);
        others.setTextAlignment(TextAlignment.CENTER);
        
        Label num = new Label("فاتوره رقم  " + txt_device_num.getText().toString());
        num.setContentDisplay(ContentDisplay.CENTER);
        num.setTextAlignment(TextAlignment.CENTER);
        
        Label date = new Label("\n   " + getDate() + " \n");
        date.setContentDisplay(ContentDisplay.CENTER);
        date.setTextAlignment(TextAlignment.CENTER);
        
        Label name = new Label(txt_customer_name.getText().toString());
        name.setContentDisplay(ContentDisplay.CENTER);
        name.setTextAlignment(TextAlignment.CENTER);
        name.setFont(new Font("arial", 20));
        Label policy = new Label(utils.PrinterText.POLICY_1);
        policy.setContentDisplay(ContentDisplay.CENTER);
        policy.setTextAlignment(TextAlignment.CENTER);
        policy.setFont(new Font("arial", 16));
        
        Label policy2 = new Label(utils.PrinterText.POLICY_2);
        policy2.setContentDisplay(ContentDisplay.CENTER);
        policy2.setTextAlignment(TextAlignment.CENTER);
        policy2.setFont(new Font("arial", 16));
        
        Label signature = new Label(utils.PrinterText.SIGNATURE);
        signature.setContentDisplay(ContentDisplay.RIGHT);
        signature.setTextAlignment(TextAlignment.RIGHT);
        signature.setFont(new Font("arial", 16));
        signature.setAlignment(Pos.BOTTOM_RIGHT);
        
        Label phone = new Label(utils.PrinterText.PHONE);
        phone.setContentDisplay(ContentDisplay.CENTER);
        phone.setTextAlignment(TextAlignment.CENTER);
        phone.setFont(new Font("arial", 16));
        phone.setAlignment(Pos.BOTTOM_CENTER);
        
        Label logo = new Label("CS Computer Shop");
        logo.setContentDisplay(ContentDisplay.CENTER);
        logo.setTextAlignment(TextAlignment.CENTER);
        logo.setFont(new Font("arial", 25));
        logo.setAlignment(Pos.CENTER);

        
        VBox vb = new VBox();
        vb.setAlignment(Pos.CENTER);
        vb.setStyle("-fx-padding: 30px, 30px, 30px, 30px");
        vb.getChildren().addAll(logo,title,name,num,details,other,others,date,policy,policy2,signature,phone);
	root.setCenter(vb);
        return root;
    }
    
        
    private static Timestamp getDate() {
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        return date;
    }
    
    
    private String getProblemsString(){
        String txt = device_name.getText().toString() + "\n";
        if(fill_printer == 1){
            txt = txt + "ملئ حبر";
        }
        if(change_dram == 1){
            txt = txt + "\n تغير درام";
        }
        if(check_all == 1){
            txt = txt + "\n check all";
        }
        
        if(txt_prob.getText().toString() != null || !txt_prob.getText().toString().equals("")){
            txt = txt + "\n  ***مشكلات اخري";
            txt = txt + "\n" + txt_prob.getText().toString() + "\n";
        }
        
        return txt + "\n";
        
    }
    
    private String getOthersString(){
        String txt = "";
        if(cable_power == 1){
            txt = txt + "\n cable power";
        }
        if(cable_data == 1){
            txt = txt + "\n cable power";
        }          if(cd == 1){
            txt = txt + "\n CD";
        }   
              
        if(other.getText().toString() != null || !other.getText().toString().equals("")){
            txt = txt + "\n *** ملحقات اخري";
            txt = txt + "\n" + other.getText().toString();
        }
        
        return txt;
    }
        
    private void submit() throws ClassNotFoundException, IOException {
        System.out.println("Save");
        //database.DatabaseHelper.makeDB();
        // check CheckBoxes
        checkCheckBoxes();
        if(database.RemoteDB.insertPrinterDevice(Integer.parseInt(txt_device_num.getText().toString())
                , txt_customer_name.getText().toString()
                , Integer.parseInt(txt_phone_num.getText().toString())
                , txt_prob.getText().toString(), fill_printer, change_dram, check_all, cable_power, cable_data, cd
                , other.getText().toString(), device_name.getText().toString(), 0)){
            
            if(check_print.isSelected()){
                print();
            }else{
                utils.utils.AlertMSG("تم التسجيل بنجاح");
            }
            //utils.utils.AlertMSG("تم التسجيل بنجاح");
            // incremnt devices counter
            database.DatabaseHelper.updateCount(Integer.parseInt(txt_device_num.getText().toString()));
            emptfyForm();
        }else{
            utils.utils.AlertMSG("عفوا لم يتم الحفظ");
        }
        
    }
        
}