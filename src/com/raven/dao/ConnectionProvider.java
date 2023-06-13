package com.raven.dao;
import java.sql.*;
import javax.swing.JOptionPane;

public class ConnectionProvider {
    public static Connection getCon() {
        try {           
            String username = "root";
<<<<<<< HEAD
            String password = "huuphuoc@2105";
=======
            String password = "thinh247";
>>>>>>> fb7f02d22af6f56b1ba0903f98b75bf4ef12393a
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlyquancafe", username, password);
            
            String setSqlModeQuery = "SET sql_mode=(SELECT REPLACE(@@sql_mode, 'ONLY_FULL_GROUP_BY', ''))";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(setSqlModeQuery);
            
            return con;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fail to connect database", "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
}
