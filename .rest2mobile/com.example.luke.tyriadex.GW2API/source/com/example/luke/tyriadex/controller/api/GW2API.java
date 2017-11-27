/**
 * File generated by Magnet rest2mobile 1.1 - 27/11/2017 4:11:17 PM
 * @see {@link http://developer.magnet.com}
 */

package com.example.luke.tyriadex.controller.api;

import com.magnet.android.mms.async.Call;
import com.magnet.android.mms.async.StateChangedListener;

import com.example.luke.tyriadex.model.beans.*;
import java.util.List;

public interface GW2API {

  /**
   * Generated from URL GET https://api.guildwars2.com/v2/account/bank?access_token=
   * GET v2/account/bank
   * @param access_token  style:QUERY
   * @param listener
   * @return List<BankResult>
   */
  Call<List<BankResult>> getBank(
     String access_token,
     StateChangedListener listener
  );



  /**
   * Generated from URL GET https://api.guildwars2.com/v2/characters/{characterName}?access_token=
   * GET v2/characters/{characterName}
   * @param characterName  style:TEMPLATE
   * @param access_token  style:QUERY
   * @param listener
   * @return CharacterByNameResult
   */
  Call<CharacterByNameResult> getCharacterByName(
     String characterName,
     String access_token,
     StateChangedListener listener
  );



  /**
   * Generated from URL GET https://api.guildwars2.com/v2/characters?access_token=
   * GET v2/characters
   * @param access_token  style:QUERY
   * @param listener
   * @return List<java.lang.String>
   */
  Call<List<java.lang.String>> getCharacterNames(
     String access_token,
     StateChangedListener listener
  );



  /**
   * Generated from URL GET https://api.guildwars2.com/v2/commerce/transactions/current/buys?access_token=
   * GET v2/commerce/transactions/current/buys
   * @param access_token  style:QUERY
   * @param listener
   * @return List<CurrentBuysResult>
   */
  Call<List<CurrentBuysResult>> getCurrentBuys(
     String access_token,
     StateChangedListener listener
  );



  /**
   * Generated from URL GET https://api.guildwars2.com/v2/commerce/transactions/current/sells?access_token=
   * GET v2/commerce/transactions/current/sells
   * @param access_token  style:QUERY
   * @param listener
   * @return List<CurrentSalesResult>
   */
  Call<List<CurrentSalesResult>> getCurrentSales(
     String access_token,
     StateChangedListener listener
  );



  /**
   * Generated from URL GET https://api.guildwars2.com/v2/items/{itemID}
   * GET v2/items/{itemID}
   * @param itemID  style:TEMPLATE
   * @param listener
   * @return ItemByIdResult
   */
  Call<ItemByIdResult> getItemById(
     String itemID,
     StateChangedListener listener
  );


}
