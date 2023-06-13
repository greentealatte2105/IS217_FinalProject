/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.dao;

import com.raven.model.Customer;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class CustomerDAO {
    public static void save(Customer customer) {
        String query = "insert into customer (idRank, phoneNumber, total) values('" + customer.getIdRank() + "','" + customer.getPhoneNumber() + "','" + customer.getTotal() + "') ";
        DbOperations.SetDataOrDelete(query, "Customer Added Successfully");
    }
    
    public static void update(Customer customer) {
        try {
            String query = "UPDATE customer SET idRank = ?, phoneNumber = ?, total = ? WHERE id = ?";
            PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
            stmt.setInt(1, customer.getIdRank());
            stmt.setString(2, customer.getPhoneNumber());
            stmt.setInt(3, customer.getTotal());
            stmt.setInt(4, customer.getId());
            DbOperations.SetDataOrDelete(stmt, "Customer Updated Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static void delete(int customerId) {
        try {
            String query = "DELETE FROM customer WHERE id = ?";
            PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
            stmt.setInt(1, customerId);
            DbOperations.SetDataOrDelete(stmt, "Customer Deleted Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static Customer getRecordById(int id){
        Customer result = new Customer();
        try {
            String query = "select * from customer where id = ?";
            PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs =  DbOperations.getData(query);
            while(rs.next()) {
                result.setId(rs.getInt("id"));
                result.setIdRank(rs.getInt("idRank"));
                result.setPhoneNumber(rs.getString("phoneNumber"));
                result.setTotal(rs.getInt("total"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return result;
    }
    
    public static ArrayList<Customer> getAllRecords() {
        ArrayList<Customer> arrayList = new ArrayList<>();
        try {
            ResultSet rs = DbOperations.getData("select * from customer");
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setIdRank(rs.getInt("idRank"));
                customer.setPhoneNumber(rs.getString("phoneNumber"));
                customer.setTotal(rs.getInt("total"));
                arrayList.add(customer);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return arrayList;

    }
}
