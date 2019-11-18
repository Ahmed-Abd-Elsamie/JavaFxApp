package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafxapp.Main;
import model.DeviceItem;
import model.PrinterItem;
import org.json.JSONObject;

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
    private TextField txt_price;
    
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
    private void BtnPrint(ActionEvent event) throws IOException {
        System.out.println("Print");
        //ppp();
        print();
    }

    
    @FXML
    private void BtnSaveChange(ActionEvent event) throws IOException, ClassNotFoundException {
        System.out.println("Save Changes");
        // check CheckBoxes
        GetCheckedCheckBoxes();
        int id = utils.genVars.device.getInt("id");
        if(utils.genVars.type == 0){
            if(database.RemoteDB.checkInternetConnection()){
                if(database.RemoteDB.updateHardware(id
                        , txt_customer_name.getText().toString()
                        , Integer.parseInt(txt_phone_num.getText().toString())
                        , txt_prob.getText().toString(), win7, win8, win10, check_all, software, hardware, battery, charger, bag, cd
                        , other.getText().toString(), device_name.getText().toString(), Float.parseFloat(txt_price.getText().toString()))){
                utils.utils.AlertMSG("تم التعديل بنجاح");
                // increment devices counter
                database.DatabaseHelper.updateCount(Integer.parseInt(txt_device_num.getText().toString()));
                }else{
                    utils.utils.AlertMSG("عفوا لم يتم الحفظ");
                }
            }else{
                utils.utils.AlertMSG("NO INTERNET CONNECTION");
            }
            
        }else if(utils.genVars.type == 1){
            
            if(database.RemoteDB.checkInternetConnection()){
                if(database.RemoteDB.updateSoftware(id
                        , txt_customer_name.getText().toString()
                        , Integer.parseInt(txt_phone_num.getText().toString())
                        , txt_prob.getText().toString(), win7, win8, win10, check_all, software, battery, charger, bag, cd
                        , other.getText().toString(), device_name.getText().toString(), Float.parseFloat(txt_price.getText().toString()))){
                utils.utils.AlertMSG("تم التعديل بنجاح");
                // increment devices counter
                database.DatabaseHelper.updateCount(Integer.parseInt(txt_device_num.getText().toString()));
                }else{
                    utils.utils.AlertMSG("عفوا لم يتم الحفظ");
                }
            }else{
                utils.utils.AlertMSG("NO INTERNET CONNECTION");
            }
            
            
        }else{
            if(database.RemoteDB.checkInternetConnection()){
                if(database.RemoteDB.updatePrinterint(id
                , txt_customer_name.getText().toString()
                , Integer.parseInt(txt_phone_num.getText().toString())
                , txt_prob.getText().toString(), fill_printer, change_dram, check_all, cable_power, cable_data, cd
                , other.getText().toString(), device_name.getText().toString(), Float.parseFloat(txt_price.getText().toString()))){
                utils.utils.AlertMSG("تم التعديل بنجاح");
                // increment devices counter
                database.DatabaseHelper.updateCount(Integer.parseInt(txt_device_num.getText().toString()));
                }else{
                    utils.utils.AlertMSG("عفوا لم يتم الحفظ");
                }
            }else{
                utils.utils.AlertMSG("NO INTERNET CONNECTION");
            }
            
        }
    }
    
    
    @FXML
    private void BtnDelete(ActionEvent event) throws IOException {
        System.out.println("Delete");
        int id = utils.genVars.device.getInt("id");
        if(database.RemoteDB.checkInternetConnection()){
            if(utils.genVars.type == 0){
                if(database.RemoteDB.deleteDevice(id, "hardware_fix")){
                    utils.utils.AlertMSG("تم الحذف بنجاح");
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/fxml/Reports.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Main.setContent(root, "Student home");

                    } catch (Exception e) {

                    }
                    
                }else{
                    utils.utils.AlertMSG("لم يتم الحذف ");
                }
                
            }else if(utils.genVars.type == 1){
                if(database.RemoteDB.deleteDevice(id, "software_fix")){
                    utils.utils.AlertMSG("تم الحذف بنجاح");
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/fxml/Reports.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Main.setContent(root, "Student home");

                    } catch (Exception e) {

                    }                    
                }else{
                    utils.utils.AlertMSG("لم يتم الحذف ");
                }
            }else{
                if(database.RemoteDB.deleteDevice(id, "printer_fix")){
                    utils.utils.AlertMSG("تم الحذف بنجاح");
                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/res/fxml/Reports.fxml"));
                        Parent root = (Parent) fxmlLoader.load();
                        Main.setContent(root, "Student home");

                    } catch (Exception e) {

                    }                }else{
                    utils.utils.AlertMSG("لم يتم الحذف ");
                }
            }
        }
        
        
    }

    
    @FXML
    private void BtnDone(ActionEvent event) throws IOException {

        int id = utils.genVars.device.getInt("id");
        if(utils.genVars.type == 0){
            database.RemoteDB.updateDevice(id, 1, "hardware_fix");
        }else if(utils.genVars.type == 1){
            database.RemoteDB.updateDevice(id, 1, "software_fix");
        }else{
            database.RemoteDB.updateDevice(id, 1, "printer_fix");
        }
        utils.genVars.device.put("state", 1);
        init();
        System.out.println("DONE");
        
    }
    
        
    @FXML
    private void BtnNotDone(ActionEvent event) throws IOException {
        int id = utils.genVars.device.getInt("id");
        if(utils.genVars.type == 0){
            database.RemoteDB.updateDevice(id, 2, "hardware_fix");
        }else if(utils.genVars.type == 1){
            database.RemoteDB.updateDevice(id, 2, "software_fix");
        }else{
            database.RemoteDB.updateDevice(id, 2, "printer_fix");
        }
        utils.genVars.device.put("state", 2);
        init();
        System.out.println("NOT DONE");
                
    }
    
    @FXML
    private void BtnSubmit(ActionEvent event) throws IOException {
        
        int id = utils.genVars.device.getInt("id");
        if(utils.genVars.type == 0){
            database.RemoteDB.updateDevice(id, 3, "hardware_fix");
        }else if(utils.genVars.type == 1){
            database.RemoteDB.updateDevice(id, 3, "software_fix");
        }else{
            database.RemoteDB.updateDevice(id, 3, "printer_fix");
        }
        utils.genVars.device.put("state", 3);
        init();
        System.out.println("SUBMITED");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        init();
    }    
    
    private void init(){
        
        if(utils.genVars.type == 0){// hardware
            JSONObject deviceItem = utils.genVars.device;
            txt_device_num.setText(deviceItem.get("device_num") + "");
            txt_customer_name.setText(deviceItem.get("customer_name") + "");
            txt_phone_num.setText(deviceItem.get("phone_num") + "" + "");
            txt_prob.setText(deviceItem.get("prob") + "");
            txt_date_time.setText(deviceItem.get("date_time") + "");
            device_name.setText(deviceItem.get("device_name") + "");
            txt_price.setText(deviceItem.get("price") + "");
            other.setText(deviceItem.get("other") + "");
            win7 =  deviceItem.getInt("win7");
            win8 =  deviceItem.getInt("win8");
            win10 =  deviceItem.getInt("win10");
            check_all =  deviceItem.getInt("check_all");
            software =  deviceItem.getInt("software");
            hardware =  deviceItem.getInt("hardware");
            battery =  deviceItem.getInt("battery");
            charger =  deviceItem.getInt("charger");
            bag =  deviceItem.getInt("bag");
            cd =  deviceItem.getInt("cd");
            
            if(deviceItem.getInt("state") == 3){
                btn_submit.setDisable(true);
                btn_done.setDisable(true);
                btn_not_done.setDisable(true);
                
                txt_state.setText("تم التسليم");
                
            }
            if(deviceItem.getInt("state") == 1){
                txt_state.setText("تمت الصيانه");
            }
            if(deviceItem.getInt("state") == 2){
                txt_state.setText("لم تتم الصيانه");
            }          
            if(deviceItem.getInt("state") == 0){
                txt_state.setText("في الانتظار");
            }                      
                        
        }else if(utils.genVars.type == 1){ // software
            JSONObject deviceItem = utils.genVars.device;
            txt_device_num.setText(deviceItem.get("device_num") + "");
            txt_customer_name.setText(deviceItem.get("customer_name") + "");
            txt_phone_num.setText(deviceItem.get("phone_num") + "" + "");
            txt_prob.setText(deviceItem.get("prob") + "");
            txt_date_time.setText(deviceItem.get("date_time") + "");
            device_name.setText(deviceItem.get("device_name") + "");
            txt_price.setText(deviceItem.get("price") + "");
            other.setText(deviceItem.get("other") + "");
            win7 =  deviceItem.getInt("win7");
            win8 =  deviceItem.getInt("win8");
            win10 =  deviceItem.getInt("win10");
            check_all =  deviceItem.getInt("check_all");
            software =  deviceItem.getInt("software");
            battery =  deviceItem.getInt("battery");
            charger =  deviceItem.getInt("charger");
            bag =  deviceItem.getInt("bag");
            cd =  deviceItem.getInt("cd");
            
            if(deviceItem.getInt("state") == 3){
                btn_submit.setDisable(true);
                btn_done.setDisable(true);
                btn_not_done.setDisable(true);
                
                txt_state.setText("تم التسليم");
                
            }
            if(deviceItem.getInt("state") == 1){
                txt_state.setText("تمت الصيانه");
            }
            if(deviceItem.getInt("state") == 2){
                txt_state.setText("لم تتم الصيانه");
            }          
            if(deviceItem.getInt("state") == 0){
                txt_state.setText("في الانتظار");
            }                      
                             
            
        }else if(utils.genVars.type == 2){ // printer
            JSONObject printerItem = utils.genVars.device;
            txt_device_num.setText(printerItem.get("device_num") + "");
            txt_customer_name.setText(printerItem.get("customer_name") + "");
            txt_phone_num.setText(printerItem.get("phone_num") + "");
            txt_prob.setText(printerItem.get("prob") + "");
            txt_date_time.setText(printerItem.get("date_time") + "");
            device_name.setText(printerItem.get("device_name") + "");
            txt_price.setText(printerItem.get("price") + "");
            other.setText(printerItem.get("other") + "");
            fill_printer =  printerItem.getInt("fill_printer");
            cable_power =  printerItem.getInt("cable_power");
            cable_data =  printerItem.getInt("cable_data");
            change_dram =  printerItem.getInt("change_dram");
            check_all = printerItem.getInt("check_all");
            cd =  printerItem.getInt("cd");
            
            if(printerItem.getInt("state") == 3){
                btn_submit.setDisable(true);
                btn_done.setDisable(true);
                btn_not_done.setDisable(true);
                txt_state.setText("تم التسليم");
            }
            if(printerItem.getInt("state") == 1){
                txt_state.setText("تمت الصيانه");
            }
            if(printerItem.getInt("state") == 2){
                txt_state.setText("لم تتم الصيانه");
            }          
            if(printerItem.getInt("state") == 0){
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
    
    private void GetCheckedCheckBoxes() {
        win7 = 0;
        win8 = 0;
        win10 = 0;
        check_all = 0;
        software = 0;
        hardware = 0;
        battery = 0;
        charger = 0;
        bag = 0;
        cd = 0;
        fill_printer = 0;
        change_dram = 0;
        cable_power = 0;
        cable_data = 0;
        
        
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
        if(Cfill_printer.isSelected()){
            fill_printer = 1;
        }
        if(Cchange_dram.isSelected()){
            change_dram = 1;
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
    
        
    private String getProblemsString(){
        String txt = device_name.getText().toString() + "\n";
        if(win7 == 1){
            txt = txt + "win7";
        }
        if(win8 == 1){
            txt = txt + "\n win8";
        }
        if(win10 == 1){
            txt = txt + "\n win10";
        }
        if(check_all == 1){
            txt = txt + "\n check all";
        }
        if(software == 1){
            txt = txt + "\n software";
        }
        if(hardware == 1){
            txt = txt + "\n hardware";
        }
        if(fill_printer == 1){
            txt = txt + "\n ملئ حبر";
        }
        if(change_dram == 1){
            txt = txt + "\n تغيير درام";
        }
        
        if(txt_prob.getText().toString() != null || !txt_prob.getText().toString().equals("")){
            txt = txt + "\n  مشكلات اخري";
            txt = txt + "\n" + txt_prob.getText().toString() + "\n";
        }
        
        return txt + "\n";
        
    }
    
    private String getOthersString(){
        String txt = "";
        if(battery == 1){
            txt = txt + "\n بطاريه";
        }
        if(charger == 1){
            txt = txt + "\n شاحن";
        }
        if(cd == 1){
            txt = txt + "\n CD";
        }        
        if(bag == 1){
            txt = txt + "\n حقيبه";
        }
        if(cable_power == 1){
            txt = txt + "\n Cable Power";
        }        
        if(cable_data == 1){
            txt = txt + "\n Cable Data";
        }
        if(other.getText().toString() != null || !other.getText().toString().equals("")){
            txt = txt + "\n ملحقات اخري";
            txt = txt + "\n" + other.getText().toString();
        }
        
        return txt;
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
        utils.utils.PrinterDialog(comp(), "تم الارسال الي الطابعه");
        
    }
    
    private Node comp() throws IOException{
        BorderPane root;
        root = new BorderPane();
        Label title = new Label();
        if(utils.genVars.type == 0){
            title.setText("ايصال صيانه هاردوير\n ============");
        }else if(utils.genVars.type == 1){
            title.setText("ايصال صيانه سوفت وير\n ============");
        }else{
            title.setText("ايصال صيانه احبار\n =========");
        }
        
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
        other.setTextAlignment(TextAlignment.CENTER);
        
        Label others = new Label(getOthersString());
        others.setContentDisplay(ContentDisplay.CENTER);
        others.setTextAlignment(TextAlignment.CENTER);
        
        Label num = new Label("ايصال رقم  " + txt_device_num.getText().toString());
        num.setContentDisplay(ContentDisplay.CENTER);
        num.setTextAlignment(TextAlignment.CENTER);
        
        Label date = new Label("\n   " + txt_date_time.getText() + "\n ------------------------------------------------------------------------");
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
}