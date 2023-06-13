
package com.raven.event;

import com.raven.model.Product;

/**
 *
 * @author dothinhtpr247gmai.com
 */
public interface EventBillRow {
    public void increase(Product product);
    public void delete(Product product, int amount);
    public void decrease(Product product);
    
}
