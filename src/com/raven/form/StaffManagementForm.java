package com.raven.form;

import com.raven.dao.ConnectionProvider;
import com.raven.dao.UserDAO;
import com.raven.model.User;
import com.raven.swing.scrollbar.ScrollBarCustom;
import com.raven.swing.table.EventAction;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

public class StaffManagementForm extends javax.swing.JPanel {

    private DefaultTableModel model;
    private EventAction eventAction;
    public StaffManagementForm() {
        initComponents();
        setOpaque(false);
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        tbStaff.fixTable(jScrollPane1);
        model = (DefaultTableModel) tbStaff.getModel();
        initTableData();
    }

    private void initTableData() {
        
       this.eventAction = new EventAction() {
            @Override
            public void delete(User user) {
               UserDAO.delete(user.getId());
               model.removeRow(tbStaff.getSelectedRow());
               model.fireTableDataChanged();

            }

            @Override
            public void update(User user) {
                System.err.println("clicked");
                DecimalFormat df = new DecimalFormat("##.##");
                
                txtId.setText(String.valueOf(user.getId()));
                txtName.setText(user.getUserName());
                txtEmail.setText(user.geteMail());
                txtTime.setText(df.format(user.getTime()));
                txtPhoneNumber.setText(user.getPhoneNumber());
                int row = tbStaff.getSelectedRow();
                int col = tbStaff.getSelectedColumn();
                System.err.println(row + "   " + col);
                txtSalary.setText((String) model.getValueAt(row, col - 1).toString());
              
                
            }
        };     
        model.setRowCount(0);
        ArrayList<User> staffList = UserDAO.getAllStaff();
        System.err.println("sonhv" + staffList.size());
        Iterator<User> itr = staffList.iterator();
        while (itr.hasNext()) {
            User staff = (User) itr.next();
            tbStaff.addRow(staff.toRowTable(this.eventAction));
        }        
                    model.fireTableDataChanged();

//         DefaultTableModel tableModel = (DefaultTableModel) tbStaff.getModel();
//         tableModel.setRowCount(0);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel7 = new javax.swing.JLabel();
        panelBorder1 = new com.raven.swing.PanelBorder();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtName = new com.raven.swing.TextField();
        txtId = new com.raven.swing.TextField();
        txtEmail = new com.raven.swing.TextField();
        txtTime = new com.raven.swing.TextField();
        txtSalary = new com.raven.swing.TextField();
        jLabel6 = new javax.swing.JLabel();
        bUpdate = new com.raven.swing.ButtonBadges();
        jLabel8 = new javax.swing.JLabel();
        txtPhoneNumber = new com.raven.swing.TextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbStaff = new com.raven.swing.table.Table();
        panelBorder2 = new com.raven.swing.PanelBorder();
        bTimekeeping = new com.raven.swing.Button();
        jLabel9 = new javax.swing.JLabel();
        txtSalaryPerTime = new com.raven.swing.TextField();
        jLabel10 = new javax.swing.JLabel();
        txtSearchedName = new com.raven.swing.TextField();
        jLabel11 = new javax.swing.JLabel();
        txtSearchedEmail = new com.raven.swing.TextField();
        jLabel12 = new javax.swing.JLabel();
        txtSearchedPhone = new com.raven.swing.TextField();
        bSeach = new com.raven.swing.Button();

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Salary:");

        panelBorder1.setBackground(new java.awt.Color(102, 102, 102));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Name:");

        jLabel3.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("E-mail:");

        jLabel4.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Salary:");

        jLabel5.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel5.setText("Time:");

        txtName.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N

        txtId.setDisabledTextColor(new java.awt.Color(204, 204, 204));
        txtId.setEnabled(false);
        txtId.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N

        txtEmail.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N

        txtTime.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N

        txtSalary.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Information");

        bUpdate.setBackground(new java.awt.Color(0, 153, 204));
        bUpdate.setForeground(new java.awt.Color(255, 255, 255));
        bUpdate.setText("UPDATE");
        bUpdate.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        bUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUpdateActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Phone number:");

        txtPhoneNumber.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPhoneNumber, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(5, 5, 5)
                        .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder1Layout.createSequentialGroup()
                                .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(6, 6, 6)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtSalary, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(bUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)))))
                .addGap(30, 30, 30))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(5, 5, 5)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addComponent(txtPhoneNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel4))
                    .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(panelBorder1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelBorder1Layout.createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addComponent(jLabel5))))
                .addGap(49, 49, 49)
                .addComponent(bUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setBorder(null);

        tbStaff.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Id", "Name", "Email", "Phone number", "Time", "Salary", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbStaff);
        if (tbStaff.getColumnModel().getColumnCount() > 0) {
            tbStaff.getColumnModel().getColumn(0).setPreferredWidth(10);
        }

        panelBorder2.setBackground(new java.awt.Color(102, 102, 102));

        bTimekeeping.setBackground(new java.awt.Color(153, 153, 153));
        bTimekeeping.setForeground(new java.awt.Color(255, 255, 255));
        bTimekeeping.setText("TIMEKEEPING");
        bTimekeeping.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        bTimekeeping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bTimekeepingActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Salary:");

        txtSalaryPerTime.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Name:");

        txtSearchedName.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Email:");

        txtSearchedEmail.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Phone:");

        txtSearchedPhone.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N

        bSeach.setBackground(new java.awt.Color(153, 153, 153));
        bSeach.setForeground(new java.awt.Color(255, 255, 255));
        bSeach.setText("SEARCH");
        bSeach.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        bSeach.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSeachActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchedName, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchedEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearchedPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bSeach, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jLabel9)
                .addGap(5, 5, 5)
                .addComponent(txtSalaryPerTime, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(bTimekeeping, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel10)
                        .addComponent(txtSearchedName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(txtSearchedEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel12)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtSearchedPhone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bSeach, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel9)
                        .addComponent(txtSalaryPerTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bTimekeeping, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addGap(20, 20, 20)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUpdateActionPerformed
        // TODO add your handling code here:
        int id = Integer.parseInt(txtId.getText());
        String name = txtName.getText();
        String email = txtEmail.getText();
        float time = Float.parseFloat(txtTime.getText());
        String phone = txtPhoneNumber.getText();
        
        User newUser = new User(id, name, email);
        newUser.setTime(time);
        newUser.setPhoneNumber(phone);
        newUser.setRole("staff");
        UserDAO.update(newUser);
    }//GEN-LAST:event_bUpdateActionPerformed

    private void bTimekeepingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bTimekeepingActionPerformed
        // TODO add your handling code here:
        float salary = Float.parseFloat(txtSalaryPerTime.getText());
        DecimalFormat df = new DecimalFormat("###,###,###");
        for (int i = 0; i < tbStaff.getRowCount(); i++)
        {
            float time = Float.parseFloat(model.getValueAt(i, 4).toString());
            time *= salary;
            tbStaff.setValueAt(df.format(time), i, 5);
             tbStaff.repaint();
                tbStaff.revalidate();
        }
                   model.fireTableDataChanged();

    }//GEN-LAST:event_bTimekeepingActionPerformed

    private void bSeachActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSeachActionPerformed
        System.err.println(model.getRowCount());
        for(int i = 0; i< model.getRowCount(); i++)
            model.removeRow(i);
        model.setRowCount(0);
        model.fireTableDataChanged();
       
            // TODO add your handling code here:
            Connection conn = ConnectionProvider.getCon();
            String query = "SELECT a.id, a.userName, a.password, a.phoneNumber, a.email, a.role, b.timeCount " +
                    "FROM account a " +
                    "left JOIN staffmanagement b ON a.id = b.id " +
                    "WHERE a.role = 'staff'";
            String name = txtSearchedName.getText();
            String mail = txtSearchedEmail.getText();
            String phone = txtSearchedPhone.getText();
            if (!name.equals(""))
                query += "and a.userName = '" + name + "'";
            if (!mail.equals(""))
                query += "and a.email = '" + mail + "'";
            if (!phone.equals(""))
                query += "and a.phoneNumber = '" + phone + "'";
           
        try {
            Statement stat = conn.createStatement();            
            ResultSet res = stat.executeQuery(query);
            while (res.next()) {
                 
                User staff = new User();
                staff.setId(res.getInt("id"));
                staff.setUserName(res.getString("userName"));
                staff.seteMail(res.getString("email"));
                staff.setPassword(res.getString("password"));

                staff.setPhoneNumber(res.getString("phoneNumber"));
                staff.setRole(res.getString("role"));
                staff.setTime(res.getFloat("timeCount"));
                model.addRow(staff.toRowTable(this.eventAction));
            }
            model.fireTableDataChanged();
        } catch (SQLException ex) {
            Logger.getLogger(StaffManagementForm.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    }//GEN-LAST:event_bSeachActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Button bSeach;
    private com.raven.swing.Button bTimekeeping;
    private com.raven.swing.ButtonBadges bUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private com.raven.swing.PanelBorder panelBorder1;
    private com.raven.swing.PanelBorder panelBorder2;
    private com.raven.swing.table.Table tbStaff;
    private com.raven.swing.TextField txtEmail;
    private com.raven.swing.TextField txtId;
    private com.raven.swing.TextField txtName;
    private com.raven.swing.TextField txtPhoneNumber;
    private com.raven.swing.TextField txtSalary;
    private com.raven.swing.TextField txtSalaryPerTime;
    private com.raven.swing.TextField txtSearchedEmail;
    private com.raven.swing.TextField txtSearchedName;
    private com.raven.swing.TextField txtSearchedPhone;
    private com.raven.swing.TextField txtTime;
    // End of variables declaration//GEN-END:variables
}
