package com.raven.form;


import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.raven.component.Card;
import com.raven.component.billInfoRow;
import com.raven.dao.BillDAO;
import com.raven.dao.ConnectionProvider;
import com.raven.dao.CustomerDAO;
import com.raven.dao.ProductCategoryDAO;
import com.raven.dao.ProductDAO;
import com.raven.event.EventBillRow;
import com.raven.event.EventCard;
import com.raven.model.Product;
import com.raven.model.ProductCategory;
import com.raven.swing.Button;
import com.raven.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.awt.Dimension;

public class OrderForm extends javax.swing.JPanel {
    private int total;
    private Button currentCatagoryButton = null;
    private final Color defaultColor = new Color(0,0,0,150);
    private final Color choiceColor = new Color(0,0,0,100);
    private int discount = 0;
    private final DecimalFormat df = new DecimalFormat("#,###,###");
    private int idCustomer = -1;
    private final TreeMap<String, billInfoRow> billRestore ;
    public OrderForm() {
        initComponents();
       
        setOpaque(false);
        scrollBill.setVerticalScrollBar(new ScrollBarCustom());
        scrollProduct.setVerticalScrollBar(new ScrollBarCustom());
        scrollCatogory.setHorizontalScrollBar(new ScrollBarCustom());
        
        initCatagory();
        billRestore = new TreeMap<>();
//        cbOption.removeAllItems();
//        cbOption.addItem("Trending");
        cbOption.addItem("Increase");
        cbOption.addItem("Descrease");
        

    }
    private void initCatagory(){
        ArrayList<ProductCategory> list = ProductCategoryDAO.getAllRecords();
        catagoryPanel.setPreferredSize(new Dimension(list.size() * 110 + 10,catagoryPanel.getHeight()));

        System.out.println(list.size());
        Iterator<ProductCategory> itr = list.iterator();
        while (itr.hasNext()) {
            ProductCategory productCategoryObj = itr.next();
            catagoryPanel.add( createCatagroryButton(productCategoryObj) );
        }            
        Button initProducts = (Button) catagoryPanel.getComponent(0);
        currentCatagoryButton = initProducts;
        currentCatagoryButton.setBackground(choiceColor);
        getProduct(list.get(0).getId());
       
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
                
                productView.removeAll();
                productView.repaint();
                productView.revalidate();
                getProduct(productCategory.getId());
                
            }});
        return bt;
    }
    
    public void getProduct(int idCatagory){
        int choice = cbOption.getSelectedIndex();
        ArrayList<Product> list = ProductDAO.getRecordsByIdCategory(idCatagory);
        Iterator<Product> itr = list.iterator();
        DecimalFormat df = new DecimalFormat("#,###,###");
        
         EventBillRow evtBill = new EventBillRow() {
            @Override
            public void increase(Product product) {
                
//                discount = Integer.valueOf(txtDiscount.getText());
                total = Integer.parseInt(lbTotalView.getText().replaceAll("[,\\.]", ""));
                total = total * 100 / (100 - discount);
                total = total + product.getPrice();
                total = total * (100 - discount) / 100;
                lbTotalView.setText(df.format(total));
           }

            @Override
            public void delete(Product product, int amount) {
//              discount = Integer.valueOf(txtDiscount.getText());
                total = Integer.parseInt(lbTotalView.getText().replaceAll("[,\\.]", ""));
                total = total * 100 / (100 - discount);
                total = total - amount;
                total = total * (100 - discount) / 100;
                
                if (total <= 0)
                    lbTotalView.setText("0");
                else  lbTotalView.setText(df.format(total));
            }

            @Override
            public void decrease(Product product) {
//                discount = Integer.valueOf(txtDiscount.getText());
                total = Integer.parseInt(lbTotalView.getText().replaceAll("[,\\.]", ""));
                total = total * 100 / (100 - discount);
                total = total - product.getPrice();
                total = total * (100 - discount) / 100;
                
                if (total <= 0)
                    lbTotalView.setText("0");
                else  lbTotalView.setText(df.format(total));
            }
        };
         
        EventCard evt = new EventCard() {
            @Override
            public void update(Product product) {
               

               int discount = Integer.valueOf(txtDiscount.getText());
               total = Integer.parseInt(lbTotalView.getText().replaceAll("[,\\.]", "")) + product.getPrice() * (100 - discount)/100;;
//               total = total * (100 - discount)/100;
               lbTotalView.setText(df.format(total));
               if (billRestore.get(product.getName()) == null)
               {
               billInfoRow row = new billInfoRow(product,evtBill);
               billPanel.add(row);
               billPanel.repaint();
               billPanel.revalidate();
               billRestore.put(product.getName(), row);
               }
               else{
                   billInfoRow row = (billInfoRow) billRestore.get(product.getName());
                   row.increaseQuantity(1);
               }
            }
        };
        productView.setPreferredSize(new Dimension(productView.getWidth(), list.size() * 290 /2 ));
        while (itr.hasNext()) {
            Product productObj = itr.next();
            
            Product product = new Product(productObj.getId(), productObj.getName(), productObj.getPrice()); 
            String path = "/com/raven/images/" + String.valueOf(idCatagory)+"/"+String.valueOf(productObj.getId())+".jpg";
      
            addProduct(new Card(product, evt,path));
        }
    }
    
   private void addProduct(Card card){
       productView.add(card);
  }
    public void setProducts(ArrayList<Product> products){
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
        txtCustomer = new com.raven.swing.TextField();
        txtRank = new com.raven.swing.TextField();
        txtDiscount = new com.raven.swing.TextField();
        productViewParentPanel = new com.raven.swing.PanelBorder();
        scrollProduct = new javax.swing.JScrollPane();
        productView = new javax.swing.JPanel();
        scrollCatogory = new javax.swing.JScrollPane();
        catagoryPanel = new javax.swing.JPanel();
        cbOption = new javax.swing.JComboBox<>();
        txtSearch = new com.raven.swing.TextField();
        cmdMenu1 = new com.raven.swing.Button();

        setBackground(new java.awt.Color(255, 255, 255));

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

        lbTotalView.setBackground(new java.awt.Color(102, 102, 102));
        lbTotalView.setFont(new java.awt.Font("Montserrat", 0, 18)); // NOI18N
        lbTotalView.setForeground(new java.awt.Color(255, 255, 255));
        lbTotalView.setText("0");
        lbTotalView.setAlignmentX(0.5F);
        lbTotalView.setOpaque(true);
        lbTotalView.setPreferredSize(new java.awt.Dimension(50, 30));

        jLabel2.setBackground(new java.awt.Color(102, 102, 102));
        jLabel2.setFont(new java.awt.Font("Montserrat", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Total: ");
        jLabel2.setOpaque(true);

        jLabel3.setFont(new java.awt.Font("Montserrat", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Receipt");

        scrollBill.setBorder(null);
        scrollBill.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        billPanel.setBackground(new java.awt.Color(255, 255, 255));
        billPanel.setPreferredSize(new java.awt.Dimension(400, 800));
        billPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 0));
        scrollBill.setViewportView(billPanel);

        txtCustomer.setBorder(null);
        txtCustomer.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtCustomer.setHint("Customer");
        txtCustomer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCustomerActionPerformed(evt);
            }
        });
        txtCustomer.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCustomerKeyReleased(evt);
            }
        });

        txtRank.setBorder(null);
        txtRank.setEnabled(false);
        txtRank.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtRank.setHint("Rank");
        txtRank.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtRankActionPerformed(evt);
            }
        });

        txtDiscount.setBorder(null);
        txtDiscount.setText("0");
        txtDiscount.setEnabled(false);
        txtDiscount.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtDiscount.setHint("Discount");
        txtDiscount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiscountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout orderBillParentPanelLayout = new javax.swing.GroupLayout(orderBillParentPanel);
        orderBillParentPanel.setLayout(orderBillParentPanelLayout);
        orderBillParentPanelLayout.setHorizontalGroup(
            orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                        .addComponent(bPrintBill, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbTotalView, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(txtCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtRank, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
            .addComponent(scrollBill, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        orderBillParentPanelLayout.setVerticalGroup(
            orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel3)
                .addGap(30, 30, 30)
                .addGroup(orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRank, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiscount, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scrollBill, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                        .addComponent(bPrintBill, javax.swing.GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE)
                        .addGap(20, 20, 20))
                    .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                        .addGroup(orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbTotalView, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        productViewParentPanel.setBackground(new java.awt.Color(102, 102, 102));
        productViewParentPanel.setToolTipText("");
        productViewParentPanel.setPreferredSize(new java.awt.Dimension(400, 600));

        scrollProduct.setBorder(null);
        scrollProduct.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollProduct.setOpaque(false);
        scrollProduct.setPreferredSize(new java.awt.Dimension(100, 800));

        productView.setBackground(new java.awt.Color(255, 255, 255));
        productView.setPreferredSize(new java.awt.Dimension(100, 600));
        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 20, 20);
        flowLayout1.setAlignOnBaseline(true);
        productView.setLayout(flowLayout1);
        scrollProduct.setViewportView(productView);

        javax.swing.GroupLayout productViewParentPanelLayout = new javax.swing.GroupLayout(productViewParentPanel);
        productViewParentPanel.setLayout(productViewParentPanelLayout);
        productViewParentPanelLayout.setHorizontalGroup(
            productViewParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productViewParentPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(scrollProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        productViewParentPanelLayout.setVerticalGroup(
            productViewParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, productViewParentPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(scrollProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
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
        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 10, 10);
        flowLayout2.setAlignOnBaseline(true);
        catagoryPanel.setLayout(flowLayout2);
        scrollCatogory.setViewportView(catagoryPanel);

        cbOption.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Trending", "New", "Increase", "Decrease", " " }));
        cbOption.setBorder(null);
        cbOption.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbOptionActionPerformed(evt);
            }
        });

        txtSearch.setBackground(new java.awt.Color(204, 204, 204));
        txtSearch.setForeground(new java.awt.Color(153, 153, 153));
        txtSearch.setFont(new java.awt.Font("Montserrat", 0, 14)); // NOI18N
        txtSearch.setHint("Search...");
        txtSearch.setUnderlineColor(new java.awt.Color(153, 153, 153));
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        cmdMenu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/search.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbOption, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(productViewParentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE))
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtSearch, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(scrollCatogory, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(cmdMenu1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)))
                .addComponent(orderBillParentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
                    .addComponent(cmdMenu1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20)
                .addComponent(scrollCatogory, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(cbOption, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(productViewParentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
                .addGap(2, 2, 2))
            .addComponent(orderBillParentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    private void exportBillPdf() {
//        String filePath = getClass().getResource("filename").getPath();
//        System.out.println(filePath + "s##########################################");
        if (billPanel.getComponentCount() > 0){
            String name;
            String id;
            String amount;
            String quantity;
            String price;
            String total = lbTotalView.getText();
            SimpleDateFormat dFormat = new SimpleDateFormat("dd-MM-yyyy");
            
        String path = "";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(this);
        
        if (x == JFileChooser.APPROVE_OPTION){
            path = j.getSelectedFile().getPath();
        }
        
//        com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
//        PdfWriter.getInstance(doc, new FileOutputStream(path + "bill.pdf"));
//            String path = "C:\\Users\\Dell\\Desktop\\";
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String formattedDate = currentDate.format(formatter);
        int idBill = BillDAO.getLastestBillId();
            try{
                Document doc = new Document();
                PdfWriter.getInstance(doc, new FileOutputStream(path + "/bill" + idBill + ".pdf"));
                doc.open();
                Paragraph cafeName = new Paragraph("                                                                 Home Cafe\n");
                doc.add(cafeName);
                Paragraph starLine = new Paragraph("****************************************************************************************************************");
                
                doc.add(starLine);
                doc.add(new Paragraph("Date: " + formattedDate));
                doc.add(new Paragraph("\nCustomer Phone: "+txtCustomer.getText()));
                MainForm parent = (MainForm) getParent();
        
                doc.add(new Paragraph("\nStaff: " + parent.getUser().getUserName()) );
                
                doc.add(starLine);
                PdfPTable tb1 = new PdfPTable(4);
                tb1.addCell("Name");
                tb1.addCell("Price");
                tb1.addCell("Quantity");
                tb1.addCell("Total");
                for(int i=0; i < billPanel.getComponentCount();i++){
                    billInfoRow billinfo = (billInfoRow) billPanel.getComponent(i);
                    name = String.valueOf(billinfo.getNameProduct());
                    System.out.println(name);
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
                Paragraph paragraphDiscount = new Paragraph("\nDiscount: "+ txtDiscount.getText() + "%");
                Paragraph paragraph3 = new Paragraph("Total Paid: "+lbTotalView.getText());
                doc.add(paragraphDiscount);
                doc.add(paragraph3);
                doc.add(starLine);
                Paragraph thanksMsg = new Paragraph("Thank You,Please Visit Again");
                doc.add(thanksMsg);
                doc.close();
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null,e);
            }
            
        }
    }
    // update total customer, if customer is new, add new customer into database
    private void updateCustomerTotal(int idCustomer, int idBill){
        // create new customer
        if (idCustomer == -1) {
            try {
                String query = "CALL USP_AddCustomer(1, ?, 0);";
                PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
                stmt.setString(1, txtCustomer.getText());
                stmt.execute();
                // get the id of the recently added customer
                idCustomer = CustomerDAO.getLastestId();
                JOptionPane.showMessageDialog(null, "New customer added into database");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // update total and rank of customer
        try {
                String query = "CALL USP_UpdateCustomerTotal(?, ?);";
                PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
                stmt.setInt(1, idCustomer);
                stmt.setInt(2, idBill);
                stmt.execute();
                
                query = "CALL USP_UpdateCustomerRank;";
                stmt = ConnectionProvider.getCon().prepareStatement(query);
                stmt.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        
        // thêm data vào bill_customer
        try {
                String query = "CALL USP_AddBill_Customer(?, ?);";
                PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
                stmt.setInt(1, idBill);
                stmt.setInt(2, idCustomer);
                stmt.execute();
                // get the id of the recently added customer
//                idCustomer = CustomerDAO.getLastestId();
        } catch (Exception e) {
            e.printStackTrace();
        }
        

    }
    public void test(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Bill");
        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try {
                FileWriter writer = new FileWriter(fileToSave);
                writer.write("Customer Phone: " + txtCustomer.getText() + "\n");
                writer.write("Product Name: " + "tra" + "\n");
                writer.write("Price: " + "10000" + "\n");
                writer.write("Quantity: " + "1" + "\n");
                writer.write("Amount: " + "10000" + "\n");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
}
    }
    
     private void clearBill(){
        billPanel.removeAll();
        billPanel.repaint();
        billPanel.revalidate();
        lbTotalView.setText("0");
        txtCustomer.setText("");
        txtRank.setText("");
        txtDiscount.setText("0");
        this.total = 0;
        billRestore.clear();
     }
       
    
    private void bPrintBillActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bPrintBillActionPerformed
        // TODO add your handling code here:
        // create a new bill

        MainForm parent = (MainForm) getParent();
        int idUser = parent.getUser().getId();
        try {
            String query = "CALL USP_AddBill(?, CURDATE(), ?);";
            PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
            
            stmt.setInt(1, idUser);
            stmt.setInt(2, Integer.parseInt(txtDiscount.getText()));
            stmt.execute();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        // get id of new bill
        int idBill = BillDAO.getLastestBillId();
//        JOptionPane.showMessageDialog(null, idBill);
        
        for(int i=0; i < billPanel.getComponentCount();i++){
            billInfoRow billinfo = (billInfoRow) billPanel.getComponent(i);
            int idProduct = billinfo.getIdProduct();
            int count = billinfo.getQuantity();
            
            try {
                String query = "CALL USP_AddBillInfo(?, ?, ?);";
                PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
                stmt.setInt(1, idBill);
                stmt.setInt(2,idProduct);
                stmt.setInt(3, count);
                stmt.execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        

        // tính tiền mọi bill
        try {
                String query = "CALL USP_CalculateBill(?);";
                PreparedStatement stmt = ConnectionProvider.getCon().prepareStatement(query);
                stmt.setInt(1, idBill);
                stmt.execute();
                JOptionPane.showMessageDialog(null, "Bill created");
            } catch (Exception e) {
                e.printStackTrace();
            }
        
        // cập nhật tổng tiền đã chi của customer và thêm data vào bill_customer
        updateCustomerTotal(idCustomer, idBill);
        exportBillPdf();
        clearBill();
              
    }//GEN-LAST:event_bPrintBillActionPerformed

    private void txtCustomerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCustomerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCustomerActionPerformed

    private void txtRankActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtRankActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtRankActionPerformed

    private void txtDiscountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiscountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiscountActionPerformed

    // check the phone number then return rank and discount
    private void txtCustomerKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCustomerKeyReleased
        try {
            String phoneNumber = txtCustomer.getText();
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            
            String query = "SELECT c.id as idCustomer, cc.name as `Rank`, cc.discount as Discount FROM Customer c " +
                        "JOIN CustomerCategory cc ON c.idRank = cc.id WHERE c.phoneNumber = " + phoneNumber + ";";
            
            ResultSet rs = st.executeQuery(query);
            // có customer trong database
            if (rs.next()) {
                this.idCustomer = rs.getInt("idCustomer");
                String rank = rs.getString("Rank");
                
                 int total = Integer.parseInt(lbTotalView.getText().replaceAll("[,\\.]", "")) ;
               total = total * 100 / ( 100 - discount);
               discount = rs.getInt("Discount");
               total = total * (100 - discount)/100;
               lbTotalView.setText(df.format(total));
               
               txtRank.setText(rank);
               txtDiscount.setText(String.valueOf(discount));
            } 
            else {
            // When no matching customer is found, so we will create a new customer
                total = total * 100 / (100 - discount);
                
                this.idCustomer = -1;
                txtRank.setText("None");
                txtDiscount.setText("0");
                discount = 0;
            }
        } catch (Exception e) {
//            txtRank.setText("None");
//            txtDiscount.setText("0");
            this.idCustomer = -1;
            e.printStackTrace();
        }
    }//GEN-LAST:event_txtCustomerKeyReleased
  
    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtSearchActionPerformed

    private void cbOptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbOptionActionPerformed
        // TODO add your handling code here:
        System.out.println(cbOption.getSelectedItem().toString());
    }//GEN-LAST:event_cbOptionActionPerformed

    // tìm kiếm live (không cần nhấn enter, nhập tới đâu tìm tới đó)
    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
    }//GEN-LAST:event_txtSearchKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.Button bPrintBill;
    private javax.swing.JPanel billPanel;
    private javax.swing.JPanel catagoryPanel;
    private javax.swing.JComboBox<String> cbOption;
    private com.raven.swing.Button cmdMenu1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lbTotalView;
    private com.raven.swing.PanelBorder orderBillParentPanel;
    private javax.swing.JPanel productView;
    private com.raven.swing.PanelBorder productViewParentPanel;
    private javax.swing.JScrollPane scrollBill;
    private javax.swing.JScrollPane scrollCatogory;
    private javax.swing.JScrollPane scrollProduct;
    private com.raven.swing.TextField txtCustomer;
    private com.raven.swing.TextField txtDiscount;
    private com.raven.swing.TextField txtRank;
    private com.raven.swing.TextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
