/**
 * File generated by Magnet rest2mobile 1.1 - 05/12/2017 3:57:04 PM
 * @see {@link http://developer.magnet.com}
 */

package com.example.luke.tyriadex.model.beans;


/**
 * Generated from json example
{
  "id" : 98,
  "spent" : 24,
  "done" : true
}

 */

public class Training {

  
  private Boolean done;

  
  private Integer id;

  
  private Integer spent;

  public Boolean getDone() {
    return done;
  }
  public Integer getId() {
    return id;
  }
  public Integer getSpent() {
    return spent;
  }

  /**
  * Builder for Training
  **/
  public static class TrainingBuilder {
    private Training toBuild = new Training();

    public TrainingBuilder() {
    }

    public Training build() {
      return toBuild;
    }

    public TrainingBuilder done(Boolean value) {
      toBuild.done = value;
      return this;
    }
    public TrainingBuilder id(Integer value) {
      toBuild.id = value;
      return this;
    }
    public TrainingBuilder spent(Integer value) {
      toBuild.spent = value;
      return this;
    }
  }
}
