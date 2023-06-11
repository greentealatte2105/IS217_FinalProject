/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.raven.model;

import java.text.DecimalFormat;
import com.raven.swing.table.EventActionProduct;

/**
 *
 * @author dothinhtpr247gmai.com
 */
public class ProductCategory {
    private int id;
    private String name;

    public ProductCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProductCategory() {
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

    
    public Object[] toRowTable(EventActionProduct event) {
        return new Object[]{id, name };
    }
    
}
