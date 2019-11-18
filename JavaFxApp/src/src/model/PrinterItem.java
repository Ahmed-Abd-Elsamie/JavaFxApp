/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Timestamp;

/**
 *
 * @author Ahmed
 */
public class PrinterItem {
    private int id;
    private int txt_device_num;
    private String txt_customer_name;
    private int txt_phone_num;
    private String txt_prob;
    private int fill_printer;
    private int change_dram;
    private int check_all;
    private int cable_power;
    private int cable_data;
    private int cd;
    private String other;
    private java.sql.Timestamp date_time;
    private String device_name;
    private int state;

    public PrinterItem() {
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

    public int getFill_printer() {
        return fill_printer;
    }

    public void setFill_printer(int fill_printer) {
        this.fill_printer = fill_printer;
    }

    public int getChange_dram() {
        return change_dram;
    }

    public void setChange_dram(int change_dram) {
        this.change_dram = change_dram;
    }

    public int getCheck_all() {
        return check_all;
    }

    public void setCheck_all(int check_all) {
        this.check_all = check_all;
    }

    public int getCable_power() {
        return cable_power;
    }

    public void setCable_power(int cable_power) {
        this.cable_power = cable_power;
    }

    public int getCable_data() {
        return cable_data;
    }

    public void setCable_data(int cable_data) {
        this.cable_data = cable_data;
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

    public Timestamp getDate_time() {
        return date_time;
    }

    public void setDate_time(Timestamp date_time) {
        this.date_time = date_time;
    }

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
    
    
    
}
