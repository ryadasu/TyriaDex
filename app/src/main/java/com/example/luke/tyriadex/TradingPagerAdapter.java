package com.example.luke.tyriadex;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by luke on 29/11/17.
 */

public class TradingPagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Bundle args;

    public TradingPagerAdapter(FragmentManager fm, int NumOfTabs, Bundle args) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.args = args;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                TradingDeliveriesFragment TDF = new TradingDeliveriesFragment();
                TDF.setArguments(args);
                return TDF;
            case 1:
                TradingCurrentBuysFragment TCBF = new TradingCurrentBuysFragment();
                TCBF.setArguments(args);
                return TCBF;
            case 2:
                TradingCurrentSalesFragment TCSF = new TradingCurrentSalesFragment();
                TCSF.setArguments(args);
                return TCSF;
            case 3:
                TradingPastBuysFragment TPBF = new TradingPastBuysFragment();
                TPBF.setArguments(args);
                return TPBF;
            case 4:
                TradingPastSalesFragment TPSF = new TradingPastSalesFragment();
                TPSF.setArguments(args);
                return TPSF;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}