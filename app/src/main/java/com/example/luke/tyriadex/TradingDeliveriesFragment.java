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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.luke.tyriadex.model.beans.DeliveryResult;
import com.example.luke.tyriadex.model.beans.Item;
import com.magnet.android.mms.exception.SchemaException;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by luke on 29/11/17.
 */

public class TradingDeliveriesFragment extends Fragment {

    private String apiKey = null;
    RecyclerView deliveryRecyclerView;
    TradingDeliveriesAdapter mAdapter;
    ProgressBar loading;

    private class DeliveriesAsyncCall extends AsyncTask<String, Void, DeliveryResult> {

        View rootView;
        TextView tvDeliveryGold;
        ImageView ivDeliveryIcon;

        public DeliveriesAsyncCall(View view, TextView tvDG, ImageView ivDI) {
            rootView = view;
            tvDeliveryGold = tvDG;
            ivDeliveryIcon = ivDI;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (loading != null) {
                loading.setVisibility(View.VISIBLE);
            }
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
                loading.setVisibility(View.INVISIBLE);
                int pickupCoins = result.getCoins();
                String pickupCoinsReadable = String.format(Locale.ENGLISH,"%05d", pickupCoins);
                pickupCoinsReadable = new StringBuilder(pickupCoinsReadable).insert(pickupCoinsReadable.length() - 4, "g ").toString();
                pickupCoinsReadable = new StringBuilder(pickupCoinsReadable).insert(pickupCoinsReadable.length() - 2, "s ").toString();
                pickupCoinsReadable = new StringBuilder(pickupCoinsReadable).insert(pickupCoinsReadable.length(), "c").toString();
                tvDeliveryGold.setText(pickupCoinsReadable);
                Picasso.with(rootView.getContext())
                        .load(R.drawable.gold)
                        .placeholder(R.drawable.currency_placeholder)
                        .fit()
                        .into(ivDeliveryIcon);

                mAdapter.setmDataSource(result.getItems());
                mAdapter.notifyDataSetChanged();
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

        TextView deliveriesGold = rootView.findViewById(R.id.tv_pickup_list_name);
        ImageView deliveriesIcon = rootView.findViewById(R.id.iv_pickup_list_icon);
        loading = rootView.findViewById(R.id.pb_trading_deliveries_loading);

        deliveryRecyclerView = rootView.findViewById(R.id.recycler_trading_deliveries);
        mAdapter = new TradingDeliveriesAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        deliveryRecyclerView.setHasFixedSize(true);
        deliveryRecyclerView.setLayoutManager(mLayoutManager);
        deliveryRecyclerView.setItemAnimator(new DefaultItemAnimator());
        deliveryRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        deliveryRecyclerView.setAdapter(mAdapter);

        DeliveriesAsyncCall asyncDeliveries = new DeliveriesAsyncCall(rootView, deliveriesGold, deliveriesIcon);
        asyncDeliveries.execute(apiKey);

        return rootView;
    }
}
