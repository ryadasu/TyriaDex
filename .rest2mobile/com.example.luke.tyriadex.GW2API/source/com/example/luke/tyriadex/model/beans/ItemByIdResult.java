/**
 * File generated by Magnet rest2mobile 1.1 - 24/11/2017 4:35:46 PM
 * @see {@link http://developer.magnet.com}
 */

package com.example.luke.tyriadex.model.beans;

import java.util.List;

/**
 * Generated from json example
{
  "name" : "StrongSoftWoodLongbowofFire",
  "description" : "",
  "type" : "Weapon",
  "level" : 44,
  "rarity" : "Masterwork",
  "vendor_value" : 120,
  "default_skin" : 3942,
  "game_types" : [ "Activity", "Wvw", "Dungeon", "Pve" ],
  "flags" : [ "SoulBindOnUse" ],
  "id" : 28445,
  "chat_link" : "[&AgEdbwAA]",
  "icon" : "https://render.guildwars2.com/file/C6110F52DF5AFE0F00A56F9E143E9732176DDDE9/65015.png",
  "details" : {
    "type" : "LongBow",
    "damage_type" : "Physical",
    "min_power" : 385,
    "max_power" : 452,
    "defense" : 0,
    "infix_upgrade" : {
        ...

 */

public class ItemByIdResult {

  
  private String chat_link;

  
  private Integer default_skin;

  
  private String description;

  
  private Details details;

  
  private List<java.lang.String> flags;

  
  private List<java.lang.String> game_types;

  
  private String icon;

  
  private Integer id;

  
  private Integer level;

  
  private String name;

  
  private String rarity;

  
  private String type;

  
  private Integer vendor_value;

  public String getChat_link() {
    return chat_link;
  }
  public Integer getDefault_skin() {
    return default_skin;
  }
  public String getDescription() {
    return description;
  }
  public Details getDetails() {
    return details;
  }
  public List<java.lang.String> getFlags() {
    return flags;
  }
  public List<java.lang.String> getGame_types() {
    return game_types;
  }
  public String getIcon() {
    return icon;
  }
  public Integer getId() {
    return id;
  }
  public Integer getLevel() {
    return level;
  }
  public String getName() {
    return name;
  }
  public String getRarity() {
    return rarity;
  }
  public String getType() {
    return type;
  }
  public Integer getVendor_value() {
    return vendor_value;
  }

  /**
  * Builder for ItemByIdResult
  **/
  public static class ItemByIdResultBuilder {
    private ItemByIdResult toBuild = new ItemByIdResult();

    public ItemByIdResultBuilder() {
    }

    public ItemByIdResult build() {
      return toBuild;
    }

    public ItemByIdResultBuilder chat_link(String value) {
      toBuild.chat_link = value;
      return this;
    }
    public ItemByIdResultBuilder default_skin(Integer value) {
      toBuild.default_skin = value;
      return this;
    }
    public ItemByIdResultBuilder description(String value) {
      toBuild.description = value;
      return this;
    }
    public ItemByIdResultBuilder details(Details value) {
      toBuild.details = value;
      return this;
    }
    public ItemByIdResultBuilder flags(List<java.lang.String> value) {
      toBuild.flags = value;
      return this;
    }
    public ItemByIdResultBuilder game_types(List<java.lang.String> value) {
      toBuild.game_types = value;
      return this;
    }
    public ItemByIdResultBuilder icon(String value) {
      toBuild.icon = value;
      return this;
    }
    public ItemByIdResultBuilder id(Integer value) {
      toBuild.id = value;
      return this;
    }
    public ItemByIdResultBuilder level(Integer value) {
      toBuild.level = value;
      return this;
    }
    public ItemByIdResultBuilder name(String value) {
      toBuild.name = value;
      return this;
    }
    public ItemByIdResultBuilder rarity(String value) {
      toBuild.rarity = value;
      return this;
    }
    public ItemByIdResultBuilder type(String value) {
      toBuild.type = value;
      return this;
    }
    public ItemByIdResultBuilder vendor_value(Integer value) {
      toBuild.vendor_value = value;
      return this;
    }
  }
}