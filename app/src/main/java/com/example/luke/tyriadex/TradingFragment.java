package com.example.luke.tyriadex;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.magnet.android.mms.exception.SchemaException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by luke on 22/11/17.
 */

public class TradingFragment extends Fragment {

    String apiKey = null;

//    private class DeliveriesAsyncCall extends AsyncTask<String, Void, List<String>> {
//
//        TextView tvDeliveriesGold;
//        TextView tvDeliveriesItems;
//
//        public DeliveriesAsyncCall(TextView tvDG, TextView tvDI) {
//            tvDeliveriesGold = tvDG;
//            tvDeliveriesItems = tvDI;
//        }
//
//        public List<String> doInBackground(String... params) {
//            List<String> result = new ArrayList<String>();
//            try {
//                result = ApiCall.getDelivery(params[0]);
//            } catch (SchemaException e) {
//                e.printStackTrace();
//            }
//            return result;
//        }
//
//        public void onPostExecute(List<String> result) {
//            tvDeliveriesGold.setText(result.get(0));
//
//            if (result.size() > 1) {
//                String item = "";
//                for (int i = 1; i < result.size(); i++) {
//                    item = (result.get(i) + ",  ");
//                }
//                item = item.substring(0, item.length() -3);
//                tvDeliveriesItems.setText(item);
//            }
//        }
//    }
//
//    private class CurrentBuysAsyncCall extends AsyncTask<String, Void, String> {
//
//        TextView tvCurrentOrders;
//
//        public CurrentBuysAsyncCall(TextView tvCO) {
//            tvCurrentOrders = tvCO;
//        }
//
//        public String doInBackground(String... params) {
//            String result = null;
//            try {
//                result = ApiCall.getCurrentBuys(params[0]);
//            } catch (SchemaException e) {
//                e.printStackTrace();
//            }
//            return result;
//        }
//
//        public void onPostExecute(String result) {
//            tvCurrentOrders.setText(result);
//        }
//    }
//
//    private class CurrentSalesAsyncCall extends AsyncTask<String, Void, String> {
//
//        TextView tvCurrentSales;
//
//        public CurrentSalesAsyncCall(TextView tvCO) {
//            tvCurrentSales = tvCO;
//        }
//
//        public String doInBackground(String... params) {
//            String result = null;
//            try {
//                result = ApiCall.getCurrentSales(params[0]);
//            } catch (SchemaException e) {
//                e.printStackTrace();
//            }
//            return result;
//        }
//
//        public void onPostExecute(String result) {
//            tvCurrentSales.setText(result);
//        }
//    }
//
//    private class PastBuysAsyncCall extends AsyncTask<String, Void, String> {
//
//        TextView tvPastBuys;
//
//        public PastBuysAsyncCall(TextView tvCO) {
//            tvPastBuys = tvCO;
//        }
//
//        public String doInBackground(String... params) {
//            String result = null;
//            try {
//                result = ApiCall.getPastBuys(params[0]);
//            } catch (SchemaException e) {
//                e.printStackTrace();
//            }
//            return result;
//        }
//
//        public void onPostExecute(String result) {
//            tvPastBuys.setText(result);
//        }
//    }
//
//    private class PastSalesAsyncCall extends AsyncTask<String, Void, String> {
//
//        TextView tvPastSales;
//
//        public PastSalesAsyncCall(TextView tvCO) {
//            tvPastSales = tvCO;
//        }
//
//        public String doInBackground(String... params) {
//            String result = null;
//            try {
//                result = ApiCall.getPastSales(params[0]);
//            } catch (SchemaException e) {
//                e.printStackTrace();
//            }
//            return result;
//        }
//
//        public void onPostExecute(String result) {
//            tvPastSales.setText(result);
//        }
//    }

    public TradingFragment() {
        //
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_trading, container, false);
        Bundle args = getArguments();
        if (args != null) {
            apiKey = args.getString("key");
        }
        Log.d("LOG", "Trading Fragment load api key: " + apiKey);
        ApiCall.update(getContext());
        ((MainActivity) getActivity()).setToolbarTitle("Black Lion Trading Post");

        TabLayout tabLayout = rootView.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Deliveries"));
        tabLayout.addTab(tabLayout.newTab().setText("Current Buys"));
        tabLayout.addTab(tabLayout.newTab().setText("Current Sales"));
        tabLayout.addTab(tabLayout.newTab().setText("Past Buys"));
        tabLayout.addTab(tabLayout.newTab().setText("Past Sales"));

        final ViewPager viewPager = rootView.findViewById(R.id.pager);
        final TradingPagerAdapter adapter = new TradingPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount(), args);
        viewPager.setAdapter(adapter);
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

//        TextView deliveriesGold = rootView.findViewById(R.id.tv_trading_delivery_gold);
//        TextView deliveriesItems = rootView.findViewById(R.id.tv_trading_delivery_items);
//        TextView currentOrders = rootView.findViewById(R.id.tv_trading_current_orders);
//        TextView currentSales = rootView.findViewById(R.id.tv_trading_current_sales);
//        TextView pastBuys = rootView.findViewById(R.id.tv_trading_past_buys);
//        TextView pastSales = rootView.findViewById(R.id.tv_trading_past_sales);
//
//        DeliveriesAsyncCall asyncDeliveries = new DeliveriesAsyncCall(deliveriesGold, deliveriesItems);
//        asyncDeliveries.execute(apiKey);
//
//        CurrentBuysAsyncCall asyncCurrentBuys = new CurrentBuysAsyncCall(currentOrders);
//        asyncCurrentBuys.execute(apiKey);
//
//        CurrentSalesAsyncCall asyncCurrentSales = new CurrentSalesAsyncCall(currentSales);
//        asyncCurrentSales.execute(apiKey);
//
//        PastBuysAsyncCall asyncPastBuys = new PastBuysAsyncCall(pastBuys);
//        asyncPastBuys.execute(apiKey);
//
//        PastSalesAsyncCall asyncPastSales = new PastSalesAsyncCall(pastSales);
//        asyncPastSales.execute(apiKey);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }
}