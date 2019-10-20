/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import controller.HardwareFixController;
import java.io.File;
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
                String sql_hardware_fix = "CREATE TABLE hardware_fix (id Integer PRIMARY KEY AUTOINCREMENT,"
                        + " device_num Integer(50),"
                        + " customer_name TEXT NOT NULL,"
                        + " phone_num Integer(255),"
                        + " prob TEXT,"
                        + " win7 Integer(1),"
                        + " win8 Integer(1),"
                        + " win10 Integer(1),"
                        + " check_all Integer(1),"
                        + " hardware Integer(1),"
                        + " software Integer(1),"
                        + " battery Integer(1),"
                        + " charger Integer(1),"
                        + " bag Integer(1),"
                        + " cd Integer(1),"
                        + " other TEXT,"
                        + " date_time DATE,"
                        + " device_name TEXT,"
                        + " state int(1))";
                
                stmt.execute(sql_hardware_fix);
                
                String sql_software_fix = "CREATE TABLE software_fix (id Integer PRIMARY KEY AUTOINCREMENT,"
                        + " device_num Integer(50),"
                        + " customer_name TEXT NOT NULL,"
                        + " phone_num Integer(255),"
                        + " prob TEXT,"
                        + " win7 Integer(1),"
                        + " win8 Integer(1),"
                        + " win10 Integer(1),"
                        + " check_all Integer(1),"
                        + " software Integer(1),"
                        + " battery Integer(1),"
                        + " charger Integer(1),"
                        + " bag Integer(1),"
                        + " cd Integer(1),"
                        + " other TEXT,"
                        + " date_time DATE,"
                        + " device_name TEXT,"
                        + " state int(1))";
                
                stmt.execute(sql_software_fix);
                
                String sql_printer_fix = "CREATE TABLE printer_fix (id Integer PRIMARY KEY AUTOINCREMENT,"
                        + " device_num Integer(50),"
                        + " customer_name TEXT NOT NULL,"
                        + " phone_num Integer(255),"
                        + " prob TEXT,"
                        + " fill_printer Integer(1),"
                        + " change_dram Integer(1),"
                        + " check_all Integer(1),"
                        + " cable_power Integer(1),"
                        + " cable_data Integer(1),"
                        + " cd Integer(1),"
                        + " other TEXT,"
                        + " date_time DATE,"
                        + " device_name TEXT,"
                        + " state int(1))";
                
                stmt.executeUpdate(sql_printer_fix);
                
                System.out.println("Tables created successfully");
                stmt.close();
                conn.close();
            }
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            e.printStackTrace();
            System.exit(0);
        }
        
    }
    
    public static void insertHardwareDevice(int device_num, String customer_name, int phone_number, String prob, int win7, int win8, int win10, int check_all, int software, int hardware, int battery, int charger, int bag, int cd, String other, String device_name, int state) throws ClassNotFoundException{
        
        /*Connection conn = null;
        Statement stmt = null;
        
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:mydb.db");
            conn.setAutoCommit(false);
            System.out.println("Opened database successfully");
            
            // make some checks
            
            stmt = conn.createStatement();
            String sql = "INSERT INTO hardware_fix (device_num, customer_name, phone_num, prob, win7, win8, win10, check_all, software, hardware, battery, charger, bag, cd, date_time, other) " + "VALUES ("+ device_num +","
                    + ""+ customer_name +","
                    + ""+ phone_number +","
                    + ""+ prob +","
                    + ""+ win7 +","
                    + ""+ win8 +","
                    + ""+ win10 +","
                    + ""+ check_all +","
                    + ""+ software +","
                    + ""+ hardware +","
                    + ""+ battery +","
                    + ""+ charger +","
                    + ""+ bag +","
                    + ""+ cd +","
                    + ""+ null +","
                    + ""+ other +");";
            
            stmt.executeUpdate(sql);
            // show some notification
            stmt.close();
            conn.commit();
            conn.close();
            
            
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        
        System.out.println("Device Recorded successfully");

*/
        
        if(checkInternetConnection()){
            onlineDBConfig();
        }else{
            localDBConfig();
        }
        
        
        String SQL_INSERT  = "INSERT INTO hardware_fix (device_num, customer_name, phone_num, prob, win7, win8, win10, check_all, software, hardware, battery, charger, bag, cd, date_time, other, device_name, state) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL + unicode, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT)) {
            preparedStatement.executeQuery("SET NAMES 'UTF8'");
            preparedStatement.executeQuery("SET CHARACTER SET 'UTF8'");
            preparedStatement.setInt(1, device_num);
            preparedStatement.setString(2, customer_name);
            preparedStatement.setInt(3, phone_number);
            preparedStatement.setString(4, prob);
            preparedStatement.setInt(5, win7);
            preparedStatement.setInt(6, win8);
            preparedStatement.setInt(7, win10);
            preparedStatement.setInt(8, check_all);
            preparedStatement.setInt(9, software);
            preparedStatement.setInt(10, hardware);
            preparedStatement.setInt(11, battery);
            preparedStatement.setInt(12, charger);
            preparedStatement.setInt(13, bag);
            preparedStatement.setInt(14, cd);
            preparedStatement.setTimestamp(15, getDate());
            preparedStatement.setString(16, other);
            preparedStatement.setString(17, device_name);
            preparedStatement.setInt(18, state);
            
            int row = preparedStatement.executeUpdate();
            // rows affected
            System.out.println(row); //1
            if(row == 1){
                AlertMSG("تم الحفظ بنجاح");
            }else{
                AlertMSG("لم يتم الحفظ");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    
    public static void insertSoftwareDevice(int device_num, String customer_name, int phone_number, String prob, int win7, int win8, int win10, int check_all, int software, int battery, int charger, int bag, int cd, String other,  String device_name, int state) throws ClassNotFoundException{
        
        if(checkInternetConnection()){
            onlineDBConfig();
        }else{
            localDBConfig();
        }
        
        String SQL_INSERT  = "INSERT INTO software_fix (device_num, customer_name, phone_num, prob, win7, win8, win10, check_all, software, battery, charger, bag, cd, date_time, other, device_name, state) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL + unicode, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT)) {

            preparedStatement.setInt(1, device_num);
            preparedStatement.setString(2, customer_name);
            preparedStatement.setInt(3, phone_number);
            preparedStatement.setString(4, prob);
            preparedStatement.setInt(5, win7);
            preparedStatement.setInt(6, win8);
            preparedStatement.setInt(7, win10);
            preparedStatement.setInt(8, check_all);
            preparedStatement.setInt(9, software);
            preparedStatement.setInt(10, battery);
            preparedStatement.setInt(11, charger);
            preparedStatement.setInt(12, bag);
            preparedStatement.setInt(13, cd);
            preparedStatement.setTimestamp(14, getDate());
            preparedStatement.setString(15, other);
            preparedStatement.setString(16, device_name);
            preparedStatement.setInt(17, state);
            
            int row = preparedStatement.executeUpdate();
            // rows affected
            System.out.println(row); //1
            if(row == 1){
                AlertMSG("تم الحفظ بنجاح");
            }else{
                AlertMSG("لم يتم الحفظ");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
        public static void insertPrinterDevice(int device_num, String customer_name, int phone_number, String prob, int fill_printer, int change_dram, int check_all, int cable_power, int cable_data, int cd, String other, String device_name, int state) throws ClassNotFoundException{
        
        if(checkInternetConnection()){
            onlineDBConfig();
        }else{
            localDBConfig();
        }
        
        String SQL_INSERT  = "INSERT INTO printer_fix (device_num, customer_name, phone_num, prob, fill_printer, change_dram, check_all, cable_power, cable_data, cd, other, date_time, device_name, state) " + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Class.forName("com.mysql.jdbc.Driver");
        try (Connection conn = DriverManager.getConnection(
                DATABASE_URL + unicode, DATABASE_USERNAME, DATABASE_PASSWORD);
             PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT)) {

            preparedStatement.setInt(1, device_num);
            preparedStatement.setString(2, customer_name);
            preparedStatement.setInt(3, phone_number);
            preparedStatement.setString(4, prob);
            preparedStatement.setInt(5, fill_printer);
            preparedStatement.setInt(6, change_dram);
            preparedStatement.setInt(7, check_all);
            preparedStatement.setInt(8, cable_power);
            preparedStatement.setInt(9, cable_data);
            preparedStatement.setInt(10, cd);
            preparedStatement.setString(11, other);
            preparedStatement.setTimestamp(12, getDate());
            preparedStatement.setString(13, device_name);
            preparedStatement.setInt(14, state);
            
            int row = preparedStatement.executeUpdate();
            // rows affected
            System.out.println(row); //1
            if(row == 1){
                AlertMSG("تم الحفظ بنجاح");
            }else{
                AlertMSG("لم يتم الحفظ");
            }

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
        
   
        
    public static List<DeviceItem> getHardwareDevices(String table){
        String query = "SELECT * FROM " + table;
        if(checkInternetConnection()){
            onlineDBConfig();
        }else{
            localDBConfig();
        }   
        List<DeviceItem> list = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");  
            Connection conn = DriverManager.getConnection(DATABASE_URL + unicode, DATABASE_USERNAME, DATABASE_PASSWORD);
            
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                DeviceItem item = new DeviceItem();
                item.setId(resultSet.getInt("id"));
                item.setTxt_device_num(resultSet.getInt("device_num"));
                item.setTxt_customer_name(resultSet.getString("customer_name"));
                item.setTxt_phone_num(resultSet.getInt("phone_num"));
                item.setTxt_prob(resultSet.getString("prob"));
                item.setWin7(resultSet.getInt("win7"));
                item.setWin8(resultSet.getInt("win8"));
                item.setWin10(resultSet.getInt("win10"));
                item.setCheck_all(resultSet.getInt("check_all"));
                item.setHardware(resultSet.getInt("hardware"));
                item.setSoftware(resultSet.getInt("software"));
                item.setBattery(resultSet.getInt("battery"));
                item.setCharger(resultSet.getInt("charger"));
                item.setBag(resultSet.getInt("bag"));
                item.setCd(resultSet.getInt("cd"));
                item.setOther(resultSet.getString("other"));
                item.setDate_time(resultSet.getTimestamp("date_time"));
                item.setDevice_name(resultSet.getString("device_name"));
                item.setState(resultSet.getInt("state"));
                
                list.add(item);
                
                System.out.println(resultSet.getString("customer_name"));
            }
            
            
        } catch (Exception e) {
            
        }
        Collections.reverse(list);
        return list;
    }
    
    
        
    public static List<DeviceItem> getSoftwareDevices(String table){
        String query = "SELECT * FROM " + table;
        if(checkInternetConnection()){
            onlineDBConfig();
        }else{
            localDBConfig();
        }  
        List<DeviceItem> list = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");  
            Connection conn = DriverManager.getConnection(DATABASE_URL + unicode, DATABASE_USERNAME, DATABASE_PASSWORD);
            
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                DeviceItem item = new DeviceItem();
                item.setId(resultSet.getInt("id"));
                item.setTxt_device_num(resultSet.getInt("device_num"));
                item.setTxt_customer_name(resultSet.getString("customer_name"));
                item.setTxt_phone_num(resultSet.getInt("phone_num"));
                item.setTxt_prob(resultSet.getString("prob"));
                item.setWin7(resultSet.getInt("win7"));
                item.setWin8(resultSet.getInt("win8"));
                item.setWin10(resultSet.getInt("win10"));
                item.setCheck_all(resultSet.getInt("check_all"));
                item.setSoftware(resultSet.getInt("software"));
                item.setBattery(resultSet.getInt("battery"));
                item.setCharger(resultSet.getInt("charger"));
                item.setBag(resultSet.getInt("bag"));
                item.setCd(resultSet.getInt("cd"));
                item.setOther(resultSet.getString("other"));
                item.setDate_time(resultSet.getTimestamp("date_time"));
                item.setDevice_name(resultSet.getString("device_name"));
                item.setState(resultSet.getInt("state"));
                
                list.add(item);
                
                System.out.println(resultSet.getString("customer_name"));
            }
            
            
        } catch (Exception e) {
            
        }
        Collections.reverse(list);
        return list;
    }
    
        
    public static List<PrinterItem> getPrinerDevices(String table){
        String query = "SELECT * FROM " + table;
        if(checkInternetConnection()){
            onlineDBConfig();
        }else{
            localDBConfig();
        } 
        List<PrinterItem> list = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.jdbc.Driver");  
            Connection conn = DriverManager.getConnection(DATABASE_URL + unicode, DATABASE_USERNAME, DATABASE_PASSWORD);
            
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            while(resultSet.next()){
                PrinterItem item = new PrinterItem();
                item.setId(resultSet.getInt("id"));
                item.setTxt_device_num(resultSet.getInt("device_num"));
                item.setTxt_customer_name(resultSet.getString("customer_name"));
                item.setTxt_phone_num(resultSet.getInt("phone_num"));
                item.setTxt_prob(resultSet.getString("prob"));
                item.setFill_printer(resultSet.getInt("fill_printer"));
                item.setChange_dram(resultSet.getInt("change_dram"));
                item.setCheck_all(resultSet.getInt("check_all"));
                item.setCable_power(resultSet.getInt("cable_power"));
                item.setCable_data(resultSet.getInt("cable_data"));
                item.setCd(resultSet.getInt("cd"));
                item.setOther(resultSet.getString("other"));
                item.setDate_time(resultSet.getTimestamp("date_time"));
                item.setDevice_name(resultSet.getString("device_name"));
                item.setState(resultSet.getInt("state"));
                
                list.add(item);
                
                System.out.println(resultSet.getString("customer_name"));
            }
            
            
        } catch (Exception e) {
            
        }
        
        Collections.reverse(list);
        return list;
    }

    private static Timestamp getDate() {
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        return date;
    }

    private static void AlertMSG(String msg){
        Stage dialogStage = new Stage();
        dialogStage.initModality(Modality.WINDOW_MODAL);
        Button btn = new Button("OK");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dialogStage.hide();
            }
        });
        VBox vbox = new VBox(new Text(msg), btn);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(30));
        dialogStage.setScene(new Scene(vbox));
        dialogStage.show();
        
        
    }
    
    
    public static void updateItem(int id, String table, int type){
        if(checkInternetConnection()){
            onlineDBConfig();
        }else{
            localDBConfig();
        }
        
        System.out.println(id + "    " + table + "    " + type);
        
        try {
            String sqlUpdate = "UPDATE " + table + " SET state = ? WHERE id = ?";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DATABASE_URL + unicode, DATABASE_USERNAME, DATABASE_PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);
            pstmt.setInt(1, type);
            pstmt.setInt(2, id);
            
            int row = pstmt.executeUpdate();
            
            System.out.println(row);
            
        } catch (Exception e) {
        }
        
    }
    
    
    public static boolean checkInternetConnection(){
        try { 
            URL url = new URL("https://www.google.com"); 
            URLConnection connection = url.openConnection(); 
            connection.connect(); 
  
            System.out.println("Connection Successful"); 
            return true;
        } 
        catch (Exception e) { 
            System.out.println("Internet Not Connected"); 
            return false;
        } 
    }
    
    
    private static void localDBConfig(){
        unicode = "?useUnicode=yes&characterEncoding=UTF-8";
        DATABASE_URL = "jdbc:mysql://localhost:3306/mydb";
        DATABASE_USERNAME = "root";
        DATABASE_PASSWORD = "";
        System.out.println("LOCALE");
    }
    
    private static void onlineDBConfig(){
        unicode = "?useUnicode=yes&characterEncoding=UTF-8";
        DATABASE_URL = "jdbc:mysql://sql2.freemysqlhosting.net:3306/sql2309193";   //?useSSL=true
        DATABASE_USERNAME = "sql2309193";
        DATABASE_PASSWORD = "mA6!sV3!";
        System.out.println("ONLINE");
    }

}