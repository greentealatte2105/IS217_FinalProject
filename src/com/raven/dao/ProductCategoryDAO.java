/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.dao;

import com.raven.model.ProductCategory;
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
}
