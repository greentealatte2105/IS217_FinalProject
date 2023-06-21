package com.raven.dao;
import com.raven.model.User;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMessages;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;


/**
 *
 * @author Thien
 */
public class UserDAO {
    public static int login(String username, String password){
//        User user = null;
        try {
            Connection con = ConnectionProvider.getCon();
            String storedFunction = "Call USP_Login(?, ?)";
            
            CallableStatement statement = con.prepareCall(storedFunction);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            
            // trả về là id (default là 0), cho nên id > 0 là có tồn tại
            int loginID = 0;
            if (resultSet.next()){
                loginID = resultSet.getInt("id");
            }
            
            if (loginID > 0) {
                JOptionPane.showMessageDialog(null, "Welcome " + username);
                return loginID;
            }
            else
                JOptionPane.showMessageDialog(null, "Username or password is incorrect!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return 0;
    }
    
    public static String getUserRole(int id){
        try {
            String query = "SELECT Role FROM Account WHERE id=" +id;
            ResultSet rs = DbOperations.getData(query);
            if (rs.next()) {
                String role = rs.getString("role");
                return role;
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e, "Error", JOptionPane.ERROR_MESSAGE);
        }
        return "";
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
    
    public static ArrayList<User> getAllStaff() {
        ArrayList<User> arrayList = new ArrayList<>();
        String query = "SELECT a.id, a.userName, a.password, a.phoneNumber, a.email, a.role, b.timeCount " +
                        "FROM account a " +
                        "JOIN staffmanagement b ON a.id = b.id " +
                        "WHERE a.role = 'staff';";
        try {
            ResultSet rs = DbOperations.getData(query);
            while (rs.next()) {
                User staff = new User();
                staff.setId(rs.getInt("id"));
                staff.setUserName(rs.getString("userName"));
                staff.seteMail(rs.getString("email"));
                staff.setPassword(rs.getString("password"));

                staff.setPhoneNumber(rs.getString("phoneNumber"));
                staff.setRole(rs.getString("role"));
                staff.setTime(rs.getFloat("timeCount"));
                arrayList.add(staff);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        return arrayList;
    }
    
    public static void delete(int id){
        try {
            String query = "DELETE FROM account WHERE id = ?";
            PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
            stmt.setInt(1, id);
            DbOperations.SetDataOrDelete(stmt, "User Deleted Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static void update(User user) {
        try {
            String query = "UPDATE Account SET userName = ?, email = ?, phoneNumber = ?, role = ? WHERE id = ?";
            PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.geteMail());
            stmt.setString(3, user.getPhoneNumber());
            stmt.setString(4, user.getRole());
            stmt.setInt(5, user.getId());
            DbOperations.SetDataOrDelete(stmt, "User Updated Successfully");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    public static void addTimeForUser(int idUser, float time){
        try {
            // find user in management
            String sql = "SELECT * FROM StaffManagement WHERE id =" + idUser +";";
            ResultSet rs = DbOperations.getData(sql);
            if (rs.next()){
                // found a user, add more time to exist time of user
                sql = "UPDATE StaffManagement sm SET sm.timeCount = sm.timeCount + ? WHERE sm.id = ?;";
                PreparedStatement st = ConnectionProvider.getCon().prepareStatement(sql);
                st.setFloat(1, time);
                st.setInt(2, idUser);
                st.execute();
            }
            else{
                // not found user, so we will add new user to management
                sql = "INSERT INTO StaffManagement(id, timeCount) VALUES (" + idUser + "," + time + ");";
                Connection con = ConnectionProvider.getCon();
                Statement st = con.createStatement();
                st.execute(sql);
                
//                // get lastestID to add time
//                sql = "SELECT MAX(id) AS lastestID FROM StaffManagement;";
//                rs = DbOperations.getData(sql);
//                int lastestidUser = rs.getInt("lastestID");
//                JOptionPane.showMessageDialog(null, lastestidUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}