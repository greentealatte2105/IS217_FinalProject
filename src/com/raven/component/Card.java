package com.raven.component;

import com.raven.model.Product;
import com.raven.model.ProductCategory;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;

public class Card extends javax.swing.JPanel {

    private Product product;
    private ProductCategory productCategory;
   
    private Color colorGradient;
    
    public Card(ProductCategory productCategory) {
        initComponents();
        setOpaque(false);
        setSize(138, 40);
        this.productCategory = productCategory;
        updateProductCategory();
    }
    
    public Card(Product product) {
        initComponents();
        setOpaque(false);
        setSize(138, 72);
        this.product = product;
        updateProduct();
    }
    
    public void updateProductCategory() {
        lbProductName.setText(productCategory.getName());
    }
    public void updateProduct() {
       lbProductName.setText(product.getName());
       DecimalFormat df = new DecimalFormat("#,###,###");
       priceView.setText(df.format(product.getPrice()));
    }
    public void actionPush(ActionEvent evt){
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbProductName = new javax.swing.JLabel();
        priceView = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(233, 220, 190));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setMaximumSize(new java.awt.Dimension(138, 72));
        setMinimumSize(new java.awt.Dimension(138, 72));
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                formComponentAdded(evt);
            }
        });

        lbProductName.setBackground(new java.awt.Color(138, 131, 122));
        lbProductName.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        lbProductName.setForeground(new java.awt.Color(52, 52, 52));
        lbProductName.setText("Tra sua");

        priceView.setBackground(new java.awt.Color(142, 139, 150));
        priceView.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        priceView.setForeground(new java.awt.Color(142, 139, 150));
        priceView.setText("1121313123123");

        jLabel3.setBackground(new java.awt.Color(142, 139, 150));
        jLabel3.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(142, 139, 150));
        jLabel3.setText("Price:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(priceView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lbProductName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbProductName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceView, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentAdded
        // TODO add your handling code here:
       
    }//GEN-LAST:event_formComponentAdded

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbProductName;
    private javax.swing.JLabel priceView;
    // End of variables declaration//GEN-END:variables
}
