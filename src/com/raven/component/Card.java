package com.raven.component;

import com.raven.model.Product;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.JLabel;

import javax.swing.JPanel;

public class Card extends javax.swing.JPanel {

    private Product product;
    private Color colorGradient;
    

    public Card(Product product, JPanel panel, JLabel lbTotal) {
        initComponents();
        setOpaque(false);
        setSize(138, 72);
        this.product = product;
        update();
        DecimalFormat df = new DecimalFormat("#,###,###");
        lbProductName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               System.out.println(".mouseClicked()");
               setBackground(new Color(214,200,174,255));
               billInfoRow row = new billInfoRow(product,lbTotal);

               int total = Integer.parseInt(lbTotal.getText().replaceAll("[\\.]", "")) + product.getPrice();
               lbTotal.setText(df.format(total));
               
               panel.add(row);
               panel.repaint();
               panel.revalidate();
            }
            @Override
            public void mouseEntered(MouseEvent e){
                System.out.println(".mouseEntered()");
                String txt = "<HTML><u>"+product.getName() +"</u></HTML>";
                lbProductName.setText(txt);
            }
            @Override
            public void mouseExited(MouseEvent e){
                setBackground(new Color(233,220,190));

                lbProductName.setText(product.getName());
            }
        });
        priceView.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               System.out.println(".mouseClicked()");
                setBackground(new Color(214,200,174,255));

               billInfoRow row = new billInfoRow(product,lbTotal);
               panel.add(row);
               panel.repaint();
            }
            @Override
            public void mouseEntered(MouseEvent e){
                System.out.println(".mouseEntered()");
                String txt = "<HTML><u>"+product.getName() +"</u></HTML>";
                lbProductName.setText(txt);
            }
            @Override
            public void mouseExited(MouseEvent e){
                setBackground(new Color(233,220,190));

                lbProductName.setText(product.getName());
            }
        });
        
        
    }
    public void addBill(JPanel billPanel){
       
    }
    

    public void update() {
       lbProductName.setText(product.getName());
       DecimalFormat df = new DecimalFormat("#,###,###");
       priceView.setText("Price: " + df.format(product.getPrice()));
    }
    public void actionPush(ActionEvent evt){
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbProductName = new javax.swing.JLabel();
        priceView = new javax.swing.JLabel();

        setBackground(new java.awt.Color(233, 220, 190));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setMaximumSize(new java.awt.Dimension(138, 72));
        setMinimumSize(new java.awt.Dimension(120, 70));
        setPreferredSize(new java.awt.Dimension(140, 80));
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                formComponentAdded(evt);
            }
        });

        lbProductName.setBackground(new java.awt.Color(138, 131, 122));
        lbProductName.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        lbProductName.setForeground(new java.awt.Color(52, 52, 52));
        lbProductName.setText("Tra sua");

        priceView.setBackground(new java.awt.Color(142, 139, 150));
        priceView.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        priceView.setForeground(new java.awt.Color(142, 139, 150));
        priceView.setText("1121313123123");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbProductName, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(priceView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lbProductName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(priceView, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
    private javax.swing.JLabel lbProductName;
    private javax.swing.JLabel priceView;
    // End of variables declaration//GEN-END:variables
}
