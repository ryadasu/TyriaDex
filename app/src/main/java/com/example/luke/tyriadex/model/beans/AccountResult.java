/**
 * File generated by Magnet rest2mobile 1.1 - 28/11/2017 4:10:12 PM
 * @see {@link http://developer.magnet.com}
 */

package com.example.luke.tyriadex.model.beans;

import java.util.List;

/**
 * Generated from json example
{
  "id" : "50270AFA-7C68-E111-809D-78E7D1936EF0",
  "name" : "Ryadasu.7425",
  "age" : 4682940,
  "world" : 1014,
  "guilds" : [ "D78B8CC9-7307-457F-A250-AD5B17BB857B" ],
  "guild_leader" : [ "D78B8CC9-7307-457F-A250-AD5B17BB857B" ],
  "created" : "2012-04-16T06:59:00Z",
  "access" : [ "GuildWars2", "HeartOfThorns", "PathOfFire" ],
  "commander" : false,
  "fractal_level" : 5,
  "daily_ap" : 2212,
  "monthly_ap" : 410,
  "wvw_rank" : 10
}

 */

public class AccountResult {

  
  private List<java.lang.String> access;

  
  private Integer age;

  
  private Boolean commander;

  
  private String created;

  
  private Integer daily_ap;

  
  private Integer fractal_level;

  
  private List<java.lang.String> guild_leader;

  
  private List<java.lang.String> guilds;

  
  private String id;

  
  private Integer monthly_ap;

  
  private String name;

  
  private Integer world;

  
  private Integer wvw_rank;

  public List<java.lang.String> getAccess() {
    return access;
  }
  public Integer getAge() {
    return age;
  }
  public Boolean getCommander() {
    return commander;
  }
  public String getCreated() {
    return created;
  }
  public Integer getDaily_ap() {
    return daily_ap;
  }
  public Integer getFractal_level() {
    return fractal_level;
  }
  public List<java.lang.String> getGuild_leader() {
    return guild_leader;
  }
  public List<java.lang.String> getGuilds() {
    return guilds;
  }
  public String getId() {
    return id;
  }
  public Integer getMonthly_ap() {
    return monthly_ap;
  }
  public String getName() {
    return name;
  }
  public Integer getWorld() {
    return world;
  }
  public Integer getWvw_rank() {
    return wvw_rank;
  }

  /**
  * Builder for AccountResult
  **/
  public static class AccountResultBuilder {
    private AccountResult toBuild = new AccountResult();

    public AccountResultBuilder() {
    }

    public AccountResult build() {
      return toBuild;
    }

    public AccountResultBuilder access(List<java.lang.String> value) {
      toBuild.access = value;
      return this;
    }
    public AccountResultBuilder age(Integer value) {
      toBuild.age = value;
      return this;
    }
    public AccountResultBuilder commander(Boolean value) {
      toBuild.commander = value;
      return this;
    }
    public AccountResultBuilder created(String value) {
      toBuild.created = value;
      return this;
    }
    public AccountResultBuilder daily_ap(Integer value) {
      toBuild.daily_ap = value;
      return this;
    }
    public AccountResultBuilder fractal_level(Integer value) {
      toBuild.fractal_level = value;
      return this;
    }
    public AccountResultBuilder guild_leader(List<java.lang.String> value) {
      toBuild.guild_leader = value;
      return this;
    }
    public AccountResultBuilder guilds(List<java.lang.String> value) {
      toBuild.guilds = value;
      return this;
    }
    public AccountResultBuilder id(String value) {
      toBuild.id = value;
      return this;
    }
    public AccountResultBuilder monthly_ap(Integer value) {
      toBuild.monthly_ap = value;
      return this;
    }
    public AccountResultBuilder name(String value) {
      toBuild.name = value;
      return this;
    }
    public AccountResultBuilder world(Integer value) {
      toBuild.world = value;
      return this;
    }
    public AccountResultBuilder wvw_rank(Integer value) {
      toBuild.wvw_rank = value;
      return this;
    }
  }
}
