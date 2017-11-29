package com.example.luke.tyriadex;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by luke on 29/11/17.
 */

public class TradingPastSalesFragment extends Fragment {

    private String apiKey = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_trading_recycler, container, false);
        Bundle args = getArguments();
        if (args != null) {
            apiKey = args.getString("key");
        }
        Log.d("LOG", "Trading Deliveries Fragment load api key: " + apiKey);
        ApiCall.update(getContext());

//        TextView deliveriesGold = rootView.findViewById(R.id.tv_trading_delivery_gold);

        return rootView;
    }
}
