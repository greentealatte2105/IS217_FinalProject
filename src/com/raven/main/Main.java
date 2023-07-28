package com.raven.main;

import com.raven.component.Header;
import com.raven.component.Menu;
import com.raven.dao.UserDAO;
import com.raven.dialog.Message;
import com.raven.event.EventMenuSelected;
import com.raven.event.EventShowPopupMenu;
import com.raven.form.BillForm;
import com.raven.form.CustomerForm;
import com.raven.form.EditForm;
import com.raven.form.StaffManagementForm;
import com.raven.form.OrderForm;
import com.raven.form.MainForm;
import com.raven.form.ReportForm;
import com.raven.model.User;
import com.raven.swing.MenuItem;
import com.raven.swing.PopupMenu;
import com.raven.swing.icon.GoogleMaterialDesignIcons;
import com.raven.swing.icon.IconFontSwing;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.text.DecimalFormat;
import java.time.Duration;
import java.time.Instant;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Main extends javax.swing.JFrame {

    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm main;
    private Animator animator;
    private User user;
    private int idUser;
    private Instant start = Instant.now();
    public Main(){
        initComponents();
        init();
    }

    public Main(User objUser) {
        this.user = objUser;
        objUser.getUserName();
        this.idUser = objUser.getId();
        objUser.getRole();
        initComponents();
        init();
    }
    
    public void setUser(User user){
        this.user = user;
    }
    
    public User getUser() {
        return this.user;
    }

    private void init() {
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        bg.setLayout(layout);
        menu = new Menu();
        header = new Header(user);
        main = new MainForm(user);

        menu.addEvent(new EventMenuSelected() {
            @Override
            public void menuSelected(int menuIndex, int subMenuIndex) {
                System.out.println("Menu Index : " + menuIndex + " SubMenu Index " + subMenuIndex);
                if (menuIndex == 0) {

                        //show home
            
                }
                else if (menuIndex == 1) {
                    main.showForm(new OrderForm());
                }
                else if (menuIndex == 2&& user.getRole().equals("admin")) {
                        main.showForm(new EditForm());
                }
                else if (menuIndex == 3&& user.getRole().equals("admin")) {
                    main.showForm(new ReportForm());
                }
                else if (menuIndex == (2 + (user.getRole().equals("admin") ? 2 : 0))) {
                    subMenuIndex = user.getRole().equals("admin") ? subMenuIndex : (subMenuIndex >= 0)?2:-1;
                    switch (subMenuIndex) {
                        case 0:
                            main.showForm(new StaffManagementForm());
                            break;
                        case 1:
                            main.showForm(new CustomerForm());
                            break;
                        case 2:
                            main.showForm(new BillForm(user));
                            break;
                        default:
                            break;
                    }
                }
                 else if (menuIndex == (3 + (user.getRole().equals("admin") ? 2 : 0))){
                     new ChangePassword(user.getId()).setVisible(true);
                     setVisible(false);
                 }
                 else if (menuIndex == (4 + (user.getRole().equals("admin") ? 2 : 0))){
                     //Log out
                     DecimalFormat dfFloat = new DecimalFormat("##.###");
                     Instant end = Instant.now();
                     Duration timeElapsed = Duration.between(start, end);
//                     System.out.println((float)timeElapsed.toMillis()/3600000);
                     // show message and usage time
                     float totalTime = (float)timeElapsed.toMillis()/3600000;
                     String message = "<HTML><p style=\"text-align:center\">“Your working time is <b>"+dfFloat.format(totalTime)+ 
                            "</b><br>Do you want to log out?</p></HTML>";
                     if ( showMessage(message,"question"))
                     {
                        user.setTime(totalTime);
                        new Login().setVisible(true);
                        setVisible(false);
                        UserDAO.addTimeForUser(idUser, totalTime);
                     }
                    
                }
            }
            
        });
        menu.addEventShowPopup(new EventShowPopupMenu() {
            @Override
            public void showPopup(Component com) {
                MenuItem item = (MenuItem) com;
                PopupMenu popup = new PopupMenu(Main.this, item.getIndex(), item.getEventSelected(), item.getMenu().getSubMenu());
                int x = Main.this.getX() + 52;
                int y = Main.this.getY() + com.getY() + 86;
                popup.setLocation(x, y);
                popup.setVisible(true);
            }
        });
        menu.initMenuItem(user);
        bg.add(menu, "w 200!, spany 2");    // Span Y 2cell
        bg.add(header, "h 50!, wrap");
        bg.add(main, "w 100%, h 100%");
        
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double width;
                if (menu.isShowMenu()) {
                    width = 60 + (150 * (1f - fraction));
                } else {
                    width = 60 + (150 * fraction);
                }
                layout.setComponentConstraints(menu, "w " + width + "!, spany2");
                menu.revalidate();
            }

            @Override
            public void end() {
                menu.setShowMenu(!menu.isShowMenu());
                menu.setEnableMenu(true);
            }

        };
        animator = new Animator(500, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
        animator.setAcceleration(0.5f);
        header.addMenuEvent((ActionEvent ae) -> {
            if (!animator.isRunning()) {
                animator.start();
            }
            menu.setEnableMenu(false);
            if (menu.isShowMenu()) {
                menu.hideallMenu();
            }
        });
        //  Init google icon font
        IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
        //  Start with this form
        main.showForm(new OrderForm());
    }
     private boolean showMessage(String message, String option) {
        Message obj = new Message(this.getFrames()[0], true, option);
        obj.showMessage(message);
        return obj.isOk();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new com.raven.swing.PanelBorder();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 783, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.PanelBorder bg;
    // End of variables declaration//GEN-END:variables
}
