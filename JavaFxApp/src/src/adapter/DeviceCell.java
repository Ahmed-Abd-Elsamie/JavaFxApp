/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adapter;

import java.io.IOException;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;
import model.DeviceItem;

/**
 *
 * @author Ahmed
 */
public class DeviceCell extends ListCell<DeviceItem>{
    
    @FXML
    private Label device_name;

    @FXML
    private Label device_state;

    @FXML
    private Label date_time;
    
    @FXML
    private GridPane anchorPane;
    
    private FXMLLoader mLLoader;


    @Override
    protected void updateItem(DeviceItem deviceItem, boolean empty) {
        super.updateItem(deviceItem, empty);

        if(empty || deviceItem == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("res/fxml/DeviceItem.fxml"));
                mLLoader.setController(this);

                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            device_name.setText(String.valueOf(deviceItem.getOther()));
            device_state.setText(deviceItem.getTxt_customer_name());


            setText(null);
            setGraphic(anchorPane);
        }

    }
}
