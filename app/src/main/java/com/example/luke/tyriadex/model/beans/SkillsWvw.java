/**
 * File generated by Magnet rest2mobile 1.1 - 05/12/2017 4:34:52 PM
 * @see {@link http://developer.magnet.com}
 */

package com.example.luke.tyriadex.model.beans;

import java.util.List;

/**
 * Generated from json example
{
  "heal" : 30305,
  "utilities" : [ 30814, 29526, 10200 ],
  "elite" : 30359
}

 */

public class SkillsWvw {

  
  private Integer elite;

  
  private Integer heal;

  
  private List<java.lang.Integer> utilities;

  public Integer getElite() {
    return elite;
  }
  public Integer getHeal() {
    return heal;
  }
  public List<java.lang.Integer> getUtilities() {
    return utilities;
  }

  /**
  * Builder for SkillsWvw
  **/
  public static class SkillsWvwBuilder {
    private SkillsWvw toBuild = new SkillsWvw();

    public SkillsWvwBuilder() {
    }

    public SkillsWvw build() {
      return toBuild;
    }

    public SkillsWvwBuilder elite(Integer value) {
      toBuild.elite = value;
      return this;
    }
    public SkillsWvwBuilder heal(Integer value) {
      toBuild.heal = value;
      return this;
    }
    public SkillsWvwBuilder utilities(List<java.lang.Integer> value) {
      toBuild.utilities = value;
      return this;
    }
  }
}
