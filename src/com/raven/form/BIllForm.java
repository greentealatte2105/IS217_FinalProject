
package com.raven.form;

import com.raven.dao.DbOperations;
import com.raven.main.Main;
import com.raven.swing.scrollbar.ScrollBarCustom;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;


public class BillForm extends javax.swing.JPanel {
    private DefaultTableModel model1;
    private DefaultTableModel model2;
    private DefaultTableModel model3;
    private String role;
    private String name;
    
    /** Creates new form CustomerForm */
    public BillForm() {
        initComponents();
        jScrollPane1.setVerticalScrollBar(new ScrollBarCustom());
        jScrollPane3.setVerticalScrollBar(new ScrollBarCustom());
     
        model2 = (DefaultTableModel) tbBillInfo.getModel();
        model3 = (DefaultTableModel) tbBillDetail.getModel();
        
        model2.setRowCount(0);
        model3.setRowCount(0);
        
        tbBillInfo.getColumnModel().getColumn(0).setMinWidth(0);
        tbBillInfo.getColumnModel().getColumn(0).setMaxWidth(0);
        tbBillInfo.getColumnModel().getColumn(0).setWidth(0);
        MainForm parent = (MainForm) getParent();
        role = parent.getUser().getUserName();
        if (role == "admin"){
            tbBillInfo.getColumnModel().getColumn(1).setMaxWidth(0);
            tbBillInfo.getColumnModel().getColumn(1).setMinWidth(0);
            tbBillInfo.getColumnModel().getColumn(1).setWidth(0);
        }
        else{
            
        }
        init(); // khởi tạo bill đã thanh toán
            
        
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
                        model3.addRow(new Object[]{nameProduct, quantity, total});
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                
            }
        });

    }
    
    private void init() {
        String query = "SELECT c.id, c.phoneNumber AS phone, c.total, cc.name as rankName, cc.discount " +
                                "FROM Customer c " +
                                "JOIN CustomerCategory cc ON c.idRank = cc.id order by c.id";
        ResultSet rs = DbOperations.getData(query);
        try {
            while (rs.next()){
                int id = rs.getInt("id");
                String phone = rs.getString("phone");
                int total = rs.getInt("total");
                String rank = rs.getString("rankName");
                int discount = rs.getInt("discount");
                model1.addRow(new Object[]{id, phone, total, rank, discount+"%"});
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

  
     @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jOptionPane1 = new javax.swing.JOptionPane();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbBillInfo = new com.raven.swing.table.Table();
        jLayeredPane2 = new javax.swing.JLayeredPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tbBillDetail = new com.raven.swing.table.Table();

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
                "ID", "Staff", "Date", "Discount", "Total"
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
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Product", "Quantity", "Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(jLayeredPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jLayeredPane2Layout.createSequentialGroup()
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLayeredPane1)
                .addGap(20, 20, 20)
                .addComponent(jLayeredPane2))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 101, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLayeredPane2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLayeredPane1, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JLayeredPane jLayeredPane2;
    private javax.swing.JOptionPane jOptionPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private com.raven.swing.table.Table tbBillDetail;
    private com.raven.swing.table.Table tbBillInfo;
    // End of variables declaration//GEN-END:variables

}
