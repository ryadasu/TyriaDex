package com.example.luke.tyriadex;

import android.content.Context;

import com.example.luke.tyriadex.controller.api.GW2API;
import com.example.luke.tyriadex.controller.api.GW2APIFactory;
import com.example.luke.tyriadex.model.beans.ItemByIdResult;
import com.magnet.android.mms.MagnetMobileClient;
import com.magnet.android.mms.async.Call;
import com.magnet.android.mms.exception.SchemaException;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by luke on 24/11/17.
 */

public class ApiCall {

    private static Context c;
    private static String key;
    private static GW2APIFactory controllerFactory;
    private static GW2API api;

    public static void update(Context con, String apiKey) {
        c = con;
        key = apiKey;
    }

    public static String getCharacterNames(String key) throws SchemaException {
        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
        controllerFactory = new GW2APIFactory(magnetClient);
        api = controllerFactory.obtainInstance();

        Call<List<String>> callObject = api.getCharacterNames(key, null);
        //auto-generated object
        List<String> result = null;
        try {
            result = callObject.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (result != null) {
            String s = "";
            for (int i = 0; i < result.size(); i++) {
                s += (result.get(i) + "\n");
            }
            return s;
        } else {
            return "Error! Please try again";
        }
    }

//    public static int getBuild() throws SchemaException {
//        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
//        controllerFactory = new GW2APIFactory(magnetClient);
//        api = controllerFactory.obtainInstance();
//
//        Call<BuildNumResult> callObject = api.getBuildNum(null);
//        //auto-generated object
//        BuildNumResult result = null;
//        try {
//            result = callObject.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        if (result != null) {
//            return result.getId();
//        } else {
//            return 0;
//        }
//    }

//    public static String getAccount() throws SchemaException {
//        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
//        controllerFactory = new GW2apiFactory(magnetClient);
//        api = controllerFactory.obtainInstance();
//
//        Call<AccountResult> callObject = api.getAccount(key, null);
//
//        AccountResult result = null;
//        try {
//            result = callObject.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        String s = "Error! Please try again";
//        if (result != null) {
//            s = result.getName();
//        }
//        return s;
//    }

//    public static String getWallet() throws SchemaException {
//        String s = "";
//        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
//        controllerFactory = new GW2apiFactory(magnetClient);
//        api = controllerFactory.obtainInstance();
//
//        Call<List<WalletResult>> callObject = api.getWallet(key, null);
//        List<WalletResult> list = null;
//        try {
//            list = callObject.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        if (list != null) {
//            for (int i = 0; i < list.size(); i++) {
//                WalletResult result = list.get(i);
//                s += (result.getId() + "\n" + result.getValue() + "\n\n");
//            }
//        } else {
//            return "Error! Please try again";
//        }
//
//        return s;
//    }

//    public static String getGold() throws SchemaException {
//        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
//        controllerFactory = new GW2apiFactory(magnetClient);
//        api = controllerFactory.obtainInstance();
//
//        Call<List<WalletResult>> callObject = api.getWallet(key, null);
//        //auto-generated object
//        List<WalletResult> list = null;
//        try {
//            list = callObject.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        if (list != null) {
//            int fullValue = list.get(0).getValue();
//            String readable = Integer.toString(fullValue);
//            readable = new StringBuilder(readable).insert(readable.length() - 4, "g ").toString();
//            readable = new StringBuilder(readable).insert(readable.length() - 2, "s ").toString();
//            readable = new StringBuilder(readable).insert(readable.length(), "c").toString();
//            return readable;
//        } else {
//            return "Error! Please try again";
//        }
//    }

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

//    public static String getSales() throws SchemaException {
//        MagnetMobileClient magnetClient = MagnetMobileClient.getInstance(c);
//        controllerFactory = new GW2apiFactory(magnetClient);
//        api = controllerFactory.obtainInstance();
//
//        Call<List<SalesResult>> callObject = api.getSales(key, null);
//        //auto-generated object
//        List<SalesResult> list = null;
//        try {
//            list = callObject.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//        if (list != null) {
//            String s = "";
//            for (int i = 0; i < 3; i++) {
//                SalesResult result = list.get(i);
//                int itemID = result.getItem_id();
//                String itemName = getItem(String.valueOf(itemID));
//
//                int itemPrice = result.getPrice();
//
//                String itemPriceReadable = String.format("%05d", itemPrice);
//                itemPriceReadable = new StringBuilder(itemPriceReadable).insert(itemPriceReadable.length() - 4, "g ").toString();
//                itemPriceReadable = new StringBuilder(itemPriceReadable).insert(itemPriceReadable.length() - 2, "s ").toString();
//                itemPriceReadable = new StringBuilder(itemPriceReadable).insert(itemPriceReadable.length(), "c").toString();
//
//                int itemQuantity = result.getQuantity();
//                s += (itemName + "\n" + itemPriceReadable + "\n" + String.valueOf(itemQuantity) + "\n\n");
//            }
//            return s;
//        } else {
//            return "Error! Please try again";
//        }
//    }
}
