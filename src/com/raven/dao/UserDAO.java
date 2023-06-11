/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.dao;
import com.raven.model.User;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMessages;
import java.sql.*;
import javax.swing.*;


/**
 *
 * @author Thien
 */
public class UserDAO {
    public static boolean login(String username, String password){
        User user = null;
        try {
            Connection con = ConnectionProvider.getCon();
            String storedFunction = "Call USP_Login(?, ?)";
            
            CallableStatement statement = con.prepareCall(storedFunction);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            
            // kiểm tra hợp lệ
            boolean loginStatus = false;
            if (resultSet.next()){
                loginStatus = resultSet.getBoolean("login_status");
            }
            
            if (loginStatus) {
                JOptionPane.showMessageDialog(null, "Welcome " + username);
                return true;
            }
            else
                JOptionPane.showMessageDialog(null, "Username or password is incorrect!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }
    
    public static void save(User user){
            String query = "INSERT INTO Account (userName, password, email, phoneNumber, role) " +
                "VALUES (?, ?, ?, ?, 'staff')";
        try (Connection con = ConnectionProvider.getCon();
            PreparedStatement statement = con.prepareStatement(query)) {

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.geteMail());
            statement.setString(4, user.getPhoneNumber());
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Sign up successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
