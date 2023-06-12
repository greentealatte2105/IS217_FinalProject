
package com.raven.component;


import com.raven.model.Product;
import java.text.DecimalFormat;

import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author dothinhtpr247gmai.com
 */
public class billInfoRow extends javax.swing.JPanel {


//    private BillInfo info;
    private int idProduct;
    private String nameProduct;

    public int getPrice() {
        return price;
    }
    private int price;
    private int quantity;
    private int amount;

    public int getIdProduct() {
        return idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getAmount() {
        return amount;
    }
    private DecimalFormat df = new DecimalFormat("#,###,###");
    private JLabel lbTotal;
    
    public billInfoRow(Product product, JLabel lbTotal) {
        initComponents();
        setOpaque(false);
        idProduct = product.getId();
        nameProduct = product.getName();
        price = product.getPrice();
        quantity = 1;
        amount = price * quantity;
        this.lbTotal = lbTotal;
        update();
    }
    
    public void update(){
        

        lbName.setText(nameProduct);
        lbQuantity.setText(String.valueOf(quantity));
        lbAmount.setText(df.format(amount));
    }
    
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new com.raven.swing.PanelBorder();
        bInscrease = new com.raven.swing.Button();
        bDelete = new com.raven.swing.Button();
        bDescrease = new com.raven.swing.Button();
        lbQuantity = new javax.swing.JLabel();
        lbName = new javax.swing.JLabel();
        lbAmount = new javax.swing.JLabel();

        setAutoscrolls(true);
        setMaximumSize(new java.awt.Dimension(330, 43));
        setMinimumSize(new java.awt.Dimension(330, 43));

        panelBorder1.setBackground(new java.awt.Color(204, 204, 204));

        bInscrease.setBackground(new java.awt.Color(0, 204, 102));
        bInscrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/icons8-add-18.png"))); // NOI18N
        bInscrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInscreaseActionPerformed(evt);
            }
        });

        bDelete.setBackground(new java.awt.Color(255, 255, 255));
        bDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/delete.png"))); // NOI18N
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });

        bDescrease.setBackground(new java.awt.Color(255, 51, 51));
        bDescrease.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/icons8-minus-18.png"))); // NOI18N
        bDescrease.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bDescrease.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        bDescrease.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDescreaseActionPerformed(evt);
            }
        });

        lbQuantity.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        lbQuantity.setText("10");
        lbQuantity.setAutoscrolls(true);
        lbQuantity.setMaximumSize(new java.awt.Dimension(30, 32));
        lbQuantity.setMinimumSize(new java.awt.Dimension(14, 32));

        lbName.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lbName.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbName.setText("Hong tra");
        lbName.setMaximumSize(new java.awt.Dimension(80, 32));
        lbName.setMinimumSize(new java.awt.Dimension(60, 32));

        lbAmount.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        lbAmount.setText("1000000");
        lbAmount.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap(10, Short.MAX_VALUE)
                .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(lbQuantity, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(lbAmount, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(bDescrease, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bInscrease, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(bInscrease, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bDescrease, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
            .addComponent(lbAmount, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbQuantity, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bDescreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDescreaseActionPerformed
        // TODO add your handling code here:
        quantity -= 1;
        amount = price * quantity;
        if(quantity <= 0){
            JComponent parent = (JComponent) this.getParent();
            parent.remove(this);
            parent.repaint();
            parent.revalidate();
        }
        else
        {int total = Integer.parseInt(lbTotal.getText().replaceAll("[,\\.]", "")) - price;
            if (total <= 0)
                this.lbTotal.setText("0");
            else  this.lbTotal.setText(df.format(total));
            update();
        }
    }//GEN-LAST:event_bDescreaseActionPerformed

    private void bInscreaseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInscreaseActionPerformed
        // TODO add your handling code here:
        quantity += 1;
        amount = quantity * price;
        int total = Integer.parseInt(lbTotal.getText().replaceAll("[,\\.]", "")) + price;
        this.lbTotal.setText(df.format(total));
        update();
    }//GEN-LAST:event_bInscreaseActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        // TODO add your handling code here:
        JComponent parent = (JComponent) this.getParent();
        int total = Integer.parseInt(lbTotal.getText().replaceAll("[,\\.]", "")) - amount;
        if (total <= 0)
                this.lbTotal.setText("0");
            else  this.lbTotal.setText(df.format(total));
        parent.remove(this);
        parent.repaint();
        parent.revalidate();
    }//GEN-LAST:event_bDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Button bDelete;
    private com.raven.swing.Button bDescrease;
    private com.raven.swing.Button bInscrease;
    private javax.swing.JLabel lbAmount;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbQuantity;
    private com.raven.swing.PanelBorder panelBorder1;
    // End of variables declaration//GEN-END:variables
}
