package com.raven.dao;
import java.sql.*;
import javax.swing.JOptionPane;

public class ConnectionProvider {
    public static Connection getCon() {
        try {           
            String username = "root";
            String password = "huuphuoc@2105";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlyquancafe", username, password);
            return con;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fail to connect database", "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
