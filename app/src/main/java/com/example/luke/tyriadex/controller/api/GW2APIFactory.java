/**
 * File generated by Magnet rest2mobile 1.1 - 05/12/2017 4:34:52 PM
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

      //controller schema for controller method getAccount
      JMethod method1 = addMethod("getAccount",
        "v2/account",
        "GET",
        AccountResult.class,
        null,
        null,
        Arrays.asList("application/json"));
      method1.setBaseUrl("https://api.guildwars2.com");
      method1.addParam("access_token",
        "QUERY",
        String.class,
        null,
        "",
        true);

      //controller schema for controller method getAllCharacters
      JMethod method2 = addMethod("getAllCharacters",
        "v2/characters",
        "GET",
        List.class,
        AllCharactersResult.class,
        null,
        Arrays.asList("application/json"));
      method2.setBaseUrl("https://api.guildwars2.com");
      method2.addParam("page",
        "QUERY",
        String.class,
        null,
        "",
        true);
      method2.addParam("access_token",
        "QUERY",
        String.class,
        null,
        "",
        true);

      //controller schema for controller method getBank
      JMethod method3 = addMethod("getBank",
        "v2/account/bank",
        "GET",
        List.class,
        BankResult.class,
        null,
        Arrays.asList("application/json"));
      method3.setBaseUrl("https://api.guildwars2.com");
      method3.addParam("access_token",
        "QUERY",
        String.class,
        null,
        "",
        true);

      //controller schema for controller method getCharacterByName
      JMethod method4 = addMethod("getCharacterByName",
        "v2/characters/{characterName}",
        "GET",
        CharacterByNameResult.class,
        null,
        null,
        Arrays.asList("application/json"));
      method4.setBaseUrl("https://api.guildwars2.com");
      method4.addParam("characterName",
        "TEMPLATE",
        String.class,
        null,
        "",
        false);
      method4.addParam("access_token",
        "QUERY",
        String.class,
        null,
        "",
        true);

      //controller schema for controller method getCharacterNames
      JMethod method5 = addMethod("getCharacterNames",
        "v2/characters",
        "GET",
        List.class,
        String.class,
        null,
        Arrays.asList("application/json"));
      method5.setBaseUrl("https://api.guildwars2.com");
      method5.addParam("access_token",
        "QUERY",
        String.class,
        null,
        "",
        true);

      //controller schema for controller method getCurrencyById
      JMethod method6 = addMethod("getCurrencyById",
        "v2/currencies/{currencyID}",
        "GET",
        CurrencyByIdResult.class,
        null,
        null,
        Arrays.asList("application/json"));
      method6.setBaseUrl("https://api.guildwars2.com");
      method6.addParam("currencyID",
        "TEMPLATE",
        String.class,
        null,
        "",
        false);

      //controller schema for controller method getCurrentBuys
      JMethod method7 = addMethod("getCurrentBuys",
        "v2/commerce/transactions/current/buys",
        "GET",
        List.class,
        CurrentBuysResult.class,
        null,
        Arrays.asList("application/json"));
      method7.setBaseUrl("https://api.guildwars2.com");
      method7.addParam("access_token",
        "QUERY",
        String.class,
        null,
        "",
        true);

      //controller schema for controller method getCurrentSales
      JMethod method8 = addMethod("getCurrentSales",
        "v2/commerce/transactions/current/sells",
        "GET",
        List.class,
        CurrentSalesResult.class,
        null,
        Arrays.asList("application/json"));
      method8.setBaseUrl("https://api.guildwars2.com");
      method8.addParam("access_token",
        "QUERY",
        String.class,
        null,
        "",
        true);

      //controller schema for controller method getDelivery
      JMethod method9 = addMethod("getDelivery",
        "v2/commerce/delivery",
        "GET",
        DeliveryResult.class,
        null,
        null,
        Arrays.asList("application/json"));
      method9.setBaseUrl("https://api.guildwars2.com");
      method9.addParam("access_token",
        "QUERY",
        String.class,
        null,
        "",
        true);

      //controller schema for controller method getItemById
      JMethod method10 = addMethod("getItemById",
        "v2/items/{itemID}",
        "GET",
        ItemByIdResult.class,
        null,
        null,
        Arrays.asList("application/json"));
      method10.setBaseUrl("https://api.guildwars2.com");
      method10.addParam("itemID",
        "TEMPLATE",
        String.class,
        null,
        "",
        false);

      //controller schema for controller method getPastBuys
      JMethod method11 = addMethod("getPastBuys",
        "v2/commerce/transactions/history/buys",
        "GET",
        List.class,
        PastBuysResult.class,
        null,
        Arrays.asList("application/json"));
      method11.setBaseUrl("https://api.guildwars2.com");
      method11.addParam("access_token",
        "QUERY",
        String.class,
        null,
        "",
        true);

      //controller schema for controller method getPastSales
      JMethod method12 = addMethod("getPastSales",
        "v2/commerce/transactions/history/sells",
        "GET",
        List.class,
        PastSalesResult.class,
        null,
        Arrays.asList("application/json"));
      method12.setBaseUrl("https://api.guildwars2.com");
      method12.addParam("access_token",
        "QUERY",
        String.class,
        null,
        "",
        true);

      //controller schema for controller method getWallet
      JMethod method13 = addMethod("getWallet",
        "v2/account/wallet",
        "GET",
        List.class,
        WalletResult.class,
        null,
        Arrays.asList("application/json"));
      method13.setBaseUrl("https://api.guildwars2.com");
      method13.addParam("access_token",
        "QUERY",
        String.class,
        null,
        "",
        true);
    }

  }

}
