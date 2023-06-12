package com.raven.form;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.raven.component.Card;
import com.raven.component.billInfoRow;
import com.raven.dao.ProductCategoryDAO;
import com.raven.dao.ProductDAO;
import com.raven.dialog.Message;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class OrderForm extends javax.swing.JPanel {
    private ArrayList<Product> products;
    private int idTabe;
    private int total;
    private Button currentCatagoryButton = null;
    private Color defaultColor = new Color(0,0,0,150);
    private Color choiceColor = new Color(0,0,0,100);

    public OrderForm() {
        initComponents();
        setOpaque(false);
        scrollBill.setVerticalScrollBar(new ScrollBarCustom());
        scrollProduct.setVerticalScrollBar(new ScrollBarCustom());
        scrollCatogory.setHorizontalScrollBar(new ScrollBarCustom());
        initBillInfo();
        initCatagory();
    }
    private void initCatagory(){
        ArrayList<ProductCategory> list = ProductCategoryDAO.getAllRecords();
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
                
    private void initBillInfo() {
        
//        addBillRow(new billInfoRow(new Product(1,"Tra vvvvvsua", 200000)));
//        addBillRow(new billInfoRow(new Product(4,"Tra sua", 200000)));
//        addBillRow(new billInfoRow(new Product(1,"Tra vvvsua", 200000)));
//        addBillRow(new billInfoRow(new Product(4,"Tra sua", 200000)));
//        addBillRow(new billInfoRow(new Product(1,"Tra vvvsua", 200000)));
//        addBillRow(new billInfoRow(new Product(4,"Tra sua", 200000)));
        
    }
    
    private void initProductCard(ArrayList<Product> listProduct){
        Iterator<Product> itr = listProduct.iterator();
        while (itr.hasNext()) {
            Product productObj = itr.next();
            addProduct(new Card(new Product(productObj.getId(), productObj.getName(), productObj.getPrice()), billPanel, lbTotalView));
        }
    }
    
    private void addBillRow(billInfoRow row){
        billPanel.add(row);
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

        orderBillParentPanel = new com.raven.swing.PanelBorder();
        bPrintBill = new com.raven.swing.Button();
        lbTotalView = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        scrollBill = new javax.swing.JScrollPane();
        billPanel = new javax.swing.JPanel();
        productViewParentPanel = new com.raven.swing.PanelBorder();
        scrollProduct = new javax.swing.JScrollPane();
        productView = new javax.swing.JPanel();
        scrollCatogory = new javax.swing.JScrollPane();
        catagoryPanel = new javax.swing.JPanel();

        orderBillParentPanel.setBackground(new java.awt.Color(102, 102, 102));

        bPrintBill.setBackground(new java.awt.Color(0, 153, 153));
        bPrintBill.setForeground(new java.awt.Color(255, 255, 255));
        bPrintBill.setText("Print");
        bPrintBill.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        bPrintBill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bPrintBillActionPerformed(evt);
            }
        });

        lbTotalView.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        lbTotalView.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbTotalView.setText("000000000");

        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel2.setText("Total");

        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Receipt");

        scrollBill.setBorder(null);
        scrollBill.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        billPanel.setBackground(new java.awt.Color(255, 255, 255));
        billPanel.setPreferredSize(new java.awt.Dimension(400, 800));
        billPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        scrollBill.setViewportView(billPanel);

        javax.swing.GroupLayout orderBillParentPanelLayout = new javax.swing.GroupLayout(orderBillParentPanel);
        orderBillParentPanel.setLayout(orderBillParentPanelLayout);
        orderBillParentPanelLayout.setHorizontalGroup(
            orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(scrollBill, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                        .addComponent(bPrintBill, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbTotalView, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(259, 259, 259)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        orderBillParentPanelLayout.setVerticalGroup(
            orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel3)
                .addGap(138, 138, 138)
                .addComponent(scrollBill, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bPrintBill, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(lbTotalView))
                .addGap(10, 10, 10))
        );

        productViewParentPanel.setBackground(new java.awt.Color(102, 102, 102));
        productViewParentPanel.setToolTipText("");
        productViewParentPanel.setPreferredSize(new java.awt.Dimension(400, 600));

        scrollProduct.setBorder(null);
        scrollProduct.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollProduct.setOpaque(false);
        scrollProduct.setPreferredSize(new java.awt.Dimension(100, 800));

        productView.setBackground(new java.awt.Color(243, 243, 243));
        productView.setPreferredSize(new java.awt.Dimension(100, 800));
        productView.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 10, 10));
        scrollProduct.setViewportView(productView);

        javax.swing.GroupLayout productViewParentPanelLayout = new javax.swing.GroupLayout(productViewParentPanel);
        productViewParentPanel.setLayout(productViewParentPanelLayout);
        productViewParentPanelLayout.setHorizontalGroup(
            productViewParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productViewParentPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(scrollProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 659, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );
        productViewParentPanelLayout.setVerticalGroup(
            productViewParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productViewParentPanelLayout.createSequentialGroup()
                .addContainerGap(64, Short.MAX_VALUE)
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
        catagoryPanel.setPreferredSize(new java.awt.Dimension(1200, 100));
        catagoryPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 10, 10));
        scrollCatogory.setViewportView(catagoryPanel);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(productViewParentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(scrollCatogory, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(orderBillParentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(orderBillParentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(scrollCatogory, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(productViewParentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bPrintBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPrintBillActionPerformed
        // TODO add your handling code here:
        if (billPanel.getComponentCount() > 0){
            String name;
            String id;
            String amount;
            String quantity;
            String price;
            String total = lbTotalView.getText();
            SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");

            com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
            try{
                PdfWriter.getInstance(doc, new FileOutputStream("/Users/dothinhtpr247gmai.com/Desktop/test/IS217_FinalProject/src/com/raven/receiptPdf/invoice.pdf"));
                doc.open();
                Paragraph cafeName = new Paragraph("                                                                 Mood Lift Cafe\n");
                doc.add(cafeName);
                Paragraph starLine = new Paragraph("****************************************************************************************************************");
                doc.add(starLine);

//                Paragraph customer = new Paragraph("\nCustomer Phone: "+txtCustomerPhone);
//                doc.add(customer);
                doc.add(starLine);
                PdfPTable tb1 = new PdfPTable(4);
                tb1.addCell("Name");
                tb1.addCell("Price");
                tb1.addCell("Quantity");
                tb1.addCell("Total");
                for(int i=0; i < billPanel.getComponentCount();i++){
                    billInfoRow billinfo = (billInfoRow) billPanel.getComponent(i);
                    name = billinfo.getName();
    //                id = String.valueOf(billinfo.getIdProduct());
                    price = String.valueOf(billinfo.getPrice());
                    amount = String.valueOf(billinfo.getAmount());
                    quantity = String.valueOf(billinfo.getQuantity());
                    tb1.addCell(name);
                    tb1.addCell(price);
                    tb1.addCell("x"+quantity);
                    tb1.addCell(amount);
                }
                doc.add(tb1);
                Paragraph paragraph3 = new Paragraph("\nTotal Paid: "+lbTotalView);
                doc.add(paragraph3);
                doc.add(starLine);
                Paragraph thanksMsg = new Paragraph("Thank You,Please Visit Again");
                doc.add(thanksMsg);
                
                 }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
        }
        
    }//GEN-LAST:event_bPrintBillActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Button bPrintBill;
    private javax.swing.JPanel billPanel;
    private javax.swing.JPanel catagoryPanel;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbTotalView;
    private com.raven.swing.PanelBorder orderBillParentPanel;
    private javax.swing.JPanel productView;
    private com.raven.swing.PanelBorder productViewParentPanel;
    private javax.swing.JScrollPane scrollBill;
    private javax.swing.JScrollPane scrollCatogory;
    private javax.swing.JScrollPane scrollProduct;
    // End of variables declaration//GEN-END:variables
}
