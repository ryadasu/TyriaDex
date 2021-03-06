/**
 * File generated by Magnet rest2mobile 1.1 - 05/12/2017 4:34:52 PM
 * @see {@link http://developer.magnet.com}
 */

package com.example.luke.tyriadex.model.beans;

import java.util.List;

/**
 * Generated from json example
{
  "coins" : 16937,
  "items" : [ {
    "id" : 19699,
    "count" : 7
  } ]
}

 */

public class DeliveryResult {

  
  private Integer coins;

  
  private List<Item> items;

  public Integer getCoins() {
    return coins;
  }
  public List<Item> getItems() {
    return items;
  }

  /**
  * Builder for DeliveryResult
  **/
  public static class DeliveryResultBuilder {
    private DeliveryResult toBuild = new DeliveryResult();

    public DeliveryResultBuilder() {
    }

    public DeliveryResult build() {
      return toBuild;
    }

    public DeliveryResultBuilder coins(Integer value) {
      toBuild.coins = value;
      return this;
    }
    public DeliveryResultBuilder items(List<Item> value) {
      toBuild.items = value;
      return this;
    }
  }
}
