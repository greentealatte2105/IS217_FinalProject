
package com.raven.form;

import com.raven.dao.BillDAO;
import com.raven.dao.DbOperations;
import com.raven.datechooser.DateBetween;
import com.raven.datechooser.DateChooser;
import com.raven.datechooser.DateChooserException;
import com.raven.datechooser.listener.DateChooserAction;
import com.raven.datechooser.listener.DateChooserAdapter;
import com.raven.model.Bill;
import com.raven.model.User;
import com.raven.swing.scrollbar.ScrollBarCustom;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;


public class BillForm extends javax.swing.JPanel {
    private DefaultTableModel model2;
    private DefaultTableModel model3;
    private DateChooser date = new DateChooser();
    private final SimpleDateFormat dfDate = new SimpleDateFormat("yyyy-MM-dd");
    private final DecimalFormat df = new DecimalFormat("#,###,###");

   
    public BillForm(User user) {
        initComponents();
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane3.setVerticalScrollBar(new ScrollBarCustom());
        
        txtBill.setEnabled(false);
        txtTotal.setEnabled(false);
        
        model2 = (DefaultTableModel) tbBillInfo.getModel();
        model3 = (DefaultTableModel) tbBillDetail.getModel();
        model2.setRowCount(0);
        model3.setRowCount(0);
        
        if (user.isStaff()){
//            txtStaff.setText(user.getUserName());
            txtStaff.setHint(user.getUserName());
            txtStaff.setText(user.getUserName());
            txtStaff.setEnabled(false);
        }
        
        date.setTextField(txtDate);
        date.setDateSelectionMode(DateChooser.DateSelectionMode.BETWEEN_DATE_SELECTED);
        date.setLabelCurrentDayVisible(true);
       
        date.setDateFormat(dfDate);
        date.addActionDateChooserListener(new DateChooserAdapter(){
            @Override
            public void dateBetweenChanged(DateBetween date, DateChooserAction action) {
               
                String fromDate = dfDate.format(date.getFromDate());
                String toDate = dfDate.format(date.getToDate());
                ArrayList<Bill> bills;
                String name = txtStaff.getText();
                if(!name.equals(""))
                    bills = BillDAO.getBillByDateAndStaff(fromDate, toDate, name);
                else
                    bills = BillDAO.getBillByDate(fromDate, toDate);
                loadData(bills);
            }
           
        });
        txtStaff.addActionListener((e) -> {
            String name = txtStaff.getText();
            ArrayList<Bill> bills;
            try {
                
                DateBetween selecDate = date.getSelectedDateBetween();
                String fromDate = dfDate.format(selecDate.getFromDate());
                String toDate = dfDate.format(selecDate.getToDate());
                System.err.println(fromDate + " to "  +toDate);
                bills = BillDAO.getBillByDateAndStaff(fromDate, toDate, name);
                
            } catch(DateChooserException ex) {
                bills = BillDAO.getBillByStaff(name);
            }
                    
            loadData(bills);
        });
        
        
        tbBillInfo.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                model3.setRowCount(0);
                int row = tbBillInfo.getSelectedRow();
                String idBill = model2.getValueAt(row, 0).toString();
                // query các sản phẩm của bill đang chọn
                String query = "SELECT p.name, p.price, bi.count " +
                                "FROM Product p " +
                                "JOIN BillInfo bi ON p.id = bi.idProduct " +
                                "WHERE bi.idBill =" + idBill;
                ResultSet rs = DbOperations.getData(query);
                try {
                    while(rs.next()){
                        String nameProduct = rs.getString("name");
                        int price = rs.getInt("price");
                        int quantity = rs.getInt("count");
                        int total = price * quantity;
                       
                        model3.addRow(new Object[]{nameProduct, quantity,df.format(price), df.format(total)});
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
            }
        });
        

    }
    private void loadData(ArrayList<Bill> bills){
        model2.setRowCount(0);

        int total = 0;
       
        for(Bill x: bills){
            total += x.getTotal();
            model2.addRow((Object[])x.toRowObject());
        }
        txtTotal.setText(df.format(total));
        txtBill.setText(String.valueOf(bills.size()));

    }

  
     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBillInfo = new com.raven.swing.table.Table();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbBillDetail = new com.raven.swing.table.Table();
        txtBill = new com.raven.swing.TextField();
        txtStaff = new com.raven.swing.TextField();
        txtDate = new com.raven.swing.TextField();
        txtTotal = new com.raven.swing.TextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jLayeredPane1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Paid Invoice", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", 1, 14))); // NOI18N

        tbBillInfo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Staff", "Date", "Discount(%)", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbBillInfo);

        jLayeredPane1.setLayer(jScrollPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
        );

        jLayeredPane2.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Detail", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", 1, 14))); // NOI18N

        tbBillDetail.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Product", "Quantity", "Price", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tbBillDetail);

        jLayeredPane2.setLayer(jScrollPane3, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane2Layout = new javax.swing.GroupLayout(jLayeredPane2);
        jLayeredPane2.setLayout(jLayeredPane2Layout);
        jLayeredPane2Layout.setHorizontalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE))
        );
        jLayeredPane2Layout.setVerticalGroup(
            jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 458, Short.MAX_VALUE)
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        txtBill.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Number of Bill", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", 1, 14))); // NOI18N
        txtBill.setForeground(new java.awt.Color(102, 102, 102));
        txtBill.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtBill.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtBill.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtBill.setHint("Số hoá đơn");
        txtBill.setSelectionColor(new java.awt.Color(0, 0, 0));
        txtBill.setUnderlineColor(new java.awt.Color(153, 153, 153));
        txtBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBillActionPerformed(evt);
            }
        });

        txtStaff.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", 1, 14))); // NOI18N
        txtStaff.setForeground(new java.awt.Color(102, 102, 102));
        txtStaff.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtStaff.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtStaff.setHint("User");
        txtStaff.setUnderlineColor(new java.awt.Color(153, 153, 153));

        txtDate.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Date Check In", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", 1, 14))); // NOI18N
        txtDate.setForeground(new java.awt.Color(102, 102, 102));
        txtDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtDate.setUnderlineColor(new java.awt.Color(153, 153, 153));

        txtTotal.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Total", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Montserrat", 1, 14))); // NOI18N
        txtTotal.setForeground(new java.awt.Color(102, 102, 102));
        txtTotal.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTotal.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtTotal.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtTotal.setHint("Tổng tiền");
        txtTotal.setUnderlineColor(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(txtStaff, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtBill, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1)
                .addGap(18, 18, 18)
                .addComponent(jLayeredPane2)
                .addGap(2, 2, 2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtStaff, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBill, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLayeredPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBillActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBillActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private com.raven.swing.table.Table tbBillDetail;
    private com.raven.swing.table.Table tbBillInfo;
    private com.raven.swing.TextField txtBill;
    private com.raven.swing.TextField txtDate;
    private com.raven.swing.TextField txtStaff;
    private com.raven.swing.TextField txtTotal;
    // End of variables declaration//GEN-END:variables

}
