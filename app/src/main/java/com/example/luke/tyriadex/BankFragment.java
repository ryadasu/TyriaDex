package com.example.luke.tyriadex;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luke.tyriadex.model.beans.BankResult;
import com.example.luke.tyriadex.model.beans.WalletResult;
import com.magnet.android.mms.exception.SchemaException;

import java.util.List;

/**
 * Created by luke on 22/11/17.
 */

public class BankFragment extends Fragment {

    String apiKey = null;
    BankAsyncCall async = null;
    RecyclerView bankRecyclerView;
    BankAdapter mAdapter;
    ProgressBar loading;

    private class BankAsyncCall extends AsyncTask<String, Void, List<BankResult>> {

        View rootView;
        Boolean cancelled = false;

        public BankAsyncCall(View view) {
            rootView = view;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (loading != null) {
                loading.setVisibility(View.VISIBLE);
            }
        }

        public List<BankResult> doInBackground(String... params) {
            cancelled = false;
            List<BankResult> result = null;
            try {
                result = ApiCall.getBankObject(params[0]);
            } catch (SchemaException e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onCancelled() {
            cancelled = true;
            super.onCancelled();
        }

        public void onPostExecute(List<BankResult> result) {
            if (!cancelled) {
                loading.setVisibility(View.INVISIBLE);
                mAdapter.setmDataSource(result);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public BankFragment() {
        //
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_wallet, container, false);
        Bundle args = getArguments();
        if (args != null) {
            apiKey = args.getString("key");
        }
        Log.d("LOG", "Bank Fragment load api key: " + apiKey);
        ApiCall.update(getContext());
        ((MainActivity) getActivity()).setToolbarTitle("Bank");

        loading = rootView.findViewById(R.id.pb_wallet_loading);

        bankRecyclerView = rootView.findViewById(R.id.recycler_wallet);
        mAdapter = new BankAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        bankRecyclerView.setHasFixedSize(true);
        bankRecyclerView.setLayoutManager(mLayoutManager);
        bankRecyclerView.setItemAnimator(new DefaultItemAnimator());
        bankRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        bankRecyclerView.setAdapter(mAdapter);

        async = new BankAsyncCall(rootView);
        async.execute(apiKey);

        return rootView;
    }

    @Override
    public void onDetach() {
        if (async != null) {
            async.cancel(true);
        }

        super.onDetach();
    }
}