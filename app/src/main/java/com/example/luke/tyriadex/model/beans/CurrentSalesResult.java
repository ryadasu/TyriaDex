/**
 * File generated by Magnet rest2mobile 1.1 - 05/12/2017 4:34:52 PM
 * @see {@link http://developer.magnet.com}
 */

package com.example.luke.tyriadex.model.beans;


/**
 * Generated from json example
{
  "id" : 1887681967,
  "item_id" : 82866,
  "price" : 92,
  "quantity" : 53,
  "created" : "2017-10-14T07:51:56+00:00"
}

 */

public class CurrentSalesResult extends TradingResult{

  
//  private String created;
//
//
//  private Integer id;
//
//
//  private Integer item_id;
//
//
//  private Integer price;
//
//
//  private Integer quantity;
//
//  public String getCreated() {
//    return created;
//  }
//  public Integer getId() {
//    return id;
//  }
//  public Integer getItem_id() {
//    return item_id;
//  }
//  public Integer getPrice() {
//    return price;
//  }
//  public Integer getQuantity() {
//    return quantity;
//  }

  /**
  * Builder for CurrentSalesResult
  **/
  public static class CurrentSalesResultBuilder {
    private CurrentSalesResult toBuild = new CurrentSalesResult();

    public CurrentSalesResultBuilder() {
    }

    public CurrentSalesResult build() {
      return toBuild;
    }

    public CurrentSalesResultBuilder created(String value) {
      toBuild.created = value;
      return this;
    }
    public CurrentSalesResultBuilder id(Integer value) {
      toBuild.id = Long.valueOf(value);
      return this;
    }
    public CurrentSalesResultBuilder item_id(Integer value) {
      toBuild.item_id = value;
      return this;
    }
    public CurrentSalesResultBuilder price(Integer value) {
      toBuild.price = value;
      return this;
    }
    public CurrentSalesResultBuilder quantity(Integer value) {
      toBuild.quantity = value;
      return this;
    }
  }
}
