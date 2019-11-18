/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import model.DeviceItem;
import model.PrinterItem;
import org.json.JSONObject;

/**
 *
 * @author Ahmed
 */
public class genVars {
    
    public static DeviceItem  deviceItem = null;
    public static PrinterItem printerItem = null;
    public static int type = 0;  //type of clicking on item to show nex window as a printer or device 0 == device AND 1 == printer
    
    public static int hard_soft = 0;  /* 1 == software  AND 0 == hardware */
    
    public static JSONObject device = null;
    
}
