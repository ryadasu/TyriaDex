/**
 * File generated by Magnet rest2mobile 1.1 - 05/12/2017 4:34:52 PM
 * @see {@link http://developer.magnet.com}
 */

package com.example.luke.tyriadex.model.beans;

import java.util.List;

/**
 * Generated from json example
{
  "id" : 9448,
  "size" : 15,
  "inventory" : [ {
    "id" : 23045,
    "count" : 1,
    "charges" : 239,
    "binding" : "Account"
  }, {
    "id" : 71647,
    "count" : 1,
    "binding" : "Account",
    "stats" : {
      "id" : 161,
      "attributes" : {
        "Power" : 239,
        "Precision" : 171,
        "CritDamage" : 171
      }
    }
   ...

 */

public class Bag {

  
  private Integer id;

  
  private List<Inventory> inventory;

  
  private Integer size;

  public Integer getId() {
    return id;
  }
  public List<Inventory> getInventory() {
    return inventory;
  }
  public Integer getSize() {
    return size;
  }

  /**
  * Builder for Bag
  **/
  public static class BagBuilder {
    private Bag toBuild = new Bag();

    public BagBuilder() {
    }

    public Bag build() {
      return toBuild;
    }

    public BagBuilder id(Integer value) {
      toBuild.id = value;
      return this;
    }
    public BagBuilder inventory(List<Inventory> value) {
      toBuild.inventory = value;
      return this;
    }
    public BagBuilder size(Integer value) {
      toBuild.size = value;
      return this;
    }
  }
}
