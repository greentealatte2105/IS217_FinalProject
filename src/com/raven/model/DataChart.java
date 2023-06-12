package com.raven.model;

public class DataChart {
    public DataChart(String month, double amount, int product) {
        this.month = month;
        this.revenue = amount;
//        this.profit = cost;
        this.products = product ;
    }

    public DataChart() {
    }
    
    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public double getRevenue() {
        return revenue;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public double getProducts() {
        return products;
    }

    public void setProducts(double products) {
        this.products = products;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }


    private String month;
    private double revenue;
    private double products;
    private double profit;
}
