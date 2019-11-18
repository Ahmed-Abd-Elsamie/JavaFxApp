/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Ahmed
 */
public class Main extends Application {
    
    public static BorderPane root;

    @Override
    public void start(Stage stage) throws Exception {
        
        Parent content = FXMLLoader.load(getClass().getResource("/res/fxml/Main.fxml"));
        //Root 
        root = new BorderPane();
	root.setCenter(content);
        
        Scene scene = new Scene(root);
        //scene.getStylesheets().add("/res/css/stylesheet.css");
        stage.setScene(scene);
        stage.show();
        //stage.setResizable(false);
        stage.setMinHeight(700); 
        stage.setMinWidth(1000);
        stage.setTitle("كمبيوتر شوب");
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);        
    }
    
    public static void setContent(Node node , String title) {
        root.setCenter(null);
        root.setCenter(node);
        
	
    }
    
    /*private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
*/
    
}