/**
 * File generated by Magnet rest2mobile 1.1 - 27/11/2017 4:11:17 PM
 * @see {@link http://developer.magnet.com}
 */

package com.example.luke.tyriadex.model.beans;


/**
 * Generated from json example
{
  "type" : "LongBow",
  "damage_type" : "Physical",
  "min_power" : 385,
  "max_power" : 452,
  "defense" : 0,
  "infix_upgrade" : {
    "id" : 142,
    "attributes" : [ {
      "attribute" : "Power",
      "modifier" : 85
    }, {
      "attribute" : "Precision",
      "modifier" : 61
    } ]
  },
  "suffix_item_id" : 24547,
  "secondary_suffix_item_id" : ""
}

 */

public class Details {

  
  private String damage_type;

  
  private Integer defense;

  
  private Infix_upgrade infix_upgrade;

  
  private Integer max_power;

  
  private Integer min_power;

  
  private String secondary_suffix_item_id;

  
  private Integer suffix_item_id;

  
  private String type;

  public String getDamage_type() {
    return damage_type;
  }
  public Integer getDefense() {
    return defense;
  }
  public Infix_upgrade getInfix_upgrade() {
    return infix_upgrade;
  }
  public Integer getMax_power() {
    return max_power;
  }
  public Integer getMin_power() {
    return min_power;
  }
  public String getSecondary_suffix_item_id() {
    return secondary_suffix_item_id;
  }
  public Integer getSuffix_item_id() {
    return suffix_item_id;
  }
  public String getType() {
    return type;
  }

  /**
  * Builder for Details
  **/
  public static class DetailsBuilder {
    private Details toBuild = new Details();

    public DetailsBuilder() {
    }

    public Details build() {
      return toBuild;
    }

    public DetailsBuilder damage_type(String value) {
      toBuild.damage_type = value;
      return this;
    }
    public DetailsBuilder defense(Integer value) {
      toBuild.defense = value;
      return this;
    }
    public DetailsBuilder infix_upgrade(Infix_upgrade value) {
      toBuild.infix_upgrade = value;
      return this;
    }
    public DetailsBuilder max_power(Integer value) {
      toBuild.max_power = value;
      return this;
    }
    public DetailsBuilder min_power(Integer value) {
      toBuild.min_power = value;
      return this;
    }
    public DetailsBuilder secondary_suffix_item_id(String value) {
      toBuild.secondary_suffix_item_id = value;
      return this;
    }
    public DetailsBuilder suffix_item_id(Integer value) {
      toBuild.suffix_item_id = value;
      return this;
    }
    public DetailsBuilder type(String value) {
      toBuild.type = value;
      return this;
    }
  }
}
