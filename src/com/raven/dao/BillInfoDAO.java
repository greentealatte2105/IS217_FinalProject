/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.dao;

import com.raven.model.BillInfo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class BillInfoDAO {
    public static void save(BillInfo billInfo) {
        String query = "insert into BillInfo (idBill, idProduct, count) values('" + billInfo.getIdBill() + "','" + billInfo.getIdProduct() + "','" + billInfo.getCount() + "') ";
        DbOperations.SetDataOrDelete(query, "BillInfo Added Successfully");
    }
    
    public static void update(BillInfo billInfo) {
        try {
            String query = "UPDATE billinfo SET idBill = ?, idProduct = ?, count = ? WHERE id = ?";
            PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
            stmt.setInt(1, billInfo.getIdBill());
            stmt.setInt(2, billInfo.getIdProduct());
            stmt.setInt(3, billInfo.getCount());
            stmt.setInt(4, billInfo.getId());
            DbOperations.SetDataOrDelete(stmt, "BillInfo Updated Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static void delete(int billInfoId) {
        try {
            String query = "DELETE FROM billinfo WHERE id = ?";
            PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
            stmt.setInt(1, billInfoId);
            DbOperations.SetDataOrDelete(stmt, "BillInfo Deleted Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static ArrayList<BillInfo> getAllRecords() {
        ArrayList<BillInfo> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select * from bill");
            while (rs.next()) {
                BillInfo billInfo = new BillInfo();
                billInfo.setId(rs.getInt("id"));
                billInfo.setIdBill(rs.getInt("idBill"));
                billInfo.setIdProduct(rs.getInt("idProduct"));
                billInfo.setCount(rs.getInt("count"));
                arrayList.add(billInfo);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return arrayList;

    }
}
