/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import controller.HardwareFixController;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URLConnection; 
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DeviceItem;
import model.PrinterItem;

/**
 *
 * @author Ahmed
 */
public class DatabaseHelper {
    
    private static String unicode= "?useUnicode=yes&characterEncoding=UTF-8";
    private static String DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
    private static String DATABASE_USERNAME = "root";
    private static String DATABASE_PASSWORD = "";
    
    private static int counter;
    
    public static void makeDB(){
        
        Connection conn = null;
        Statement stmt = null;
        
        try {
            Boolean dbExist = new File("mydb.db").isFile();
            if(dbExist){
                System.out.println("DB mydb already Exist !");
            }else{
                System.out.println("DB mydb doesn't Exist, Making a new One .....");
                Class.forName("org.sqlite.JDBC");
                conn = DriverManager.getConnection("jdbc:sqlite:mydb.db");
                System.out.println("Opened database successfully");
                stmt = conn.createStatement();
                
                
                // Define Database Tables
                String sql_hardware_fix = "CREATE TABLE device_count (id Integer PRIMARY KEY AUTOINCREMENT, count int(255))";
                stmt.execute(sql_hardware_fix);
                System.out.println("Tables created successfully");
                String sql = "INSERT INTO device_count (count)" + "VALUES ('1');";
                stmt.executeUpdate(sql);
                stmt.close();
                conn.close();
            }
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
        
    }
    
    public static void updateCount(int counter){      
        Connection conn = null;
        Statement stmt = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:mydb.db");
            conn.setAutoCommit(false);
            System.out.println("Opened database successfully");
            
            // make some checks
            
            stmt = conn.createStatement();
            String sql = "UPDATE device_count SET count = ? "
                + "WHERE id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, counter);
            pstmt.setInt(2, 1);
            // update 
            pstmt.executeUpdate();
            
            stmt.close();
            conn.commit();
            conn.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        
        System.out.println("Device Recorded successfully");
        
        
    }
    
    
    
     public static void getCount(){      
         Connection conn = null;
         Statement stmt = null;
        
         try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:mydb.db");
            conn.setAutoCommit(false);
            System.out.println("Opened database successfully");
            
            // make some checks
            
            stmt = conn.createStatement();
            String sql = "SELECT * FROM device_count ORDER BY id DESC LIMIT 1";
            stmt.executeUpdate(sql);
            
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(rs.getInt("count"));
            counter = rs.getInt("count");
            stmt.close();
            conn.commit();
            conn.close();
            
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        
        System.out.println("Device Recorded successfully");
     }

    public static int getCounter() {
        return counter;
    }
    
}