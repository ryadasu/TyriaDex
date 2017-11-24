/**
 * File generated by Magnet rest2mobile 1.1 - 24/11/2017 4:35:46 PM
 * @see {@link http://developer.magnet.com}
 */

package com.example.luke.tyriadex.controller.api;

import com.magnet.android.mms.MagnetMobileClient;
import com.magnet.android.mms.controller.ControllerFactory;
import com.magnet.android.mms.controller.AbstractControllerSchemaFactory;
import com.magnet.android.mms.controller.RequestSchema;
import com.magnet.android.mms.controller.RequestSchema.JMethod;
import com.magnet.android.mms.controller.RequestSchema.JMeta;
import com.magnet.android.mms.controller.RequestSchema.JParam;

import java.util.Arrays;
import java.util.Collection;

import com.example.luke.tyriadex.model.beans.*;
import java.util.List;

public class GW2APIFactory extends ControllerFactory<GW2API> {
  public GW2APIFactory(MagnetMobileClient magnetClient) {
    super(GW2API.class, GW2APISchemaFactory.getInstance().getSchema(), magnetClient);
  }

  // Schema factory for controller GW2API
  public static class GW2APISchemaFactory extends AbstractControllerSchemaFactory {
    private static GW2APISchemaFactory __instance;

    public static synchronized GW2APISchemaFactory getInstance() {
      if(null == __instance) {
        __instance = new GW2APISchemaFactory();
      }

      return __instance;
    }

    private GW2APISchemaFactory() {}

    protected synchronized void initSchemaMaps() {
      if(null != schema) {
        return;
      }

      schema = new RequestSchema();
      schema.setRootPath("");

      //controller schema for controller method getCharacterByName
      JMethod method1 = addMethod("getCharacterByName",
        "v2/characters/{characterName}",
        "GET",
        CharacterByNameResult.class,
        null,
        null,
        Arrays.asList("application/json"));
      method1.setBaseUrl("https://api.guildwars2.com");
      method1.addParam("characterName",
        "TEMPLATE",
        String.class,
        null,
        "",
        false);
      method1.addParam("access_token",
        "QUERY",
        String.class,
        null,
        "",
        true);

      //controller schema for controller method getCharacterNames
      JMethod method2 = addMethod("getCharacterNames",
        "v2/characters",
        "GET",
        List.class,
        String.class,
        null,
        Arrays.asList("application/json"));
      method2.setBaseUrl("https://api.guildwars2.com");
      method2.addParam("access_token",
        "QUERY",
        String.class,
        null,
        "",
        true);

      //controller schema for controller method getItemById
      JMethod method3 = addMethod("getItemById",
        "v2/items/{itemID}",
        "GET",
        ItemByIdResult.class,
        null,
        null,
        Arrays.asList("application/json"));
      method3.setBaseUrl("https://api.guildwars2.com");
      method3.addParam("itemID",
        "TEMPLATE",
        String.class,
        null,
        "",
        false);
    }

  }

}
