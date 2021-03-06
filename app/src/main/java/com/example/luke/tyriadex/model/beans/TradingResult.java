/**
 * File generated by Magnet rest2mobile 1.1 - 28/11/2017 4:10:12 PM
 * @see {@link http://developer.magnet.com}
 */

package com.example.luke.tyriadex.model.beans;


/**
 * Generated from json example
 {
 "id" : 1917768093,
 "item_id" : 19699,
 "price" : 174,
 "quantity" : 7,
 "created" : "2017-11-27T05:09:26+00:00"
 }

 */

public class TradingResult {

    public ItemByIdResult item;


    public String created;


    public Long id;


    public Integer item_id;


    public Integer price;


    public Integer quantity;

    public String purchased;

    public String getCreated() {
        return created;
    }
    public Long getId() {
        return id;
    }
    public Integer getItem_id() {
        return item_id;
    }
    public Integer getPrice() {
        return price;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public String getPurchased() { return purchased; }

    public ItemByIdResult getItem() {
        return item;
    }

    public void setItem(ItemByIdResult item) {
        this.item = item;
    }
}
