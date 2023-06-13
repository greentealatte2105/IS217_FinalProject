package com.raven.form;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.raven.component.Card;
import com.raven.component.billInfoRow;
import com.raven.dao.ConnectionProvider;
import com.raven.dao.ProductCategoryDAO;
import com.raven.dao.ProductDAO;
import com.raven.dialog.Message;
import com.raven.main.Main;
import com.raven.model.DataChart;
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
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import raven.chart.CurveLineChart;
import raven.chart.ModelChart;

public class ReportForm extends javax.swing.JPanel {
    public ReportForm() {
        initComponents();
        chartRevenue.setTitle("Thống kê doanh thu");
        chartRevenue.addLegend("Doanh thu", Color.decode("#1B5461"), Color.decode("#c0e5b1"));
        
        chartProduct.setTitle("Thống kê số sản phẩn");
        chartProduct.addLegend("Sản phẩm", Color.decode("#7b4397"), Color.decode("#dc2430"));
//        test();
        setDataByQuarter();
    }
    
    private void setDataByQuarter() {
        try 
        {
            Connection con = ConnectionProvider.getCon();
            
            List<DataChart> listRevenue = new ArrayList<>();
            List<DataChart> listProduct = new ArrayList<>();
            
            // get data for revenue
            String sql = "SELECT QUARTER(b.dateCheckIn) AS Quarter, SUM(b.totalPrice) AS Revenue " +
                        "FROM Bill b JOIN BillInfo bi ON b.id = bi.idBill GROUP BY QUARTER(b.dateCheckIn) ORDER BY b.dateCheckIn DESC;";
            PreparedStatement p = con.prepareStatement(sql);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                String month = r.getString("Quarter");
                double revenue = r.getDouble("Revenue");
                listRevenue.add(new DataChart(month, revenue, 0));
            }
            r.close();
            p.close();
            //  Add Data to chart
            for (int i = listRevenue.size() - 1; i >= 0; i--) {
                DataChart d = listRevenue.get(i);
                chartRevenue.addData(new ModelChart(d.getMonth(), new double[]{d.getRevenue(), d.getProducts()}));
            }
            chartRevenue.start();
            
            // get data for product
            sql = "SELECT QUARTER(b.dateCheckIn) AS Quarter, SUM(bi.count) AS Products " +
                "FROM Bill b JOIN BillInfo bi ON b.id = bi.idBill GROUP BY QUARTER(b.dateCheckIn) ORDER BY b.dateCheckIn DESC;";
            p = con.prepareStatement(sql);
            r = p.executeQuery();
            while (r.next()) {
                String month = r.getString("Quarter");
                int products = r.getInt("Products");
                listProduct.add(new DataChart(month, 0, products));
            }
            r.close();
            p.close();
            //  Add Data to chart
            for (int i = listProduct.size() - 1; i >= 0; i--) {
                DataChart d = listProduct.get(i);
                chartProduct.addData(new ModelChart(d.getMonth(), new double[]{d.getProducts()}));
            }
            chartProduct.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String numProduct = txtNumProducts.getText();
        // get data for table by current month
        try 
        {
            
            
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            String sql = "SELECT p.name as ProductName, SUM(count) as Quantity FROM BillInfo bi " +
                        "JOIN Product p ON bi.idProduct = p.id JOIN Bill b on b.id = bi.idBill " +
                        "WHERE QUARTER(b.dateCheckIn) = QUARTER(CURRENT_DATE()) GROUP BY bi.idProduct ORDER BY SUM(count) " +
                        "DESC limit " + numProduct + ";";
            
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                String productName = rs.getString("ProductName");
                int quantity = rs.getInt("Quantity");
                
                Object[] row = {productName, quantity};
                
                DefaultTableModel tblBest = (DefaultTableModel)tableBestSelling.getModel();
                tblBest.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try 
        {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            String sql = "SELECT p.name as ProductName, SUM(count) as Quantity FROM BillInfo bi " +
                        "JOIN Product p ON bi.idProduct = p.id JOIN Bill b on b.id = bi.idBill " +
                        "WHERE QUARTER(b.dateCheckIn) = QUARTER(CURRENT_DATE()) GROUP BY bi.idProduct ORDER BY SUM(count) " +
                        "limit " + numProduct + ";";
            
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                String productName = rs.getString("ProductName");
                int quantity = rs.getInt("Quantity");
                
                Object[] row = {productName, quantity};
                
                DefaultTableModel tblWorst = (DefaultTableModel)tableWorstSelling.getModel();
                tblWorst.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    private void setDataByMonth() {
        try 
        {
            Connection con = ConnectionProvider.getCon();
            
            List<DataChart> listRevenue = new ArrayList<>();
            List<DataChart> listProduct = new ArrayList<>();
            
            // get data for revenue
            String sql = "select DATE_FORMAT(DateCheckIn,'%M') as `Month`, SUM(totalPrice) as Revenue "
                    + "from Bill JOIN BillInfo ON BillInfo.idBill = Bill.id group by DATE_FORMAT(DateCheckIn,'%M') order by DateCheckIn DESC limit 12;";
            PreparedStatement p = con.prepareStatement(sql);
            ResultSet r = p.executeQuery();
            while (r.next()) {
                String month = r.getString("Month");
                double revenue = r.getDouble("Revenue");
                listRevenue.add(new DataChart(month, revenue, 0));
            }
            r.close();
            p.close();
            //  Add Data to chart
            for (int i = listRevenue.size() - 1; i >= 0; i--) {
                DataChart d = listRevenue.get(i);
                chartRevenue.addData(new ModelChart(d.getMonth(), new double[]{d.getRevenue(), d.getProducts()}));
            }
            chartRevenue.start();
            
            // get data for product
            sql = "select DATE_FORMAT(DateCheckIn,'%M') as `Month`, SUM(BillInfo.count) AS Products "
                    + "from Bill JOIN BillInfo ON BillInfo.idBill = Bill.id group by DATE_FORMAT(DateCheckIn,'%M') order by DateCheckIn DESC limit 12;";
            p = con.prepareStatement(sql);
            r = p.executeQuery();
            while (r.next()) {
                String month = r.getString("Month");
                int products = r.getInt("Products");
                listProduct.add(new DataChart(month, 0, products));
            }
            r.close();
            p.close();
            //  Add Data to chart
            for (int i = listProduct.size() - 1; i >= 0; i--) {
                DataChart d = listProduct.get(i);
                chartProduct.addData(new ModelChart(d.getMonth(), new double[]{d.getProducts()}));
            }
            chartProduct.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        String numProduct = txtNumProducts.getText();
        // get data for table by month
        try 
        {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            String sql = "SELECT p.name as ProductName, SUM(count) as Quantity FROM BillInfo bi " +
                        "JOIN Product p ON bi.idProduct = p.id JOIN Bill b on b.id = bi.idBill " +
                        "WHERE MONTH(b.dateCheckIn) = MONTH(CURRENT_DATE()) GROUP BY bi.idProduct ORDER BY SUM(count) " +
                        "DESC limit " + numProduct + ";";
            
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                String productName = rs.getString("ProductName");
                int quantity = rs.getInt("Quantity");
                
                Object[] row = {productName, quantity};
                
                DefaultTableModel tblBest = (DefaultTableModel)tableBestSelling.getModel();
                tblBest.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        try 
        {
            Connection con = ConnectionProvider.getCon();
            Statement st = con.createStatement();
            String sql = "SELECT p.name as ProductName, SUM(count) as Quantity FROM BillInfo bi " +
                        "JOIN Product p ON bi.idProduct = p.id JOIN Bill b on b.id = bi.idBill " +
                        "WHERE MONTH(b.dateCheckIn) = MONTH(CURRENT_DATE()) GROUP BY bi.idProduct ORDER BY SUM(count) " +
                        "limit " + numProduct + ";";
            
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()){
                String productName = rs.getString("ProductName");
                int quantity = rs.getInt("Quantity");
                
                Object[] row = {productName, quantity};
                
                DefaultTableModel tblWorst = (DefaultTableModel)tableWorstSelling.getModel();
                tblWorst.addRow(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    // test without database
    private void test() {
        chartProduct.clear();
        chartProduct.addData(new ModelChart("January", new double[]{500, 50, 100}));
        chartProduct.addData(new ModelChart("February", new double[]{600, 300, 150}));
        chartProduct.addData(new ModelChart("March", new double[]{200, 50, 900}));
        chartProduct.addData(new ModelChart("April", new double[]{480, 700, 100}));
        chartProduct.addData(new ModelChart("May", new double[]{350, 540, 500}));
        chartProduct.addData(new ModelChart("June", new double[]{450, 800, 100}));
        chartProduct.start();
    }
  


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        orderBillParentPanel = new com.raven.swing.PanelBorder();
        panelShadow1 = new raven.panel.PanelShadow();
        chartProduct = new raven.chart.CurveLineChart();
        panelShadow2 = new raven.panel.PanelShadow();
        chartRevenue = new raven.chart.CurveLineChart();
        jLabel1 = new javax.swing.JLabel();
        bMonth = new com.raven.swing.ButtonBadges();
        bQuarter = new com.raven.swing.ButtonBadges();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBestSelling = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableWorstSelling = new javax.swing.JTable();
        bQuarter1 = new com.raven.swing.ButtonBadges();
        txtNumProducts = new javax.swing.JTextField();

        orderBillParentPanel.setBackground(new java.awt.Color(204, 204, 204));

        chartProduct.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartProduct, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelShadow1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        chartRevenue.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
        panelShadow2.setLayout(panelShadow2Layout);
        panelShadow2Layout.setHorizontalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartRevenue, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow2Layout.setVerticalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chartRevenue, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Bán nhiều nhất");

        bMonth.setBackground(new java.awt.Color(204, 255, 204));
        bMonth.setText("Tháng");
        bMonth.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bMonthActionPerformed(evt);
            }
        });

        bQuarter.setBackground(new java.awt.Color(102, 153, 255));
        bQuarter.setText("Quý");
        bQuarter.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bQuarter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bQuarterActionPerformed(evt);
            }
        });

        tableBestSelling.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên sản phẩm", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableBestSelling.setRowHeight(25);
        jScrollPane1.setViewportView(tableBestSelling);
        if (tableBestSelling.getColumnModel().getColumnCount() > 0) {
            tableBestSelling.getColumnModel().getColumn(0).setResizable(false);
            tableBestSelling.getColumnModel().getColumn(1).setResizable(false);
        }

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Thống kê theo :");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Bán ít nhất");

        tableWorstSelling.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên sản phẩm", "Số lượng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableWorstSelling.setRowHeight(25);
        jScrollPane2.setViewportView(tableWorstSelling);
        if (tableWorstSelling.getColumnModel().getColumnCount() > 0) {
            tableWorstSelling.getColumnModel().getColumn(0).setResizable(false);
            tableWorstSelling.getColumnModel().getColumn(1).setResizable(false);
        }

        bQuarter1.setBackground(new java.awt.Color(255, 255, 204));
        bQuarter1.setText("Số sản phẩm");
        bQuarter1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        bQuarter1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bQuarter1ActionPerformed(evt);
            }
        });

        txtNumProducts.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        txtNumProducts.setText("5");

        javax.swing.GroupLayout orderBillParentPanelLayout = new javax.swing.GroupLayout(orderBillParentPanel);
        orderBillParentPanel.setLayout(orderBillParentPanelLayout);
        orderBillParentPanelLayout.setHorizontalGroup(
            orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                .addGroup(orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(bQuarter, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                        .addGroup(orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                                .addGroup(orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtNumProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(bQuarter1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(72, 72, 72)
                                .addGroup(orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                        .addGroup(orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        orderBillParentPanelLayout.setVerticalGroup(
            orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bMonth, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bQuarter, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelShadow2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                        .addGroup(orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bQuarter1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(txtNumProducts, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(orderBillParentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(orderBillParentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bMonthActionPerformed
        // TODO add your handling code here:
        chartRevenue.clear();
        chartProduct.clear();
        DefaultTableModel tableBest = (DefaultTableModel) tableBestSelling.getModel();
        tableBest.setRowCount(0);
        DefaultTableModel tableWorst = (DefaultTableModel) tableWorstSelling.getModel();
        tableWorst.setRowCount(0);

        setDataByMonth();
    }//GEN-LAST:event_bMonthActionPerformed

    private void bQuarterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bQuarterActionPerformed
        // TODO add your handling code here:
        chartRevenue.clear();
        chartProduct.clear();
        
        DefaultTableModel tableBest = (DefaultTableModel) tableBestSelling.getModel();
        tableBest.setRowCount(0);
        DefaultTableModel tableWorst = (DefaultTableModel) tableWorstSelling.getModel();
        tableWorst.setRowCount(0);
        
        setDataByQuarter();
    }//GEN-LAST:event_bQuarterActionPerformed

    private void bQuarter1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bQuarter1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bQuarter1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.raven.swing.ButtonBadges bMonth;
    private com.raven.swing.ButtonBadges bQuarter;
    private com.raven.swing.ButtonBadges bQuarter1;
    private raven.chart.CurveLineChart chartProduct;
    private raven.chart.CurveLineChart chartRevenue;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.raven.swing.PanelBorder orderBillParentPanel;
    private raven.panel.PanelShadow panelShadow1;
    private raven.panel.PanelShadow panelShadow2;
    private javax.swing.JTable tableBestSelling;
    private javax.swing.JTable tableWorstSelling;
    private javax.swing.JTextField txtNumProducts;
    // End of variables declaration//GEN-END:variables
}
