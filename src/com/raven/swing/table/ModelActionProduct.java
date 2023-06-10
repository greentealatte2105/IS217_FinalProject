package com.raven.swing.table;

import com.raven.model.ModelStudent;
import com.raven.model.Product;

public class ModelActionProduct {

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public EventActionProduct getEvent() {
        return event;
    }

    public void setEvent(EventActionProduct event) {
        this.event = event;
    }

    public ModelActionProduct(Product product, EventActionProduct event) {
        this.product = product;
        this.event = event;
    }

    public ModelActionProduct() {
    }

    private Product product;
    private EventActionProduct event;
}
