package com.raven.component;

import com.raven.event.EventCard;
import com.raven.model.Product;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

public class Card extends javax.swing.JPanel {

 
    private Color colorGradient;
       
    public Card(Product product, EventCard eventCard, String src) {
        initComponents();
        setOpaque(false);
//        setSize(138, 72);
//        int length = Math.max(product.getName().length(), 
//                                String.valueOf(product.getPrice()).length() + 8);
//        this.setPreferredSize(new Dimension( length * 10 + 8,250));
        background1.setImageSource(src);

        lbProductName.setText(product.getName());
       DecimalFormat df = new DecimalFormat("#,###,###");
       String price = "Price: " + df.format(product.getPrice());
   
       priceView.setText(price);
//       priceView.repaint();
       MouseAdapter mouseAdapter = new MouseAdapter() {
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
        };
        
        lbProductName.addMouseListener(mouseAdapter);
        priceView.addMouseListener(mouseAdapter);
        background1.addMouseListener(mouseAdapter);
        
        
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelShadow1 = new com.raven.panel.PanelShadow();
        background1 = new com.raven.component.Background();
        lbProductName = new javax.swing.JLabel();
        priceView = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setAutoscrolls(true);
        setOpaque(false);

        background1.setFancyBorderRadius("88% 12% 89% 11% / 12% 88% 12% 88% ");

        lbProductName.setBackground(new java.awt.Color(0, 153, 153));
        lbProductName.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        lbProductName.setForeground(new java.awt.Color(0, 153, 153));
        lbProductName.setText("Tra sua");
        lbProductName.setAutoscrolls(true);
        lbProductName.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbProductName.setMaximumSize(new java.awt.Dimension(250, 20));
        lbProductName.setMinimumSize(new java.awt.Dimension(60, 20));
        lbProductName.setPreferredSize(new java.awt.Dimension(60, 20));

        priceView.setBackground(new java.awt.Color(204, 194, 187));
        priceView.setFont(new java.awt.Font("Montserrat", 1, 16)); // NOI18N
        priceView.setForeground(new java.awt.Color(0, 153, 153));
        priceView.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        priceView.setText("1121313123123");
        priceView.setAlignmentX(1.0F);
        priceView.setAutoscrolls(true);
        priceView.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        priceView.setMaximumSize(new java.awt.Dimension(250, 100));
        priceView.setMinimumSize(new java.awt.Dimension(60, 30));
        priceView.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(background1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(priceView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelShadow1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(lbProductName, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(background1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbProductName, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(priceView, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelShadow1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.component.Background background1;
    private javax.swing.JLabel lbProductName;
    private com.raven.panel.PanelShadow panelShadow1;
    private javax.swing.JLabel priceView;
    // End of variables declaration//GEN-END:variables
}
