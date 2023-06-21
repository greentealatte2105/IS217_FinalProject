/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

import com.raven.dao.DbOperations;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class Customer {
    private int id;
    private int idRank;
    private String phoneNumber;
    private int total;

    public Customer() {
    }

    public Customer(int id, int idRank, String phoneNumber, int total) {
        this.id = id;
        this.idRank = idRank;
        this.phoneNumber = phoneNumber;
        this.total = total;
    }

    public Customer(int idRank, String phoneNumber, int total) {
        this.idRank = idRank;
        this.phoneNumber = phoneNumber;
        this.total = total;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRank() {
        return idRank;
    }

    public void setIdRank(int idRank) {
        this.idRank = idRank;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
}
