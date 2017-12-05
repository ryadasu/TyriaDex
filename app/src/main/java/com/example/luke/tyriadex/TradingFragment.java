package com.example.luke.tyriadex;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by luke on 22/11/17.
 */

public class TradingFragment extends Fragment {

    String apiKey = null;


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
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int index = tab.getPosition();
                viewPager.setCurrentItem(index);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }
}