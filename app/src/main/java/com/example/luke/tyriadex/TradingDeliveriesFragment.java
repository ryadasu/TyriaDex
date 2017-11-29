package com.example.luke.tyriadex;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luke.tyriadex.model.beans.DeliveryResult;
import com.example.luke.tyriadex.model.beans.Item;
import com.magnet.android.mms.exception.SchemaException;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by luke on 29/11/17.
 */

public class TradingDeliveriesFragment extends Fragment {

    private String apiKey = null;
    List<Item> deliveryItems = null;
    RecyclerView deliveryRecyclerView;
    TradingDeliveriesAdapter mAdapter;

    private class DeliveriesAsyncCall extends AsyncTask<String, Void, DeliveryResult> {

        View rootView;
        TextView tvDeliveryGold;

        public DeliveriesAsyncCall(View view, TextView tvDG) {
            rootView = view;
            tvDeliveryGold = tvDG;
        }

        public DeliveryResult doInBackground(String... params) {
            DeliveryResult result = null;
            try {
                result = ApiCall.getDeliveryObject(params[0]);
            } catch (SchemaException e) {
                e.printStackTrace();
            }
            return result;
        }

        public void onPostExecute(DeliveryResult result) {
            if (result != null) {
                int pickupCoins = result.getCoins();
                String pickupCoinsReadable = String.format(Locale.ENGLISH,"%05d", pickupCoins);
                pickupCoinsReadable = new StringBuilder(pickupCoinsReadable).insert(pickupCoinsReadable.length() - 4, "g ").toString();
                pickupCoinsReadable = new StringBuilder(pickupCoinsReadable).insert(pickupCoinsReadable.length() - 2, "s ").toString();
                pickupCoinsReadable = new StringBuilder(pickupCoinsReadable).insert(pickupCoinsReadable.length(), "c").toString();
                tvDeliveryGold.setText(pickupCoinsReadable);

                deliveryItems = result.getItems();
                //            mAdapter.notifyDataSetChanged();
                deliveryRecyclerView = rootView.findViewById(R.id.recycler_trading_deliveries);
                mAdapter = new TradingDeliveriesAdapter(deliveryItems);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
                deliveryRecyclerView.setLayoutManager(mLayoutManager);
                deliveryRecyclerView.setItemAnimator(new DefaultItemAnimator());
                deliveryRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
                if (deliveryRecyclerView != null && mAdapter != null) {
                    deliveryRecyclerView.setAdapter(mAdapter);
                }
            }
        }
    }

    public TradingDeliveriesFragment() {
        //
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_trading_deliveries, container, false);
        Bundle args = getArguments();
        if (args != null) {
            apiKey = args.getString("key");
        }
        Log.d("LOG", "Trading Deliveries Fragment load api key: " + apiKey);
        ApiCall.update(getContext());

        TextView deliveriesGold = rootView.findViewById(R.id.tv_trading_delivery_gold);

        DeliveriesAsyncCall asyncDeliveries = new DeliveriesAsyncCall(rootView, deliveriesGold);
        asyncDeliveries.execute(apiKey);

        return rootView;
    }
}
