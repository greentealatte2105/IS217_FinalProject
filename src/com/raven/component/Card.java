package com.raven.component;

import com.raven.event.EventCard;
import com.raven.model.Product;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

public class Card extends javax.swing.JPanel {

    private Product product;
    private Color colorGradient;
       
    public Card(Product product, EventCard eventCard, String src) {
        initComponents();
        setOpaque(false);
//        setSize(138, 72);
        this.product = product;
//        int length = Math.max(product.getName().length(), 
//                                String.valueOf(product.getPrice()).length() + 8);
//        this.setPreferredSize(new Dimension( length * 10 + 8,250));
        background1.setImageSource(src);

        update();
        
        
        lbProductName.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                eventCard.update(product);
//              
                setBackground(new Color(214,200,174,255));
               
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
                eventCard.update(product);

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

    public void update() {
       lbProductName.setText(product.getName());
       DecimalFormat df = new DecimalFormat("#,###,###");
       priceView.setText("Price: " + df.format(product.getPrice()));
    }
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background1 = new com.raven.component.Background();
        jPanel1 = new javax.swing.JPanel();
        lbProductName = new javax.swing.JLabel();
        priceView = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setAutoscrolls(true);
        setMaximumSize(new java.awt.Dimension(250, 150));
        setMinimumSize(new java.awt.Dimension(200, 100));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(250, 250));
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                formComponentAdded(evt);
            }
        });

        background1.setBlur(jPanel1);
        background1.setFancyBorderRadius("88% 12% 89% 11% / 12% 88% 12% 88% ");

        jPanel1.setOpaque(false);

        lbProductName.setBackground(new java.awt.Color(138, 131, 122));
        lbProductName.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        lbProductName.setForeground(new java.awt.Color(111, 51, 0));
        lbProductName.setText("Tra sua");
        lbProductName.setAutoscrolls(true);
        lbProductName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbProductName.setMaximumSize(new java.awt.Dimension(250, 20));
        lbProductName.setMinimumSize(new java.awt.Dimension(60, 20));
        lbProductName.setPreferredSize(new java.awt.Dimension(60, 20));

        priceView.setBackground(new java.awt.Color(204, 194, 187));
        priceView.setFont(new java.awt.Font("Montserrat", 0, 16)); // NOI18N
        priceView.setForeground(new java.awt.Color(125, 55, 74));
        priceView.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceView.setText("1121313123123");
        priceView.setAutoscrolls(true);
        priceView.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        priceView.setMaximumSize(new java.awt.Dimension(250, 30));
        priceView.setMinimumSize(new java.awt.Dimension(60, 30));
        priceView.setOpaque(true);
        priceView.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceView, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(lbProductName, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(priceView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        background1.add(jPanel1);
        jPanel1.setBounds(0, 120, 210, 130);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentAdded
        // TODO add your handling code here:
       
    }//GEN-LAST:event_formComponentAdded


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.component.Background background1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbProductName;
    private javax.swing.JLabel priceView;
    // End of variables declaration//GEN-END:variables
}
