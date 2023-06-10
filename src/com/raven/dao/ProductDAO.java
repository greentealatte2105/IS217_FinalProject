/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.dao;

import com.raven.model.Product;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class ProductDAO {
    public static void save(Product product) {
        String query = "insert into product (name,category,price) values('" + product.getName() + "','" + product.getIdCategory() + "','" + product.getPrice() + "') ";
        DbOperations.SetDataOrDelete(query, "Product Added Successfully");
    }
    public static String getProductName(int idProduct){
        String productName = "";
        ResultSet rs = DbOperations.getData("select name from ProductCategory where id=" + idProduct);
        try {
            while (rs.next()){
            productName = rs.getString("name");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productName;
    }
    
    public static ArrayList<Product> getAllRecords() {
        ArrayList<Product> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select * from product");
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setIdCategory(rs.getInt("idCategory"));
                product.setPrice(rs.getInt("price"));
                arrayList.add(product);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return arrayList;

    }
}
