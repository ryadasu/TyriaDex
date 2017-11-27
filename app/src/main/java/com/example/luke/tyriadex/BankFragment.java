package com.example.luke.tyriadex;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.magnet.android.mms.exception.SchemaException;

/**
 * Created by luke on 22/11/17.
 */

public class BankFragment extends Fragment {

    String apiKey = null;

    private class BankAsyncCall extends AsyncTask<String, Void, String> {

        TextView tvB;

        public BankAsyncCall(TextView tvBank) {
            tvB = tvBank;
        }

        public String doInBackground(String... params) {
            String result = null;
            try {
                result = ApiCall.getBank(params[0]);
            } catch (SchemaException e) {
                e.printStackTrace();
            }
            return result;
        }

        public void onPostExecute(String result) {
            tvB.setText(result);
        }
    }

    public BankFragment() {
        //
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_bank, container, false);
        Bundle args = getArguments();
        if (args != null) {
            apiKey = args.getString("key");
        }
        Log.d("LOG", "Bank Fragment load api key: " + apiKey);
        ApiCall.update(getContext());

        TextView tvBank = rootView.findViewById(R.id.tv_bank);
        BankAsyncCall async = new BankAsyncCall(tvBank);
        async.execute(apiKey);

        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);


    }
}