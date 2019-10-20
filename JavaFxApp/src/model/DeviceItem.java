/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 *
 * @author Ahmed
 */
public class DeviceItem {
    
    private int id;
    private int txt_device_num;
    private String txt_customer_name;
    private int txt_phone_num;
    private String txt_prob;
    private int win7;
    private int win8;
    private int win10;
    private int check_all;
    private int software;
    private int hardware;
    private int battery;
    private int charger;
    private int bag;
    private int cd;
    private String other;
    private java.sql.Timestamp date_time;
    private String device_name;
    private int state;

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Timestamp getDate_time() {
        return date_time;
    }

    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

    public DeviceItem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTxt_device_num() {
        return txt_device_num;
    }

    public void setTxt_device_num(int txt_device_num) {
        this.txt_device_num = txt_device_num;
    }

    public String getTxt_customer_name() {
        return txt_customer_name;
    }

    public void setTxt_customer_name(String txt_customer_name) {
        this.txt_customer_name = txt_customer_name;
    }

    public int getTxt_phone_num() {
        return txt_phone_num;
    }

    public void setTxt_phone_num(int txt_phone_num) {
        this.txt_phone_num = txt_phone_num;
    }

    public String getTxt_prob() {
        return txt_prob;
    }

    public void setTxt_prob(String txt_prob) {
        this.txt_prob = txt_prob;
    }

    public int getWin7() {
        return win7;
    }

    public void setWin7(int win7) {
        this.win7 = win7;
    }

    public int getWin8() {
        return win8;
    }

    public void setWin8(int win8) {
        this.win8 = win8;
    }

    public int getWin10() {
        return win10;
    }

    public void setWin10(int win10) {
        this.win10 = win10;
    }

    public int getCheck_all() {
        return check_all;
    }

    public void setCheck_all(int check_all) {
        this.check_all = check_all;
    }

    public int getSoftware() {
        return software;
    }

    public void setSoftware(int software) {
        this.software = software;
    }

    public int getHardware() {
        return hardware;
    }

    public void setHardware(int hardware) {
        this.hardware = hardware;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public int getCharger() {
        return charger;
    }

    public void setCharger(int charger) {
        this.charger = charger;
    }

    public int getBag() {
        return bag;
    }

    public void setBag(int bag) {
        this.bag = bag;
    }

    public int getCd() {
        return cd;
    }

    public void setCd(int cd) {
        this.cd = cd;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

   
    
}
