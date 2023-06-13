/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.dao;

import com.raven.model.Bill;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class BillDAO {
    public static void save(Bill bill) {
        String query = "insert into bill (idStaff, dateCheckIn, discount) values('" + bill.getIdStaff() + "','" + bill.getDate() + "','" + bill.getDiscount() + "') ";
        DbOperations.SetDataOrDelete(query, "Bill Added Successfully");
    }
    
    public static void update(Bill bill) {
        try {
            String query = "UPDATE bill SET idStaff = ?, dateCheckIn = ?, discount = ? WHERE id = ?";
            PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
            stmt.setInt(1, bill.getIdStaff());
            stmt.setString(2, bill.getDate());
            stmt.setInt(3, bill.getDiscount());
            stmt.setInt(4, bill.getId());
            DbOperations.SetDataOrDelete(stmt, "Bill Updated Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static void delete(int billId) {
        try {
            String query = "DELETE FROM bill WHERE id = ?";
            PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
            stmt.setInt(1, billId);
            DbOperations.SetDataOrDelete(stmt, "Bill Deleted Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static ArrayList<Bill> getAllRecords() {
        ArrayList<Bill> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select * from bill");
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setId(rs.getInt("id"));
                bill.setIdStaff(rs.getInt("idStaff"));
                bill.setDate(rs.getString("dateCheckIn"));
                bill.setDiscount(rs.getInt("discount"));
                arrayList.add(bill);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return arrayList;
    }
    
    public static int getLastestBillId() {
        int id = -1;
        try {
            ResultSet rs = DbOperations.getData("SELECT MAX(id) AS lastestID FROM Bill;");
            while (rs.next()) {
                id = rs.getInt("lastestID");
                return id;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return id;
    }
}
