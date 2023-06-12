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
import java.util.ArrayList;
import java.util.List;
import raven.chart.ModelChart;

public class ReportForm extends javax.swing.JPanel {
    public ReportForm() {
        initComponents();
        chartRevenue.setTitle("Thống kê doanh thu");
        chartRevenue.addLegend("Tháng", Color.decode("#1B5461"), Color.decode("#c0e5b1"));
        chartProduct.setTitle("Thống kê số sản phẩn");
        chartProduct.addLegend("Tháng", Color.decode("#7b4397"), Color.decode("#dc2430"));
//        test();
        setData();
    }
    
    
    private void setData() {
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
                chartRevenue.addData(new ModelChart(d.getMonth(), new double[]{d.getRevenue()}));
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

        javax.swing.GroupLayout orderBillParentPanelLayout = new javax.swing.GroupLayout(orderBillParentPanel);
        orderBillParentPanel.setLayout(orderBillParentPanelLayout);
        orderBillParentPanelLayout.setHorizontalGroup(
            orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                .addComponent(panelShadow1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        orderBillParentPanelLayout.setVerticalGroup(
            orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(orderBillParentPanelLayout.createSequentialGroup()
                .addGap(62, 62, 62)
                .addGroup(orderBillParentPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelShadow2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(272, Short.MAX_VALUE))
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
                .addGap(0, 0, 0)
                .addComponent(orderBillParentPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.chart.CurveLineChart chartProduct;
    private raven.chart.CurveLineChart chartRevenue;
    private com.raven.swing.PanelBorder orderBillParentPanel;
    private raven.panel.PanelShadow panelShadow1;
    private raven.panel.PanelShadow panelShadow2;
    // End of variables declaration//GEN-END:variables
}
