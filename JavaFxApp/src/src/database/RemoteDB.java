package database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;
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
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Ahmed
 */
public class RemoteDB {
    
    private static JSONArray searchArr;
        
    public static boolean insertHardwareDevice(int device_num, String customer_name, int phone_number, String prob, int win7, int win8, int win10, int check_all, int software, int hardware, int battery, int charger, int bag, int cd, String other, String device_name, int state) throws ClassNotFoundException, MalformedURLException, UnsupportedEncodingException, IOException{
        
        URL url_insert = new URL(utils.APIRequests.INSERT_HARDWARE);
        Map<String, String> params = new LinkedHashMap<>();
        params.put("device_num", String.valueOf(device_num));
        params.put("customer_name", customer_name);
        params.put("phone_num", String.valueOf(phone_number));
        params.put("prob", prob);
        params.put("win7", String.valueOf(win7));
        params.put("win8", String.valueOf(win8));
        params.put("win10", String.valueOf(win10));
        params.put("check_all", String.valueOf(check_all));
        params.put("hardware", String.valueOf(hardware));
        params.put("software", String.valueOf(software));
        params.put("battery", String.valueOf(battery));
        params.put("charger", String.valueOf(charger));
        params.put("bag", String.valueOf(bag));
        params.put("cd", String.valueOf(cd));
        params.put("other", other);
        params.put("date_time", String.valueOf(getDate()));
        params.put("device_name", device_name);
        params.put("state", String.valueOf(state));
        
        if(insertData(params, url_insert, false)){
            return true;
        }else{
            return false;
        }        
        
    }
     
        
    public static boolean insertSoftwareDevice(int device_num, String customer_name, int phone_number, String prob, int win7, int win8, int win10, int check_all, int software, int battery, int charger, int bag, int cd, String other,  String device_name, int state) throws ClassNotFoundException, MalformedURLException, UnsupportedEncodingException, IOException{
        
        URL url_insert = new URL(utils.APIRequests.INSERT_SOFTWARE);
        Map<String, String> params = new LinkedHashMap<>();
        params.put("device_num", String.valueOf(device_num));
        params.put("customer_name", customer_name);
        params.put("phone_num", String.valueOf(phone_number));
        params.put("prob", prob);
        params.put("win7", String.valueOf(win7));
        params.put("win8", String.valueOf(win8));
        params.put("win10", String.valueOf(win10));
        params.put("check_all", String.valueOf(check_all));
        params.put("software", String.valueOf(software));
        params.put("battery", String.valueOf(battery));
        params.put("charger", String.valueOf(charger));
        params.put("bag", String.valueOf(bag));
        params.put("cd", String.valueOf(cd));
        params.put("other", other);
        params.put("date_time", String.valueOf(getDate()));
        params.put("device_name", device_name);
        params.put("state", String.valueOf(state));
        
        if(insertData(params, url_insert, false)){
            return true;
        }else{
            return false;
        }        

        
    }
    

    public static boolean insertPrinterDevice(int device_num, String customer_name, int phone_number, String prob, int fill_printer, int change_dram, int check_all, int cable_power, int cable_data, int cd, String other, String device_name, int state) throws ClassNotFoundException, MalformedURLException, IOException{
                
        URL url_insert = new URL(utils.APIRequests.INSERT_PRINTER);
        Map<String, String> params = new LinkedHashMap<>();
        params.put("device_num", String.valueOf(device_num));
        params.put("customer_name", customer_name);
        params.put("phone_num", String.valueOf(phone_number));
        params.put("prob", prob);
        params.put("fill_printer", String.valueOf(fill_printer));
        params.put("change_dram", String.valueOf(change_dram));
        params.put("check_all", String.valueOf(check_all));
        params.put("cable_power", String.valueOf(cable_power));
        params.put("cable_data", String.valueOf(cable_data));
        params.put("cd", String.valueOf(cd));
        params.put("other", other);
        params.put("date_time", String.valueOf(getDate()));
        params.put("device_name", device_name);
        params.put("state", String.valueOf(state));
        
        if(insertData(params, url_insert, false)){
            return true;
        }else{
            return false;
        }
        
    }
    
    
    public static boolean insertData(Map<String, String> params, URL url, boolean isSearsch) throws UnsupportedEncodingException, IOException{
                        
        StringBuilder postData = new StringBuilder();
        for (Map.Entry param : params.entrySet()) {
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode((String) param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
            
        
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
        conn.setDoOutput(true);
        conn.getOutputStream().write(postDataBytes);
        Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;)
            sb.append((char)c);
        String response = sb.toString();
        System.out.println(response);
        JSONObject myResponse = new JSONObject(response.toString());
        System.out.println(myResponse);
        
        if(myResponse.getInt("success") == 1){
            if(isSearsch){
                searchArr = myResponse.getJSONArray("devices");
            }
            return true;
        }else{
            return false;
        }
        
    }

    
    private static Timestamp getDate() {
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());
        return date;
    }
    
    
    public static JSONArray getDevices(int type) throws MalformedURLException, IOException{
        String url_get = "";
        if(type == 0){  // hardware
           url_get = utils.APIRequests.GET_HARDWARE;
        }else if(type == 1){ // software
            url_get = utils.APIRequests.GET_SOFTWARE;
        }else{ // printer
            url_get = utils.APIRequests.GET_PRINTER;
        }
        URL url = new URL(url_get);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = conn.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
        JSONObject myResponse = new JSONObject(response.toString());
        JSONArray arr = myResponse.getJSONArray("devices");
        return arr;
    }
    
