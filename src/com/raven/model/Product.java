/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

import com.raven.swing.table.ModelActionProduct;
import java.text.DecimalFormat;
import com.raven.swing.table.EventActionProduct;

/**
 *
 * @author dothinhtpr247gmai.com
 */
public class Product {
    private int id;
    private String name;
    private int idCategory;
    private int price;

    public Product(int id, String name, int idCategory, int price) {
        this.id = id;
        this.name = name;
        this.idCategory = idCategory;
        this.price = price;
    }

    public Product() {
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    public Object[] toRowTable(EventActionProduct event) {
        DecimalFormat df = new DecimalFormat("#,##0 VND");
        return new Object[]{id, name, idCategory, df.format(price), new ModelActionProduct(this, event) };
    }
    
}
