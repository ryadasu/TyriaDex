/**
 * File generated by Magnet rest2mobile 1.1 - 27/11/2017 4:11:17 PM
 * @see {@link http://developer.magnet.com}
 */

package com.example.luke.tyriadex.model.beans;

import java.util.List;

/**
 * Generated from json example
{
  "id" : 4548,
  "slot" : "HelmAquatic",
  "upgrades" : [ 24753 ],
  "binding" : "Character",
  "bound_to" : "Lazeen"
}

 */

public class Equipment {

  
  private String binding;

  
  private String bound_to;

  
  private Integer charges;

  
  private Integer id;

  
  private Integer skin;

  
  private String slot;

  
  private Stats stats;

  
  private List<java.lang.Integer> upgrades;

  public String getBinding() {
    return binding;
  }
  public String getBound_to() {
    return bound_to;
  }
  public Integer getCharges() {
    return charges;
  }
  public Integer getId() {
    return id;
  }
  public Integer getSkin() {
    return skin;
  }
  public String getSlot() {
    return slot;
  }
  public Stats getStats() {
    return stats;
  }
  public List<java.lang.Integer> getUpgrades() {
    return upgrades;
  }

  /**
  * Builder for Equipment
  **/
  public static class EquipmentBuilder {
    private Equipment toBuild = new Equipment();

    public EquipmentBuilder() {
    }

    public Equipment build() {
      return toBuild;
    }

    public EquipmentBuilder binding(String value) {
      toBuild.binding = value;
      return this;
    }
    public EquipmentBuilder bound_to(String value) {
      toBuild.bound_to = value;
      return this;
    }
    public EquipmentBuilder charges(Integer value) {
      toBuild.charges = value;
      return this;
    }
    public EquipmentBuilder id(Integer value) {
      toBuild.id = value;
      return this;
    }
    public EquipmentBuilder skin(Integer value) {
      toBuild.skin = value;
      return this;
    }
    public EquipmentBuilder slot(String value) {
      toBuild.slot = value;
      return this;
    }
    public EquipmentBuilder stats(Stats value) {
      toBuild.stats = value;
      return this;
    }
    public EquipmentBuilder upgrades(List<java.lang.Integer> value) {
      toBuild.upgrades = value;
      return this;
    }
  }
}
