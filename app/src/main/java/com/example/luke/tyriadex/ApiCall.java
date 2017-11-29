package com.example.luke.tyriadex;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.luke.tyriadex.controller.api.GW2API;
import com.example.luke.tyriadex.controller.api.GW2APIFactory;
import com.example.luke.tyriadex.model.beans.AccountResult;
import com.example.luke.tyriadex.model.beans.BankResult;
import com.example.luke.tyriadex.model.beans.CharacterByNameResult;
import com.example.luke.tyriadex.model.beans.CurrencyByIdResult;
import com.example.luke.tyriadex.model.beans.CurrentBuysResult;
import com.example.luke.tyriadex.model.beans.CurrentSalesResult;
import com.example.luke.tyriadex.model.beans.DeliveryResult;
import com.example.luke.tyriadex.model.beans.Item;
import com.example.luke.tyriadex.model.beans.ItemByIdResult;
import com.example.luke.tyriadex.model.beans.PastBuysResult;
import com.example.luke.tyriadex.model.beans.PastSalesResult;
import com.example.luke.tyriadex.model.beans.WalletResult;
import com.magnet.android.mms.MagnetMobileClient;
import com.magnet.android.mms.async.Call;
import com.magnet.android.mms.exception.SchemaException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.xml.validation.Schema;

/**
 * Created by luke on 24/11/17.
 */

public class ApiCall {

    public static boolean cancel = false;

    public static boolean isCancel() {
        return cancel;
    }

    public static void setCancel(boolean cancel) {
        ApiCall.cancel = cancel;
    }

    private static Context c;
    private static GW2APIFactory controllerFactory;
    private static GW2API api;

    public static void update(Context con) {
        c = con;
    }

