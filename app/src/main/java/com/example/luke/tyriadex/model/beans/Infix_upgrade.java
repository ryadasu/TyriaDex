/**
 * File generated by Magnet rest2mobile 1.1 - 05/12/2017 4:34:52 PM
 * @see {@link http://developer.magnet.com}
 */

package com.example.luke.tyriadex.model.beans;

import java.util.List;

/**
 * Generated from json example
{
  "id" : 142,
  "attributes" : [ {
    "attribute" : "Power",
    "modifier" : 85
  }, {
    "attribute" : "Precision",
    "modifier" : 61
  } ]
}

 */

public class Infix_upgrade {

  
  private List<Attribute> attributes;

  
  private Integer id;

  public List<Attribute> getAttributes() {
    return attributes;
  }
  public Integer getId() {
    return id;
  }

  /**
  * Builder for Infix_upgrade
  **/
  public static class Infix_upgradeBuilder {
    private Infix_upgrade toBuild = new Infix_upgrade();

    public Infix_upgradeBuilder() {
    }

    public Infix_upgrade build() {
      return toBuild;
    }

    public Infix_upgradeBuilder attributes(List<Attribute> value) {
      toBuild.attributes = value;
      return this;
    }
    public Infix_upgradeBuilder id(Integer value) {
      toBuild.id = value;
      return this;
    }
  }
}
