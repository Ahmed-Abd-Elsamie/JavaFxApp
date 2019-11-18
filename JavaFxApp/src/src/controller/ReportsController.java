package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafxapp.Main;
import model.PrinterItem;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.*;


/**
 *
 * @author Ahmed
 */
public class ReportsController implements Initializable {

    private int last = 0;
    private int currentList = 0;
    JSONArray listDev = new JSONArray();
    private boolean search = false;
    
    @FXML
    private TextField txt_search;
    
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
    private void BtnHardware(ActionEvent event) throws IOException {
        if(database.RemoteDB.checkInternetConnection()){
            getDevice(0);
            currentList = 0;
        }else{
            // alert
            utils.utils.AlertMSG("NO INTERNET CONNECTION");
        }
    }
    
    @FXML
    private void BtnSoftware(ActionEvent event) throws IOException {
        if(database.RemoteDB.checkInternetConnection()){
            getDevice(1);
            currentList = 1;
        }else{
            // alert
            utils.utils.AlertMSG("NO INTERNET CONNECTION");
        }
    }
    
    @FXML
    private void BtnPrinters(ActionEvent event) throws IOException {
        if(database.RemoteDB.checkInternetConnection()){
            getDevice(2);
            currentList = 2;
        }else{
            // alert
            utils.utils.AlertMSG("NO INTERNET CONNECTION");
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //getLastView();
        txt_search.setOnKeyReleased(event -> {
            if (!event.getCode().isModifierKey()) {
                System.out.println(txt_search.getText());
                try {
                    // search func.
                    
                    listDev = database.RemoteDB.searchDevices(txt_search.getText().toString(), currentList);
                    if(listDev != null){
                        search = true;
                        getDevice(currentList);
                    }
                   
                } catch (IOException ex) {
                    Logger.getLogger(ReportsController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }
    
    
    private void getDevice(int type) throws IOException{
       
        Image red  = new Image("/res/images/red.png");
        Image green  = new Image("/res/images/green.png");
        Image blue  = new Image("/res/images/blue.png");
        Image right  = new Image("/res/images/true.png");

        
        if(!search){
            if(type == 0){ // hardware
                listDev = database.RemoteDB.getDevices(0);
            }else if(type == 1){ // software
                listDev = database.RemoteDB.getDevices(1);
            }else if(type == 2){ // printer
                listDev = database.RemoteDB.getDevices(2);
            }
        }
        
        List<String> strList = new ArrayList<>();
        for(int i = 0; i < listDev.length(); i++){
            String state = "";
            JSONObject device = listDev.getJSONObject(i);
            if(device.getInt("state") == 0){
                state = "في الانتظار";
            }else if(device.getInt("state") == 1){
                state = "تمت الصيانه";
            }else if(device.getInt("state") == 2){ // 2
                state = "لم تتم الصيانه";
            }else{
                state = "تم التسليم";
            }
            strList.add("  اسم الجهاز : " + device.getString("device_name") + "\n "
                    + "  رقم التليفون:  " + device.get("phone_num") + "\n"
                    + "   التاريخ:  " + device.get("date_time")  + "\n"
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
        
        //List<DeviceItem> listDevTemp = listDev;
        JSONArray listDevTemp = listDev;
        list.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                 System.out.println(list.getSelectionModel().getSelectedIndex());
                 utils.genVars.device = listDevTemp.getJSONObject(list.getSelectionModel().getSelectedIndex());
                 System.out.println(utils.genVars.device);
                   //type of clicking on item to show next window as a Device not printer
                 if(type == 0){
                     utils.genVars.type = 0;
                 }else if(type == 1){
                     utils.genVars.type = 1;
                 }else if(type == 2){
                     utils.genVars.type = 2;
                 }
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

   
    /*private void getLastView(){
        if(last == 1){ // hardware
            getDevice("hardware_fix");
            utils.genVars.hard_soft = 0; // hardware
        }else if(last == 2){ // software
            getDevice("software_fix");
            utils.genVars.hard_soft = 1; // software
        }else if(last == 3){ // printer
            getPrinter();
        }
    }*/
    
    
    
    
    

}