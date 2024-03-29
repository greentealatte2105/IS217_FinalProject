
package com.raven.model;

import com.raven.swing.table.EventAction;
import com.raven.swing.table.ModelAction;
import java.text.DecimalFormat;

public class User {
    private int id = 0;
    private String userName = "";
    private String password = "";
    private String phoneNumber = "";
    private String eMail = "";
    private String role = "";
    private float time;
   
    
    public User(){
    }

    public User(int id, String userName, String password, String role) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }
     public User(int id, String userName, String eMail) {
        this.id = id;
        this.userName = userName;
        this.eMail = eMail;
    }
     
    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public boolean isStaff(){
        return role.equals("staff");
            
    }
            
    public Object[] toRowTable(EventAction event) {
        DecimalFormat df = new DecimalFormat(".2f");
        return new Object[]{id,userName,eMail,phoneNumber,Math.round(time * 100.0) / 100.0,"0", new ModelAction(this, event)};
    }
}
