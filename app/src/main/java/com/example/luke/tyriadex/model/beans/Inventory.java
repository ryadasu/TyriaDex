/**
 * File generated by Magnet rest2mobile 1.1 - 05/12/2017 4:34:52 PM
 * @see {@link http://developer.magnet.com}
 */

package com.example.luke.tyriadex.model.beans;

import java.util.List;

/**
 * Generated from json example
{
  "id" : 23045,
  "count" : 1,
  "charges" : 239,
  "binding" : "Account"
}

 */

public class Inventory {

  
  private String binding;

  
  private String bound_to;

  
  private Integer charges;

  
  private Integer count;

  
  private Integer id;

  
  private Stats stats;

  
  private List<java.lang.Integer> upgrades;

  private ItemByIdResult item;

    public ItemByIdResult getItem() {
        return item;
    }

    public void setItem(ItemByIdResult item) {
        this.item = item;
    }

    public String getBinding() {
    return binding;
  }
  public String getBound_to() {
    return bound_to;
  }
  public Integer getCharges() {
    return charges;
  }
  public Integer getCount() {
    return count;
  }
  public Integer getId() {
    return id;
  }
  public Stats getStats() {
    return stats;
  }
  public List<java.lang.Integer> getUpgrades() {
    return upgrades;
  }

  /**
  * Builder for Inventory
  **/
  public static class InventoryBuilder {
    private Inventory toBuild = new Inventory();

    public InventoryBuilder() {
    }

    public Inventory build() {
      return toBuild;
    }

    public InventoryBuilder binding(String value) {
      toBuild.binding = value;
      return this;
    }
    public InventoryBuilder bound_to(String value) {
      toBuild.bound_to = value;
      return this;
    }
    public InventoryBuilder charges(Integer value) {
      toBuild.charges = value;
      return this;
    }
    public InventoryBuilder count(Integer value) {
      toBuild.count = value;
      return this;
    }
    public InventoryBuilder id(Integer value) {
      toBuild.id = value;
      return this;
    }
    public InventoryBuilder stats(Stats value) {
      toBuild.stats = value;
      return this;
    }
    public InventoryBuilder upgrades(List<java.lang.Integer> value) {
      toBuild.upgrades = value;
      return this;
    }
  }
}
