package com.raven.form;

import com.raven.dao.ProductDAO;
import com.raven.component.Card;
import com.raven.component.billInfoRow;
import com.raven.dao.ProductCategoryDAO;
import com.raven.dialog.Message;
import com.raven.main.Main;
import com.raven.model.Product;
import com.raven.model.ProductCategory;
import com.raven.swing.scrollbar.ScrollBarCustom;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;

public class Form_Home extends javax.swing.JPanel {
    private ArrayList<Product> products;

    public Form_Home() {
        initComponents();
        setOpaque(false);
        scrollBill.setVerticalScrollBar(new ScrollBarCustom());
        scrollProduct.setVerticalScrollBar(new ScrollBarCustom());
        initBillInfo();
        initCategoryCard();
        initProductCard();
    }
    
    private void initCategoryCard() {
        ArrayList<ProductCategory> list = ProductCategoryDAO.getAllRecords();
        Iterator<ProductCategory> itr = list.iterator();
        while (itr.hasNext()) {
            ProductCategory productCategoryObj = itr.next();
            addCategory( new Card( new ProductCategory(productCategoryObj.getId(), productCategoryObj.getName() ) ) );
        }
    }
    private void initBillInfo() {
        addBillRow(new billInfoRow(new Product(1,"Tra vvvvvsua", 200000)));
        addBillRow(new billInfoRow(new Product(4,"Tra sua", 200000)));
        
        addBillRow(new billInfoRow(new Product(1,"Tra vvvsua", 200000)));
        addBillRow(new billInfoRow(new Product(4,"Tra sua", 200000)));
        addBillRow(new billInfoRow(new Product(1,"Tra vvvsua", 200000)));
        addBillRow(new billInfoRow(new Product(4,"Tra sua", 200000)));
    }
    
    private void initProductCard(){
        
//        addProduct(new Card(new Product(0,"Tra sua",10000)));
//        addProduct(new Card(new Product(0,"Tra sua",10000)));
        ArrayList<Product> list = ProductDAO.getRecordsByIdCategory(2);
        Iterator<Product> itr = list.iterator();
        while (itr.hasNext()) {
            Product productObj = itr.next();
//            table1.addRow(new Object[]{productObj.getId(), productObj.getName(), productObj.getIdCategory(), productObj.getPrice()} );
            addProduct(new Card(new Product(productObj.getId(), productObj.getName(), productObj.getIdCategory(), productObj.getPrice())));
        }
        
    }

    private void addBillRow(billInfoRow row){
        billPanel.add(row);
    }
    private void addCategory(Card card){
        panelBorder3.add(card);
    }
    private void addProduct(Card card){
        productView.add(card);
    }
    public void setProducts(ArrayList<Product> products){
        this.products = products;
    }

    private boolean showMessage(String message) {
        Message obj = new Message(Main.getFrames()[0], true);
        obj.showMessage(message);
        return obj.isOk();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.raven.swing.PanelBorder();
        button1 = new com.raven.swing.Button();
        lbTotalView = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        scrollBill = new javax.swing.JScrollPane();
        billPanel = new javax.swing.JPanel();
        panelBorder2 = new com.raven.swing.PanelBorder();
        scrollProduct = new javax.swing.JScrollPane();
        productView = new javax.swing.JPanel();
        panelBorder3 = new com.raven.swing.PanelBorder();
        panelBorder4 = new com.raven.swing.PanelBorder();

        panelBorder1.setBackground(new java.awt.Color(102, 102, 102));

        button1.setBackground(new java.awt.Color(0, 153, 153));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Print");
        button1.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        lbTotalView.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        lbTotalView.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbTotalView.setText("000000000");

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel2.setText("Total");

        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Receipt");

        scrollBill.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        billPanel.setBackground(new java.awt.Color(255, 255, 255));
        billPanel.setPreferredSize(new java.awt.Dimension(400, 800));
        billPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        scrollBill.setViewportView(billPanel);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollBill, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbTotalView, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(259, 259, 259)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollBill, javax.swing.GroupLayout.DEFAULT_SIZE, 562, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(lbTotalView))
                .addGap(10, 10, 10))
        );

        panelBorder2.setBackground(new java.awt.Color(52, 52, 52));
        panelBorder2.setPreferredSize(new java.awt.Dimension(400, 600));

        scrollProduct.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollProduct.setPreferredSize(new java.awt.Dimension(100, 800));

        productView.setBackground(new java.awt.Color(243, 243, 243));
        productView.setPreferredSize(new java.awt.Dimension(100, 800));
        productView.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10));
        scrollProduct.setViewportView(productView);

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        panelBorder3.setPreferredSize(new java.awt.Dimension(400, 600));

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 48, Short.MAX_VALUE)
        );

        panelBorder4.setPreferredSize(new java.awt.Dimension(400, 600));

        javax.swing.GroupLayout panelBorder4Layout = new javax.swing.GroupLayout(panelBorder4);
        panelBorder4.setLayout(panelBorder4Layout);
        panelBorder4Layout.setHorizontalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 649, Short.MAX_VALUE)
        );
        panelBorder4Layout.setVerticalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 41, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                    .addComponent(panelBorder4, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                    .addComponent(panelBorder3, javax.swing.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE))
                .addGap(10, 10, 10)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(panelBorder4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(panelBorder3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel billPanel;
    private com.raven.swing.Button button1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbTotalView;
    private com.raven.swing.PanelBorder panelBorder1;
    private com.raven.swing.PanelBorder panelBorder2;
    private com.raven.swing.PanelBorder panelBorder3;
    private com.raven.swing.PanelBorder panelBorder4;
    private javax.swing.JPanel productView;
    private javax.swing.JScrollPane scrollBill;
    private javax.swing.JScrollPane scrollProduct;
    // End of variables declaration//GEN-END:variables
}
