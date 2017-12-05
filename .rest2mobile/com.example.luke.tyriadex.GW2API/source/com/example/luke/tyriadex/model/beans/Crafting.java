/**
 * File generated by Magnet rest2mobile 1.1 - 05/12/2017 3:57:04 PM
 * @see {@link http://developer.magnet.com}
 */

package com.example.luke.tyriadex.model.beans;


/**
 * Generated from json example
{
  "discipline" : "Artificer",
  "rating" : 81,
  "active" : true
}

 */

public class Crafting {

  
  private Boolean active;

  
  private String discipline;

  
  private Integer rating;

  public Boolean getActive() {
    return active;
  }
  public String getDiscipline() {
    return discipline;
  }
  public Integer getRating() {
    return rating;
  }

  /**
  * Builder for Crafting
  **/
  public static class CraftingBuilder {
    private Crafting toBuild = new Crafting();

    public CraftingBuilder() {
    }

    public Crafting build() {
      return toBuild;
    }

    public CraftingBuilder active(Boolean value) {
      toBuild.active = value;
      return this;
    }
    public CraftingBuilder discipline(String value) {
      toBuild.discipline = value;
      return this;
    }
    public CraftingBuilder rating(Integer value) {
      toBuild.rating = value;
      return this;
    }
  }
}