    public static DeliveryResult getDeliveryObject(String key) throws SchemaException {
        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
        controllerFactory = new GW2APIFactory(magnetClient);
        api = controllerFactory.obtainInstance();

        Call<DeliveryResult> callObject = api.getDelivery(key, null);
        Log.d("LOG", callObject.toString());
        DeliveryResult result = null;
        try {
            result = callObject.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (result != null) {
            if (result.getItems().isEmpty()) {
                //do nothing
            }
            else {
                for (Item item : result.getItems()) {
                    item.setItemByIdResult(getItemObjectById(String.valueOf(item.getId())));
                }
            }
        }

        return result;
    }

    public static List<String> getDeliveryString(String key) throws SchemaException {
        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
        controllerFactory = new GW2APIFactory(magnetClient);
        api = controllerFactory.obtainInstance();

        Call<DeliveryResult> callObject = api.getDelivery(key, null);
        Log.d("LOG", callObject.toString());
        DeliveryResult result = null;
        try {
            result = callObject.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        List<String> deliveries = new ArrayList<String>();

        if (result != null) {
            int maxSize;

            if (result.getItems().size() > 3) {
                maxSize = 3;
            }
            else {
                maxSize = result.getItems().size();
            }

            if (result.getItems().isEmpty() && result.getCoins() == 0) {
                deliveries.add("No items or coins to pick up");
            }
            else {
                int pickupCoins = result.getCoins();
                String pickupCoinsReadable = String.format("%05d", pickupCoins);
                pickupCoinsReadable = new StringBuilder(pickupCoinsReadable).insert(pickupCoinsReadable.length() - 4, "g ").toString();
                pickupCoinsReadable = new StringBuilder(pickupCoinsReadable).insert(pickupCoinsReadable.length() - 2, "s ").toString();
                pickupCoinsReadable = new StringBuilder(pickupCoinsReadable).insert(pickupCoinsReadable.length(), "c").toString();

                deliveries.add(pickupCoinsReadable);

                if (result.getItems().isEmpty()) {
                    //don't loop them
                }
                else {
                    for (int i = 0; i < maxSize; i++) {
                        String s = "";
                        String itemCount = String.valueOf(result.getItems().get(i).getCount());
                        String itemName = getItemById(String.valueOf(result.getItems().get(i).getId()));
                        s += (itemCount + "x " + itemName);
                        deliveries.add(s);
                    }
                }

                return deliveries;
            }
        } else {
            deliveries.add("Error retrieving deliveries");
        }
        return deliveries;
    }

    public static String getCurrentBuys(String key) throws SchemaException {
        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
        controllerFactory = new GW2APIFactory(magnetClient);
        api = controllerFactory.obtainInstance();

        Call<List<CurrentBuysResult>> callObject = api.getCurrentBuys(key, null);
        Log.d("LOG", callObject.toString());
        List<CurrentBuysResult> result = null;
        try {
            result = callObject.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (result != null) {
            String ids = "";
            int maxSize;

            if (result.size() > 3) {
                maxSize = 3;
            }
            else {
                maxSize = result.size();
            }

            if (result.isEmpty()) {
                return "No current buy orders";
            }
            else {
                for (int i = 0; i < maxSize; i++) {
                    int price = result.get(i).getPrice();
                    String itemPriceReadable = String.format("%05d", price);
                    itemPriceReadable = new StringBuilder(itemPriceReadable).insert(itemPriceReadable.length() - 4, "g ").toString();
                    itemPriceReadable = new StringBuilder(itemPriceReadable).insert(itemPriceReadable.length() - 2, "s ").toString();
                    itemPriceReadable = new StringBuilder(itemPriceReadable).insert(itemPriceReadable.length(), "c").toString();

                    ids += (String.valueOf(result.get(i).getQuantity()) + "x " + getItemById(String.valueOf(result.get(i).getItem_id())) + "\n" + itemPriceReadable + " each\n\n");
                }

                return ids;
            }
        } else {
            return "Error retrieving current buy orders";
        }
    }

    public static String getCurrentSales(String key) throws SchemaException {
        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
        controllerFactory = new GW2APIFactory(magnetClient);
        api = controllerFactory.obtainInstance();

        Call<List<CurrentSalesResult>> callObject = api.getCurrentSales(key, null);
        Log.d("LOG", callObject.toString());
        List<CurrentSalesResult> result = null;
        try {
            result = callObject.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (result != null) {
            String ids = "";
            int maxSize;

            if (result.size() > 3) {
                maxSize = 3;
            }
            else {
                maxSize = result.size();
            }

            if (result.isEmpty()) {
                return "No current sell orders";
            } else {
                for (int i = 0; i < maxSize; i++) {
                    int price = result.get(i).getPrice();
                    String itemPriceReadable = String.format("%05d", price);
                    itemPriceReadable = new StringBuilder(itemPriceReadable).insert(itemPriceReadable.length() - 4, "g ").toString();
                    itemPriceReadable = new StringBuilder(itemPriceReadable).insert(itemPriceReadable.length() - 2, "s ").toString();
                    itemPriceReadable = new StringBuilder(itemPriceReadable).insert(itemPriceReadable.length(), "c").toString();

                    ids += (String.valueOf(result.get(i).getQuantity()) + "x " + getItemById(String.valueOf(result.get(i).getItem_id())) + "\n" + itemPriceReadable + " each\n\n");
                }
                return ids;
            }
        } else {
            return "Error retrieving current buy orders";
        }
    }

    public static String getPastBuys(String key) throws SchemaException {
        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
        controllerFactory = new GW2APIFactory(magnetClient);
        api = controllerFactory.obtainInstance();

        Call<List<PastBuysResult>> callObject = api.getPastBuys(key, null);
        Log.d("LOG", callObject.toString());
        List<PastBuysResult> result = null;
        try {
            result = callObject.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (result != null) {
            String ids = "";
            int maxSize;

            if (result.size() > 3) {
                maxSize = 3;
            }
            else {
                maxSize = result.size();
            }

            for (int i = 0; i < maxSize; i++) {
                int price = result.get(i).getPrice();
                String itemPriceReadable = String.format("%05d", price);
                itemPriceReadable = new StringBuilder(itemPriceReadable).insert(itemPriceReadable.length() - 4, "g ").toString();
                itemPriceReadable = new StringBuilder(itemPriceReadable).insert(itemPriceReadable.length() - 2, "s ").toString();
                itemPriceReadable = new StringBuilder(itemPriceReadable).insert(itemPriceReadable.length(), "c").toString();

                ids += (String.valueOf(result.get(i).getQuantity()) + "x " + getItemById(String.valueOf(result.get(i).getItem_id())) + "\n" + itemPriceReadable + " each\n\n");
            }

            return ids;
        } else {
            return "Error retrieving current buy orders";
        }
    }

    public static String getPastSales(String key) throws SchemaException {
        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
        controllerFactory = new GW2APIFactory(magnetClient);
        api = controllerFactory.obtainInstance();

        Call<List<PastSalesResult>> callObject = api.getPastSales(key, null);
        Log.d("LOG", callObject.toString());
        List<PastSalesResult> result = null;
        try {
            result = callObject.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (result != null) {
            String ids = "";
            int maxSize;

            if (result.size() > 3) {
                maxSize = 3;
            }
            else {
                maxSize = result.size();
            }

            for (int i = 0; i < maxSize; i++) {
                int price = result.get(i).getPrice();
                String itemPriceReadable = String.format("%05d", price);
                itemPriceReadable = new StringBuilder(itemPriceReadable).insert(itemPriceReadable.length() - 4, "g ").toString();
                itemPriceReadable = new StringBuilder(itemPriceReadable).insert(itemPriceReadable.length() - 2, "s ").toString();
                itemPriceReadable = new StringBuilder(itemPriceReadable).insert(itemPriceReadable.length(), "c").toString();

                ids += (String.valueOf(result.get(i).getQuantity()) + "x " + getItemById(String.valueOf(result.get(i).getItem_id())) + "\n" + itemPriceReadable + " each\n\n");
            }

            return ids;
        } else {
            return "Error retrieving current buy orders";
        }
    }

    public static String getBank(String key) throws SchemaException {
        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
        controllerFactory = new GW2APIFactory(magnetClient);
        api = controllerFactory.obtainInstance();
        setCancel(false);

        Call<List<BankResult>> callObject = api.getBank(key, null);
        Log.d("LOG", callObject.toString());
        List<BankResult> result = null;
        try {
            result = callObject.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (result != null) {
            String ids = "";

            for (int i = 0; i < result.size(); i++) {
                if (result.get(i) == null) {
                    ids += "Empty Slot, ";
                } else {
                    if (cancel) {
                        break;
                    }
                    else {
                        ids += (String.valueOf(result.get(i).getCount()) + "x " + getItemById(String.valueOf(result.get(i).getId())) + "\n");
                    }
                }
            }

            return ids;
        } else {
            return "Error retrieving bank";
        }
    }

    public static String getCharacterByName(String charName, String key) throws SchemaException {
        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
        controllerFactory = new GW2APIFactory(magnetClient);
        api = controllerFactory.obtainInstance();

        Call<CharacterByNameResult> callObject = api.getCharacterByName(charName, key, null);
        Log.d("LOG", callObject.toString());
        CharacterByNameResult result = null;
        try {
            result = callObject.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (result != null) {
            String s = "";

            s += (result.getGender() + " " + result.getRace() + " " + result.getProfession());

            return s;
        } else {
            return "Error retrieving character";
        }
    }

    public static List<String> getCharacterNames(String key) throws SchemaException {
        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
        controllerFactory = new GW2APIFactory(magnetClient);
        api = controllerFactory.obtainInstance();

        Call<List<String>> callObject = api.getCharacterNames(key, null);
        List<String> result = null;
        try {
            result = callObject.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (result != null) {
            return result;
        } else {
            List<String> strings = Arrays.asList("error");
            return strings;
        }
    }

    public static String getAccount(String key) throws SchemaException {
        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
        controllerFactory = new GW2APIFactory(magnetClient);
        api = controllerFactory.obtainInstance();

        Call<AccountResult> callObject = api.getAccount(key, null);

        AccountResult result = null;
        try {
            result = callObject.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String s = "Error! Please try again";
        if (result != null) {
            s = result.getName();
        }
        return s;
    }

    public static List<WalletResult> getWallet(String key) throws SchemaException {
        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
        controllerFactory = new GW2APIFactory(magnetClient);
        api = controllerFactory.obtainInstance();

        Call<List<WalletResult>> callObject = api.getWallet(key, null);
        List<WalletResult> list = null;
        try {
            list = callObject.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (list != null) {
            for (WalletResult item : list) {
                CurrencyByIdResult currency = getCurrencyById(String.valueOf(item.getId()));
                item.setCurrency(currency);
            }
            return list;
        } else {
            return null;
        }
    }

    public static CurrencyByIdResult getCurrencyById(String id) throws SchemaException {
        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
        controllerFactory = new GW2APIFactory(magnetClient);
        api = controllerFactory.obtainInstance();

        Call<CurrencyByIdResult> callObject = api.getCurrencyById(id, null);
        CurrencyByIdResult result = null;
        try {
            result = callObject.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (result != null) {
            return result;
        } else {
            return null;
        }
    }


    public static String getItemById(String itemID) throws SchemaException {
        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
        controllerFactory = new GW2APIFactory(magnetClient);
        api = controllerFactory.obtainInstance();

        Call<ItemByIdResult> callObject = api.getItemById(itemID, null);

        ItemByIdResult result = null;
        try {
            result = callObject.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String s = "Error! Please try again";
        if (result != null) {
            s = result.getName();
        }
        return s;
    }

    public static ItemByIdResult getItemObjectById(String itemID) throws SchemaException {
        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
        controllerFactory = new GW2APIFactory(magnetClient);
        api = controllerFactory.obtainInstance();

        Call<ItemByIdResult> callObject = api.getItemById(itemID, null);

        ItemByIdResult result = null;
        try {
            result = callObject.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return result;
    }
}
