package com.raven.form;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.raven.component.Card;
import com.raven.component.billInfoRow;
import com.raven.dao.ProductCategoryDAO;
import com.raven.dao.ProductDAO;
import com.raven.dialog.Message;
import com.raven.event.EventCard;
import com.raven.main.Main;
import com.raven.model.Product;
import com.raven.model.ProductCategory;
import com.raven.swing.Button;
import com.raven.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sun.security.krb5.internal.rcache.DflCache;

public class EditForm extends javax.swing.JPanel {
    private ArrayList<Product> products;
    private int idTabe;
    private int total;
    private Button currentCatagoryButton = null;
    private Color defaultColor = new Color(0,0,0,150);
    private Color choiceColor = new Color(0,0,0,100);
    private DefaultTableModel model ;
    public EditForm() {
        initComponents();
        setOpaque(false);
        scrollProduct.setVerticalScrollBar(new ScrollBarCustom());
        scrollCatogory.setHorizontalScrollBar(new ScrollBarCustom());
        model = (DefaultTableModel)tbLog.getModel();
        
        initCatagory();
    }
    private void initCatagory(){
        ArrayList<ProductCategory> list = ProductCategoryDAO.getAllRecords();
        System.out.println(list.size());
        Iterator<ProductCategory> itr = list.iterator();
        while (itr.hasNext()) {
            ProductCategory productCategoryObj = itr.next();
            catagoryPanel.add( createCatagroryButton(productCategoryObj) );
        }            
    }
    public Button createCatagroryButton(ProductCategory productCategory){
        Button bt = new Button();
//        bt.setSize(300,200);
        bt.setPreferredSize(new Dimension(100,45));
        bt.setText(productCategory.getName());
        bt.setFont(new Font("Montserrat", Font.BOLD,16));
        bt.setForeground(new Color(255,255,255));
        bt.setBackground(defaultColor);
        bt.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent me){
                
                if (currentCatagoryButton != null){
                    currentCatagoryButton.setBackground(defaultColor);
       
                }
                bt.setBackground(choiceColor);
                currentCatagoryButton = bt;
                ArrayList<Product> list = ProductDAO.getRecordsByIdCategory(productCategory.getId());
                productView.removeAll();
                productView.repaint();
                productView.revalidate();
                initProductCard(list);
                
            }});
        return bt;
    }
                
    
    private void initProductCard(ArrayList<Product> listProduct){
        Iterator<Product> itr = listProduct.iterator();
        DecimalFormat df = new DecimalFormat("#,###,###");
        EventCard evt = new EventCard() {
            @Override
            public void update(Product product) {
               txtCatagory.setText(currentCatagoryButton.getText());
               txtName.setText(product.getName());
               txtId.setText(String.valueOf(product.getId()));
               txtPrice.setText(df.format(product.getPrice()));
               
            }
        };
        while (itr.hasNext()) {
            Product productObj = itr.next();
            
        
            Product product = new Product(productObj.getId(), productObj.getName(), productObj.getPrice());
            addProduct(new Card(product, evt));
        }
    }
    public void updateTabble(JButton button){
        model.insertRow(0,new Object[]{" "+txtCatagory.getText(), txtName.getText(),txtPrice.getText(),button.getText()});
    }
    
    
    private void addCatagoryButton(Button bt){
        catagoryPanel.add(bt);
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

        editParentPanel = new com.raven.swing.PanelBorder();
        bAdd = new com.raven.swing.Button();
        jLabel3 = new javax.swing.JLabel();
        bEdit = new com.raven.swing.Button();
        bDelete = new com.raven.swing.Button();
        lbName = new javax.swing.JLabel();
        txtName = new com.raven.swing.TextField();
        lbId = new javax.swing.JLabel();
        txtId = new com.raven.swing.TextField();
        lbPrice = new javax.swing.JLabel();
        txtPrice = new com.raven.swing.TextField();
        txtCatagory = new com.raven.swing.TextField();
        lbCatagory = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbLog = new javax.swing.JTable();
        productViewParentPanel = new com.raven.swing.PanelBorder();
        scrollProduct = new javax.swing.JScrollPane();
        productView = new javax.swing.JPanel();
        scrollCatogory = new javax.swing.JScrollPane();
        catagoryPanel = new javax.swing.JPanel();

        editParentPanel.setBackground(new java.awt.Color(102, 102, 102));

        bAdd.setBackground(new java.awt.Color(0, 153, 153));
        bAdd.setForeground(new java.awt.Color(255, 255, 255));
        bAdd.setText("Add");
        bAdd.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        bAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAddActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Product Infromation ");

        bEdit.setBackground(new java.awt.Color(0, 153, 204));
        bEdit.setForeground(new java.awt.Color(255, 255, 255));
        bEdit.setText("Edit");
        bEdit.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        bEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEditActionPerformed(evt);
            }
        });

        bDelete.setBackground(new java.awt.Color(255, 102, 0));
        bDelete.setForeground(new java.awt.Color(255, 255, 255));
        bDelete.setText("Delete");
        bDelete.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        bDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDeleteActionPerformed(evt);
            }
        });

        lbName.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lbName.setText("Name:");

        txtName.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N

        lbId.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lbId.setText("ID:");

        txtId.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtId.setMaximumSize(new java.awt.Dimension(60, 35));
        txtId.setMinimumSize(new java.awt.Dimension(20, 35));
        txtId.setPreferredSize(new java.awt.Dimension(30, 35));

        lbPrice.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lbPrice.setText("Price:");

        txtPrice.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N

        txtCatagory.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N

        lbCatagory.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        lbCatagory.setText("Catagory:");

        jScrollPane1.setBorder(null);

        tbLog.setFont(new java.awt.Font("Montserrat", 1, 14)); // NOI18N
        tbLog.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Catagory", "Name", "Price", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tbLog);

        javax.swing.GroupLayout editParentPanelLayout = new javax.swing.GroupLayout(editParentPanel);
        editParentPanel.setLayout(editParentPanelLayout);
        editParentPanelLayout.setHorizontalGroup(
            editParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editParentPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editParentPanelLayout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(editParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, editParentPanelLayout.createSequentialGroup()
                        .addComponent(bAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(editParentPanelLayout.createSequentialGroup()
                        .addComponent(lbPrice)
                        .addGap(5, 5, 5)
                        .addComponent(txtPrice, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, editParentPanelLayout.createSequentialGroup()
                        .addComponent(lbCatagory)
                        .addGap(5, 5, 5)
                        .addComponent(txtCatagory, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(lbId, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtId, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, editParentPanelLayout.createSequentialGroup()
                        .addComponent(lbName, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(txtName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(editParentPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30))
        );
        editParentPanelLayout.setVerticalGroup(
            editParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editParentPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(editParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(editParentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGroup(editParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(editParentPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addGroup(editParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCatagory, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbCatagory)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editParentPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbId))))
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(editParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbName))
                .addGap(8, 8, 8)
                .addGroup(editParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPrice))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(editParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );

        productViewParentPanel.setBackground(new java.awt.Color(102, 102, 102));
        productViewParentPanel.setToolTipText("");
        productViewParentPanel.setPreferredSize(new java.awt.Dimension(400, 600));

        scrollProduct.setBorder(null);
        scrollProduct.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollProduct.setOpaque(false);
        scrollProduct.setPreferredSize(new java.awt.Dimension(100, 800));

        productView.setBackground(new java.awt.Color(243, 243, 243));
        productView.setPreferredSize(new java.awt.Dimension(100, 600));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 10, 10);
        flowLayout1.setAlignOnBaseline(true);
        productView.setLayout(flowLayout1);
        scrollProduct.setViewportView(productView);

        javax.swing.GroupLayout productViewParentPanelLayout = new javax.swing.GroupLayout(productViewParentPanel);
        productViewParentPanel.setLayout(productViewParentPanelLayout);
        productViewParentPanelLayout.setHorizontalGroup(
            productViewParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productViewParentPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(scrollProduct, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        productViewParentPanelLayout.setVerticalGroup(
            productViewParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productViewParentPanelLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(scrollProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        scrollCatogory.setBackground(new java.awt.Color(255, 255, 255));
        scrollCatogory.setBorder(null);
        scrollCatogory.setForeground(new java.awt.Color(102, 255, 255));
        scrollCatogory.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        scrollCatogory.setOpaque(false);
        scrollCatogory.setPreferredSize(new java.awt.Dimension(800, 100));

        catagoryPanel.setBackground(new java.awt.Color(255, 255, 255));
        catagoryPanel.setForeground(new java.awt.Color(153, 255, 255));
        catagoryPanel.setOpaque(false);
        catagoryPanel.setPreferredSize(new java.awt.Dimension(1300, 60));
        catagoryPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 10, 10));
        scrollCatogory.setViewportView(catagoryPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollCatogory, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                    .addComponent(productViewParentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addComponent(editParentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(scrollCatogory, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(productViewParentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(editParentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAddActionPerformed
        // TODO add your handling code here:
        updateTabble(bAdd);
        
        
        
    }//GEN-LAST:event_bAddActionPerformed

    private void bEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEditActionPerformed
        // TODO add your handling code here:
                updateTabble(bEdit);

    }//GEN-LAST:event_bEditActionPerformed

    private void bDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDeleteActionPerformed
        // TODO add your handling code here:
                updateTabble(bDelete);

    }//GEN-LAST:event_bDeleteActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Button bAdd;
    private com.raven.swing.Button bDelete;
    private com.raven.swing.Button bEdit;
    private javax.swing.JPanel catagoryPanel;
    private com.raven.swing.PanelBorder editParentPanel;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbCatagory;
    private javax.swing.JLabel lbId;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbPrice;
    private javax.swing.JPanel productView;
    private com.raven.swing.PanelBorder productViewParentPanel;
    private javax.swing.JScrollPane scrollCatogory;
    private javax.swing.JScrollPane scrollProduct;
    private javax.swing.JTable tbLog;
    private com.raven.swing.TextField txtCatagory;
    private com.raven.swing.TextField txtId;
    private com.raven.swing.TextField txtName;
    private com.raven.swing.TextField txtPrice;
    // End of variables declaration//GEN-END:variables
}
