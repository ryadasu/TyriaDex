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

import com.example.luke.tyriadex.model.beans.CurrentSalesResult;
import com.example.luke.tyriadex.model.beans.DeliveryResult;
import com.example.luke.tyriadex.model.beans.Item;
import com.example.luke.tyriadex.model.beans.TradingResult;
import com.magnet.android.mms.exception.SchemaException;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

/**
 * Created by luke on 29/11/17.
 */

public class TradingPastSalesFragment extends Fragment {

    private String apiKey = null;
    RecyclerView tradingRecyclerView;
    TradingAdapter mAdapter;
    ProgressBar loading;
    TextView empty;

    private class PastSalesAsyncCall extends AsyncTask<String, Void, List<TradingResult>> {

        View rootView;

        public PastSalesAsyncCall(View view) {
            rootView = view;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (loading != null) {
                loading.setVisibility(VISIBLE);
                empty.setVisibility(INVISIBLE);
            }
        }

        public List<TradingResult> doInBackground(String... params) {
            List<TradingResult> result = null;
            try {
                result = ApiCall.getPastSalesObject(params[0]);
            } catch (SchemaException e) {
                e.printStackTrace();
            }
            return result;
        }

        public void onPostExecute(List<TradingResult> result) {
            if (result != null) {
                loading.setVisibility(INVISIBLE);
                mAdapter.setmDataSource(result);
                mAdapter.notifyDataSetChanged();

                if (result.size() == 0) {
                    empty.setText("No sales history");
                    empty.setVisibility(VISIBLE);
                }
            }
        }
    }

    public TradingPastSalesFragment() {
        //
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_trading_recycler, container, false);
        Bundle args = getArguments();
        if (args != null) {
            apiKey = args.getString("key");
        }
        Log.d("LOG", "Trading Past Sales Fragment load api key: " + apiKey);
        ApiCall.update(getContext());

        loading = rootView.findViewById(R.id.pb_trading_loading);
        empty = rootView.findViewById(R.id.tv_trading_empty);

        tradingRecyclerView = rootView.findViewById(R.id.recycler_trading);
        mAdapter = new TradingAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        tradingRecyclerView.setHasFixedSize(true);
        tradingRecyclerView.setLayoutManager(mLayoutManager);
        tradingRecyclerView.setItemAnimator(new DefaultItemAnimator());
        tradingRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        tradingRecyclerView.setAdapter(mAdapter);

        PastSalesAsyncCall asyncSales = new PastSalesAsyncCall(rootView);
        asyncSales.execute(apiKey);

        return rootView;
    }
}
