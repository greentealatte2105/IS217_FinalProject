package com.raven.main;

import com.raven.dao.UserDAO;
import com.raven.model.User;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;

public class Login extends javax.swing.JFrame {

  
    public Login() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        background2 = new com.raven.component.Background();
        panel = new javax.swing.JPanel();
        txtUsername = new com.raven.swing.TextField();
        txtPassword = new com.raven.swing.PasswordField();
        bLogin = new com.raven.swing.ButtonEffect();
        lbForgot = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        footerLoginfail1 = new com.raven.component.footer();
        jLabel3 = new javax.swing.JLabel();
        lbSignup = new javax.swing.JLabel();
        bExit = new com.raven.swing.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        background2.setBlur(panel);

        panel.setOpaque(false);

        txtUsername.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtUsername.setHint("User name");

        txtPassword.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        txtPassword.setHint("Password");
        txtPassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPasswordActionPerformed(evt);
            }
        });

        bLogin.setForeground(new java.awt.Color(255, 255, 255));
        bLogin.setText("LOGIN");
        bLogin.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        bLogin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bLoginMouseClicked(evt);
            }
        });
        bLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLoginActionPerformed(evt);
            }
        });

        lbForgot.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        lbForgot.setForeground(new java.awt.Color(255, 255, 255));
        lbForgot.setText("Forgot Password?");
        lbForgot.setName("Forgot Password?"); // NOI18N
        lbForgot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbForgotMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbForgotMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbForgotMouseExited(evt);
            }
        });

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(bLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbForgot, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtUsername, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtPassword, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)))))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(txtUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbForgot, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addComponent(bLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        jLabel1.setFont(new java.awt.Font("Montserrat", 1, 32)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Welcome Back!");

        jLabel2.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Sign to continue");

        footerLoginfail1.setBackground(new java.awt.Color(204, 204, 255));

        jLabel3.setFont(new java.awt.Font("Montserrat", 0, 13)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Don't have a account?");

        lbSignup.setFont(new java.awt.Font("Montserrat", 1, 13)); // NOI18N
        lbSignup.setForeground(new java.awt.Color(255, 255, 255));
        lbSignup.setText("Sign up here");
        lbSignup.setName("Sign up here"); // NOI18N
        lbSignup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbSignupMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lbSignupMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lbSignupMouseExited(evt);
            }
        });

        javax.swing.GroupLayout footerLoginfail1Layout = new javax.swing.GroupLayout(footerLoginfail1);
        footerLoginfail1.setLayout(footerLoginfail1Layout);
        footerLoginfail1Layout.setHorizontalGroup(
            footerLoginfail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, footerLoginfail1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbSignup, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80))
        );
        footerLoginfail1Layout.setVerticalGroup(
            footerLoginfail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(footerLoginfail1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(footerLoginfail1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(lbSignup))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        bExit.setBackground(new java.awt.Color(51, 51, 51));
        bExit.setForeground(new java.awt.Color(255, 255, 255));
        bExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/exit-30.png"))); // NOI18N
        bExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout background2Layout = new javax.swing.GroupLayout(background2);
        background2.setLayout(background2Layout);
        background2Layout.setHorizontalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(footerLoginfail1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(background2Layout.createSequentialGroup()
                .addGroup(background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(background2Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(background2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(bExit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        background2Layout.setVerticalGroup(
            background2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(background2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bExit, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(footerLoginfail1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bLoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bLoginMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_bLoginMouseClicked
    private void ActionLogin(){
        // TODO add your handling code here:
        String user = txtUsername.getText();
        String pass = String.valueOf(txtPassword.getPassword());
//        // code chưa connect sql
//        if (user.equals("admin") && pass.equals("admin"))
//        {
//            System.out.println("ProjectCafe.Login.bLoginActionPerformed()");
//            setVisible(false);
//            new Main().setVisible(true);
//        }
            // code sử dụng database
        int loginID = UserDAO.login(user, pass);
        if (loginID > 0) {
            setVisible(false);
            String role = UserDAO.getUserRole(loginID);
            
            User objUser = new User(loginID, user, pass, role);
            new Main(objUser).setVisible(true);
            setVisible(false);
        }
    }
    private void bLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLoginActionPerformed
        ActionLogin();
    }//GEN-LAST:event_bLoginActionPerformed

    private void lbForgotMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbForgotMouseClicked
        // TODO add your handling code here:
        System.out.println("ProjectCafe.Login.lbForgotMouseClicked()");
        setVisible(false);
        new ForgotPassword().setVisible(true);
        
    }//GEN-LAST:event_lbForgotMouseClicked

    private void lbForgotMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbForgotMouseEntered
        // TODO add your handling code here:
//        lbForgot.setText("");
          String underlineTxt = "<HTML><u>"+lbForgot.getText()+"</u></HTML>";
          lbForgot.setText(underlineTxt);
//        
    }//GEN-LAST:event_lbForgotMouseEntered

    private void lbForgotMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbForgotMouseExited
        // TODO add your handling code here:
        
        lbForgot.setText(lbForgot.getName());
//        lbForgot.setFont(new Font("Montserrat", Font.PLAIN, 13));
    }//GEN-LAST:event_lbForgotMouseExited

    private void bExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_bExitActionPerformed

    private void lbSignupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSignupMouseClicked
        // TODO add your handling code here:
        System.out.println("com.raven.main.Login.lbSignupMouseClicked()");
        setVisible(false);
        new Signup().setVisible(true);
    }//GEN-LAST:event_lbSignupMouseClicked

    private void lbSignupMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSignupMouseEntered
        // TODO add your handling code here:
        String underlineTxt = "<HTML><u>"+lbSignup.getText()+"</u></HTML>";
        lbSignup.setText(underlineTxt);
    }//GEN-LAST:event_lbSignupMouseEntered

    private void lbSignupMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbSignupMouseExited
        // TODO add your handling code here:
        lbSignup.setText(lbSignup.getName());
    }//GEN-LAST:event_lbSignupMouseExited

    private void txtPasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPasswordActionPerformed
        // TODO add your handling code here:
        ActionLogin();
    }//GEN-LAST:event_txtPasswordActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Button bExit;
    private com.raven.swing.ButtonEffect bLogin;
    private com.raven.component.Background background2;
    private com.raven.component.footer footerLoginfail1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbForgot;
    private javax.swing.JLabel lbSignup;
    private javax.swing.JPanel panel;
    private com.raven.swing.PasswordField txtPassword;
    private com.raven.swing.TextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
