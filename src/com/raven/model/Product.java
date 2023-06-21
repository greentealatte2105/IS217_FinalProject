
package com.raven.model;

import com.raven.dao.ProductDAO;
import java.text.DecimalFormat;


/**
 *
 * @author dothinhtpr247gmai.com
 */
public class Product {
    
    private int id;
    private String name;
    private int idCategory;
    
    public Product(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    
    public Product(String name, int idCategory, int price) {
        this.name = name;
        this.idCategory = idCategory;
        this.price = price;
    }
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
    
    
}