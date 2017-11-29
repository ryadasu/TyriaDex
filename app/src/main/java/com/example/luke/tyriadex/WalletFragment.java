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

import com.example.luke.tyriadex.model.beans.WalletResult;
import com.magnet.android.mms.exception.SchemaException;

import java.util.List;

/**
 * Created by luke on 22/11/17.
 */

public class WalletFragment extends Fragment {

    String apiKey = null;
    WalletAsyncCall async = null;
    RecyclerView walletRecyclerView;
    WalletAdapter mAdapter;
    ProgressBar loading;

    private class WalletAsyncCall extends AsyncTask<String, Void, List<WalletResult>> {

        View rootView;
        Boolean cancelled = false;

        public WalletAsyncCall(View view) {
            rootView = view;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading.setVisibility(View.VISIBLE);
        }

        public List<WalletResult> doInBackground(String... params) {
            cancelled = false;
            List<WalletResult> result = null;
            try {
                result = ApiCall.getWallet(params[0]);
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

        public void onPostExecute(List<WalletResult> result) {
            if (!cancelled) {
                loading.setVisibility(View.INVISIBLE);
                mAdapter.setmDataSource(result);
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public WalletFragment() {
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
        Log.d("LOG", "Wallet Fragment load api key: " + apiKey);
        ApiCall.update(getContext());
        ((MainActivity) getActivity()).setToolbarTitle("Wallet");

        loading = rootView.findViewById(R.id.pb_wallet_loading);

        walletRecyclerView = rootView.findViewById(R.id.recycler_wallet);
        mAdapter = new WalletAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        walletRecyclerView.setHasFixedSize(true);
        walletRecyclerView.setLayoutManager(mLayoutManager);
        walletRecyclerView.setItemAnimator(new DefaultItemAnimator());
        walletRecyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        walletRecyclerView.setAdapter(mAdapter);

        async = new WalletAsyncCall(rootView);
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