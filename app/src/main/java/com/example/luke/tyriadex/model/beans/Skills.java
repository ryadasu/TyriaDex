/**
 * File generated by Magnet rest2mobile 1.1 - 24/11/2017 4:35:46 PM
 * @see {@link http://developer.magnet.com}
 */

package com.example.luke.tyriadex.model.beans;


/**
 * Generated from json example
{
  "pve" : {
    "heal" : 21750,
    "utilities" : [ 30814, 29526, 10247 ],
    "elite" : 30359
  },
  "wvw" : {
    "heal" : 30305,
    "utilities" : [ 30814, 29526, 10200 ],
    "elite" : 30359
  }
}

 */

public class Skills {

  
  private SkillsPve pve;

  
  private SkillsWvw wvw;

  public SkillsPve getPve() {
    return pve;
  }
  public SkillsWvw getWvw() {
    return wvw;
  }

  /**
  * Builder for Skills
  **/
  public static class SkillsBuilder {
    private Skills toBuild = new Skills();

    public SkillsBuilder() {
    }

    public Skills build() {
      return toBuild;
    }

    public SkillsBuilder pve(SkillsPve value) {
      toBuild.pve = value;
      return this;
    }
    public SkillsBuilder wvw(SkillsWvw value) {
      toBuild.wvw = value;
      return this;
    }
  }
}