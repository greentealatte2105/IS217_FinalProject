package com.raven.component;

public class Profile extends javax.swing.JPanel {

    public Profile() {
        initComponents();
        setOpaque(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bHide = new com.raven.swing.Button();
        bResize = new com.raven.swing.Button();
        bExit = new com.raven.swing.Button();
        logo1 = new com.raven.swing.Logo();

        bHide.setBackground(new java.awt.Color(102, 102, 102));
        bHide.setMaximumSize(new java.awt.Dimension(15, 15));
        bHide.setMinimumSize(new java.awt.Dimension(18, 18));
        bHide.setPreferredSize(new java.awt.Dimension(15, 15));
        bHide.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bHideActionPerformed(evt);
            }
        });

        bResize.setBackground(new java.awt.Color(102, 102, 102));
        bResize.setMaximumSize(new java.awt.Dimension(15, 15));
        bResize.setMinimumSize(new java.awt.Dimension(18, 18));
        bResize.setPreferredSize(new java.awt.Dimension(15, 15));

        bExit.setBackground(new java.awt.Color(51, 51, 51));
        bExit.setForeground(new java.awt.Color(255, 255, 255));
        bExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/exit-15.png"))); // NOI18N
        bExit.setMaximumSize(new java.awt.Dimension(15, 15));
        bExit.setMinimumSize(new java.awt.Dimension(18, 18));
        bExit.setPreferredSize(new java.awt.Dimension(15, 15));
        bExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bExitMouseClicked(evt);
            }
        });

        logo1.setAutoscrolls(true);
        logo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/images/logo.jpg"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bExit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 224, Short.MAX_VALUE))
                    .addComponent(logo1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(bExit, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(logo1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bExitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bExitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_bExitMouseClicked

    private void bHideActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bHideActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bHideActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Button bExit;
    private com.raven.swing.Button bHide;
    private com.raven.swing.Button bResize;
    private com.raven.swing.Logo logo1;
    // End of variables declaration//GEN-END:variables
}