    public static boolean updateDevice(int id, int state, String table) throws MalformedURLException, IOException{

        URL url_update = new URL(utils.APIRequests.UPDATE_DEVICE);
        Map<String, String> params = new LinkedHashMap<>();
        params.put("id", String.valueOf(id));
        params.put("state", String.valueOf(state));
        params.put("table", table);
        if(insertData(params, url_update, false)){
            return true;
        }else{
            return false;
        }
    }
    
    public static boolean updateHardware(int id, String customer_name, int phone_number, String prob, int win7, int win8, int win10, int check_all, int software, int hardware, int battery, int charger, int bag, int cd, String other, String device_name, float price) throws ClassNotFoundException, MalformedURLException, UnsupportedEncodingException, IOException{
        
        URL url_update = new URL(utils.APIRequests.UPDATE_HARDWARE);
        Map<String, String> params = new LinkedHashMap<>();
        params.put("id", String.valueOf(id));
        params.put("customer_name", customer_name);
        params.put("phone_num", String.valueOf(phone_number));
        params.put("prob", prob);
        params.put("win7", String.valueOf(win7));
        params.put("win8", String.valueOf(win8));
        params.put("win10", String.valueOf(win10));
        params.put("check_all", String.valueOf(check_all));
        params.put("hardware", String.valueOf(hardware));
        params.put("software", String.valueOf(software));
        params.put("battery", String.valueOf(battery));
        params.put("charger", String.valueOf(charger));
        params.put("bag", String.valueOf(bag));
        params.put("cd", String.valueOf(cd));
        params.put("other", other);
        params.put("device_name", device_name);
        params.put("price", price + "");
        if(insertData(params, url_update, false)){
            return true;
        }else{
            return false;
        }
    }  
    
    public static boolean updateSoftware(int id, String customer_name, int phone_number, String prob, int win7, int win8, int win10, int check_all, int software, int battery, int charger, int bag, int cd, String other, String device_name, float price) throws ClassNotFoundException, MalformedURLException, UnsupportedEncodingException, IOException{
        
        URL url_update = new URL(utils.APIRequests.UPDATE_SOFTWARE);
        Map<String, String> params = new LinkedHashMap<>();
        params.put("id", String.valueOf(id));
        params.put("customer_name", customer_name);
        params.put("phone_num", String.valueOf(phone_number));
        params.put("prob", prob);
        params.put("win7", String.valueOf(win7));
        params.put("win8", String.valueOf(win8));
        params.put("win10", String.valueOf(win10));
        params.put("check_all", String.valueOf(check_all));
        params.put("software", String.valueOf(software));
        params.put("battery", String.valueOf(battery));
        params.put("charger", String.valueOf(charger));
        params.put("bag", String.valueOf(bag));
        params.put("cd", String.valueOf(cd));
        params.put("other", other);
        params.put("device_name", device_name);
        params.put("price", price + "");
        if(insertData(params, url_update, false)){
            return true;
        }else{
            return false;
        }
    }  


    public static boolean updatePrinterint(int id, String customer_name, int phone_number, String prob, int fill_printer, int change_dram, int check_all, int cable_power, int cable_data, int cd, String other, String device_name, float price) throws ClassNotFoundException, MalformedURLException, IOException{
        URL url_update = new URL(utils.APIRequests.UPDATE_PRINTER);
        Map<String, String> params = new LinkedHashMap<>();
        params.put("id", String.valueOf(id));
        params.put("customer_name", customer_name);
        params.put("phone_num", String.valueOf(phone_number));
        params.put("prob", prob);
        params.put("fill_printer", String.valueOf(fill_printer));
        params.put("change_dram", String.valueOf(change_dram));
        params.put("check_all", String.valueOf(check_all));
        params.put("cable_power", String.valueOf(cable_power));
        params.put("cable_data", String.valueOf(cable_data));
        params.put("cd", String.valueOf(cd));
        params.put("other", other);
        params.put("device_name", device_name);
        params.put("price", price + "");
        if(insertData(params, url_update, false)){
            return true;
        }else{
            return false;
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
            return true;
        } 
    }

        
    public static boolean deleteDevice(int id, String table) throws MalformedURLException, IOException{

        URL url_update = new URL(utils.APIRequests.DELETE_DEVICE);
        Map<String, String> params = new LinkedHashMap<>();
        params.put("id", String.valueOf(id));
        params.put("table", table);
        if(insertData(params, url_update, false)){
            return true;
        }else{
            return false;
        }
    }
    
    
        
    public static JSONArray getDevicesAnalysis() throws MalformedURLException, IOException{
        String url_get = "";
        url_get = utils.APIRequests.DEVICES_ANALYSIS;
        URL url = new URL(url_get);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = conn.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        while((inputLine = in.readLine()) != null){
            response.append(inputLine);
        }
        in.close();
        System.out.println(response.toString());
        JSONObject myResponse = new JSONObject(response.toString());
        JSONArray arr = myResponse.getJSONArray("analysis");
        return arr;
    }
    
    public static JSONArray searchDevices(String word, int type) throws MalformedURLException, IOException{

        URL url_insert = new URL(utils.APIRequests.SEARCH_DEVICE);
        Map<String, String> params = new LinkedHashMap<>();
        params.put("word", String.valueOf(word));
        if(type == 0){
            params.put("table", "hardware_fix");
        }else if(type == 1){
            params.put("table", "software_fix");
        }else if(type == 2){
            params.put("table", "printer_fix");
        }
        
        if(insertData(params, url_insert, true)){
            return searchArr;
        }else{
            return null;
        }
        
    }
    

}
