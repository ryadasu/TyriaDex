/**
 * File generated by Magnet rest2mobile 1.1 - 05/12/2017 3:57:04 PM
 * @see {@link http://developer.magnet.com}
 */

package com.example.luke.tyriadex.model.beans;


/**
 * Generated from json example
{
  "Power" : 45,
  "Precision" : 32,
  "CritDamage" : 32
}

 */

public class Attributes {

  
@com.google.gson.annotations.SerializedName("CritDamage")
  private Integer critDamage;

  
@com.google.gson.annotations.SerializedName("Power")
  private Integer power;

  
@com.google.gson.annotations.SerializedName("Precision")
  private Integer precision;

  
@com.google.gson.annotations.SerializedName("Toughness")
  private Integer toughness;

  
@com.google.gson.annotations.SerializedName("Vitality")
  private Integer vitality;

  public Integer getCritDamage() {
    return critDamage;
  }
  public Integer getPower() {
    return power;
  }
  public Integer getPrecision() {
    return precision;
  }
  public Integer getToughness() {
    return toughness;
  }
  public Integer getVitality() {
    return vitality;
  }

  /**
  * Builder for Attributes
  **/
  public static class AttributesBuilder {
    private Attributes toBuild = new Attributes();

    public AttributesBuilder() {
    }

    public Attributes build() {
      return toBuild;
    }

    public AttributesBuilder critDamage(Integer value) {
      toBuild.critDamage = value;
      return this;
    }
    public AttributesBuilder power(Integer value) {
      toBuild.power = value;
      return this;
    }
    public AttributesBuilder precision(Integer value) {
      toBuild.precision = value;
      return this;
    }
    public AttributesBuilder toughness(Integer value) {
      toBuild.toughness = value;
      return this;
    }
    public AttributesBuilder vitality(Integer value) {
      toBuild.vitality = value;
      return this;
    }
  }
}
