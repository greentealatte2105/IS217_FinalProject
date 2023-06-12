
package com.raven.model;

import java.net.IDN;
import java.util.ArrayList;


public class Bill {

    public Bill(int id, int idStaff, String date, int total) {
        this.id = id;
        this.idStaff = idStaff;
        this.date = date;
        this.total = total;
//        this.billInfoList = billInfoList;
    }

    public Bill() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdStaff() {
        return idStaff;
    }

    public void setIdStaff(int idStaff) {
        this.idStaff = idStaff;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

//    public ArrayList<BillInfo> getBillInfoList() {
//        return billInfoList;
//    }
//
//    public void setBillInfoList(ArrayList<BillInfo> billInfoList) {
//        this.billInfoList = billInfoList;
//    }
    private int id;
    private int idStaff;
    private String date;
    private int discount;
    private int total;
//    private ArrayList<BillInfo> billInfoList;
    
}
