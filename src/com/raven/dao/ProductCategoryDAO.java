/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.dao;

import com.raven.model.ProductCategory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class ProductCategoryDAO {
    
    public static ArrayList<ProductCategory> getAllRecords() {
        ArrayList<ProductCategory> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select * from productCategory");
            while (rs.next()) {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setId(rs.getInt("id"));
                productCategory.setName(rs.getString("name"));
                arrayList.add(productCategory);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        for (ProductCategory element : arrayList) {
            System.out.println(element);
        }
        return arrayList;
    }
    
    public static boolean addRecord(ProductCategory productCategory) {
    try {
        String query = "INSERT INTO productCategory (name) VALUES (?)";
        PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
        stmt.setString(1, productCategory.getName());
        DbOperations.SetDataOrDelete(stmt, "Record added successfully");
        return true;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
        return false;
    }
}

public static boolean updateRecord(ProductCategory productCategory) {
    try {
        String query = "UPDATE productCategory SET name = ? WHERE id = ?";
        PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
        stmt.setString(1, productCategory.getName());
        stmt.setInt(2, productCategory.getId());
        DbOperations.SetDataOrDelete(stmt, "Record updated successfully");
        return true;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
        return false;
    }
}

public static boolean deleteRecord(int categoryId) {
    try {
        String query = "DELETE FROM productCategory WHERE id = ?";
        PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
        stmt.setInt(1, categoryId);
        DbOperations.SetDataOrDelete(stmt, "Record deleted successfully");
        return true;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e);
        return false;
    }
}

}
