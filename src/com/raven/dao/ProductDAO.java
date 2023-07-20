/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.dao;

import com.raven.model.Product;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class ProductDAO {
    public static void save(Product product) {
        String query = "insert into product (name,idCategory,price) values('" + product.getName() + "','" + product.getIdCategory() + "','" + product.getPrice() + "') ";
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
    public static int getProductCategoryId(String productName){
        int productCategoryId = 0;
        try {
            String query = "select id from ProductCategory where name= ?";
            PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
            stmt.setString(1, productName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                productCategoryId = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productCategoryId;
    }
    public static void update(Product product) {
        try {
            String query = "UPDATE product SET name = ?, idCategory = ?, price = ? WHERE id = ?";
            PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getIdCategory());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getId());
            DbOperations.SetDataOrDelete(stmt, "Product Updated Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void delete(int productId) {
        try {
            String query = "DELETE FROM product WHERE id = ?";
            PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
            stmt.setInt(1, productId);
            DbOperations.SetDataOrDelete(stmt, "Product Deleted Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    
    public static ArrayList<Product> searchProducts(String search) {
        ArrayList<Product> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("SELECT * FROM Product WHERE name REGEXP 'Trà *'");
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
    
    
    public static ArrayList<Product> getRecordsByOptions(int option) {
        // 0 trending, 1 new, 2 increase, 3 decrease
        ArrayList<Product> arrayList = new ArrayList<>();
        String query = "";
        try {
            switch (option) {
                case 0:
                    query = "SELECT * FROM Product WHERE id IN " +
                        "(SELECT idProduct FROM BillInfo bi " +
"			GROUP BY bi.idProduct ORDER BY SUM(bi.count) DESC);";
                    break;
                case 1:
                    query = "SELECT * FROM product ORDER BY id desc";
                    break;
                case 2:
                    query = "SELECT * FROM product ORDER BY price asc";
                    break;
                case 3:
                    query = "SELECT * FROM product ORDER BY price desc";
                    break;
                default:
                    throw new AssertionError();
            }
//            if (option == 0)
//                query = "SELECT * " +
//                        "FROM Product\n" +
//                        "WHERE id IN " +
//                        "(SELECT idProduct FROM BillInfo bi " +
//"			GROUP BY bi.idProduct ORDER BY SUM(bi.count) DESC);";
//            else if (option == 1)
//                query = "SELECT * FROM product ORDER BY id desc";
//            else if (option == 2)
//                query = "SELECT * FROM product ORDER BY price asc";
//            else if (option == 3)
//                query = "SELECT * FROM product ORDER BY price desc";
            
            ResultSet rs = DbOperations.getData(query);
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
    
    public static ArrayList<Product> getRecordsByIdCategory(int idCategory) {
        ArrayList<Product> arrayList = new ArrayList<>();
        try {
            String query = "SELECT product.id, product.name, product.price, productCategory.name AS category " +
                            "FROM product " +
                            "JOIN productCategory ON product.idCategory = productCategory.id " +
                            "WHERE productCategory.id = %s;";
            query = String.format(query, Integer.toString(idCategory));
            ResultSet rs = DbOperations.getData(query);
            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
//                product.setIdCategory(rs.getInt("category"));
                product.setPrice(rs.getInt("price"));
                arrayList.add(product);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return arrayList;
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
