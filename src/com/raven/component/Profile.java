package com.raven.component;

public class Profile extends javax.swing.JPanel {

    public Profile() {
        initComponents();
        setOpaque(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        bExit = new com.raven.swing.Button();
        bHide = new com.raven.swing.Button();
        bResize = new com.raven.swing.Button();

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(224, 224, 224));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/cafe_logo.png"))); // NOI18N

        bExit.setBackground(new java.awt.Color(255, 255, 255));
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bHide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bResize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(bExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bHide, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bResize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
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
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
